package com.example.administrator.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.button)
    Button button;
    private String TAG = "MainActivity";
    private Observable<String> observable;
    private Subscriber<String> subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDate();
        initView();
    }

    private void initDate() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "e: " + e.toString());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "s: " + s);
            }
        };

        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "e: " + e.toString());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "s: " + s);
            }

            @Override
            public void onStart() {//在subscribe所在的线程执行，做一些初始化操作
                super.onStart();
                Log.i(TAG, "onStart");
            }

        };


        observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("zhang sna");
                subscriber.onNext("zhang sna2");
                subscriber.onNext("zhang sna3");
                subscriber.onCompleted();//kjkljljl
            }
        });


    }

    private void initView() {
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*if (subscriber.isUnsubscribed())
        subscriber.unsubscribe();*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(subscriber);
                //或者
                Action1<String> nextAction = new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, "nextAction: " + s);
                    }
                };
                Action1<Throwable> errorAction = new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i(TAG,"errorAction: "+ throwable.toString());
                    }
                };
                Action0 completedAction = new Action0() {
                    @Override
                    public void call() {
                        Log.i(TAG,"completedAction");
                    }
                };
                observable.subscribe(nextAction);//订阅，将nextAction作为Next事件
                observable.subscribe(nextAction,errorAction,completedAction);//订阅，将nextAction作为Next事件.....
                break;
        }
    }
}
