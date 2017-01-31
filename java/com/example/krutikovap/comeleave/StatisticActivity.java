package com.example.krutikovap.comeleave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.TextView;

import com.example.krutikovap.comeleave.activity.MainActivity;

/**
 * Created by krutikovap on 11.04.2016.
 */
public class StatisticActivity extends Activity{
    TextView _wuw;
    TextView _time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_static);
        _wuw =(TextView) findViewById(R.id.textViewWuW);
        _time =(TextView) findViewById(R.id.textViewTime);
        //TODO:сохранение данных
        //TODO:перевычсиление данных по приходу-уходу
        //TODO: сохранение данных
        //TODO:показать информацию : переработка/недоработка и +- времени
    }

    public void onBackMClick(View view){
        Intent intent = new Intent(this, MainActivity.class);                                        //Вернуться в activity_main
        startActivity(intent);
    }

    public void onExitMClick(View view){
        //TODO:Выйти из программы
    }
}
