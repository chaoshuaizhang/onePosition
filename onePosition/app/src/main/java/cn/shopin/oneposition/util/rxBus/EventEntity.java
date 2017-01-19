package cn.shopin.oneposition.util.rxBus;

/**
 * Created by zcs on 2017/1/19.
 */

public class EventEntity {
    private int tag;
    private Object object;

    public EventEntity() {
    }

    public EventEntity(int tag, Object object) {
        this.tag = tag;
        this.object = object;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
