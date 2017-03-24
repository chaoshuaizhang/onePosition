package cn.shopin.oneposition.entity.heart;

import java.util.List;

import cn.shopin.oneposition.entity.BaseEntity;

/**
 * Created by zcs on 2017/3/10.
 */

public class ActionLabelEntity extends BaseEntity {

    /**
     * errorCode : 0
     * errorStr : success
     * resultCount : 10
     * score : 0
     * balance : 0
     * results : [{"label":"用户 小** 支付￥180 预约了咨询师 张玲"},{"label":"用户 N** 支付￥200 预约了咨询师 张媛"},{"label":"用户 中** 支付￥1500 预约了咨询师 金新新"},{"label":"用户 夏** 支付￥200 预约了咨询师 邢宇星"},{"label":"用户 瑾** 支付￥300 预约了咨询师 李秀枫"},{"label":"用户 崔** 购买咨询师 陈琳 电话倾诉25分钟"},{"label":"用户 八** 购买咨询师 金新新 电话倾诉25分钟"},{"label":"用户 瑾** 购买咨询师 李秀枫 电话倾诉25分钟"},{"label":"用户 开** 购买咨询师 张媛 电话倾诉25分钟"},{"label":"用户 茶** 购买咨询师 杨艳 电话倾诉25分钟"}]
     */

    private String errorCode;
    private String errorStr;
    private String resultCount;
    private String score;
    private String balance;
    private List<LabelResultsEntity> results;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorStr() {
        return errorStr;
    }

    public void setErrorStr(String errorStr) {
        this.errorStr = errorStr;
    }

    public String getResultCount() {
        return resultCount;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public List<LabelResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<LabelResultsEntity> results) {
        this.results = results;
    }
}
