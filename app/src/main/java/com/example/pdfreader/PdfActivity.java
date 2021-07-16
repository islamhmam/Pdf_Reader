package com.example.pdfreader;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfActivity extends AppCompatActivity {
PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
//        getSupportActionBar().hide();

        pdfView= findViewById(R.id.pdfActivity_pdfViewer);

        String path = getIntent().getStringExtra("path");

        File f=new File(path);

        Uri uri= Uri.fromFile(f);

        pdfView.fromUri(uri).load();

    }
}