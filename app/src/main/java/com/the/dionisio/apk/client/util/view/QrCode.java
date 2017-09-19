package com.the.dionisio.apk.client.util.view;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * Created by Daniel on 07/06/2017.
 */

public class QrCode {
    private Point point;

    public QrCode(String idTicket, ImageView imageView) {
        QrCodeGenerator(idTicket,imageView, false);
    }

    public QrCode(String idTicket, ImageView imageView, Point point) {
        this. point = point;
        QrCodeGenerator(idTicket,imageView, true);
    }


    public void QrCodeGenerator(String idTicket, ImageView imageView, Boolean auto){
        try
        {

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = null;



            if(!auto)
            {

                bitMatrix = multiFormatWriter.encode(idTicket, BarcodeFormat.QR_CODE, imageView.getWidth(), imageView.getHeight());
            }
            else
            {
                bitMatrix = multiFormatWriter.encode(idTicket, BarcodeFormat.QR_CODE, point.x, point.x);
                imageView.getLayoutParams().height = point.x;
                imageView.getLayoutParams().width = point.x;

            }
            GQrCode barcodeEncoder = new GQrCode();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        }
        catch (WriterException err)
        {
            err.printStackTrace();
        }
    }

    public void auto()
    {

    }

}
