package cn.shopin.oneposition.util.rxBus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by zcs on 2017/1/19.
 *
 * @desc 暂时使用非粘性的事件发送机制
 */

public class RxBus {

    private final Subject<Object, Object> bus;

    /**
     * 四种subject的区别和适用场景
     * PublishSubject只会给在订阅者订阅的时间点之后的数据发送给观察者
     * BehaviorSubject在订阅者订阅时，会发送其最近发送的数据（如果此时还没有收到任何数据，它会发送一个默认值）
     * ReplaySubject在订阅者订阅时，会发送所有的数据给订阅者，无论它们是何时订阅的
     * AsyncSubject只在原Observable事件序列完成后，发送最后一个数据，后续如果还有订阅者继续订阅该Subject, 则可以直接接收到最后一个值
     */

    private RxBus() {
        this.bus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getRxBusInstance() {
        return RxInstanceHolder.rxBusInstance;
    }

    private static class RxInstanceHolder {
        public static volatile RxBus rxBusInstance = new RxBus();
    }

    /**
     * 发送一个事件
     *
     * @param obj
     */
    public void post(Object obj) {
        getRxBusInstance().bus.onNext(obj);
    }

    public  <T> Observable<T> tObservable(Class<T> eventType) {
        return getRxBusInstance().bus.ofType(eventType);
    }
}
