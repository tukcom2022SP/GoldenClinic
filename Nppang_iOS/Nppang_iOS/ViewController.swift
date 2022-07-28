//
//  ViewController.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/07/26.
//

import UIKit
import GoogleSignIn

class ViewController: UIViewController {
    @IBOutlet weak var googleSignIn: GIDSignInButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        GIDSignIn.sharedInstance()?.presentingViewController = self
    }

    
}

