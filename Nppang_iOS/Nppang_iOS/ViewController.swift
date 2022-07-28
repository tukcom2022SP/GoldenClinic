//
//  ViewController.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/07/26.
//

import UIKit
import GoogleSignIn

class ViewController: UIViewController {
    
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
    @IBAction func btnSignUp(_ sender: UIButton) {
        self.performSegue(withIdentifier: "signingUp", sender: self)
        navigationItem.hidesBackButton = true
    }
}

