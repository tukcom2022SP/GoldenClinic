//
//  categoryPostsVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/01.
//

import UIKit
import Firebase


class categoryPostsVC: UIViewController{
    var category: String = ""
    var postsCategoryPosts: [Post] = []
    
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var tvCategoryPosts: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lblTitle.text = "\(category)"
        self.navigationController?.navigationBar.tintColor = UIColor.white
        loadPosts(category)
        self.tvCategoryPosts.register(UINib.init(nibName: "listCellRealTime", bundle: nil), forCellReuseIdentifier: "cellRealTime")
    }
    
    @IBAction func btnPosting(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "posting")
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    
    func loadPosts(_ C: String){
        db.collection("LivePost").addSnapshotListener{ (querySnapshot, err) in
            if let err = err {
            } else {
                if let snapshotDocuments = querySnapshot?.documents{
                    snapshotDocuments.forEach{(doc) in
                        let data = doc.data()
                        if let postname = data["postname"] as? String,
                           let contents = data["contents"] as? String,
                           let postCategory = data["category"] as? String,
                           let storeName = data["storeName"] as? String,
                           let group = data["group"] as? [String]{
                            if postCategory == C {
                                self.postsCategoryPosts.append(Post(postname: postname, contents: contents, category: postCategory, storeName: storeName, group: group))
                            }
                            
                            DispatchQueue.main.async {
                                self.tvCategoryPosts.reloadData()
                                if self.postsCategoryPosts.count != 0 {
                                    self.tvCategoryPosts.scrollToRow(at: IndexPath(row: 0, section: 0), at: .top, animated: false)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

extension categoryPostsVC: UITableViewDelegate,UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return postsCategoryPosts.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cellRealTime", for: indexPath) as! listCellRealTime
        
        cell.lblTitle.text = postsCategoryPosts[indexPath.row].postname
        cell.lblCategory.text = postsCategoryPosts[indexPath.row].category
        cell.lblStoreName.text = postsCategoryPosts[indexPath.row].storeName
        cell.lblContent.text = postsCategoryPosts[indexPath.row].contents
        cell.lblMembers.text = "\(postsCategoryPosts[indexPath.row].group.count)"
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100 //Choose your custom row height
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "participateIn") as? participateInVC
        pushVC!.category = postsCategoryPosts[indexPath.row].category
        pushVC!.postName = postsCategoryPosts[indexPath.row].postname
        pushVC!.contents = postsCategoryPosts[indexPath.row].contents
        pushVC!.storeName = postsCategoryPosts[indexPath.row].storeName
        pushVC!.group = postsCategoryPosts[indexPath.row].group
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
}
