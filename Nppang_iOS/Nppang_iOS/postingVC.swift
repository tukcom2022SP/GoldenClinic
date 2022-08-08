//
//  postingVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/01.
//

import UIKit
import DropDown
import Firebase

class postingVC: UIViewController{
    @IBOutlet weak var tfTitle: UITextField!
    @IBOutlet weak var tvContent: UITextView!
    @IBOutlet weak var dropViewCategory: UIView!
    @IBOutlet weak var btnSelectCategory: UIButton!
    @IBOutlet weak var tfSelectCategory: UITextField!
    @IBOutlet weak var dropViewStore: UIView!
    @IBOutlet weak var btnSelectStore: UIButton!
    @IBOutlet weak var tfSelectStore: UITextField!
    @IBOutlet weak var payTimePicker: UIDatePicker!
    let dropdown = DropDown()
    let categories = ["치킨", "피자", "중식", "족발 보쌈", "분식", "기타"]
    var category = ""
    let storesChicken = ["BBQ 정왕1호점", "하임치킨 시화로데오점", "자담치킨 정왕점"]
    let storesPizza = ["현구피자 정왕점", "수빈피자 시화로데오점", "윾빈이네 피자 정왕점"]
    let storesChinese = ["현구대반점", "더 베이징", "아래향"]
    let storesPork = ["현구족발 정왕1호점", "현구보쌈 시화로데오점", "오늘사족 본점"]
    let storesBbokki = ["돼지게티 정왕점", "동대문엽기떡볶이 시화이마트점", "삼첩분식 정왕점"]
    let storesEtc = ["해피타코야끼&닭꼬치", "써브웨이 시흥정왕점", "버거킹 시흥정왕점"]
    var payTime = ""

    
    override func viewDidLoad() {
        super.viewDidLoad()
        initUI()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        navigationItem.hidesBackButton = true
    }
    
    @IBAction func btnSelectCategory(_ sender: UIButton) {
        // dataSource로 ItemList를 연결
        dropdown.dataSource = categories
        // anchorView를 통해 UI와 연결
        dropdown.anchorView = dropViewCategory
        // View를 갖리지 않고 View아래에 Item 팝업이 붙도록 설정
        dropdown.bottomOffset = CGPoint(x: 0, y: dropViewCategory.bounds.height)
        // Item 선택 시 처리
        dropdown.selectionAction = { [weak self] (index, item) in
            //선택한 Item을 TextField에 넣어준다.
            self!.tfSelectCategory.text = item
            self!.category = item
        }
        // 취소 시 처리
        dropdown.cancelAction = { [weak self] in
            //빈 화면 터치 시 DropDown이 사라지고 아이콘을 원래대로 변경
        }
        dropdown.show() // 아이템 팝업을 보여준다.
    }
    
    @IBAction func btnSelectStore(_ sender: UIButton) {
        switch category{
        case "치킨": dropdown.dataSource = storesChicken
        case "피자": dropdown.dataSource = storesPizza
        case "중식": dropdown.dataSource = storesChinese
        case "족발 보쌈": dropdown.dataSource = storesPork
        case "분식": dropdown.dataSource = storesBbokki
        case "기타": dropdown.dataSource = storesEtc
        default: dropdown.dataSource = ["카테고리를 먼저 선택하세요."]
        }
        dropdown.anchorView = dropViewStore
        dropdown.bottomOffset = CGPoint(x: 0, y: dropViewStore.bounds.height)
        dropdown.selectionAction = { [weak self] (index, item) in
            self!.tfSelectStore.text = item
        }
        dropdown.cancelAction = { [weak self] in
        }
        dropdown.show()
    }
    
    @IBAction func pickerPayTime(_ sender: UIDatePicker) {
        let timePickerView = sender
        let formatter = DateFormatter()
        
        formatter.locale = Locale(identifier: "ko")
        formatter.dateFormat = "HH시 mm분"
        payTime = formatter.string(from: timePickerView.date)
    }
    
    @IBAction func btnPost(_ sender: UIButton) {
        db.collection("LivePost").document(self.tfTitle.text!).setData([
            "postname": self.tfTitle.text!,
            "contents": self.tvContent.text!,
            "category": self.tfSelectCategory.text!,
            "storeName": self.tfSelectStore.text!,
            "group": ["\((Auth.auth().currentUser?.email)!)"],
            "payTime": payTime
        ])
        let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "category")
        self.navigationController?.pushViewController(pushVC!, animated: true)
    }
    
    @IBAction func btnCancel(_ sender: UIButton) {
        self.navigationController?.popViewController(animated: true)
    }
    
    func initUI() {
        dropViewCategory.backgroundColor = UIColor.black
        dropViewStore.backgroundColor = UIColor.black

        DropDown.appearance().textColor = UIColor.black // 아이템 텍스트 색상
        DropDown.appearance().selectedTextColor = UIColor.white// 선택된 아이템 텍스트 색상
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
