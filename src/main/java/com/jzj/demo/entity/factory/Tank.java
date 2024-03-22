package com.jzj.demo.entity.factory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Tank {

    public static void main(String[] args) throws Exception {

        String stock_yes = "[{\"org_code\":\"1001\",\"item_code\":\"1873299\",\"barcode\":\"6978765856094\",\"qty\":\"2.000\",\"priority\":\"2.000\"}]";

        //数据 zip 压缩上传
        File zipFile = zipFile(
                stock_yes,
                "/Users/jinzhengjun/Downloads/stock_yes.zip");

        String fileMd5 = getMd5(zipFile);

        System.out.println(fileMd5);

        System.out.println(System.currentTimeMillis());
    }

    /**
     * 文件压缩示例代码
     * 文本内容写 data.txt 压缩成zip文件,并返回压缩后文件的 md5 值
     * @param text 待压缩的字符串
     * @param filePath
     */
    public static File zipFile(String text, String filePath) throws Exception {
        File zipFile = new File(filePath);
        // 检查文件所在目录是否存在，不存在则创建
        File parentFile = zipFile.getParentFile();
        if( !parentFile.exists()){
            parentFile.mkdirs();
        }
        ZipOutputStream zos = null;
        InputStream inputStream = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            zos.putNextEntry(new ZipEntry("data.txt"));
            inputStream = new ByteArrayInputStream(text.getBytes("UTF-8"));
            byte[] b = new byte[1024];
            int i;
            while ((i = inputStream.read(b)) != -1) {
                //0-9，手动压缩成ZIP文件方式为：存储、最快、较快、标准、较好、最好。
                zos.setLevel(9);
                zos.write(b, 0, i);
            }
            zos.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            zos.close();
            inputStream.close();
        }
        return zipFile;
    }

    /**
     * 获取文件 md5 值
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getMd5(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }

        return MD5Util.getMD5String(outStream.toByteArray());
    }
}
