package mio.kon.sdk.api;


import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by mio on 15-5-21.
 * Subscriber包装类
 */
public abstract class SubscribeWrapper<T> extends Subscriber<T> {

    public static <T> SubscribeWrapper<T> create(final Action1<? super T> action) {
        return new SubscribeWrapper<T> () {
            @Override
            public void onNext(T t) {
                action.call (t);
            }
        };
    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

}
