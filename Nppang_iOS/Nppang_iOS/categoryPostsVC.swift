//
//  categoryPostsVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/01.
//

import UIKit

class categoryPostsVC: UIViewController{
    var category: String = ""
    
    @IBOutlet weak var lblTitle: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lblTitle.text = "\(category)"
        self.navigationController?.navigationBar.tintColor = UIColor.white
    }
    @IBAction func btnPosting(_ sender: UIButton) {
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "posting")
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
}
