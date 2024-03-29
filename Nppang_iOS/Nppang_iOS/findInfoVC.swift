//
//  findInfoVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/01.
//

import UIKit
import Firebase

class findInfoVC: UIViewController{
    @IBOutlet weak var tfEmail: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        UIApplication.shared.statusBarStyle = .darkContent
        navigationItem.hidesBackButton = true
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    @IBAction func btnResetPassword(_ sender: UIButton) {
        sendPasswordResetMessage()
    }
    
    @IBAction func btnCancel(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "logIn")
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    
    func sendPasswordResetMessage(){
        let email = self.tfEmail.text!
        // email 보내기
        Auth.auth().sendPasswordReset(withEmail: email) { (error) in
            if let error = error{
            }else{
                let alert = UIAlertController(title: "비밀번호 재설정", message: "회원님의 이메일로 비밀번호 재설정 코드가 발송되었습니다.", preferredStyle: .alert)
                alert.addAction(UIAlertAction(
                    title: "확인", style: .default){ action in
                        self.navigationController?.popViewController(animated: true)
                    }
                )
                self.present(alert, animated: true, completion: nil)
            }
        }
    }
}
