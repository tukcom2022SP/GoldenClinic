//
//  realTimePostsVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/01.
//

import UIKit
import FirebaseFirestore

class realTimePostsVC: UIViewController{
    @IBOutlet weak var tableViewRealTime: UITableView!
    var posts: [post] = []

    override func viewDidLoad() {
        super.viewDidLoad()
        loadPosts()
        self.navigationController?.navigationBar.tintColor = UIColor.white
        self.tableViewRealTime.register(UINib.init(nibName: "listCellRealTime", bundle: nil), forCellReuseIdentifier: "cellRealTime")
    }
    
    func loadPosts(){
        db.collection("LivePost").addSnapshotListener{ (querySnapshot, err) in
            if let err = err {
            } else {
                if let snapshotDocuments = querySnapshot?.documents{
                    snapshotDocuments.forEach{(doc) in
                        let data = doc.data()
                        if let postname = data["postname"] as? String,
                           let contents = data["contents"] as? String,
                           let category = data["category"] as? String,
                           let storeName = data["storeName"] as? String,
                           let group = data["group"] as? [String]{
                            self.posts.append(post(postname: postname, contents: contents, category: category, storeName: storeName, group: group))
                            
                            DispatchQueue.main.async {
                                self.tableViewRealTime.reloadData()
                                if self.posts.count != 0 {
                                    self.tableViewRealTime.scrollToRow(at: IndexPath(row: 0, section: 0), at: .top, animated: false)
                                }
                            }
                        }
                    }
                }
//                print(posts)
            }
        }
    }
}

extension realTimePostsVC: UITableViewDelegate,UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return posts.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cellRealTime", for: indexPath) as! listCellRealTime
        
        cell.lblTitle.text = posts[indexPath.row].postname
        cell.lblCategory.text = posts[indexPath.row].category
        cell.lblStoreName.text = posts[indexPath.row].storeName
        cell.lblContent.text = posts[indexPath.row].contents
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100 //Choose your custom row height
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "participateIn") as? participateInVC
        pushVC!.category = posts[indexPath.row].category
        pushVC!.postName = posts[indexPath.row].postname
        pushVC!.contents = posts[indexPath.row].contents
        pushVC!.storeName = posts[indexPath.row].storeName
        pushVC!.group = posts[indexPath.row].group
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
}
