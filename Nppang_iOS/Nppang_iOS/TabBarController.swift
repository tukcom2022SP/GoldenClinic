//
//  TabBarController.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/08.
//

import UIKit

class TabBarController: UITabBarController{
    override func viewWillAppear(_ animated: Bool) {
        navigationItem.hidesBackButton = true
    }
}
