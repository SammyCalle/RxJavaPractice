package com.dms.rxlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Rx00IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx00_intro);

        Observable<String> numerosObservables =
                Observable.just("1","2","3","4","5","6","7","8","9","10");
        Observer<String> numerosObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG1", "onSubscribe" + " Hilo: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String numero) {
                //Sera llamado hasta 10 veces cada vez por cada dato que emitra el observable
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
                .subscribe(numerosObserver);
    }
}
