package com.example.faizan.allconcepts.qrCode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.faizan.allconcepts.BaseActivity;
import com.example.faizan.allconcepts.R;
import com.example.faizan.allconcepts.auth0.AuthAwareActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class QrCodeActivity extends AuthAwareActivity {

    private ImageView qrCdodeImage;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        qrCdodeImage = findViewById(R.id.qr_image);
       new  generateQRCode().execute();
    }

    /**
     * generate QR code based on encoded string
     */
    private class generateQRCode extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            String text = "MOHAMMED FAIZAN YOUSUFF \n Qualification : Engineering \n Designation: Android developer \n ";
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 600, 600);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                bitmap = barcodeEncoder.createBitmap(bitMatrix);
            } catch (WriterException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            qrCdodeImage.setImageBitmap(bitmap);

        }
    }
}
