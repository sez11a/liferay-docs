//
//  ViewController.swift
//  Liferay Guestbook
//
//  Created by Joe Bloggs on 6/20/17.
//  Copyright © 2017 Joe Bloggs. All rights reserved.
//

import UIKit
import LiferayScreens

class ViewController: UIViewController, LoginScreenletDelegate {

    @IBOutlet weak var loginScreenlet: LoginScreenlet!

    override func viewDidLoad() {
        super.viewDidLoad()
        self.loginScreenlet?.delegate = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    func screenlet(_ screenlet: BaseScreenlet, onLoginResponseUserAttributes attributes: [String:AnyObject]) {
        print("Login Successful!")
        performSegue(withIdentifier: "loginsegue", sender: nil)
    }
    
    func screenlet(_ screenlet: BaseScreenlet, onLoginError error: NSError) {
        print("Login Failed!")
    }

}

