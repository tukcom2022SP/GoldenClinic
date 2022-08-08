//
//  participateInVC.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/04.
//

import UIKit
import DropDown
import Firebase

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
    var payTime: String = ""
    var contents: String = ""
    var storeName: String = ""
    var group: [String] = []
    var total = 0
    var delieveryFee = 3000
    let dictChicken = ["BBQ 정왕1호점" : ["황금올리브" : 20000 , "황금올리브 양념" : 21500 , "자메이카 통다리구이" : 21500], "하임치킨 시화로데오점" : ["오븐바사삭" : 17000, "불금치킨" : 18000, "갈비천왕" : 18000, "볼케이노" : 18000], "자담치킨 정왕점": ["소보로치킨" : 19000, "불패치킨" : 21000, "맵슐랭치킨" : 21000]]
    let dictPizza = ["현구피자 정왕점" : ["콤비네이션" : 13900, "불고기피자":13900, "치즈피자":13900], "수빈피자 시화로데오점" :  ["순살가득피자닭" : 29900, "돈마호크" : 35900, "립스테이크" : 35900], "윾빈이네 피자 정왕점" : ["리얼시카고 오리지날" : 22900, "BBQ 리얼 시카고" : 25900, "몽빼르 피자":21900]]
    let dictChinese = ["현구대반점": ["유니콩 짜장면" : 6000, "참짬뽕" : 8000, "야채볶음밥" : 8000], "더 베이징" : ["짜장면" : 8000, "짬뽕" : 9000, "탕수육" : 25000], "아래향": ["울면" : 7000, "콩국수" : 9000, "찹쌀탕수육" : 26000]]
    let dictPork = ["현구족발 정왕1호점" : ["족발" : 25000 ,"불족발" : 27000, "냉채족발" : 31000], "현구보쌈 시화로데오점": ["김치보쌈":27000, "1인보쌈" : 18000, "마늘보쌈" : 29000], "오늘사족 본점" : ["족발한팩" : 12000, "보쌈함팩" : 12000, "마늘족한팩" : 13000 ]]
    let dictBbokki = ["돼지게티 정왕점" : ["실속세트":21000,"1인세트":13000,"실속치킨세트":18000], "동대문엽기떡볶이 시화이마트점" : ["엽기메뉴":14000,"로제메뉴":18000,"엽봉":5000], "삼첩분식 정왕점" : ["삼첩떡볶이":8900, "바질크림떡볶이":9900,"대구막창":11000]]
    let dictEtc = ["해피타코야끼&닭꼬치" : ["수제타코야끼22알":14000,"파닭꼬치":3500,"모둠꼬치":26000], "써브웨이 시흥정왕점":["이탈리안비엠티":8700,"에그마요":7500,"스테이크치즈":10000], "버거킹 시흥정왕점" : ["콰트로치즈와퍼":10900,"통새우와퍼":10900,"갈릭불고기와퍼":10500]]
    var thisPost = db.collection("LivePost")

    
    override func viewDidLoad() {
        super.viewDidLoad()
        tvSelectedMenu.isEditable = false
        tvContent.isEditable = false
        lblCategory.text = category
        lblTitle.text = postName
        lblTimeNStoreName.text = "\(payTime) 결제 예정\t\(storeName)"
        tvContent.text = contents
        initUI()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        navigationItem.hidesBackButton = true
    }
    
    @IBAction func btnSelectMenu(_ sender: UIButton) {
        var menuNames: [String] = []
        var prices: [Int] = []
        
        switch category{
        case "치킨":
            for key in dictChicken[storeName]!.keys{
                menuNames.append(key)
                prices.append(dictChicken[storeName]![key]!)
            }
            dropdown.dataSource = menuNames
        case "피자":
            for key in dictPizza[storeName]!.keys{
                menuNames.append(key)
                prices.append(dictPizza[storeName]![key]!)
            }
            dropdown.dataSource = menuNames
        case "중식":
            for key in dictChinese[storeName]!.keys{
                menuNames.append(key)
                prices.append(dictChinese[storeName]![key]!)
            }
            dropdown.dataSource = menuNames
        case "족발 보쌈":
            for key in dictPork[storeName]!.keys{
                menuNames.append(key)
                prices.append(dictPork[storeName]![key]!)
            }
            dropdown.dataSource = menuNames
        case "분식":
            for key in dictBbokki[storeName]!.keys{
                menuNames.append(key)
                prices.append(dictBbokki[storeName]![key]!)
            }
            dropdown.dataSource = menuNames
        default:
            for key in dictEtc[storeName]!.keys{
                menuNames.append(key)
                prices.append(dictEtc[storeName]![key]!)
            }
            dropdown.dataSource = menuNames
        }
        dropdown.anchorView = dropViewMenu
        dropdown.bottomOffset = CGPoint(x: 0, y: dropViewMenu.bounds.height)
        dropdown.selectionAction = { [weak self] (index, item) in
            self!.tfSelectMenu.text = item
            self!.tvSelectedMenu.text += "\(item)\n"
            self!.total += prices[index]
        }
        dropdown.cancelAction = { [weak self] in
        }
        dropdown.show()
    }
    
    @IBAction func btnParticipateIn(_ sender: UIButton) {
        thisPost.getDocuments { (snapshot, err) in
            if let err = err {
            } else {
                guard let snapshot = snapshot else { return }
                for document in snapshot.documents {
                    if document.documentID == self.postName {
                        // 배열 데이터를 가져온다.
                        guard var data = document["group"] as? [String] else { return }
                        // 배열 데이터를 수정한다.
                        data.append("\((Auth.auth().currentUser?.email)!)")
                        // 서버의 배열 데이터를 수정된 데이터로 수정한다.
                        self.thisPost.document(self.postName).updateData(["group" : data])
                    }
                }
            }
        }
        group.append("\((Auth.auth().currentUser?.email)!)")
        delieveryFee = delieveryFee/group.count
        let alert = UIAlertController(title: "참가 하기", message: "주문 금액 : \(total)원\n본인 부담 배달비 : \(delieveryFee)원\n(원래 배달비 : 3000원)\n결제 예정 시각 : \(payTime)시\n결제 금액 : \(total+delieveryFee)원", preferredStyle: .alert)
        alert.addAction(UIAlertAction(
            title: "결제하기", style: .default){ action in
                let pushVC = self.storyboard?.instantiateViewController(withIdentifier: "category")
                self.navigationController?.pushViewController(pushVC!, animated: true)
            }
        )
        present(alert, animated: true, completion: nil)
        
//        tvSelectedMenu.text += "\(total)\n"
//        self.navigationController?.popViewController(animated: true)
    }
    
    @IBAction func btnCancel(_ sender: UIButton) {
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
