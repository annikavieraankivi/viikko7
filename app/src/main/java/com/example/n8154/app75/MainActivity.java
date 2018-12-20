package com.example.n8154.app75;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        System.out.println("KANSION SIJAINTI " + context.getFilesDir());
    }

    public void loadFile(View v) {
        try {
            InputStream ins = context.openFileInput("testi.txt"); //TODO Tälle arvo!

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";

            while ((s=br.readLine()) != null) {
                System.out.println(s);
                if (s != null) edit.setText(s);
            }
            ins.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("LUETTU");
        }
    }

    public void saveFile(View v) {
        edit = (EditText) findViewById(R.id.editText);
        try {
            OutputStreamWriter ows = new OutputStreamWriter(context.openFileOutput("testi.txt", context.MODE_PRIVATE));
            String s = "";
            s = edit.getText().toString();
            ows.write(s);
            ows.close();

        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("KIRJOITETTU");
        }
    }
}
