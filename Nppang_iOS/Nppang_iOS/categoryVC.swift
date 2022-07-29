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
    
    @IBAction func btnLogOut(_ sender: UIButton) {
        do {
            try Auth.auth().signOut()
            self.navigationController?.popViewController(animated: true)
            UserDefaults.standard.set(false, forKey: "autoLogIn")
        } catch { }
    }
}
