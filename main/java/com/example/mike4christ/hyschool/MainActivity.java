package com.example.mike4christ.hyschool;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static WebView mywebView;
    private static ProgressBar bar;
    private static TextView text;

   // private static ProgressBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mywebView = (WebView) findViewById(R.id.webview);
        text = (TextView) findViewById(R.id.text);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.setMax(100);
        mywebView.setWebViewClient(new myWebViewClient());

        mywebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                int progress=2;
                bar.setVisibility(View.VISIBLE);
                bar.setProgress(progress);
                text.setText("Loading..");

                if(progress==100){
                    bar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        WebSettings webSettings = mywebView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        mywebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mywebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mywebView.getSettings().setAppCacheEnabled(true);
        mywebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setEnableSmoothTransition(true);



        mywebView.loadUrl("http://www.hyschool.ng/sms");
        bar.setProgress(0);


    }

    @Override
    public void onBackPressed() {
        if(mywebView.canGoBack()){
            mywebView.goBack();
        }else{
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
       /*
        mywebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mywebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mywebView.getSettings().setAppCacheEnabled(true);
        mywebView.getSettings().setLoadsImagesAutomatically(true);
        mywebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSaveFormData(true);
        webSettings.setSavePassword(true);
        webSettings.setEnableSmoothTransition(true);*/





    public class myWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            bar.setVisibility(View.VISIBLE);
            view.loadUrl(url);


            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            bar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode==KeyEvent.KEYCODE_BACK)&&mywebView.canGoBack()){
            mywebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}







