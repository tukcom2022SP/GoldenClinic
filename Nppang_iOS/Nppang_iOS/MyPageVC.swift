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
    
    @IBAction func btnEasterEgg(_ sender: UIButton) {
        let alert = UIAlertController(title:"엔빵 by Team GoldenClinic", message: "권태우: 그래도 행복했다..\n유하임: (오늘 출근 안함)\n이현구: 에잇 씻팔\n최유빈: 집에 보내줘", preferredStyle: UIAlertController.Style.alert)
        let check = UIAlertAction(title: "고맙습니다", style: .destructive, handler: nil)
        alert.addAction(check)
        self.present(alert,animated: true,completion: nil)
    }
}
