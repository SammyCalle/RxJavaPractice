package com.dms.rxlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Rx02CompositeDisActivity extends AppCompatActivity {

    private DisposableObserver<String> numeroObserver;
    private DisposableObserver<String> numeroLetraObserver;

    private Observable<String> numeroObservable;
    private Observable<String> numeroLetraObservable;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx02_composite_dis);

        compositeDisposable = new CompositeDisposable();

        numeroObservable = Observable.just("1","2","3","4","5","6","7","8","9","10");

        numeroLetraObservable = Observable.just("uno","dos","tres","cuatro","cinco","seis");

        numeroObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d("TAG1", "onNextNumero: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG1", "ErrorNumero:");

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "onCompleteNumero: ");

            }
        };

        numeroLetraObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d("TAG1", "onNextLetra: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG1", "onErrorLetra: ");
            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "onCompleteLetra: ");

            }
        };
        /**
         * La principal diferencia radica en que cada observer requiere un disposable
         * En cambio para varios Observers se puede utilizar un solo compositeDisposable*/
        compositeDisposable.add(
                numeroObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(numeroObserver));

        compositeDisposable.add(
                numeroLetraObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(numeroLetraObserver));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
