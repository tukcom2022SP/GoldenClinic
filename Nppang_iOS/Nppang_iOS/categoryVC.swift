//
//  categoryVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/07/28.
//

import UIKit
import Firebase

class categoryVC: UIViewController{
    
    override func viewDidLoad() {
        super.viewDidLoad()
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
}
