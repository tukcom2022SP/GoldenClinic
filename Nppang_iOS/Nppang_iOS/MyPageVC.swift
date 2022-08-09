//
//  MyPageVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/08.
//

import UIKit
import Firebase

class MyPageVC: UIViewController{
    @IBOutlet weak var lblUserName: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lblUserName.text = Auth.auth().currentUser?.email
    }
    
    @IBAction func btnSignOut(_ sender: UIButton) {
        do {
            try Auth.auth().signOut()
            let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "logIn")
            self.navigationController?.pushViewController(pushVC!, animated: true)
            UserDefaults.standard.set(false, forKey: "autoLogIn")
        } catch { }
    }
    
    @IBAction func btnMyPosts(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "myPagePosts") as? MyPagePosts
        pushVC!.jemok = "작성한 게시물 보기"
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    
    @IBAction func btnParticipatedPosts(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "myPagePosts") as? MyPagePosts
        pushVC!.jemok = "참가한 게시물 보기"
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
}
