//
//  realTimePostsVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/01.
//

import UIKit
import FirebaseFirestore

let dbRealTimePosts = Firestore.firestore()
var posts: [post] = []

class realTimePostsVC: UIViewController{
    @IBOutlet weak var tableViewRealTime: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loadPosts()
        self.navigationController?.navigationBar.tintColor = UIColor.white
        self.tableViewRealTime.register(UINib.init(nibName: "listCellRealTime", bundle: nil), forCellReuseIdentifier: "cellRealTime")
    }
    
    func loadPosts(){
        dbRealTimePosts.collection("LivePost").addSnapshotListener{ (querySnapshot, err) in
            
            posts = []
            
            if let err = err {
            } else {
                if let snapshotDocuments = querySnapshot?.documents{
                    snapshotDocuments.forEach{(doc) in
                        let data = doc.data()
                        if let postname = data["postname"] as? String,
                           let contents = data["contents"] as? String,
                           let category = data["category"] as? String,
                           let storeName = data["storeName"] as? String {
                            posts.append(post(postname: postname, contents: contents, category: category, storeName: storeName))
                            
                            DispatchQueue.main.async {
                                self.tableViewRealTime.reloadData()
                                self.tableViewRealTime.scrollToRow(at: IndexPath(row: posts.count-1, section: 0), at: .top, animated: false)
                            }
                        }
                    }
                }
//                for document in querySnapshot!.documents {
//                    document.data()
//                }
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
        cell.lblCategoryNStoreName.text = posts[indexPath.row].category + posts[indexPath.row].storeName
        return cell
    }
}
