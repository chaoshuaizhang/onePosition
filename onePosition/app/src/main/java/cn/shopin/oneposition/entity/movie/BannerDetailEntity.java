package cn.shopin.oneposition.entity.movie;

/**
 * Created by zcs on 2016/12/16.
 *
 * @desc
 */
public class BannerDetailEntity {
    /**
     * id : 19160
     * title : 沉浸式魔幻体验
     * engname : null
     * summary : 张艺谋导演谈杜比视界版[长城]
     * createtime : /Date(1481875552000)/
     * location : 华语 好莱坞
     * pic : /Images/201612/20161216142341467.jpg
     * pics :
     * istopic : false
     * type : 12
     * subtype : {"id":15,"name":"影讯"}
     * tag : 长城
     * category : 1
     * commentcount : 0
     * sharecount : 0
     * prize : 0
     * wantwatchnums : 0
     * unwantwatchnums : 0
     * picsCount : 0
     */

    private int id;
    private String title;
    private String engname;
    private String summary;
    private String createtime;
    private String location;
    private String pic;
    private String pics;
    private boolean istopic;
    private int type;
    private String tag;
    private int category;
    private int commentcount;
    private int sharecount;
    private float prize;
    private int wantwatchnums;
    private int unwantwatchnums;
    private int picsCount;

    /**
     * id : 15
     * name : 影讯
     */
    private SubTypeEntity subtype;

    public SubTypeEntity getSubtype() {
        return subtype;
    }

    public void setSubtype(SubTypeEntity subtype) {
        this.subtype = subtype;
    }

    public boolean istopic() {

        return istopic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public boolean isIstopic() {
        return istopic;
    }

    public void setIstopic(boolean istopic) {
        this.istopic = istopic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public int getSharecount() {
        return sharecount;
    }

    public void setSharecount(int sharecount) {
        this.sharecount = sharecount;
    }

    public float getPrize() {
        return prize;
    }

    public void setPrize(float prize) {
        this.prize = prize;
    }

    public int getWantwatchnums() {
        return wantwatchnums;
    }

    public void setWantwatchnums(int wantwatchnums) {
        this.wantwatchnums = wantwatchnums;
    }

    public int getUnwantwatchnums() {
        return unwantwatchnums;
    }

    public void setUnwantwatchnums(int unwantwatchnums) {
        this.unwantwatchnums = unwantwatchnums;
    }

    public int getPicsCount() {
        return picsCount;
    }

    public void setPicsCount(int picsCount) {
        this.picsCount = picsCount;
    }

}
