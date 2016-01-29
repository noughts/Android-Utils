package jp.noughts.utilsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import jp.noughts.utils.Logging;
import jp.noughts.utils.Task;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logging.enabled = true;
        Logging.info("hoge");

    }
}
