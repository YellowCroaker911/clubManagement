package com.example.backend.utils;

import com.example.backend.exception.BusinessException;
import com.example.backend.utils.result.ReturnCodes;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Base64;
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



    /**
     * 图片转Base64字符串
     * @param imageFileName
     * @return
     */
    public static String convertImageToBase64Str(String imageFileName) {
        ByteArrayOutputStream baos = null;
        try {
            //获取图片类型
            String suffix = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
            //构建文件
            File imageFile = new File(imageFileName);
            //通过ImageIO把文件读取成BufferedImage对象
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            //构建字节数组输出流
            baos = new ByteArrayOutputStream();
            //写入流
            ImageIO.write(bufferedImage, suffix, baos);
            //通过字节数组流获取字节数组
            byte[] bytes = baos.toByteArray();
            //获取JDK8里的编码器Base64.Encoder转为base64字符
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new BusinessException(ReturnCodes.IMG_NOTFOUND);
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Base64字符串转图片
     * @param base64String
     * @param imageFileName
     */
    public static void convertBase64StrToImage(String base64String, String imageFileName) {
        ByteArrayInputStream bais = null;
        try {
            //获取图片类型
            String suffix = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
            //获取JDK8里的解码器Base64.Decoder,将base64字符串转为字节数组
            byte[] bytes = Base64.getDecoder().decode(base64String);
            //构建字节数组输入流
            bais = new ByteArrayInputStream(bytes);
            //通过ImageIO把字节数组输入流转为BufferedImage
            BufferedImage bufferedImage = ImageIO.read(bais);
            //构建文件
            File imageFile = new File(imageFileName);
            //写入生成文件
            ImageIO.write(bufferedImage, suffix, imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
