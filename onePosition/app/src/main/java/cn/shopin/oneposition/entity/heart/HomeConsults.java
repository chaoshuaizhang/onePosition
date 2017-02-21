package cn.shopin.oneposition.entity.heart;

import java.util.List;

/**
 * Created by zcs on 2017/2/19.
 *
 * @desc
 */
public class HomeConsults {

    /**
     * errorCode : 0
     * errorStr : success
     * resultCount : 4
     * score : 0
     * balance : 0
     * results : [{"userid":"7542","nickname":"曹华","avatar":"http://image.xinliji.me/o_1b8qgji1f1lth11jv1in1a33ri8a.png","title":"最佳咨询师","qualification":"国二"},{"userid":"51773","nickname":"乔瑞峰","avatar":"http://image.xinliji.me/o_1b8qgn2f21jdmire1v291e4c1mcf1h.jpg","title":"最佳咨询师","qualification":"国二"},{"userid":"87135","nickname":"金新新","avatar":"http://image.xinliji.me/o_1b8qgo3it1fes1m7h161m1bag1i5e1u.jpg","title":"最佳咨询师","qualification":"国二"},{"userid":"91313","nickname":"黄莹","avatar":"http://image.xinliji.me/o_1b8qgp322goj203ldu1jen16j52b.png","title":"最受欢迎咨询师","qualification":"国三"}]
     */

    private String errorCode;
    private String errorStr;
    private String resultCount;
    private String score;
    private String balance;
    private List<ConsultsResultsEntity> results;

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorStr(String errorStr) {
        this.errorStr = errorStr;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setResults(List<ConsultsResultsEntity> results) {
        this.results = results;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorStr() {
        return errorStr;
    }

    public String getResultCount() {
        return resultCount;
    }

    public String getScore() {
        return score;
    }

    public String getBalance() {
        return balance;
    }

    public List<ConsultsResultsEntity> getResults() {
        return results;
    }
}
