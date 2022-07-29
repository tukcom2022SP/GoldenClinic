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
                self.navigationController?.pushViewController(pushVC!, animated: true)            }
            else{
                self.tfEmail.text?.removeAll()
                self.tfPassword.text?.removeAll()
            }
        }
    }
    
    @IBAction func btnSignUp(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "signUp")
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    
    @IBAction func btnAutoLogIn(_ sender: UIButton) {
        sender.isSelected.toggle()
    }
}

