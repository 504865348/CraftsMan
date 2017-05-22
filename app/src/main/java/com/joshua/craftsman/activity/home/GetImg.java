package com.joshua.craftsman.activity.home;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetImg {
    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl
     *            网络连接地址
     * @return
     */
    /**
     * 从输入流中获取数据
     *
     * @param inStream
     *            输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    public static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
            System.out.println("btImg=="+btImg);
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void writeImageToDisk(byte[] img, String zipImageUrl) {
        try {
            File file = new File(zipImageUrl);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
            System.out.println("图片已经写入"+zipImageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String imgUrl = "http://114.55.148.222:8080/GJ/carousel/gj001.jpg";
        byte[] btImg1 = getImageFromNetByUrl(imgUrl);
        if (null != btImg1 && btImg1.length > 0) {
            System.out.println("读取到：" + btImg1.length + " 字节");
            writeImageToDisk(btImg1, "D:/1479209533362.jpg");
        } else {
            System.out.println("没有从该连接获得内容");
        }
    }
}
