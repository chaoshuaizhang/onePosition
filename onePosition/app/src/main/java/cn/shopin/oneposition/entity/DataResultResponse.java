package cn.shopin.oneposition.entity;

/**
 * Created by zcs on 2017/6/13.
 */

public class DataResultResponse<T> {

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
