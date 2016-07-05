package com.example.administrator.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.button)
    Button button;
    private String TAG = "MainActivity";

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

        Subscriber<String> subscriber = new Subscriber<String>() {
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
            public void onStart() {
                super.onStart();
                Log.i(TAG, "onStart");
            }

        };

    }

    private void initView() {
        button.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("zhang sna");
                        subscriber.onNext("zhang sna2");
                        subscriber.onNext("zhang sna3");
                        subscriber.onCompleted();
                    }
                });
                break;
        }
    }
}
