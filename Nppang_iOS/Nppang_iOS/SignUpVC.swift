//
//  SignUpVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/07/28.
//

import UIKit
import Firebase

class SignUpVC: UIViewController {
    @IBOutlet weak var tfEmail: UITextField!
    @IBOutlet weak var tfPassword: UITextField!
    @IBOutlet weak var tfCheckPassword: UITextField!
    @IBOutlet weak var tfName: UITextField!
    @IBOutlet weak var tfPhoneNumber: UITextField!
    @IBOutlet weak var tfBankAccount: UITextField!
    var users: [User] = []
    var idIsChecked = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationController?.navigationBar.tintColor = UIColor.white
        loadUsers()
    }
    
    @IBAction func btnCheckId(_ sender: UIButton) {
        checkingId()
        if idIsChecked == false {
            let alert = UIAlertController(title: "이메일 중복", message: "이미 가입된 이메일이 존재합니다. 다시 시도하세요.", preferredStyle: .alert)
            alert.addAction(UIAlertAction(
                title: "확인", style: .default){ action in
                    self.tfEmail.text?.removeAll()
                    self.tfEmail.becomeFirstResponder()
                }
            )
            present(alert, animated: true, completion: nil)
        } else{
            let alert = UIAlertController(title: "이메일 사용 가능", message: "사용 가능한 이메일입니다.", preferredStyle: .alert)
            alert.addAction(UIAlertAction(
                title: "확인", style: .default){ action in
                    self.tfPassword.becomeFirstResponder()
                }
            )
            present(alert, animated: true, completion: nil)
        }
    }
    
    @IBAction func btnSignUp(_ sender: UIButton) {
        if idIsChecked && tfPassword.text == tfCheckPassword.text {
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
        } else {
            if tfPassword.text != tfCheckPassword.text {
                let alert = UIAlertController(title: "비밀번호 확인", message: "비밀번호가 일치하지 않습니다. 다시 시도하세요.", preferredStyle: .alert)
                alert.addAction(UIAlertAction(
                    title: "확인", style: .default){ action in
                        self.tfPassword.text?.removeAll()
                        self.tfCheckPassword.text?.removeAll()
                        self.tfPassword.becomeFirstResponder()
                    }
                )
                present(alert, animated: true, completion: nil)
            }
        }
    }
    
    func loadUsers(){
        db.collection("UserData").addSnapshotListener{ (querySnapshot, err) in
           if let err = err {
           } else {
                if let snapshotDocuments = querySnapshot?.documents{
                    snapshotDocuments.forEach{(doc) in
                        let data = doc.data()
                        if let userEmail = data["userEmail"] as? String,
                           let userName = data["userName"] as? String,
                           let userPhoneNumber = data["userPhoneNumber"] as? String,
                           let userBankAccount = data["userBankAccount"] as? String {
                            self.users.append(User(userEmail: userEmail, userName: userName, userPhoneNumber: userPhoneNumber, userBankAccount: userBankAccount))
                        }
                    }
                }
            }
        }
    }
    
    
    func checkingId(){
        idIsChecked = true
        for user in users {
            if user.userEmail == tfEmail.text {
                idIsChecked = false
                break
            }
        }
    }
}
