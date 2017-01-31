package com.example.krutikovap.comeleave.data;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Данные для рабочего месяца
 */
public class MounthCL {
    /**
     * Набор рабочих дней
     */
    private DayCL _dayCL[];

    /**
     * Количество рабочих дней
     */
    private int _countDay = -13;

    /**
     * Общее время , которое надо отработать в минутах
     */
    private int _allTime = -13;

    /**
     * Номер текущего дня
     */
    private int _currentNumberDay = -13;

    /**
     * Массив дат текущего месяца
     */
    private int[] currNums;

    /**
     * Количесво минут, когда критическая недоработка, 7 часов
     */
    private int COUNT_BAD = -7 * 60;
    /**
     * Количество минут, когда достаточная пеработка, 9 часов
     */
    private int COUNT_GOOD = 9 * 60;

    /**
     *
     * @param countDay Количество дней в месяц
     * @param allTime  Общее количество рабочего времени
     */
    public MounthCL(int countDay,int allTime){
        _dayCL = new DayCL[countDay];

        _allTime = allTime;

        _countDay = countDay;

        currNums = MounthStruct.get_currMounth();
    }

    /**
     * Выбор текущего дня по порядковому номеру рабочего дня
     * @param numberDay Число месяца
     */
    public void set_dayCL(int numberDay){
        _currentNumberDay = numberDay;

        _dayCL[_currentNumberDay] = new DayCL(_allTime/_countDay,currNums[numberDay]);
    }

    /**
     * Получение текущего рабочего дня
     * @param currDateNum Текущая дата
     * @return Данные для рабочего дня
     */
    public DayCL get_dayCL(int currDateNum) {
        int data = 0;
        while(_dayCL[data].get_currDateNum() != currDateNum){
            data++;
        }

        if (data >= _dayCL.length)
            return null;
        else
            return _dayCL[data];
    }

    /** Получение времени прихода сегоднящнего дня
     * @param currDateNum сегодняшний номер
     * @return время прихода в минутах
     */
    public int get_currComeTime(int currDateNum){
        DayCL dayCL = get_dayCL(currDateNum);

        if(dayCL != null){
            return dayCL.get_timeCome();
        }

        return -13;
    }

    /**
     * Получение текущего времени ухода
     * @param currDateNum Текущая дата
     * @return  Время ухода в минутах
     */
    public int get_currLeaveTime(int currDateNum){
        DayCL dayCL = get_dayCL(currDateNum);

        if(dayCL != null){
            return dayCL.get_timeLeave();
        }

        return -13;
    }

    /**
     * Если ли текущая дата в месяце
     * @param currDateNum  текуща дата
     * @return Есть или нет
     */
    public boolean isDateNum(int currDateNum){
        return get_dayCL(currDateNum) != null;
    }

    /**
     * Установка времени прихода для текущего времени
     * @param numData Дата в месяце
     * @param time Время в минутах
     */
    public void set_come(int numData, int time){

        int data = 0;

        while(_dayCL[data].get_currDateNum() != numData){
            data++;
        }

        _dayCL[data].set_come(time);
    }

    /**
     * Установка текущей даты
     * @param dayNum  Дата в месяц
     */
    public void set_dataNum(int dayNum){
        if (_dayCL[_currentNumberDay]==null)
            _dayCL[_currentNumberDay] = new DayCL(_allTime/_countDay,dayNum);
        _dayCL[_currentNumberDay].set_dataNum(dayNum);
    }

    /**
     * Установка времени ухода для текущго дня
     * @param numData Дата в месяце
     * @param time Время в минутах
     */
    public void set_leave(int numData,int time){

        int data = 0;
        while(_dayCL[data].get_currDateNum() != numData){
            data++;
        }
        _dayCL[data].set_leave(time);
    }

    /**
     * Установка времени ухода для текущго дня
     * @param numData  Дата в месяце
     * @param time Время в минутах
     */
    public void set_need(int numData,int time){

        int data = 0;
        while(_dayCL[data].get_currDateNum() != numData){
            data++;
        }
        _dayCL[data].set_timeNeed(time);
    }

    /**
     * Функция создания текста для записи в файл
     * @return  Данные для записи в файл
     */
    public ArrayList<String> get_writeData(){
        ArrayList<String> writeData = new ArrayList<>();

        String str = "num come leave need work wuw \n\r";

        writeData.add(str);

        for (DayCL day:_dayCL){
            str = String.format(Locale.US,"%d %d %d %d\n\r",
                    day.get_currDateNum(),
                    day.get_timeCome(),
                    day.get_timeLeave(),
                    day.get_timeNeed());

            writeData.add(str);
        }

        return writeData;
    }

    /**
     * Получение данных для отображения в таблице
     * @return Данные для отображения в таблице
     */
    public ArrayList<String> get_tableData(){
        ArrayList<String> tableData = new ArrayList<>();

        for (DayCL dayCL:_dayCL){
            tableData.add(String.format(Locale.US,"%2d",dayCL.get_currDateNum()));
            //tableData.add(String.format(Locale.US,"%2.2f",dayCL.get_timeCome()));
            tableData.add(get_timeHH_MM(dayCL.get_timeCome()));
            //tableData.add(String.format(Locale.US,"%2.2f",dayCL.get_timeLeave()));
            tableData.add(get_timeHH_MM(dayCL.get_timeLeave()));
            //tableData.add(String.format(Locale.US,"%2.2f",dayCL.get_timeNeed()));
            tableData.add(get_timeHH_MM(dayCL.get_timeNeed()));
            //tableData.add(String.format(Locale.US,"%2.2f",dayCL.get_timeWork()));
            tableData.add(get_timeHH_MM(dayCL.get_timeWork()));
        }

        return tableData;
    }

    /**
     * Получение строки чч:мм
     * @param time  Время в минутах
     * @return  Строка чч:мм
     */
    private String get_timeHH_MM(int time){
        int timeHH = time/60;

        int timeMM = Math.abs(time - timeHH * 60);

        return String.format(Locale.US,"%d:%d",timeHH,timeMM);
    }

    /**
     * Получение кода цвета текста
     * @return Код цвета
     */
    public int get_color(){
        int color;

        int wuw = get_wuw();

        if (wuw < COUNT_BAD){
           color = Color.RED;
        }else if (wuw < COUNT_GOOD){
            color = Color.BLACK;
        }else{
            color = Color.GREEN;
        }

        return color;
    }

    /**
     * Получение данных по приходу и уходу
     * @return Строка чч:мм со временем
     */
    public String get_allWuw() {
        return get_timeHH_MM(get_wuw());
    }

    /**
     * Получение знака прихода/ухода
     * @return Больше 0 или нет
     */
    public boolean get_signWuW(){

        return get_wuw() >= 0;
    }

    /**
     * Получение времени ухода/прихода
     * @return Время в минутах
     */
    private int get_wuw(){
        int wuw = 0;
        for (DayCL day:_dayCL){
            wuw += day.get_timeWuW();
        }

        return wuw;
    }

    /**
     * Получение текущей даты
     * @param countDay Номер дня в месяце
     * @return Текущая дата
     */
    public int get_dayNums(int countDay){
        return _dayCL[countDay].get_currDateNum();
    }

    public String get_EndForMounth(){
        int count = MounthStruct.get_currMounth().length;
        double proc = (count -  _currentNumberDay) / count;
        return String.format("Осталось %2.2f месяца",proc);
    }
}
