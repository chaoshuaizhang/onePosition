package cn.shopin.oneposition.entity.movie;

import java.util.List;

/**
 * Created by zcs on 2017/4/19.
 */

public class WelcomeEntity {

    /**
     * groups : [{"cover":"http://img.pconline.com.cn/images/upload/upc/tx/onlinephotolib/1704/11/c1/42745043_1491904732303.jpg","id":17063,"imgUrls":["http://imgrt.pconline.com.cn/images/upload/upc/tx/onlinephotolib/1704/11/c0/spcgroup/width_300/42744070_1491903927038.jpg"],"name":"Razer Blade灵刃:它可能是颜值最高游戏本","photoCount":20,"styleType":1},{"cover":"http://img.pconline.com.cn/images/upload/upc/tx/onlinephotolib/1704/14/c0/43190975_1492142895429.jpg","id":17075,"imgUrls":["http://imgrt.pconline.com.cn/images/upload/upc/tx/onlinephotolib/1704/14/c0/spcgroup/width_300/43192452_1492143926875.jpg"],"name":"性能强劲！华硕GX701VI劲爆图赏","photoCount":20,"styleType":1}]
     * pageNo : 1
     * pageSize : 2
     * total : 1107
     */

    private int pageNo;
    private int pageSize;
    private int total;
    private List<GroupsBean> groups;

    public int getPageNo() { return pageNo;}

    public void setPageNo(int pageNo) { this.pageNo = pageNo;}

    public int getPageSize() { return pageSize;}

    public void setPageSize(int pageSize) { this.pageSize = pageSize;}

    public int getTotal() { return total;}

    public void setTotal(int total) { this.total = total;}

    public List<GroupsBean> getGroups() { return groups;}

    public void setGroups(List<GroupsBean> groups) { this.groups = groups;}

    public static class GroupsBean {
        /**
         * cover : http://img.pconline.com.cn/images/upload/upc/tx/onlinephotolib/1704/11/c1/42745043_1491904732303.jpg
         * id : 17063
         * imgUrls : ["http://imgrt.pconline.com.cn/images/upload/upc/tx/onlinephotolib/1704/11/c0/spcgroup/width_300/42744070_1491903927038.jpg"]
         * name : Razer Blade灵刃:它可能是颜值最高游戏本
         * photoCount : 20
         * styleType : 1
         */

        private String cover;
        private int id;
        private String name;
        private int photoCount;
        private int styleType;
        private List<String> imgUrls;

        public String getCover() { return cover;}

        public void setCover(String cover) { this.cover = cover;}

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public int getPhotoCount() { return photoCount;}

        public void setPhotoCount(int photoCount) { this.photoCount = photoCount;}

        public int getStyleType() { return styleType;}

        public void setStyleType(int styleType) { this.styleType = styleType;}

        public List<String> getImgUrls() { return imgUrls;}

        public void setImgUrls(List<String> imgUrls) { this.imgUrls = imgUrls;}
    }
}
