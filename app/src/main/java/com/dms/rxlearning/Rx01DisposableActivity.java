package com.dms.rxlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Rx01DisposableActivity extends AppCompatActivity {

    private Disposable disposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx01_disposable);

        Observable<String> numerosObservables =
                Observable.just("1","2","3","4","5","6","7","8","9","10");
        Observer<String> numerosObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                Log.d("TAG1", "onSubscribe" + " Hilo: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String numero) {
                //Sera llamado hasta 10 veces cada vez por cada dato que emitra el observable
                Log.d("TAG1", "isDispose: " + disposable.isDisposed());
                Log.d("TAG1", "onNext: Numero: "+ numero + " Hilo: " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG1", "onError" + " Hilo: " + Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "onComplete: Se han emitido todos los datos" + " Hilo: " + Thread.currentThread().getName());
            }
        };
        numerosObservables
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(numerosObserver);
    }

    /**
     * El Manejo de disposable y desecharlos al final del Activity nos ayuda a ahorrar memoria.*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
        Log.d("TAG1", "isDispose: " + disposable.isDisposed());
        Log.d("TAG1", "onDestroy Desechamos la subscription" + " Hilo: " + Thread.currentThread().getName());
    }
}
