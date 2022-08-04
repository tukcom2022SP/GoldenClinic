//
//  participateInVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/04.
//

import UIKit
import DropDown

class participateInVC: UIViewController{
    @IBOutlet weak var lblCategory: UILabel!
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var lblTimeNStoreName: UILabel!
    @IBOutlet weak var tvContent: UITextView!
    @IBOutlet weak var tvSelectedMenu: UITextView!
    @IBOutlet weak var tfSelectMenu: UITextField!
    @IBOutlet weak var dropViewMenu: UIView!
    let dropdown = DropDown()
    var category: String = ""
    var postName: String = ""
    var payTime: Int = 7
    var contents: String = ""
    var storeName: String = ""
    let storesChicken = ["후라이드 치킨", "양념 치킨", "간장 치킨", "치즈 치킨", "민트초코 치킨"]
    let storesPizza = ["콤비네이션 피자", "불고기 피자", "하와이안 피자", "솔의눈 피자"]
    let storesChinese = ["짜장면", "짬뽕", "탕수육", "깐풍기", "돼지국밥"]
    let storesPork = ["족발(앞다리)", "족발(뒷다리)", "삼겹살 보쌈", "목살 보쌈", "민트초코 족보 세트"]
    let storesBbokki = ["떡볶이", "매운 떡볶이", "민트초코 떡볶이", "데자와 떡볶이", "감자튀김", "민트초코 소스"]
    let storesEtc = ["김밥", "우영웅 김밥", "동그라미 김밥", "초코 돈까스 김밥", "라면", "코카콜라"]

    
    override func viewDidLoad() {
        super.viewDidLoad()
        tvSelectedMenu.isEditable = false
        tvContent.isEditable = false
        lblCategory.text = category
        lblTitle.text = postName
        lblTimeNStoreName.text = "결제 예정 시간: \(payTime)시\t\(storeName)"
        tvContent.text = contents
        initUI()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        navigationItem.hidesBackButton = true
    }
    
    @IBAction func btnSelectMenu(_ sender: UIButton) {
        switch category{
        case "치킨": dropdown.dataSource = storesChicken
        case "피자": dropdown.dataSource = storesPizza
        case "중식": dropdown.dataSource = storesChinese
        case "족발 보쌈": dropdown.dataSource = storesPork
        case "분식": dropdown.dataSource = storesBbokki
        case "기타": dropdown.dataSource = storesEtc
        default: dropdown.dataSource = ["카테고리를 먼저 선택하세요."]
        }
        dropdown.anchorView = dropViewMenu
        dropdown.bottomOffset = CGPoint(x: 0, y: dropViewMenu.bounds.height)
        dropdown.selectionAction = { [weak self] (index, item) in
            self!.tfSelectMenu.text = item
            self!.tvSelectedMenu.text += "\(item)\n"
        }
        dropdown.cancelAction = { [weak self] in
        }
        dropdown.show()
    }
    
    @IBAction func btnParticipateIn(_ sender: UIButton) {
        self.navigationController?.popViewController(animated: true)
    }
    
    func initUI() {
        dropViewMenu.backgroundColor = UIColor.black

        DropDown.appearance().textColor = UIColor.black // 아이템 텍스트 색상
        DropDown.appearance().selectedTextColor = UIColor.white// 선택된 아이템 텍스트 색상
        DropDown.appearance().backgroundColor = UIColor(named: "transparent")! // 아이템 팝업 배경 색상
        DropDown.appearance().selectionBackgroundColor = UIColor(named: "titleColor")! // 선택한 아이템 배경 색상
        dropdown.dismissMode = .automatic // 팝업을 닫을 모드 설정
            
        tfSelectMenu.text = "선택해주세요." // 힌트 텍스트
        tfSelectMenu.leftView = UIView(frame: CGRect(x: 0.0, y: 0.0, width: 16.0, height: 0.0))
        tfSelectMenu.leftViewMode = .always
    }
}
