package cn.shopin.oneposition.entity.heart;

import java.util.List;

import cn.shopin.oneposition.entity.BaseEntity;

/**
 * Created by zcs on 2017/4/13.
 */

public class HomeCarousel extends BaseEntity {

    /**
     * errorCode : 0
     * errorStr : success
     * resultCount : 4
     * score : 0
     * balance : 0
     * results : [{"id":"112","icon":"http://image.xinliji.me/o_1bb34rj3bdg35g11ia91fuo1v4ti.png","pic":"http://image.xinliji.me/o_1bbd3aus4ui010p79j41uocu15i.jpg","title":"施琪嘉约你看电影","subtitle":"施琪嘉约你看电影","type":"webview","url":"http://mp.weixin.qq.com/s/FVhRrz69q37QOHwEm72huA?id=0&token=","target":"","className":""},{"id":"114","icon":"http://image.xinliji.me/o_1bb5dfmhv3fm1ooujqop3g11tei.png","pic":"http://image.xinliji.me/o_1bb5djcvg1cuf1h7c109lusp132c14.png","title":"动力取向儿童青少年心理咨询","subtitle":"动力取向儿童青少年心理咨询","type":"none","url":"&token=","target":"","className":""},{"id":"98","icon":"http://image.xinliji.me/o_1b2upacs2kan5qh1gs71bl2107cn.png","pic":"http://image.xinliji.me/o_1b2up2c5p1mmvvr3b7o1sp91lb8i.png","title":"正念体悟疗法微课录音","subtitle":"十年督导禅修学习，十年临床咨询实践","type":"webview","url":"http://dwz.cn/4IYrG0?id=0&token=","target":"","className":""},{"id":"3","icon":"http://image.xinliji.me/o_1b2vq5v23moam2b1kmf113smbdi.png","pic":"http://image.xinliji.me/o_1b2vq62nu1gqbp125g1slo1fgqn.png","title":"咨询师免费入驻","subtitle":"咨询师免费入驻","type":"webview","url":"http://console.xinliji.me/consultant/applyViaMobile?id=1&token=","target":"","className":""}]
     */

    private String errorCode;
    private String errorStr;
    private String resultCount;
    private String score;
    private String balance;
    private List<CarouselResultEntity> results;

    public String getErrorCode() { return errorCode;}

    public void setErrorCode(String errorCode) { this.errorCode = errorCode;}

    public String getErrorStr() { return errorStr;}

    public void setErrorStr(String errorStr) { this.errorStr = errorStr;}

    public String getResultCount() { return resultCount;}

    public void setResultCount(String resultCount) { this.resultCount = resultCount;}

    public String getScore() { return score;}

    public void setScore(String score) { this.score = score;}

    public String getBalance() { return balance;}

    public void setBalance(String balance) { this.balance = balance;}

    public List<CarouselResultEntity> getResults() { return results;}

    public void setResults(List<CarouselResultEntity> results) { this.results = results;}

}
