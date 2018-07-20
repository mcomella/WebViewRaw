package xyz.mcomella.webviewevaljs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*

val FOCUS_UA = "Mozilla/5.0 (Linux; Android 8.1.0) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Focus/5.2 Chrome/67.0.3396.87 Mobile Safari/537.36"

class MainActivity : AppCompatActivity() {
    lateinit var videoContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videoContainer = fullscreenContainer

        webView.webViewClient = object : WebViewClient() {
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)
                videoContainer.addView(view)
                videoContainer.visibility = View.VISIBLE
            }

            override fun onHideCustomView() {
                super.onHideCustomView()
                videoContainer.removeAllViews()
                videoContainer.visibility = View.GONE
            }
        }

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = false
            userAgentString = FOCUS_UA
        }

        webView.loadUrl("https://m.youtube.com/")
    }
}
