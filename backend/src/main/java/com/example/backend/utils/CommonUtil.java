package com.example.backend.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Calendar;

@Slf4j
public class CommonUtil {
    /**
     * 检查类是由含有字段的值为null
     * @param obj 要检查的对象
     * @return 有值为null则返回true，否则返回false
     * @param <T> 对象的类型
     */
    public static <T> boolean checkAnyNullField(T obj) {
        if(obj == null)return true;
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) return true;
            } catch (Exception e){
                log.error(e.toString());
            }
        }
        return false;
    }

    /**
     * 检查类的fields的字段是否含有null
     * @param obj 要检查的对象
     * @param fields 要检查的字段名称
     * @return 有值为null则返回true，否则返回false
     * @param <T> 对象的类型
     * @throws NoSuchFieldException fields中有obj不包含的字段名称
     */
    public static <T> boolean checkAnyNullField(T obj, String... fields) throws NoSuchFieldException {
        if(obj == null) return true;
        Class<?> clazz = obj.getClass();
        for(String fieldName : fields) {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) return true;
            } catch (Exception e){
                log.error(e.toString());
            }
        }
        return false;
    }


    /**
     * 根据时间生成图片名称，防止图片名称重复
     */
    public static String getPhotoName(String name, String imageFileName){
        String fileName = name;
        Calendar cal=Calendar.getInstance();
        //如果年的目录不存在，创建年的目录
        int year=cal.get(Calendar.YEAR);
        fileName=fileName + "_" + year;
        //如果月份不存在，创建月份的目录
        int month=cal.get(Calendar.MONTH)+1;
        fileName=fileName+"_";
        if(month<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+month;
        //生成文件名的日部分
        int day=cal.get(Calendar.DAY_OF_MONTH);
        fileName=fileName+"_";
        if(day<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+day;
        //生成文件名的小时部分
        int hour=cal.get(Calendar.HOUR_OF_DAY);
        if(hour<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+hour;
        //生成文件名的分钟部分
        int minute=cal.get(Calendar.MINUTE);
        if(minute<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+minute;
        //生成文件名的秒部分
        int second=cal.get(Calendar.SECOND);
        if(second<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+second;
        //生成文件名的毫秒部分
        int millisecond=cal.get(Calendar.MILLISECOND);
        if(millisecond<10)
        {
            fileName=fileName+"0";
        }
        if(millisecond<100)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+millisecond;
        //生成文件的扩展名部分,只截取最后单位
        String endName = imageFileName.substring(imageFileName.lastIndexOf("."));//截取之后的值
        fileName=fileName+ endName;
        return fileName;
    }


    public static void readFileToResponse(HttpServletResponse response, File file){
//        if(!StringTools.pathIsOk(filePath)){
//            return;
//        }
        OutputStream out = null;
        FileInputStream in = null;
        try{
            if(!file.exists()){
                return;
            }
            in = new FileInputStream(file);
            byte[] byteData = new byte[1024];
            out = response.getOutputStream();
            int len = 0;
            while((len = in.read(byteData)) != -1){
                out.write(byteData, 0, len);
            }
            out.flush();
        }catch (Exception e){
            log.error("读取文件异常", e);
        }finally {
            if(out != null){
                try {
                    out.close();
                }catch (IOException e){
                    log.error("IO异常", e);
                }
            }
            if(in != null){
                try {
                    in.close();
                }catch (IOException e){
                    log.error("IO异常", e);
                }
            }
        }
    }

}
