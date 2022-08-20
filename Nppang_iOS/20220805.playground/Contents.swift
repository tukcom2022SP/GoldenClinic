import UIKit

let storesChicken = [ "BBQ 정왕1호점" : ["황금올리브" : 20000 , "황금올리브 양념" : 21500 , "자메이카 통다리구이" : 21500], "하임치킨 시화로데오점" : ["오븐바사삭" : 17000, "불금치킨" : 18000, "갈비천왕" : 18000, "볼케이노" : 18000]]

for key in storesChicken["BBQ 정왕1호점"]!.values{
    print(type(of: key))
}
