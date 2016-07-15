package com.example.administrator.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.rxjavademo.util.HttpMethods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

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
                //处理耗时操作，一般发生在工作线程由 subscribeOn(Schedulers.io()) 来决定工作线程
                Log.i(TAG,"obserable threadName: "+ Thread.currentThread().getName());
                subscriber.onNext("zhang sna");
                subscriber.onNext("zhang sna2");
                subscriber.onNext("zhang sna3");
                subscriber.onCompleted();//kjkljljl
            }
        });
        String[] names = {"s1","s2"};
//        observable = Observable.from(names);
//        observable = Observable.just(1,"s",3,0.1f);


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
                loginTest();
                /*observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(subscriber);*/
                //或者
                Action1<String> nextAction = new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //用于处理回调操作一般发生在主线程，用于界面显示 由此方法决定工作线程 observeOn(AndroidSchedulers.mainThread())
                        String name = Thread.currentThread().getName();
                        Log.i(TAG, "nextAction: " + s+",currentThread: "+ name);
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
//                observable.subscribe(nextAction);//订阅，将nextAction作为Next事件

//                observable.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(nextAction,errorAction,completedAction);//订阅，将nextAction作为Next事件.....

                break;
        }
    }

    private void loginTest() {
        Subscriber<ResponseBody> subscriber = new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                Log.i(TAG,"throwable: "+ throwable.toString());
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    Log.i(TAG,"success : "+ responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i(TAG,"analysis fail");
                }
            }
        };
        //        HttpMethods.getInstance().upLoadImge(subscriber,orderBean.getId()+"", DictionaryTool.getToken(this), map);
        Map<String,String> maps = new HashMap<>(2);
        maps.put("phone","18780224529");
        maps.put("password","123456");
        HttpMethods.getInstance().loginWithPwd(subscriber,maps);
    }
}
