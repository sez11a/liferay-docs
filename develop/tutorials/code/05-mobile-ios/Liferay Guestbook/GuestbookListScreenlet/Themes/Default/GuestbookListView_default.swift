//
//  GuestbookListView_default.swift
//  Liferay Guestbook
//
//  Created by Joe Bloggs on 6/30/17.
//  Copyright © 2017 Joe Bloggs. All rights reserved.
//

import UIKit
import LiferayScreens

class GuestbookListView_default: BaseListTableView {

    override public func doFillLoadedCell(row: Int, cell: UITableViewCell, object: AnyObject) {
        let guestbook = object as! GuestbookModel
        
        cell.textLabel?.text = guestbook.name
    }
    
    override public func doFillInProgressCell(row: Int, cell: UITableViewCell) {
        cell.textLabel?.text = "Loading..."
    }

}
