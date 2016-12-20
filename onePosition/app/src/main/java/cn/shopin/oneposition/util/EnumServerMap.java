package cn.shopin.oneposition.util;

import cn.shopin.oneposition.constants.Cans;

/**
 * Created by zcs on 2016/12/18.
 *
 * @desc 因为server地址不同，根据tag获得对应的url
 */
public enum EnumServerMap {

    movie(Cans.TAG_MOVIE, "http://moviewapp.dazui.com"),
    heart(Cans.TAG_HEART, ""),
    live(Cans.TAG_LIVE, ""),
    ocean(Cans.TAG_OCEAN, ""),
    balloon(Cans.TAG_BALLOON, "");

    private int tag;
    private String baseUrl;

    private EnumServerMap(int tag, String baseUrl) {
        this.tag = tag;
        this.baseUrl = baseUrl;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private static EnumServerMap getEnumByTag(int tag) {
        for (EnumServerMap enumServerMap : EnumServerMap.values()) {
            if (enumServerMap.getTag() == tag) {
                return enumServerMap;
            }
        }
        return null;
    }

    public static String getBaseUrlByTag(int tag) {
        EnumServerMap serverMap = getEnumByTag(tag);
        if (serverMap != null) {
            return serverMap.getBaseUrl();
        }
        return "www.changePosition.com";
    }

}
