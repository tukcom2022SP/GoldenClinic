//
//  categoryVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/07/28.
//

import UIKit
import Firebase
import FirebaseFirestore

class categoryVC: UIViewController{
    @IBOutlet weak var tableViewCategory: UITableView!
    var postsPreview: [post] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loadPosts()
        self.tableViewCategory.register(UINib.init(nibName: "PreviewTableViewCell", bundle: nil), forCellReuseIdentifier: "cellPreview")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        navigationItem.hidesBackButton = true
    }
    
    @IBAction func btnChicken(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "categoryPosts") as? categoryPostsVC
        pushVC!.category = "치킨"
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    @IBAction func btnPizza(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "categoryPosts") as? categoryPostsVC
        pushVC!.category = "피자"
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    @IBAction func btnChinese(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "categoryPosts") as? categoryPostsVC
        pushVC!.category = "중식"
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    @IBAction func btnPork(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "categoryPosts") as? categoryPostsVC
        pushVC!.category = "족발 보쌈"
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    @IBAction func btnBbokki(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "categoryPosts") as? categoryPostsVC
        pushVC!.category = "분식"
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    @IBAction func btnEtc(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "categoryPosts") as? categoryPostsVC
        pushVC!.category = "기타"
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    @IBAction func btnLogOut(_ sender: UIButton) {
        do {
            try Auth.auth().signOut()
            self.navigationController?.popViewController(animated: true)
            UserDefaults.standard.set(false, forKey: "autoLogIn")
        } catch { }
    }
    @IBAction func btnSeeAll(_ sender: UIButton) {
        pushViewController(vcName: "realTimePosts")
    }
    
    func pushViewController(vcName: String){
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: vcName)
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    
    func loadPosts(){
        db.collection("LivePost").addSnapshotListener{ (querySnapshot, err) in
            
            var cnt = 0
            
            if let err = err {
            } else {
                if let snapshotDocuments = querySnapshot?.documents{
                    snapshotDocuments.forEach{(doc) in
                        let data = doc.data()
                        if let postname = data["postname"] as? String,
                           let contents = data["contents"] as? String,
                           let category = data["category"] as? String,
                           let storeName = data["storeName"] as? String {
                            if cnt < 4 {
                                self.postsPreview.append(post(postname: postname, contents: contents, category: category, storeName: storeName))
                            }
                            cnt += 1
                            
                            DispatchQueue.main.async {
                                self.tableViewCategory.reloadData()
                                if self.postsPreview.count != 0 {
                                    self.tableViewCategory.scrollToRow(at: IndexPath(row: 0, section: 0), at: .top, animated: false)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

extension categoryVC: UITableViewDelegate,UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return postsPreview.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cellPreview", for: indexPath) as! PreviewTableViewCell
        cell.lblTitle.text = postsPreview[indexPath.row].postname
        cell.lblCategory.text = postsPreview[indexPath.row].category
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 75 //Choose your custom row height
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "participateIn") as? participateInVC
        pushVC!.category = postsPreview[indexPath.row].category
        pushVC!.postName = postsPreview[indexPath.row].postname
        pushVC!.contents = postsPreview[indexPath.row].contents
        pushVC!.storeName = postsPreview[indexPath.row].storeName
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
}
