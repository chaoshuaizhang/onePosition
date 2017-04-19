package cn.shopin.oneposition.entity.movie;

import cn.shopin.oneposition.entity.BaseEntity;

/**
 * Created by zcs on 2017/4/2.
 */

public class PieceEntity extends BaseEntity {

    /**
     * name : 嫌疑人X的献身
     * events : 42594
     */

    private String name;
    private int events;
    private float ratio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEvents() {
        return events;
    }

    public void setEvents(int events) {
        this.events = events;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
