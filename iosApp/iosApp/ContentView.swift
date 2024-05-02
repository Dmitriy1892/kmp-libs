import SwiftUI
import SharedSDK

struct ContentView: View {

	var body: some View {
        ComposeInteropView().ignoresSafeArea(.all)
	}
}

struct ComposeInteropView: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> UIViewController {
        return Main_iosKt.MainViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
