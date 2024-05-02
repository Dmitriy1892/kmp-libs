import SwiftUI
import SharedSDK

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate
    
    @Environment(\.scenePhase)
    var scenePhase: ScenePhase
    
    var lifecycle: IosLifecycleHandler { appDelegate.lifecycleHolder.lifecycleHandler }
    
    init() {
        KoinDiHolder.companion.getInstance()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
                .onChange(of: scenePhase) { newPhase in
                    switch newPhase {
                    case .background: lifecycle.onStop()
                    case .inactive: lifecycle.onPause()
                    case .active: lifecycle.onResume()
                    @unknown default: break
                    }
                }
		}
	}
}
