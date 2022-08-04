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
    
    @IBAction func btnSelectMenu(_ sender: UIButton) {
        switch storeName{
        case "현구피자 정왕점": dropdown.dataSource = ["d", "ddd"]
        default: dropdown.dataSource = ["카테고리를 먼저 선택하세요."]
        }
        dropdown.anchorView = dropViewMenu
        dropdown.bottomOffset = CGPoint(x: 0, y: dropViewMenu.bounds.height)
        dropdown.selectionAction = { [weak self] (index, item) in
            self!.tfSelectMenu.text = item
        }
        dropdown.cancelAction = { [weak self] in
        }
        dropdown.show()
    }
    
    @IBAction func btnParticipateIn(_ sender: UIButton) {
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
