package com.example.krutikovap.comeleave.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

//import com.example.krutikovap.comeleave.ComeLeaveLocal;
import com.example.krutikovap.comeleave.DataFile;
import com.example.krutikovap.comeleave.R;
import com.example.krutikovap.comeleave.data.MounthCL;
import com.example.krutikovap.comeleave.data.MounthStruct;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;




public class MainActivity extends Activity {
//public class MainActivity extends AppCompatActivity {
    private int _timeMM;
    private int _timeHH;
    private int _timeDD;

    private TextView dataStr;
    private TextView proc;

    private TextView _wuw;
    private TextView _time;

    private MounthCL mounthCl;

    private final String TAG = "CL";

    private String fileName;

    /*private ComeLeaveLocal comeLeaveLocal;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _wuw =(TextView) findViewById(R.id.textViewWuW);
        _time =(TextView) findViewById(R.id.textViewTime);

        dataStr = (TextView) findViewById(R.id.txtViewData);

        proc = (TextView) findViewById(R.id.txtViewProc);

        this.fileName = MounthStruct.get_currFileName();

        viewStatic();
 /*
        comeLeaveLocal = new ComeLeaveLocal(this);

        //TODO: Взять определение координат через какое -то время,
        // если координаты от констант изменились, то или приход или уход. и вызываются Кнопки приход, уход

        ComeLeaveLocal comeLeaveLocal = new ComeLeaveLocal(this);


        comeLeaveLocal.onLocationChanged();

        double longit = comeLeaveLocal.getLongitude();

        double latit = comeLeaveLocal.getLatitude();

        //TODO:Совпадает с заданными значениями(шгирота , долгота полюса)
        if(true){
            //TODO:Алгоритм прихода- ухода.
            //Если нет прихода - то приход, иначе - уход
            if (CiclCL.getIsCome()){
                //TODO: Записать приход
            }
            else{
                //TODO:записать уход
            }
        }
*/


    }

    private boolean _flagCome = false;



    public void onComeClick(View view)  {

        _flagCome = false;

        //Получение системного времени
        get_systemData();

        //Если приход на сегодня есть, то выдавать предупреждение и возможность выбора
        AlertDialog.Builder ad = isComeLeave("Приход");

        //***********************

        Log.i(TAG,"Load file");

        DataFile dataFile = new DataFile();

        try {
            this.mounthCl = dataFile.ReadF(this.fileName, this);
        } catch (IOException e) {
            this.mounthCl = dataFile.getDefaultMounthCL(this.fileName,this);
        }

        int currCome = this.mounthCl.get_currComeTime(_timeDD);

        if (currCome >0) {
            ad.show();
        }else{
            this.mounthCl.set_come(_timeDD,_timeHH*60+_timeMM);

            dataFile.WriteF(this.fileName,this,this.mounthCl.get_writeData());
        }

        if (_flagCome)  {
            this.mounthCl.set_come(_timeDD,_timeHH*60+_timeMM);

            dataFile.WriteF(this.fileName,this,this.mounthCl.get_writeData());
        }

        Log.i(TAG,"Go to activity_statictic");


        Log.i(TAG,"Correct file");

        Log.i(TAG,"Save file");

        viewStatic(this.mounthCl) ;
    }

    @NonNull
    private AlertDialog.Builder isComeLeave(String comeLeave) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle(comeLeave + " есть");
        ad.setMessage("Сегодня есть "+comeLeave+", перезаписать?");
        String buttonOk = "Да";
        String buttonNo = "Нет";

        ad.setPositiveButton(buttonOk, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _flagCome = true;
                //_mounthCl.set_come(_timeDD,_timeHH*60+_timeMM);
            }
        });

        ad.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        ad.setCancelable(false);
        return ad;
    }

    /**
     * Получение системного времени
     */
    private void get_systemData() {
        SimpleDateFormat time = new SimpleDateFormat("HH", Locale.US);
        String result = time.format(new Date());
        _timeHH = Integer.parseInt(result);

        time = new SimpleDateFormat("mm", Locale.US);
        result = time.format(new Date());
        _timeMM = Integer.parseInt(result);

        time = new SimpleDateFormat("dd", Locale.US);
        result = time.format(new Date());
        _timeDD = Integer.parseInt(result);
    }

    public void onLeaveClick(View view){
        get_systemData();                                                                              //Получение системного времени

        _flagCome = false;
        //Если уход на сегодня есть, то выдавать предупреждение и возможность выбора
        AlertDialog.Builder ad = isComeLeave("Уход");

        Log.i(TAG,"Go to activity_statictic");



        //intent.putExtra("flag","Leave");
        //intent.putExtra("timeMM",_timeMM);
        //intent.putExtra("timeHH",_timeHH);

        Log.i(TAG,"Load file");


        //try{
        //    MounthCL mounthCL = dataFile.ReadF(getString(R.string.MAY), this);

        DataFile dataFile = new DataFile();

        try {
            this.mounthCl = dataFile.ReadF(this.fileName, this);
        } catch (IOException e) {
            this.mounthCl = dataFile.getDefaultMounthCL(this.fileName,this);
        }

        this.mounthCl.set_leave(_timeDD,_timeHH*60+_timeMM);

        dataFile.WriteF(this.fileName,this,this.mounthCl.get_writeData());
       // }
        //catch (IOException e){
        //    dataFile.getDefaultMounthCL("may.txt",this);
        //}

        int currLeave = this.mounthCl.get_currLeaveTime(_timeDD);

        if (currLeave >0) {
            ad.show();
        } else{
            this.mounthCl.set_leave(_timeDD,_timeHH*60+_timeMM);

            dataFile.WriteF(this.fileName,this,this.mounthCl.get_writeData());
        }

        if (_flagCome)  {
            this.mounthCl.set_leave(_timeDD,_timeHH*60+_timeMM);

            dataFile.WriteF(this.fileName,this,this.mounthCl.get_writeData());
        }


        Log.i(TAG,"Correct file");

        Log.i(TAG,"Save file");

        viewStatic(this.mounthCl) ;

        //startActivity(intent);
    }

    public void onPropClick(View view){
        Intent intent = new Intent(this,PropActivity.class);                                        //Переход в activity_properties

        Log.i(TAG,"Go to activity_properties");

        startActivity(intent);
    }

    private void viewStatic() {
        DataFile dataFile = new DataFile();

        try {
            this.mounthCl = dataFile.ReadF(this.fileName,this);
        } catch (IOException e) {
            this.mounthCl = dataFile.getDefaultMounthCL(this.fileName,this);
        }

        viewStatic(this.mounthCl) ;
    }

    private void viewStatic(MounthCL mounthCL){
        String time = mounthCL.get_allWuw();                                                        //перевычсиление данных по приходу-уходу

        dataStr.setText(MounthStruct.get_currBigDate());

        proc.setText(mounthCL.get_EndForMounth());

        Log.i(TAG,"Calculate data");

        _time.setTextColor(mounthCL.get_color());

        if(time.equalsIgnoreCase("0:0")){
            _time.setText("Нет ухода");
        }
        else{
            _time.setText(time);                                                                    //показать информацию : переработка/недоработка и +- времени
        }

        String wuw = "Недоработка";

        if (mounthCL.get_signWuW()){
            wuw = "Переработка";
        }

        _wuw.setText(wuw);

        Log.i(TAG,"Show info");
    }

   /* @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        comeLeaveLocal.getLocationManager().removeUpdates(comeLeaveLocal);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        comeLeaveLocal.getLocationManager().requestLocationUpdates(comeLeaveLocal.getProvider(), 400, 1, comeLeaveLocal);
    }*/
}
