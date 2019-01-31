package com.java.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class File_Write_Load {

    public static void saveDataToFile(String fileName,String data){
        BufferedWriter writer = null;
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        String nowdate = ft.format(date);
        File file = new File("D:/tmp/analysis/"+fileName + ".json");
        //如果文件不存在,则新建文件
        if(!file.exists()){
            try{

                file.createNewFile();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //写文件
        try{

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8"));
            writer.write(data);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{

                if(writer != null){
                    writer.close();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("Write Success!");
    }


}
