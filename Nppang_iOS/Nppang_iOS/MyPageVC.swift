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
    @IBOutlet weak var lblUserEmail: UILabel!
    var users: [User] = []
    var name = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lblUserName.text = Auth.auth().currentUser?.email
        loadUserInfo()
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

    }
    
    @IBAction func btnParticipatedPosts(_ sender: UIButton) {
        
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
    
    func loadUserInfo(){
        loadUsers()

        for user in users {
            print(user.userName)
            if user.userEmail == lblUserEmail.text{
                name = user.userName as String
            }
        }
    }
}
