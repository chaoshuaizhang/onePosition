package cn.shopin.oneposition.fragments.webdetail;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;

import butterknife.BindView;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.api.MovieApi;
import cn.shopin.oneposition.entity.movie.NostalgicEntity;
import cn.shopin.oneposition.fragments.BaseMvpFragment;
import cn.shopin.oneposition.model.db.DBManager;

/**
 * Created by zcs on 2017/3/4.
 */
public class WebDetailFrag extends BaseMvpFragment<WebDetailPresenter> implements WebDetailContract.IWebDetailView {
    protected ImageView imgBack;
    protected TextView textComments;
    protected TextView textTitle;
    @BindView(R.id.ptr_webview)
    protected PullToRefreshWebView refreshWebView;
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
    protected DBManager dbManager;
    private boolean isSelected = false;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_webdetail;
    }

    @Override
    protected void initEventAndData() {
        initView();
        initListener();
        initData();
    }

    private void initTailView(View view) {
        imgShare = (ImageView) view.findViewById(R.id.img_share);
        imgComment = (ImageView) view.findViewById(R.id.img_comment);
        imgCollect = (ImageView) view.findViewById(R.id.img_collect);
    }

    @Override
    public void initView() {
        tailView = LayoutInflater.from(getContext()).inflate(R.layout.movie_detail_tail, null);
        initTailView(tailView);
        initPopWindow();
//        dbManager = new DBManager(getActivity());
        imgBack = (ImageView) mView.findViewById(R.id.back);
        textComments = (TextView) mView.findViewById(R.id.comments);
        textTitle = (TextView) mView.findViewById(R.id.title);
    }

    @Override
    public void initListener() {
        gestureListener = new GestureDetector(getActivity(), new GestureDelectorSimlpeListener());
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
        imgCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgCollect.setSelected(mPresenter.setLiked(isSelected = !isSelected, mCurrentEntity));
            }
        });
    }

    @Override
    public void initData() {
        bun = getArguments();
        mCurrentEntity = (NostalgicEntity) bun.getSerializable("entity");
        textComments.setText(String.valueOf(mCurrentEntity.getCommentcount()));
        textTitle.setText(mCurrentEntity.getSubtype().getName());
        webViewConfig();
        webView.loadUrl(MovieApi.MOVIE_DETAIL + mCurrentEntity.getId());
        isSelected = mPresenter.getDetailById(mCurrentEntity.getId());
        imgCollect.setSelected(isSelected);
    }

    private void initPopWindow() {
        tailPopupWindow = new PopupWindow(tailView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tailPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        tailPopupWindow.setAnimationStyle(R.style.popwin_anim_style_first);
        tailPopupWindow.showAtLocation(mView, Gravity.BOTTOM, 0, -tailPopupWindow.getHeight());
        tailPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
    }

    /**
     * 配置一些webView的属性
     */
    private void webViewConfig() {
        refreshWebView.setMode(PullToRefreshBase.Mode.BOTH);//设置上拉和下拉都可以
        webView = refreshWebView.getRefreshableView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            webView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY > oldScrollY) {
                        tailPopupWindow.showAtLocation(mView, Gravity.BOTTOM, 0, -tailPopupWindow.getHeight());
                    }
                    if (scrollY < oldScrollY) {
                        tailPopupWindow.dismiss();
                    }
                }
            });
        } else {
            webView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    gestureListener.onTouchEvent(event);
                    return false;
                }
            });
        }
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
    }

    private void initIndicator() {
        ILoadingLayout startLabels = refreshWebView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("-----");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("");// 刷新时
        startLabels.setReleaseLabel("");// 下来达到一定距离时，显示的提示
        ILoadingLayout endLabels = refreshWebView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("---------");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("");// 刷新时
        endLabels.setReleaseLabel("");// 下来达到一定距离时，显示的提示
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
            if (upy - y > 0) {
                tailPopupWindow.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
            } else if (y - upy > 0) {
                tailPopupWindow.dismiss();
            }
        }
    }
}