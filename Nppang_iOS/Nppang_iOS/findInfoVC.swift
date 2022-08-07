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
                self.navigationController?.popViewController(animated: true)
            }
        }
    }
}
