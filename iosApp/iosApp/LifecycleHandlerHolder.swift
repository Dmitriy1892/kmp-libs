//
//  LifecycleHandler.swift
//  iosApp
//
//  Created by Дмитрий on 19.04.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import UIKit

class LifecycleHandlerHolder : ObservableObject {
    
    let lifecycleHandler: IosLifecycleHandler
    
    init() {
        self.lifecycleHandler = IosLifecycleHandler.shared
        lifecycleHandler.onCreate()
    }
    
    deinit {
        lifecycleHandler.onDestroy()
    }
}

class AppDelegate : NSObject, UIApplicationDelegate {
    let lifecycleHolder: LifecycleHandlerHolder = LifecycleHandlerHolder()
}
