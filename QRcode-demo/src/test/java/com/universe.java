package com;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

public class universe {

    /**
     * 生成本地图片二维码
     * */
    @Test
    public void test01() throws WriterException, IOException {
        String content = "http://www.baidu.com";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400);

        Path path = new File("/Users/x/Pictures/"+ UUID.randomUUID().toString().replace("-","") + ".png").toPath();

        MatrixToImageWriter.writeToPath(bitMatrix,"png",path);
    }


    /**
     * dataUrl 生成二维码 Base64
     * */
    @Test
    public void test02() throws WriterException, IOException {
        String content = "http://www.baidu.com";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ImageIO.write(image,"png",os);

        String encode = Base64.encode(os.toByteArray());

        System.out.println("data:image/png;base64," + encode);

    }

}
