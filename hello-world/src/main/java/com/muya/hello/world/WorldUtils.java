package com.muya.hello.world;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.ConnectException;

public class WorldUtils {


    public static boolean getLicense() {
        boolean result = false;
        try {
            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(s.getBytes());
            //InputStream inputStream = Xml2Word2Pdf.class.getClassLoader().getResourceAsStream("\\license.xml");
            License license = new License();
            license.setLicense(inputStream);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 使用aspose.word把word文档转为pdf文档
     *
     * @param sourceFile word文档绝对路径(如:D:/templates/order.doc)
     * @param destFile   pdf文档绝对路径(如:D:/templates/order.pdf)
     */
    public static String word2Pdf(String sourceFile, String destFile, HttpServletResponse response) throws Exception {
        destFile = StringUtils.isEmpty(destFile) ? sourceFile.replace(".doc", ".pdf") : destFile;
        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            throw new Exception("生成PDF文档,验证License失败!");
        }
        try {
            File file = new File(destFile);  //新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(sourceFile);//通过sourceFile创建word文档对象
            doc.save(os, SaveFormat.PDF);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("生成PDF文档失败!");
        }
        return destFile;
    }

    public static File doc2Pdf(String docPath, String pdfPath) {
        System.out.println("pdfPath = "+pdfPath);
        System.out.println("pdfPath = "+pdfPath);
        File pdfFile = new File(pdfPath);
        //判断是否windows系统，Linux要读取字体，否则pdf字体为方格
      /*  if(!OSinfo.isWindows()){
            //在Linux 里没有中文字体，pdf会出现方格，需要手动将windows目录（C:\Windows\Fonts）的字体包考到linux的字体目录下，然后用这个方法指定读取一下字体
            FontSettings.getDefaultInstance().setFontsFolder(File.separator + "usr"
                    + File.separator + "share" + File.separator + "fonts" +File.separator + "Fonts", true);
        }*/
        try {
            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
            License license = new License();
            license.setLicense(is);
            Document document = new Document(docPath);
            FileOutputStream fileOutputStream = new FileOutputStream(pdfFile);

            document.save(fileOutputStream, SaveFormat.PDF);
        } catch (Exception e) {
//            logger.info("****aspose doc转pdf异常");
            e.printStackTrace();
        }
        return pdfFile;
    }


    public static  void doc2pdf(String fileName, HttpServletResponse response) {
        File pdfFile = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        String docPath = fileName + ".doc";
        String pdfPath = fileName + ".pdf";
        try {
            pdfFile = WorldUtils.doc2Pdf(docPath, pdfPath);
            outputStream = response.getOutputStream();
            response.setContentType("application/pdf;charset=UTF-8");
            bufferedInputStream = new BufferedInputStream(new FileInputStream(pdfFile));
            byte buffBytes[] = new byte[1024];
            outputStream = response.getOutputStream();
            int read = 0;
            while ((read = bufferedInputStream.read(buffBytes)) != -1) {
                outputStream.write(buffBytes, 0, read);
            }
        } catch (ConnectException e) {
//            logger.info("****调用Doc2PdfUtil doc转pdf失败****");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
