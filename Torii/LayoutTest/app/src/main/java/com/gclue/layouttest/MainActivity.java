package com.gclue.layouttest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adapterの作成
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        // 値を追加
        mAdapter.add("1行目");
        mAdapter.add("2行目");
        mAdapter.add("3行目");
        mAdapter.add("4行目");
        mAdapter.add("5行目");
        mAdapter.add("6行目");
        mAdapter.add("7行目");
        mAdapter.add("8行目");
        mAdapter.add("9行目");
        mAdapter.add("10行目");
        mAdapter.add("11行目");
        mAdapter.add("12行目");

        // ListViewの取り込み
        mListView = (ListView)findViewById(R.id.listView1);

        // アダプターをセット
        mListView.setAdapter(mAdapter);

        // クリックされた時のイベントを追加
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectList = (String) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "pos=" + position + ":" + selectList, Toast.LENGTH_SHORT).show();
            }
        });
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
}
