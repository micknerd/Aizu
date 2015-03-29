package com.gclue.dentaku;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener{

    /**
     * Buttonの配列
     */
    Button mButton[];

    /**
     * Idの配列
     */
    int mId[] = { R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonPlus, R.id.buttonMinus,
            R.id.buttonEqual, R.id.buttonTen, R.id.buttonClear };

    /**
     * キー
     */
    private final int KEY_0 = 0;
    private final int KEY_1 = 1;
    private final int KEY_2 = 2;
    private final int KEY_3 = 3;
    private final int KEY_4 = 4;
    private final int KEY_5 = 5;
    private final int KEY_6 = 6;
    private final int KEY_7 = 7;
    private final int KEY_8 = 8;
    private final int KEY_9 = 9;
    private final int KEY_PLUS = 10;
    private final int KEY_MINUS = 11;
    private final int KEY_EQUAL = 12;
    private final int KEY_TEN = 13;
    private final int KEY_CLEAR = 14;

    /**
     * TextView
     */
    TextView mTextView;

    /**
     * 前の処理
     */
    int beforeStatus = 0;

    /**
     * 合計
     */
    int total = 0;
    int inputvalue = 0;
    int operator = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 表示用TextView
        mTextView = (TextView) findViewById(R.id.display);

        // Button
        mButton = new Button[mId.length];

        // Buttonの取り込みとイベントのはりつけ
        for (int i = 0; i < mId.length; i++) {
            // buttonを取り込む
            mButton[i] = (Button) findViewById(mId[i]);
            // buttonのイベント処理
            mButton[i].setOnClickListener(this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        // 押されたボタンがどのボタンかを判定
        for (int i = 0; i < mId.length; i++) {
            if (view.equals(mButton[i])) {
                // CLEAR
                if (i == KEY_CLEAR) {
                    mTextView.setText("");
                    total = 0;
                    beforeStatus = KEY_CLEAR;
                    operator=-1;
                }
                // PLus
                else if (i == KEY_PLUS) {
                    mTextView.setText("");
                    total = total + inputvalue;
                    beforeStatus = KEY_PLUS;
                    inputvalue=0;
                    operator=KEY_PLUS;
                }
                // Minus
                else if (i == KEY_MINUS) {
                    mTextView.setText("");
                    if (operator==-1) {total = inputvalue;}
                    else {total = total - inputvalue;}
                    beforeStatus = KEY_MINUS;
                    inputvalue=0;
                    operator=KEY_MINUS;
                }

                // EQUAL
                else if (i == KEY_EQUAL) {
                    if (operator==KEY_PLUS) {
                        total = total + inputvalue;
                    }
                    else if (operator == KEY_MINUS) {
                        total = total - inputvalue;
                    }
                    mTextView.setText(String.valueOf(total));
                    beforeStatus = KEY_EQUAL;
                    inputvalue=0;
                    //operator=-1;
                    //total=0;
                }
                // 数字
                else if (i < 10) {
                    String nowValue = mTextView.getText().toString();
                    nowValue = nowValue + i;
                    inputvalue = Integer.parseInt(nowValue);
                    mTextView.setText(nowValue);
                    beforeStatus = i;
                }
                break;
            }
        }
    }
}
