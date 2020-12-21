package com.example.parksandrecs;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrCodeFragment extends DialogFragment {
    ImageView qrImage;
    String qrData;

    public QrCodeFragment(String qrData) {
        this.qrData = qrData;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qr_code, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        qrImage = getDialog().findViewById(R.id.reservationQrImage);

        // Create qr code
        // "geo:latitude,longitude"
        QRGEncoder qrgEncoder = new QRGEncoder(qrData, null, QRGContents.Type.TEXT, 600);
        qrgEncoder.setColorBlack(Color.BLACK);
        qrgEncoder.setColorWhite(Color.WHITE);
        try {
            Bitmap qrBitmap = qrgEncoder.getBitmap();
            qrImage.setImageBitmap(qrBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}