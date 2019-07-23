package com.zcs.test.observer;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.internal.schedulers.NewThreadScheduler;

public class ObserverTest {


    public static void main(String[] args) throws InterruptedException {
        Observable<String> observable = getObserver();
        observable.flatMap(s->{
            System.out.println(s);
            return getObserverInfo();
        }).flatMap(s->{
            return onComplete();
        }).observeOn(new IoScheduler())
//                .subscribeOn(new NewThreadScheduler())
                .subscribe(s -> {
            System.out.println("subscribe");
            System.out.println(s);
        }, f -> {

        }, () -> {
            System.out.println("in onComplete");
        });
        System.out.println("end is end ");
        Thread.sleep(5000);
//        basicRxjava2();
    }
//    private Prm
    private static Observable<String> getObserver(){
        return Observable.create(s->{
//            s.onComplete();
            System.out.println("13456");
            Thread.sleep(1000);
            s.onNext("getObserver onNext1");
            return;
//            s.onNext("getObserver onNext2");
//            s.onNext("getObserver onNext3");
//            s.onNext("getObserver onNext4");
//            s.onNext("getObserver onNext5");
        });
    }
    private static Observable<String> getObserverInfo(){
        return Observable.create(s->{
            Thread.sleep(1000);
            s.onNext("getObserverInfo onNext");
//            System.out.println("print getObserverInfo");
        });
    }

    private static Observable<String> onComplete(){
        return Observable.create(s->{
            System.out.println("start onComplete");
            s.onComplete();
        });
    }
    private static void basicRxjava2() {
        Observable mObservable = Observable.create((ObservableOnSubscribe) e -> {
            e.onNext("1");
            e.onNext("2");
            e.onNext("3");
            e.onNext("4");
            e.onComplete();
        });

        Observer mObserver = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("\nonSubcribe: d=" + d);
            }

            @Override
            public void onNext(Object s) {
                System.out.println("onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        mObservable.subscribe(mObserver);
    }
}
