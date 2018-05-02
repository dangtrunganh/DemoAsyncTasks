package com.dt.anh.demointent;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by trunganh on 5/2/18.
 */

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity contextParent;

    public MyAsyncTask(Activity contextParent) {
        this.contextParent = contextParent;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Phương thức này sẽ được chạy đầu tiên khi AsyncTask này được gọi
        //Ở đây, ta sẽ thông báo quá trình load bắt đầu "Start"
        Toast.makeText(contextParent, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Phương thức này được thực hiện tiếp ngay sau phương thức onPreExecute()
        //Phương thức này thực hiện các tác vụ chạy ngầm
        //Không vẽ giao diện trong phương thức này
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100);
            //Khi gọi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        Log.d("A", "doInBackground: ");
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //Hàm này thực hiện update giao diện khi có dữ liệu từ hàm doInBackground gửi xuống
        super.onProgressUpdate(values);
        //Thông qua context parent để lấy được control trong MainActivity
        ProgressBar progressBar = (ProgressBar) contextParent.findViewById(R.id.prb_demo);
        //Vì publishProgress chỉ truyền 1 đối số
        //Nên mảng values chỉ có 1 phần tử
        int number = values[0];
        //Tăng giá trị của ProgressBar lên
        progressBar.setProgress(number);

        //Đồng thời hiện giá trị là % lên TextView
        TextView textView = (TextView) contextParent.findViewById(R.id.tv_status);
        textView.setText(number + "%");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Hàm này được thực hiện khi tiến trình kết thúc
        //ở đây, ta thông báo là "Finished" để người dùng biết
        Toast.makeText(contextParent, "Done, Finished!", Toast.LENGTH_SHORT).show();
    }
}
