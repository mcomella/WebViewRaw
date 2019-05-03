package xyz.mcomella.webviewevaljs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.mozilla.tv.firefox.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WebView.setWebContentsDebuggingEnabled(true)

        webView.webViewClient = object : WebViewClient() {
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)
                fullscreenContainer.addView(view)
            }

            override fun onHideCustomView() {
                super.onHideCustomView()
                fullscreenContainer.removeAllViews()
            }
        }

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = false
        }

        webView.loadUrl("https://developer.jwplayer.com/tools/stream-tester/?playerversion=8")
    }

    override fun onResume() {
        super.onResume()
        parentContainer.visibility = View.VISIBLE
        parentContainer.visibility = View.GONE
        Log.e("lol", "${parentContainer.visibility == View.GONE} ${child.visibility == View.GONE}")
    }
}
