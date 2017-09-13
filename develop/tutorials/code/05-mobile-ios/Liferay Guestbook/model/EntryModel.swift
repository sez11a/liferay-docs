//
//  EntryModel.swift
//  Liferay Guestbook
//
//  Created by Joe Bloggs on 7/7/17.
//  Copyright © 2017 Joe Bloggs. All rights reserved.
//

import UIKit

@objc public class EntryModel: NSObject {
    
    public let attributes: [String:AnyObject]
    
    public var entryId: Int64 {
        return attributes["entryId"] as? Int64 ?? 0
    }
    
    public var groupId: Int64 {
        return attributes["groupId"] as? Int64 ?? 0
    }
    
    public var companyId: Int64 {
        return attributes["companyId"] as? Int64 ?? 0
    }
    
    public var userId: Int64 {
        return attributes["userId"] as? Int64 ?? 0
    }
    
    public var userName: String {
        return attributes["userName"] as? String ?? ""
    }
    
    public var createDate: Int64 {
        return attributes["createDate"] as? Int64 ?? 0
    }
    
    public var modifiedDate: Int64 {
        return attributes["modifiedDate"] as? Int64 ?? 0
    }
    
    public var name: String {
        return attributes["name"] as? String ?? ""
    }
    
    public var email: String {
        return attributes["email"] as? String ?? ""
    }
    
    public var message: String {
        return attributes["message"] as? String ?? ""
    }
    
    public var guestbookId: Int64 {
        return attributes["guestbookId"] as? Int64 ?? 0
    }
    
    //MARK: Initializer
    
    public init(attributes: [String:AnyObject]) {
        self.attributes = attributes
    }

}
