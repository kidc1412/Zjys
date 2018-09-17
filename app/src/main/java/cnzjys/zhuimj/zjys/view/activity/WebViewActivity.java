package cnzjys.zhuimj.zjys.view.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;

import cnzjys.zhuimj.zjys.R;
import cnzjys.zhuimj.zjys.constant.Constant;
import cnzjys.zhuimj.zjys.utils.StateBarUtils;

public class WebViewActivity extends AppCompatActivity {

    private FrameLayout mWebViewPanel;
    private WebView mWebView;
    private String webUrl;
    private String mCurrentWebName;
    private ViewGroup mToolBarPanel;
    private TextView mTitleTv;
    private ImageView mBackIv;
    private TextView mPlayTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);

        webUrl = getIntent().getStringExtra(Constant.WEB_URL);
        mCurrentWebName = getIntent().getStringExtra(Constant.CURRENT_WEB_NAME);
        initView();
        initListener();
    }

    private void initView(){
        StateBarUtils.transparentStateBar(this);
        Slidr.attach(this);

        mToolBarPanel = findViewById(R.id.web_view_toolbar_panel);
        mToolBarPanel.setPadding(0, StateBarUtils.getStatusBarHeight(this), 0,
                0);

        mTitleTv = findViewById(R.id.web_view_toolbar_title_tv);
        mTitleTv.setText(mCurrentWebName);
        mBackIv = findViewById(R.id.web_view_toolbar_back_iv);
        mPlayTv = findViewById(R.id.web_view_toolbar_play_tv);

        mWebViewPanel = findViewById(R.id.web_view_panel);

        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(){
        mWebView = new WebView(getApplicationContext());
        mWebView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mWebViewPanel.addView(mWebView);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        mWebView.loadUrl(webUrl);
        //启用支持JS
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void initListener(){
        mBackIv.setOnClickListener(v -> finish());
        mPlayTv.setOnClickListener(v -> Toast.makeText(
                WebViewActivity.this, "播放", Toast.LENGTH_SHORT).show());
    }
}
