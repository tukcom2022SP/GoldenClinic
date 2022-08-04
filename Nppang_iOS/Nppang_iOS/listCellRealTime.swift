//
//  listCellRealTime.swift
//  Nppang_iOS
//
//  Created by 권태우 on 2022/08/04.
//

import UIKit

class listCellRealTime: UITableViewCell {
    @IBOutlet weak var imgCategory: UIImageView!
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var lblCategoryNStoreName: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
