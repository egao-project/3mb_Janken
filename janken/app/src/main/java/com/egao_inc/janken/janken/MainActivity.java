package com.egao_inc.janken.janken;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

import java.util.Random;

public class MainActivity extends Activity implements OnClickListener {
    private Button otherGuBtn;
    private Button otherChokiBtn;
    private Button otherPaBtn;
    private Button myGuBtn;
    private Button myChokiBtn;
    private Button myPaBtn;
    private Button retryBtn;
    private TextView message;
    private TextView result;
    private static final int GU = 0;
    private static final int CHOKI = 1;
    private static final int PA = 2;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 自分の手
        myGuBtn = (Button) findViewById(R.id.my_gu);
        myChokiBtn = (Button) findViewById(R.id.my_choki);
        myPaBtn = (Button) findViewById(R.id.my_pa);
        // 相手の手
        otherGuBtn = (Button) findViewById(R.id.other_gu);
        otherChokiBtn = (Button) findViewById(R.id.other_choki);
        otherPaBtn = (Button) findViewById(R.id.other_pa);
        // 自分の手にイベントリスナーをセット
        myGuBtn.setOnClickListener(this);
        myChokiBtn.setOnClickListener(this);
        myPaBtn.setOnClickListener(this);

        result = (TextView) findViewById(R.id.result);
        message = (TextView) findViewById(R.id.message);
        // リトライボタン
        retryBtn = (Button) findViewById(R.id.retry);
        retryBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                initView();
            }
        });
        // ビューの初期化
        initView();
    }

    public void initView() {
        message.setText("じゃんけん・・・");
        result.setText(null);
        // 自分の手を利用可能にする
        myGuBtn.setEnabled(true);
        myChokiBtn.setEnabled(true);
        myPaBtn.setEnabled(true);
        myGuBtn.setClickable(true);
        myChokiBtn.setClickable(true);
        myPaBtn.setClickable(true);
        // 相手の手をクリックできないようにする
        otherGuBtn.setEnabled(true);
        otherChokiBtn.setEnabled(true);
        otherPaBtn.setEnabled(true);
        otherGuBtn.setClickable(false);
        otherChokiBtn.setClickable(false);
        otherPaBtn.setClickable(false);
        // リトライボタンを非表示にする
        retryBtn.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        // メッセージラベルの変更
        message.setText("じゃんけん・・・ぽん");
        // 自分の手をクリックできないようにする
        myGuBtn.setEnabled(true);
        myChokiBtn.setEnabled(true);
        myPaBtn.setEnabled(true);
        myGuBtn.setClickable(false);
        myChokiBtn.setClickable(false);
        myPaBtn.setClickable(false);
        // リトライボタンを表示する
        retryBtn.setVisibility(View.VISIBLE);
        // 自分の手の表示状態を変更
        myImage(v.getId());
        // 相手の手を求め、表示状態を変更
        int other = getResult();
        otherImage(other);

        int id = v.getId();
        switch (other) {
            case GU:
                switch (id) {
                    case R.id.my_gu:
                        aiko();
                        break;
                    case R.id.my_choki:
                        loose();
                        break;
                    case R.id.my_pa:
                        win();
                        break;
                }
                break;
            case CHOKI:
                switch (id) {
                    case R.id.my_gu:
                        win();
                        break;
                    case R.id.my_choki:
                        aiko();
                        break;
                    case R.id.my_pa:
                        loose();
                        break;
                }
                break;
            case PA:
                switch (id) {
                    case R.id.my_gu:
                        loose();
                        break;
                    case R.id.my_choki:
                        win();
                        break;
                    case R.id.my_pa:
                        aiko();
                        break;
                }
                break;
        }
    }

    // 勝った場合の処理
    public void win() {
        result.setText("あなたの勝ち");
    }

    // 負けた場合の処理
    public void loose() {
        result.setText("あなたの負け");
    }

    // あいこの場合の処理
    public void aiko() {
        myGuBtn.setEnabled(true);
        myChokiBtn.setEnabled(true);
        myPaBtn.setEnabled(true);
        myGuBtn.setClickable(true);
        myChokiBtn.setClickable(true);
        myPaBtn.setClickable(true);
        result.setText("あいこで・・・");
        retryBtn.setVisibility(View.GONE);
    }

    // 相手の手を決定
    public int getResult() {
        long seed = System.currentTimeMillis();
        Random rnd = new Random(seed);
        return rnd.nextInt(3);
    }

    public void myImage(int id) {
        switch (id) {
            case R.id.my_gu:
                myGuBtn.setEnabled(true);
                myChokiBtn.setEnabled(false);
                myPaBtn.setEnabled(false);
                break;
            case R.id.my_choki:
                myGuBtn.setEnabled(false);
                myChokiBtn.setEnabled(true);
                myPaBtn.setEnabled(false);
                break;
            case R.id.my_pa:
                myGuBtn.setEnabled(false);
                myChokiBtn.setEnabled(false);
                myPaBtn.setEnabled(true);
                break;
        }
    }

    public void otherImage(int other) {
        switch (other) {
            case GU:
                otherGuBtn.setEnabled(true);
                otherChokiBtn.setEnabled(false);
                otherPaBtn.setEnabled(false);
                break;
            case CHOKI:
                otherGuBtn.setEnabled(false);
                otherChokiBtn.setEnabled(true);
                otherPaBtn.setEnabled(false);
                break;
            case PA:
                otherGuBtn.setEnabled(false);
                otherChokiBtn.setEnabled(false);
                otherPaBtn.setEnabled(true);
                break;
        }
    }
}