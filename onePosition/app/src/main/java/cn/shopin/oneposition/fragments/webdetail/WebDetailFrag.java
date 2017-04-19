package cn.shopin.oneposition.fragments.webdetail;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;

import cn.shopin.oneposition.R;
import cn.shopin.oneposition.api.MovieApi;
import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.util.db.DBManager;

/**
 * Created by zcs on 2017/3/4.
 */

public class WebDetailFrag extends Fragment {
    private View view;
    private ImageView imgBack;
    private TextView textComments;
    private TextView textTitle;
    private PullToRefreshWebView refreshWebView;
    private WebView webView;
    private Bundle bun;
    private PopupWindow tailPopupWindow;
    private View tailView;
    private TextView textComment;
    private ImageView imgShare;
    private ImageView imgComment;
    private ImageView imgCollect;
    private GestureDetector gestureListener;
    private NostalgicEntity mCurrentEntity;
    private DBManager dbManager;
    private boolean isSelected = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_webdetail, null);
        tailView = inflater.inflate(R.layout.movie_detail_tail, null);
        initTailView(tailView);
        initPopWindow();
        dbManager = new DBManager(getActivity());
        imgBack = (ImageView) view.findViewById(R.id.back);
        textComments = (TextView) view.findViewById(R.id.comments);
        textTitle = (TextView) view.findViewById(R.id.title);
        initView();
        bun = getArguments();
        mCurrentEntity = (NostalgicEntity) bun.getSerializable("entity");
        textComments.setText(String.valueOf(mCurrentEntity.getCommentcount()));
        textTitle.setText(mCurrentEntity.getSubtype().getName());
        webViewConfig();
        Log.d("TTAAGG", MovieApi.MOVIE_DETAIL + mCurrentEntity.getId());
        webView.loadUrl(MovieApi.MOVIE_DETAIL + mCurrentEntity.getId());
        Log.d("sqlOP", "---isSelected---");
        isSelected = dbManager.query(mCurrentEntity.getId());
        Log.d("sqlOP", "---isSelected--- " + isSelected);
        imgCollect.setSelected(isSelected);
        gestureListener = new GestureDetector(getActivity(), new GestureDelectorSimlpeListener());
        return view;
    }

    private void initTailView(View view) {
        textComments = (TextView) view.findViewById(R.id.text_writecomms);
        imgShare = (ImageView) view.findViewById(R.id.img_share);
        imgComment = (ImageView) view.findViewById(R.id.img_comment);
        imgCollect = (ImageView) view.findViewById(R.id.img_collect);
        imgCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected = !isSelected;
                imgCollect.setSelected(isSelected);
                if (isSelected) {
                    dbManager.insert(mCurrentEntity);
                } else {
                    dbManager.delete(mCurrentEntity);
                }
            }
        });
    }

    private void initView() {

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        textComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initPopWindow() {
        tailPopupWindow = new PopupWindow(tailView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tailPopupWindow.setAnimationStyle(R.style.popwin_anim_style_first);
        tailPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        tailPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
    }

    /**
     * 配置一些webView的属性
     */
    private void webViewConfig() {
        refreshWebView = (PullToRefreshWebView) view.findViewById(R.id.ptr_webview);
        refreshWebView.setMode(PullToRefreshBase.Mode.BOTH);//设置上拉和下拉都可以
        webView = refreshWebView.getRefreshableView();
        final WebSettings webseting = webView.getSettings();
        webseting.setJavaScriptEnabled(true);
        webseting.setDomStorageEnabled(true);
        webseting.setAppCacheMaxSize(1024 * 1024 * 8); //设置缓冲大小，这里设的是8M
        String appCacheDir = getActivity().getApplicationContext().getCacheDir().getAbsolutePath();
        webseting.setAppCachePath(appCacheDir);
        webseting.setAllowFileAccess(true);
        webseting.setAppCacheEnabled(true);
        webseting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webseting.setPluginState(WebSettings.PluginState.ON);
        webseting.setBlockNetworkImage(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setBackgroundColor(16777215);
        webView.addJavascriptInterface(new JsClickListener(), "clickListener");
        initIndicator();
        webView.setWebViewClient(new WebViewClient() {//将页面内的链接跳转限制在 WebView 内
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                webView.loadUrl(request.getUrl().getPath());
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.getSettings().setJavaScriptEnabled(true);
                super.onPageFinished(view, url);
                //网页加载完成
                webView.loadUrl("javascript:(function(){" +
                        "var objs = document.getElementsByTagName(\"img\"); " +
                        "for(var i=0;i<objs.length;i++)  " +
                        "{"
                        + "    objs[i].onclick=function()  " +
                        "    {  "
                        + "        window.clickListener.getImage(this.src);  " +
                        "    }  " +
                        "}" +
                        "})()");
                /**
                 * 异常：View not attached to window manager
                 * */
/*                if (!WebDetailFrag.this.isDetached()) {
                    tailPopupWindow.dismiss();
                }*/
                if (getActivity() != null && !getActivity().isFinishing()) {
                    tailPopupWindow.dismiss();
                }
            }
        });
        refreshWebView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<WebView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<WebView> refreshView) {
                refreshView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<WebView> refreshView) {
                refreshView.onRefreshComplete();
            }
        });
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureListener.onTouchEvent(event);
                return false;
            }
        });
    }

    private void initIndicator() {
/*        ILoadingLayout startLabels = refreshWebView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("");// 刷新时
        startLabels.setReleaseLabel("");// 下来达到一定距离时，显示的提示
        ILoadingLayout endLabels = refreshWebView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("");// 刷新时
        endLabels.setReleaseLabel("");// 下来达到一定距离时，显示的提示*/
    }

    class JsClickListener {
        @JavascriptInterface
        public void getImage(final String imgUrl) {
            Toast.makeText(getActivity(), imgUrl, Toast.LENGTH_SHORT).show();
        }
    }

    class GestureDelectorSimlpeListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            drawTouch(e1.getX(), e1.getY(), e2.getX(), e2.getY());
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        /**
         * 手势判断
         *
         * @param x
         * @param y
         * @param upx
         * @param upy
         * @return
         */
        private void drawTouch(float x, float y, float upx, float upy) {
            if (upy - y > 100) {
                tailPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            } else if (y - upy > 100) {
                tailPopupWindow.dismiss();
            }
        }
    }
}