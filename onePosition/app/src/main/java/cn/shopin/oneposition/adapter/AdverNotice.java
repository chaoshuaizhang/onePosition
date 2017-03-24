package cn.shopin.oneposition.adapter;

/**
 * Created by zcs on 2017/3/10.
 */

public class AdverNotice {
    public String title;
    public String url;

    public AdverNotice(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
