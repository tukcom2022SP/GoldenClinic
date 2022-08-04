//
//  ViewController.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/07/26.
//

import UIKit
import GoogleSignIn
import Firebase

class ViewController: UIViewController {
    
    @IBOutlet weak var tfEmail: UITextField!
    @IBOutlet weak var tfPassword: UITextField!
    @IBOutlet weak var btnGoogleLogIn: GIDSignInButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        if UserDefaults.standard.bool(forKey: "autoLogIn") {
            if let user = Auth.auth().currentUser {
                let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "category")
                self.navigationController?.pushViewController(pushVC!, animated: true)
            }
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        GIDSignIn.sharedInstance().presentingViewController=self
    }

    @IBAction func googleLoginButtonTapped(_ sender: UIButton) {
        GIDSignIn.sharedInstance().signIn()
    }
    
    @IBAction func btnLogIn(_ sender: UIButton) {
        Auth.auth().signIn(withEmail: tfEmail.text!, password: tfPassword.text!) {(user, error) in
            if user != nil{
                let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "category")
                self.navigationController?.pushViewController(pushVC!, animated: true)
            }
            else{
                self.tfEmail.text?.removeAll()
                self.tfPassword.text?.removeAll()
                self.tfEmail.becomeFirstResponder()
                let alert = UIAlertController(title:"로그인 오류", message: "다시 시도하세요.", preferredStyle: UIAlertController.Style.alert)
                let check = UIAlertAction(title: "확인", style: .default, handler: nil)
                alert.addAction(check)
                self.present(alert,animated: true,completion: nil)
            }
        }
    }
    
    @IBAction func btnSignUp(_ sender: UIButton) {
        pushViewController(vcName: "signUp")
    }
    
    @IBAction func btnAutoLogIn(_ sender: UIButton) {
        sender.isSelected.toggle()
        UserDefaults.standard.set(sender.isSelected, forKey: "autoLogIn")
    }
    
    @IBAction func btnFindInfo(_ sender: UIButton) {
        pushViewController(vcName: "findInfo")
    }
    
    func pushViewController(vcName: String){
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: vcName)
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
}

