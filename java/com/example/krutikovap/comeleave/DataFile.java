package com.example.krutikovap.comeleave;


import android.content.Context;

import com.example.krutikovap.comeleave.data.MounthCL;
import com.example.krutikovap.comeleave.data.MounthStruct;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Для работы с файлом текущего месяца
 */
public class DataFile {

    public DataFile(){

    }

    /**
     * Запись в файл текстовые данные
     * @param fileName  Имя файла
     * @param ctx контекст
     * @param strings Набор строк для записи в файл
     */
    public void WriteF(String fileName,Context ctx,ArrayList<String> strings) {
        try {
            FileOutputStream fOut = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);

            for (String str:strings) {
                fOut.write(str.getBytes());
            }

            fOut.flush();

            fOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Преобразование входящего потока байтов в строку
     * @param is  Входящий поток
     * @return  Строку
     * @throws IOException Если нет файла
     */
    private String  convertStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = is.read();
        while( i != -1)
        {
            baos.write(i);
            i = is.read();
        }
        return  baos.toString();
    }

    /**
     * Чтение данных из текстового файла
     * @param fileName  имя файла
     * @param ctx  контекст
     * @return  данные для Месяца
     * @throws IOException  Если нет файла
     */
    public MounthCL ReadF(String fileName, Context ctx) throws IOException {
        InputStream inputStream;

        String myText;

        FileInputStream file = ctx.openFileInput(fileName);

        inputStream = new BufferedInputStream(file);

        myText = convertStreamToString(inputStream);

        inputStream.close();

        if (myText.length() == 0) {
            return getDefaultMounthCL(fileName, ctx);
        }

        String[] txt = myText.split("\n\r*");

        if (txt.length == 1){
            getDefaultMounthCL(fileName, ctx);
        }

        MounthCL mounth = new MounthCL(txt.length - 1,MounthStruct.get_allTime());

        for (int index = 1; index < txt.length;index++) {

            String str = txt[index];

            String[] data = str.split(" ");

            int leave;
            int come;
            int dataNum;

            try{
                dataNum = Integer.parseInt(data[0]);

                come = Integer.parseInt(data[1]);

                leave = Integer.parseInt(data[2]);
            }
            catch(NumberFormatException e){
                dataNum = Integer.parseInt(data[1]);

                come = Integer.parseInt(data[2]);

                leave = Integer.parseInt(data[3]);
            }

            mounth.set_dayCL(index-1);
            mounth.set_leave(dataNum,leave);
            mounth.set_come(dataNum,come);

            mounth.set_dataNum(dataNum);
        }

        /*
        FileReader fr = new FileReader(ctx.getFileStreamPath(fileName));

        BufferedReader br = new BufferedReader(fr);


        String asd = br.readLine();

        int countDay = MounthStruct.get_currMounth().length;

        String[] txt0 = new String[countDay];

        for (int cc = 0;cc< countDay;cc++){
            txt0[cc] = br.readLine();
        }

        fr.close(); */
        return mounth;
    }

    /**
     * Если нет текущего файла месяца задаётся по умолчанию. Все времена призходов и уходов = 0,0
     * @param fileName Имя файла
     * @param ctx  Контекст
     * @return Набор данных теущего месяца
     */
    public MounthCL getDefaultMounthCL(String fileName, Context ctx) {

        int[] currNums = MounthStruct.get_currMounth();

        MounthCL mounthCL = new MounthCL(currNums.length,MounthStruct.get_allTime());

        for (int day = 0;day < currNums.length;day++){
            mounthCL.set_dayCL(day);

            mounthCL.set_dataNum(currNums[day]);

            mounthCL.set_come(currNums[day],0);

            mounthCL.set_leave(currNums[day],0);
        }

        WriteF(fileName,ctx,mounthCL.get_writeData());

        return mounthCL;
    }

    /**
     * Получение данных адаптера для таблицы
     * @param fileName Имя файла месяца, откуда берём данные
     * @param ctx Контекст для работы с файлом
     * @return Набор данных для создания адаптера
     */
    public ArrayList<String> getAdapter(String fileName,Context ctx)  {
        ArrayList<String> adapter = new ArrayList<String>();

        adapter.add("Дата");
        adapter.add("Приход");
        adapter.add("Уход");
        adapter.add("Норма");
        adapter.add("Раб.");

        MounthCL mounthCL;
        try {
            mounthCL = ReadF(fileName,ctx);
        } catch (IOException e) {
            mounthCL = getDefaultMounthCL(fileName,ctx);
        }

        for (String data : mounthCL.get_tableData()){
            adapter.add(data);
        }

        return adapter;
    }

    /**
     * Сохранение файла
     * @param filePath путь к файлу
     * @param fileContent текстовые данные для сохранения
     */
    public static void SaveFile (String filePath, String fileContent)  {
        //Создание объекта файла.
        File fhandle = new File(filePath);
        try{
            //Если нет директорий в пути, то они будут созданы:
            if (!fhandle.getParentFile().exists())
                fhandle.getParentFile().mkdirs();
            //Если файл существует, то он будет перезаписан:
            fhandle.createNewFile();
            FileOutputStream fOut = new FileOutputStream(fhandle);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.write(fileContent);
            myOutWriter.close();
            fOut.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            //textInfo.setText("Path " + filePath + ", " + e.toString());
        }
    }


}
