package cn.shopin.oneposition.entity.heart;

/**
 * Created by zcs on 2017/2/19.
 *
 * @desc
 */
public class ConsultsResultsEntity {
    /**
     * userid : 7542
     * nickname : 曹华
     * avatar : http://image.xinliji.me/o_1b8qgji1f1lth11jv1in1a33ri8a.png
     * title : 最佳咨询师
     * qualification : 国二
     */

    private String userid;
    private String nickname;
    private String avatar;
    private String title;
    private String qualification;

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getUserid() {
        return userid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getTitle() {
        return title;
    }

    public String getQualification() {
        return qualification;
    }
}
