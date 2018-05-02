package com.dt.anh.demointent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart;
    private AsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        btnStart = (Button) findViewById(R.id.btn_start);

        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                load();
                break;
            default:
                break;
        }
    }

    private void load() {
        //Khởi tạo tiến trình ta đã tạo
        //Truyền Activity chính là MainActivity sang bên tiến trình đó
        asyncTask = new MyAsyncTask(MainActivity.this);
        //Gọi ham execute để kích hoạt tiến trình
        asyncTask.execute();
    }
}
