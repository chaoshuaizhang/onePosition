package cn.shopin.oneposition.util.rxBus;


import cn.shopin.oneposition.util.RxUtil;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by zcs on 2017/1/19.
 *
 * @desc 暂时使用非粘性的事件发送机制
 */

public class RxBus {

    private final FlowableProcessor<Object> bus;

    /**
     * 四种subject的区别和适用场景
     * PublishSubject只会给在订阅者订阅的时间点之后的数据发送给观察者
     * BehaviorSubject在订阅者订阅时，会发送其最近发送的数据（如果此时还没有收到任何数据，它会发送一个默认值）
     * ReplaySubject在订阅者订阅时，会发送所有的数据给订阅者，无论它们是何时订阅的
     * AsyncSubject只在原Observable事件序列完成后，发送最后一个数据，后续如果还有订阅者继续订阅该Subject, 则可以直接接收到最后一个值
     */

    private RxBus() {
//        this.bus = PublishSubject.create().toSerialized();//无被压处理
        this.bus = PublishProcessor.create().toSerialized();//有被压处理
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

    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

    // 封装默认订阅
    public <T> Disposable toDefaultFlowable(Class<T> eventType, Consumer<T> action) {
        return bus.ofType(eventType).compose(RxUtil.<T>rxSchedulerHelper()).subscribe(action);
    }
}
