package com.example.pdfreader;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemSelected {
    private RecyclerView rc;
    private List<File> pdfList;
    private MainAdapter adapter;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runPermission();


    }

    private void runPermission() {
        Dexter.withContext(MainActivity.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        displayPdf();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                    permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    public ArrayList<File> findPdfFun(File file){
        ArrayList<File> pdfFiles=new ArrayList<>();

        File[] files =file.listFiles();

        for(File singleFile :files){

            if(singleFile.isDirectory()&& !singleFile.isHidden()){
                pdfFiles.addAll(findPdfFun(singleFile));
            }else if (singleFile.getName().endsWith(".pdf")){
                pdfFiles.add(singleFile);
            }
        }
return pdfFiles;
    }

    public void displayPdf(){
        rc=findViewById(R.id.Main_rv);
        Log.i(TAG, "displayPdf: 1");
        rc.setHasFixedSize(true);
        Log.i(TAG, "displayPdf: 2");
        rc.setLayoutManager(new GridLayoutManager(this,3));
        Log.i(TAG, "displayPdf: 3");
        pdfList=new ArrayList<>();
        pdfList.addAll(findPdfFun(Environment.getExternalStorageDirectory()));
        Log.i(TAG, "displayPdf: 4");
        adapter=new MainAdapter(this,pdfList,this::selectItem);
        Log.i(TAG, "displayPdf: 5");
        rc.setAdapter(adapter);


    }

    @Override
    public void selectItem(File file) {
        startActivity(new Intent(MainActivity.this,PdfActivity.class)
                .putExtra("path",file.getAbsolutePath()));
    }
}