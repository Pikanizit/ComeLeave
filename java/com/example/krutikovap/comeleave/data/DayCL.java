package com.example.krutikovap.comeleave.data;

import java.util.ArrayList;

/**
 * Класс данных одного дня
 */
public class DayCL {

    private int BASE_NUMBER = -13;
    /**
     * Набор данных входов и выходов
     */
    private ArrayList<CiclCL> comeLeave;

    /**
     * Время прихода в минутах
     */
    private int timeCome = BASE_NUMBER;
    /**
     * Время ухода в минутах
     */
    private int timeLeave = BASE_NUMBER;
    /**
     * Отработанное время в минутах
     */
    private int timeWork = BASE_NUMBER;
    /**
     * Сколько надо отработать в минутах
     */
    private int timeNeed = BASE_NUMBER;
    /**
     * Переработка/недоработка дня в минутах
     */
    private int timeWuW = BASE_NUMBER;
    /**
     * Число месяца 19 мая и т.д.
     */
    private int currDateNum = BASE_NUMBER;

    /**
     * Установка данных для одного дня
     * @param timeNeed  Сколько времени отработать в день
     * @param dayNum  Число месяца
     */
    public DayCL(int timeNeed,int dayNum){
        this.timeNeed = timeNeed;
        this.currDateNum = dayNum;

        comeLeave = new ArrayList<>();
    }

    /**
     * Установка времени прихода, и возможный расчёт рабочего времени и переработки/недоработки
     * @param timeCome Время прихода в минутах
     */
    public void set_come(int timeCome){
        this.timeCome = timeCome;

        set_time();


    }

    /**
     *  Установка числа месяца
     * @param dayNum  Число месяца
     */
    public void set_dataNum(int dayNum){
        this.currDateNum = dayNum;
    }

    /**
     * Получение текущей даты месяца
     * @return Текущая дата месяца
     */
    public int get_currDateNum(){
        return this.currDateNum;
    }

    /**
     * Получение текущего времени прихода
     * @return Текущее время прихода в минутах
     */
    public int get_timeCome(){
        return this.timeCome;
    }

    /**
     * Получение текущего времени ухода
     * @return Время ухода в минутах
     */
    public int get_timeLeave(){
        return this.timeLeave;
    }

    /**
     * Получение необходимого времени длоя отработки
     * @return Необходимое время отработки в минутах
     */
    public int get_timeNeed(){
        return this.timeNeed;
    }

    /**
     * Расчёт рабочего времени и переработки/недоработки
     */
    private void set_time() {
        if (this.timeCome >= 0 && this.timeLeave >= 0)
        {
            if (this.timeCome != 0 || this.timeLeave !=0)
                set_timeWork();

            if (this.timeCome != 0 || this.timeLeave !=0)
                set_timeWuW();
        }
    }

    /**
     * Установка времени отработки
     * @param time Время отработки в минутах
     */
    public void set_timeNeed(int time){
        this.timeNeed = time;
    }

    /**
     *Установка времени ухода, и возможный расчёт рабочего времени и переработки/недоработки
     * @param timeLeave Время ухода в минутах
     */
    public void set_leave(int timeLeave){
        this.timeLeave = timeLeave;

        set_time();
    }

    /**
     * Получение текущего рабочего времени
     * @return  Текущее отработанное время в минутах
     */
    public int get_timeWork(){                                                                    //
        if (this.timeWork >=0)
            return this.timeWork;
        else
            return 0;
    }

    /**
     * Получение текущего времени переработки/недоработки
     * @return Текущее время переработки/недоработки в минутах
     */
    public int get_timeWuW(){
        if (this.timeWork >=0){
            set_timeWuW();
            return this.timeWuW;
        }
        else
            return 0;
    }

    /**
     * Установка отработанного времени
     */
    private void set_timeWork(){
        this.timeWork = this.timeLeave - this.timeCome;
    }

    /**
     * Установка переработки/недоработки
     */
    private void set_timeWuW(){
        this.timeWuW = this.timeWork - this.timeNeed;
    }
}
