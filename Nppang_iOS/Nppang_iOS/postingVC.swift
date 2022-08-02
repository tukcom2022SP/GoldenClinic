//
//  postingVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/01.
//

import UIKit
import DropDown

class postingVC: UIViewController{
    @IBOutlet weak var tfTitle: UITextField!
    @IBOutlet weak var tvContent: UITextView!
    @IBOutlet weak var dropViewCategory: UIView!
    @IBOutlet weak var btnSelectCategory: UIButton!
    @IBOutlet weak var tfSelectCategory: UITextField!
    @IBOutlet weak var iconCategoty: UIImageView!
    @IBOutlet weak var dropViewStore: UIView!
    @IBOutlet weak var btnSelectStore: UIButton!
    @IBOutlet weak var iconStore: UIImageView!
    @IBOutlet weak var tfSelectStore: UITextField!
    
    let dropdown = DropDown()
    let categories = ["chicken", "pizza", "chinese", "pork", "bbokki", "etc"]
    let storesChicken = ["BBQ 정왕1호점", "BHC 시화로데오점", "자담치킨 정왕점"]
    let storesPizza = ["현구피자 정왕점", "수빈피자 시화로데오점", "윾빈이네 피자 정왕점"]
    let storesChinese = ["현구대반점", "더 베이징", "아래향"]
    let storesPork = ["현구족발 정왕1호점", "현구보쌈 시화로데오점", "오늘사족 본점"]
    let storesBbokki = ["돼지게티 정왕점", "동대문엽기떡볶이 시화이마트점", "삼첩분식 정왕점"]
    let storesEtc = ["해피타코야끼&닭꼬치", "써브웨이 시흥정왕점", "버거킹 시흥정왕점"]

    
    override func viewDidLoad() {
        super.viewDidLoad()
        initUI()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        navigationItem.hidesBackButton = true
    }
    @IBAction func btnCancel(_ sender: UIButton) {
        self.navigationController?.popViewController(animated: true)
    }
    
    func initUI() {
        dropViewCategory.backgroundColor = UIColor.black
        dropViewStore.backgroundColor = UIColor.black

        DropDown.appearance().textColor = UIColor.black // 아이템 텍스트 색상
        DropDown.appearance().selectedTextColor = UIColor(named: "themeColor")! // 선택된 아이템 텍스트 색상
        DropDown.appearance().backgroundColor = UIColor(named: "transparent")! // 아이템 팝업 배경 색상
        DropDown.appearance().selectionBackgroundColor = UIColor(named: "titleColor")! // 선택한 아이템 배경 색상
        dropdown.dismissMode = .automatic // 팝업을 닫을 모드 설정
            
        tfSelectCategory.text = "선택해주세요." // 힌트 텍스트
        tfSelectCategory.leftView = UIView(frame: CGRect(x: 0.0, y: 0.0, width: 16.0, height: 0.0))
        tfSelectCategory.leftViewMode = .always
        tfSelectStore.text = "선택해주세요." // 힌트 텍스트
        tfSelectStore.leftView = UIView(frame: CGRect(x: 0.0, y: 0.0, width: 16.0, height: 0.0))
        tfSelectStore.leftViewMode = .always
    }
}
