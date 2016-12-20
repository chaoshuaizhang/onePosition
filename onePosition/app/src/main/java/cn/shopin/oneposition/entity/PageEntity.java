package cn.shopin.oneposition.entity;

import java.util.List;

/**
 * Created by zcs on 2016/12/16.
 *
 * @desc
 */
public class PageEntity<T extends BaseEntity> {
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
