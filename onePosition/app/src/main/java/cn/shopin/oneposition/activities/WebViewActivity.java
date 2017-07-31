package cn.shopin.oneposition.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import cn.shopin.oneposition.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.100:8080/movie/getMovieDetailById/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TestMovieDetail detail = retrofit.create(TestMovieDetail.class);
        Call<TestDetailBean<MvBean>> call = detail.getTopMovie(21622);
        call.enqueue(new Callback<TestDetailBean<MvBean>>() {
            @Override
            public void onResponse(Call<TestDetailBean<MvBean>> call, Response<TestDetailBean<MvBean>> response) {
                MvBean mv = response.body().getData();
                webView.loadDataWithBaseURL(null,mv.getWebInfo(), "text/html",  "utf-8", null);
            }

            @Override
            public void onFailure(Call<TestDetailBean<MvBean>> call, Throwable t) {
                webView.loadData("失败:" + t.getMessage(), "text/html", "UTF-8");
            }
        });
    }
}
