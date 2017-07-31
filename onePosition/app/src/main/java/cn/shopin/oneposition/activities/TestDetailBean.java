package cn.shopin.oneposition.activities;

/**
 * Created by zcs on 2017/7/10.
 */

public class TestDetailBean<T> {
    private String code;
    private String codeInfo;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeInfo() {
        return codeInfo;
    }

    public void setCodeInfo(String codeInfo) {
        this.codeInfo = codeInfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
