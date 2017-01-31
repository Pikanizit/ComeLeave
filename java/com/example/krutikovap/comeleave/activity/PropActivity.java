package com.example.krutikovap.comeleave.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.krutikovap.comeleave.DataFile;
import com.example.krutikovap.comeleave.R;
import com.example.krutikovap.comeleave.data.MounthCL;
import com.example.krutikovap.comeleave.data.MounthStruct;

import java.io.IOException;
import java.util.ArrayList;


public class PropActivity extends AppCompatActivity {

    final String TAG = "CL";

    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);

        fileName = MounthStruct.get_currFileName();

        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Log.i(TAG,"Load file");

        GridView gridView = (GridView) findViewById(R.id.gridView);

        DataFile dataFile = new DataFile();

        ArrayList<String> adap = dataFile.getAdapter(fileName,this);             //подгружать файлы с расписанием необходимого посещения

        ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adap);      //отображение данных в таблице

        gridView.setAdapter(adapter);


        Log.i(TAG,"Show data in table");
    }


    /**
     * Вернуться в activity_correct
     * @param view  отображение
     */
    public void onCorrectClick(View view){
        Intent intent = new Intent(this,CorrectActivity.class);

        Log.i(TAG,"Go to activity_correct");

        startActivity(intent);
    }

    /**
     * Вернуться в main_correct
     * @param view отображение
     */
    public void onBack(View view){
        Intent intent = new Intent(this,MainActivity.class);

        Log.i(TAG,"Go to main_correct");

        startActivity(intent);
    }

    /**
     * Активити со статистикой
     * @param view отображение
     */
    public void onStatic(View view) {
        /*String fullpath, foldername, filename;
        foldername = "temp/myFolder";
        filename = "myFile.txt";

        //Сохранение файла на External Storage:
        fullpath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/" + foldername
                + "/" + filename;


        if (isExternalStorageWritable())
        {
            DataFile dataFile = new DataFile();

            MounthCL mounthCl;

            try {
                mounthCl = dataFile.ReadF(fileName, this);
            } catch (IOException e) {
                mounthCl = dataFile.getDefaultMounthCL(fileName,this);
            }

            String fileContent="";
            for (String str:mounthCl.get_writeData()) {
                fileContent +=str;
            }

            DataFile.SaveFile(fullpath, fileContent);
        }*/

    }

    /*
    Проверяет, доступно ли external storage для чтения и записи
    */
    public boolean isExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;
    }
}
