package com.example.krutikovap.comeleave.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.krutikovap.comeleave.DataFile;
import com.example.krutikovap.comeleave.R;
import com.example.krutikovap.comeleave.data.DayCL;
import com.example.krutikovap.comeleave.data.MounthCL;
import com.example.krutikovap.comeleave.data.MounthStruct;

import java.io.IOException;

public class CorrectActivity extends AppCompatActivity {

    final String TAG = "CL";

    private EditText _comeHH;

    private EditText _comeMM;

    private EditText _leaveHH;

    private EditText _leaveMM;

    private EditText _needMM;

    private EditText _needHH;

    private MounthCL _mounthCL;

    private Spinner _dayNums;

    private int _dayNum;

    private int oldDay;

    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct);
        //_dayNum = (EditText) findViewById(R.id.editTextDataNum);

        fileName = MounthStruct.get_currFileName();

        _dayNums = (Spinner) findViewById(R.id.spinnerDayNums);


        /*DataFile dataFile = new DataFile();

        try {
            _mounthCL = dataFile.ReadF(getString(R.string.MAY_FILE),this);
        } catch (IOException e) {
            _mounthCL = dataFile.getDefaultMounthCL(getString(R.string.MAY_FILE),this);
        }  */

        final MounthCL mounthCL = getMounthCL();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                MounthStruct.get_spinnerMounth(), android.R.layout.simple_spinner_item);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        _dayNums.setAdapter(adapter);

        oldDay=0;

        _dayNums.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                //String[] choose = getResources().getStringArray(R.array.may);

                _dayNum =   mounthCL.get_dayNums(selectedItemPosition) ;

                onDataClick(_dayNum);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        _comeHH = (EditText) findViewById(R.id.editTextComeHH);

        _comeMM = (EditText) findViewById(R.id.editTextComeMM);

        _leaveHH = (EditText) findViewById(R.id.editTextLeaveHH);

        _leaveMM = (EditText) findViewById(R.id.editTextLeaveMM);

        _needMM = (EditText) findViewById(R.id.editTextNeedMM);

        _needHH = (EditText) findViewById(R.id.editTextNeedHH);

    }


    private void onShow() {
        MounthCL mounthCL = getMounthCL();

        DayCL day = mounthCL.get_dayCL(_dayNum);

        if (day == null){
            //Если такого дня нет - вывести Toast
            Toast toast = Toast.makeText(this,"Нет такой даты",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        //Показать данные по введенному дню
        _comeHH.setText(get_HH(day.get_timeCome()));

        _comeMM.setText(get_MM(day.get_timeCome()));

        _leaveHH.setText(get_HH(day.get_timeLeave()));

        _leaveMM.setText(get_MM(day.get_timeLeave()));

        _needHH.setText(get_HH(day.get_timeNeed()));

        _needMM.setText(get_MM(day.get_timeNeed()));
    }

    String get_HH(int time){
        return Integer.toString(time/60);
    }

    String get_MM(int time){
        int hh = time/60;
        return Integer.toString(time - hh*60);
    }

    public void onSave(View view){                                                                  //Сохранить введенные данные
        //Если Данных каких-то нет, то вывести это сообщение
        try{
            int comeHH = Integer.parseInt(_comeHH.getText().toString());
            int comeMM = Integer.parseInt(_comeMM.getText().toString());
            int needHH = Integer.parseInt(_needHH.getText().toString());
            int needMM = Integer.parseInt(_needMM.getText().toString());
            int leaveHH =  Integer.parseInt(_leaveHH.getText().toString());
            int leaveMM =  Integer.parseInt(_leaveMM.getText().toString());

            _mounthCL.set_come(_dayNum,comeHH*60+comeMM);

            _mounthCL.set_need(_dayNum,needHH*60+needMM);

            _mounthCL.set_leave(_dayNum,leaveHH*60+leaveMM);

            DataFile dataFile = new DataFile();

            dataFile.WriteF(fileName,this,_mounthCL.get_writeData());

            onBack(view);
        }
        catch (NumberFormatException e){
            Toast toast = Toast.makeText(getApplicationContext(),"Не все данные введены",Toast.LENGTH_SHORT);

            toast.show();
        }
    }


    /** Переход в activity_properties
     * @param view отображение
     */
    public void onBack(View view){
        Intent intent = new Intent(this,PropActivity.class);

        Log.i(TAG,"Go to activity_properties");

        startActivity(intent);
    }

    private MounthCL getMounthCL()
    {
        if (_mounthCL == null){
            DataFile dataFile = new DataFile();

            try {
                _mounthCL = dataFile.ReadF(fileName,this);
            } catch (IOException e) {
                _mounthCL = dataFile.getDefaultMounthCL(fileName,this);
            }
        }

        return _mounthCL;
    }

    //private boolean isDateNum(int currDateNum){
    //    MounthCL mounthCL = getMounthCL();

     //   return mounthCL.isDateNum(currDateNum);
    //}

    /** Установить данные
     * @param dayNum текущий номер дня
     */
    public void onDataClick(int dayNum){
         MounthCL mounthCL = getMounthCL();

         if (mounthCL.isDateNum(dayNum)){                                                                       // Если есть, то вывести данные

             _comeHH.setEnabled(true);

             _comeMM.setEnabled(true);

             _leaveHH.setEnabled(true);

             _leaveMM.setEnabled(true);

             _needHH.setEnabled(true);

             _needMM.setEnabled(true);

             onShow();
         }
         else{
             Toast toast = Toast.makeText(getApplicationContext(),"Нет такого дня",Toast.LENGTH_SHORT); // Если нет, вывести тоаст
             toast.show();
         }
    }
}
