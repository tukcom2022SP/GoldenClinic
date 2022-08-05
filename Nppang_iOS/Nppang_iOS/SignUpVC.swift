//
//  SignUpVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/07/28.
//

import UIKit
import Firebase
import FirebaseFirestore

class SignUpVC: UIViewController {
    @IBOutlet weak var tfEmail: UITextField!
    @IBOutlet weak var tfPassword: UITextField!
    @IBOutlet weak var tfCheckPassword: UITextField!
    @IBOutlet weak var tfName: UITextField!
    @IBOutlet weak var tfPhoneNumber: UITextField!
    @IBOutlet weak var tfBankAccount: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        navigationItem.hidesBackButton = true
    }
    
    @IBAction func btnSignUp(_ sender: UIButton) {
        Auth.auth().createUser(withEmail: tfEmail.text!, password: tfPassword.text!) {(authResut, error) in
            let storyboard = UIStoryboard(name: "Main", bundle: Bundle.main)
            let logInVC = storyboard.instantiateViewController(withIdentifier: "signUp")
            let alert = UIAlertController(title:"회원 가입 오류", message: error?.localizedDescription, preferredStyle: UIAlertController.Style.alert)
            let check = UIAlertAction(title: "확인", style: .default, handler: nil)
            alert.addAction(check)
            logInVC.present(alert,animated: true,completion: nil)
            guard let user = authResut?.user else { return }
            // Update one field, creating the document if it does not exist.
            db.collection("UserData").document(self.tfEmail.text!).setData(["userEmail": self.tfEmail.text!,
                                                         "userName": self.tfName.text!,
                                                         "userPhoneNumber": self.tfPhoneNumber.text!,
                                                         "userBankAccount": self.tfBankAccount.text!])
            self.navigationController?.popViewController(animated: true)
        }
    }
}
