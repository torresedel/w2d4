package com.example.admin.w2d4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText etAddData;
    TextView tvFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAddData = (EditText) findViewById(R.id.etAddData);
        tvFileContent = (TextView) findViewById(R.id.tvFileContent);
    }


    public void updateData(View view) {

        switch (view.getId()){

            case R.id.btnAddData:

                String data  = etAddData.getText().toString();
                try {
                    FileOutputStream fileOutputStream = openFileOutput("myText.txt",MODE_PRIVATE);
                    fileOutputStream.write(data.getBytes());
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(),"Text Saved",Toast.LENGTH_LONG).show();
                    etAddData.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.btnGetData:

                try {
                    FileInputStream fileInputStream= openFileInput("myText.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer = new StringBuffer();
                    String lines;
                    while ((lines=bufferedReader.readLine())!=null) {
                        stringBuffer.append(lines+"\n");
                    }
                    tvFileContent.setText(stringBuffer.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

        }

    }


}
