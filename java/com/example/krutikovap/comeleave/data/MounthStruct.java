package com.example.krutikovap.comeleave.data;

import com.example.krutikovap.comeleave.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Данные по месяцам(время, какие даты и т.д.)
 */
public class MounthStruct {
    /**
     * Даты в январе
     */
    private static int[] januaryNums = new int[]{
            9, 10,11,12,13,
            16,17,18,19,20,
            23,24,25,26,27,
            30,31};

    /**
     * Даты в феврале
     */
    private static int[] februaryNums = new int[]{
            1, 2, 3,
            6, 7, 8, 9,10,
            13,14,15,16,17,
            20,21,22,  24,
            27};

    /**
     * Даты в марте
     */
    private static int[] marchNums = new int[]{
            1, 2, 3,
            6, 7,   9, 10,
            13,14,15,16,17,
            20,21,22,23,24,
            27,28,29,30,31};

    /**
     * Даты в апреле
     */
    private static int[] aprilNums = new int[]{
            3, 4, 5, 6, 7,
            10,11,12,13,14,
            17,18,19,20,21,
            24,25,26,27,28};

    /**
     * Даты в мае
     */
    private static int[] mayNums = new int[]{
            2, 3, 4, 5,
            8,
            10,11,12,
            15,16,17,18,19,
            21,23,24,25,26,
            29,30,31};

    /**
     * Даты в июне
     */
    private static int[] juneNums = new int[]{
            1, 2,
            5, 6, 7, 8, 9,
            13,14,15,16,
            19,20,21,22,23,
            26,27,28,29,30};
    /**
     * Даты в июле
     */
    private static int[] julyNums = new int[]{
            3, 4, 5, 6, 7,
            10,11,12,13,14,
            17,18,19,20,21,
            24,25,26,27,28,
            31};
    /**
     * Даты в августе
     */
    private static int[] augustNums = new int[]{
            1, 2, 3, 4,
            7, 8, 9, 10,11,
            14,15,16,17,18,
            21,22,23,24,25,
            28,29,30,31};

    /**
     * Даты в сентябре
     */
    private static int[] septemberNums = new int[]{
            1,
            4, 5, 6, 7, 8,
            11,12,13,14,15,
            18,19,20,21,22,
            25,26,27,28,29};

    /**
     * Даты в октябре
     */
    private static int[] octoberNums = new int[]{
            2, 3, 4, 5, 6,
            9, 10,11,12,13,
            16,17,18,19,20,
            23,24,25,26,27,
            30,31};

    /**
     * Даты в ноябре
     */
    private static int[] novemberNums = new int[]{
            1, 2, 3,
            7, 8, 9, 10,
            13,14,15,16,17,
            20,21,22,23,24,
            27,28,29,30};

    /**
     * Даты в декабре
     */
    private static int[] decemberNums = new int[]{
            1,
            4, 5, 6, 7, 8,
            11,12,13,14,15,
            18,19,20,21,22,
            25,26,27,28,29};

    /**
     * Массив со временем по всем месяцам. каждый элемент массива - месяц
     */
    //private static double[] allTime = new double[]{120.00,160.00,168.25,167.00,151.75,168.25,167.00,184.75,175.25,168.25,168.25,175.25};
    private static double[] allTime = new double[]{136.50,143.50,174.00,160.00,160.00,167.00,168.25,184.75,167.00,176.5,168.25,167.25};

    /**
     * Текущий номер месяца
     */
    private static int numMounth;

    /**
     * Константа
     */
    private final static int JANUARY = 1;

    /**
     * Константа
     */
    private final static int FEBRUARY = 2;

    /**
     * Константа
     */
    private final static int MARCH = 3;

    /**
     * Константа
     */
    private final static int APRIL = 4;

    /**
     * Константа мая
     */
    private final static int MAY = 5;

    /**
     * Константа июня
     */
    private final static int JUNE = 6;

    /**
     * Константа июля
     */
    private final static int JULY = 7;

    /**
     * Константа августа
     */
    private final static int AUGUST = 8;

    /**
     * Константа сентября
     */
    private final static int SEPTEMBER = 9;

    /**
     * Константа октября
     */
    private final static int OCTOBER = 10;

    /**
     * Константа ноября
     */
    private final static int NOVEMBER = 11;

    /**
     * Константа декабря
     */
    private final static int DECEMBER = 12;

    /**
     * Получение календарного номера месяца
     * @return Календарный номер месяца
     */
    public static int get_numMounth() {
        if (numMounth == 0){
            numMounth = get_systemNumMounth();
        }
        return numMounth;
    }

    /**
     * Получение времени, которое необходимо отработать в месяц
     * @return Время необходимое в текущем месяце
     */
    public static int get_allTime(){
        double currTime = allTime[get_systemNumMounth()-1];
        return (int)(currTime*60);
    }

    /**
     * Получение данных для выпадающего списка по месяцам
     * @return Текущий указатель на xml данные
     */
    public static int get_spinnerMounth(){
        int spinner_mounth = 0;
        switch(get_numMounth())  {
            case JANUARY:
                spinner_mounth = R.array.january;
                break;
            case FEBRUARY:
                spinner_mounth = R.array.february;
                break;
            case MARCH:
                spinner_mounth = R.array.march;
                break;
            case APRIL:
                spinner_mounth = R.array.april;
                break;
            case MAY:
                spinner_mounth = R.array.may;
                break;
            case JUNE:
                spinner_mounth = R.array.june;
                break;
            case JULY:
                spinner_mounth = R.array.july;
                break;
            case AUGUST:
                spinner_mounth = R.array.august;
                break;
            case SEPTEMBER:
                spinner_mounth = R.array.september;
                break;
            case OCTOBER:
                spinner_mounth = R.array.october;
                break;
            case NOVEMBER:
                spinner_mounth = R.array.november;
                break;
            case DECEMBER:
                spinner_mounth = R.array.december;
                break;
            default:
                break;
        }
        return spinner_mounth;
    }

    /**
     * Получение массива дат текущего месяца
     * @param numMounth номер месяца
     * @return Массив дет текущего месяца
     */
    private static int[] get_currMounth(int numMounth) {
        int[] mounth;
         switch (numMounth){
             case JANUARY:
                 mounth = januaryNums;
                 break;
             case FEBRUARY:
                 mounth = februaryNums;
                 break;
             case MARCH:
                 mounth = marchNums;
                 break;
             case APRIL:
                 mounth = aprilNums;
                 break;
             case MAY:
                 mounth = mayNums;
                 break;
             case JUNE:
                 mounth = juneNums;
                 break;
             case JULY:
                 mounth = julyNums;
                 break;
             case AUGUST:
                 mounth = augustNums;
                 break;
             case SEPTEMBER:
                 mounth = septemberNums;
                 break;
             case OCTOBER:
                 mounth = octoberNums;
                 break;
             case NOVEMBER:
                 mounth = novemberNums;
                 break;
             case DECEMBER:
                 mounth=decemberNums;
                 break;
             default:
                 mounth = null;
         }
        return mounth;
    }

    /**
     * Получение массива значений текущего месяца
     * @return Массив значений дат
     */
    public static int[] get_currMounth(){
        return get_currMounth(get_numMounth());
    }



    /**
     * Получение системного месяца
     * @return Номер месяца
     */
    private static int get_systemNumMounth(){
        SimpleDateFormat time = new SimpleDateFormat("MM",Locale.US);
        String result = time.format(new Date());
        return Integer.parseInt(result);
    }

    /**
     * Получение текущей даты в формате дд.мм.гг
     * @return Текущая дата в строковом виде
     */
    public static String get_currBigDate(){
        SimpleDateFormat time = new SimpleDateFormat("Сегодня dd.MM.yyyy");

        return time.format(new Date());
    }



    /**
     * Получения имя файла для сохранения
     * @return Имя файла для сохранения текстовых данных
     */
    public static String get_currFileName(){
        String fileName;
        switch (get_numMounth()){
            case JANUARY:
                fileName = "january.txt";
                break;
            case FEBRUARY:
                fileName = "february.txt";
                break;
            case MARCH:
                fileName = "march.txt";
                break;
            case APRIL:
                fileName = "april.txt";
                break;
            case MAY:
                fileName = "may.txt";
                break;
            case JUNE:
                fileName = "june.txt";
                break;
            case JULY:
                fileName = "july.txt";
                break;
            case AUGUST:
                fileName = "august.txt";
                break;
            case SEPTEMBER:
                fileName = "september.txt";
                break;
            case OCTOBER:
                fileName = "october.txt";
                break;
            case NOVEMBER:
                fileName = "november.txt";
                break;
            case DECEMBER:
                fileName = "december.txt";
                break;
            default:
                fileName = null;
        }

        return fileName;
    }
}
