//
//  MyPagePosts.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/09.
//

import UIKit
import Firebase

class MyPagePosts: UIViewController{
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var tvMyPosts: UITableView!
    var jemok = ""
    var myPosts: [Post] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lblTitle.text = jemok
        switch jemok{
        case "작성한 게시물 보기":
            loadMyPosts()
        default:
            loadParticipatedPosts()
        }
        self.tvMyPosts.register(UINib.init(nibName: "listCellRealTime", bundle: nil), forCellReuseIdentifier: "cellRealTime")
    }
    
    func loadMyPosts(){
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
                           let group = data["group"] as? [String],
                           let payTime = data["payTime"] as? String{
                            if group[0] == Auth.auth().currentUser?.email {
                                self.myPosts.append(Post(postname: postname, contents: contents, category: postCategory, storeName: storeName, group: group, payTime: payTime))
                            }
                            
                            DispatchQueue.main.async {
                                self.tvMyPosts.reloadData()
                                if self.myPosts.count != 0 {
                                    self.tvMyPosts.scrollToRow(at: IndexPath(row: 0, section: 0), at: .top, animated: false)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    func loadParticipatedPosts(){
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
                           let group = data["group"] as? [String],
                           let payTime = data["payTime"] as? String{
                            if group[0] != Auth.auth().currentUser?.email {
                                for member in group {
                                    if member == Auth.auth().currentUser?.email {
                                        self.myPosts.append(Post(postname: postname, contents: contents, category: postCategory, storeName: storeName, group: group, payTime: payTime))
                                    }
                                }
                            }
                            
                            DispatchQueue.main.async {
                                self.tvMyPosts.reloadData()
                                if self.myPosts.count != 0 {
                                    self.tvMyPosts.scrollToRow(at: IndexPath(row: 0, section: 0), at: .top, animated: false)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

extension MyPagePosts: UITableViewDelegate,UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return myPosts.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cellRealTime", for: indexPath) as! listCellRealTime
        
        cell.lblTitle.text = myPosts[indexPath.row].postname
        cell.lblCategory.text = myPosts[indexPath.row].category
        cell.lblStoreName.text = myPosts[indexPath.row].storeName
        cell.lblContent.text = myPosts[indexPath.row].contents
        cell.lblMembers.text = "\(myPosts[indexPath.row].group.count)"
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 100 //Choose your custom row height
    }
    
//    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
//        tableView.deselectRow(at: indexPath, animated: true)
//        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "participateIn") as? participateInVC
//        pushVC!.category = postsCategoryPosts[indexPath.row].category
//        pushVC!.postName = postsCategoryPosts[indexPath.row].postname
//        pushVC!.contents = postsCategoryPosts[indexPath.row].contents
//        pushVC!.storeName = postsCategoryPosts[indexPath.row].storeName
//        pushVC!.group = postsCategoryPosts[indexPath.row].group
//        pushVC!.payTime = postsCategoryPosts[indexPath.row].payTime
//        self.navigationController?.pushViewController(pushVC!, animated: true)
//    }
}
