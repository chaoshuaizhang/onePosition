package cn.shopin.oneposition.entity.movie;

import cn.shopin.oneposition.entity.BaseEntity;

/**
 * Created by zcs on 2016/12/16.
 */
public class SubTypeEntity extends BaseEntity {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
