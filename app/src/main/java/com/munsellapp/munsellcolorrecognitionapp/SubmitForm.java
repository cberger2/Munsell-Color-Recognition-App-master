package com.munsellapp.munsellcolorrecognitionapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class SubmitForm extends AppCompatActivity implements View.OnClickListener {
    ImageButton save, email;
    EditText idNumber, notes;
    TextView munsell, munsellValueText, updatedText;
    String munsellChip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_form);
        save = (ImageButton) findViewById(R.id.sfSaveButton);
        save.setOnClickListener(this);
        Bundle getBundle= getIntent().getExtras();
        munsellChip=getBundle.getString("MunsellChip");

//        if(getBundle!=null){
//            String datalist= getBundle.getString("dataList");
//            updatedText=(TextView) findViewById(R.id.sfInfoStorage);
//            updatedText.setText(datalist+"\n");
//        }


        munsellValueText = (TextView) findViewById(R.id.sfMunsellChip);
        munsellValueText.setText(munsellChip);

    }

    //        public void save (View v) {
//            idNumber=(EditText) findViewById(R.id.sfIdEdit);
//            munsellChip=(TextView) findViewById(R.id.sfMunsellChip);
//            notes=(EditText) findViewById(R.id.sfNotesEdit);
//            Intent sendData= new Intent(this,DataForm.class);
//            Bundle dataBundle= new Bundle();
//            dataBundle.putString("idNumber", idNumber.getText().toString());
//            dataBundle.putString("munsellChip", munsellChip.getText().toString());
//            dataBundle.putString("notes", notes.getText().toString());
//            sendData.putExtras(dataBundle);
//            startActivity(sendData);
//
//        }
//        idNumber=(EditText) findViewById(R.id.sfIdEdit);
//        notes=(EditText) findViewById(R.id.sfNotesEdit);
//        munsell=(TextView) findViewById(R.id.sfMunsellChip);
//        String csv = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
//        CSVWriter writer = new CSVWriter(new FileWriter(csv));
//        List<String[]> data = new ArrayList<String[]>();
//        data.add(new String[] {idNumber.getText().toString(), ", ",munsell.getText().toString(), ", ", notes.getText().toString(), "/n" });
//
//        writer.writeAll(data);
//
//        Toast toast= Toast.makeText(this, "INFORMATION SAVED", Toast.LENGTH_LONG);
//        toast.show();
//
//        writer.close();
//
//
//
//
//    }
    private void saveInInternalFolder(String aStringToSave, String aFileName){
        FileOutputStream fos=null;
        aStringToSave=idNumber+" , "+munsellChip+" , "+ notes;
        try{
            fos=openFileOutput(aFileName, this.MODE_PRIVATE);
            fos.write(aStringToSave.getBytes());
            fos.close();
            Toast.makeText(this, "file saved", Toast.LENGTH_LONG).show();

            FileInputStream fis = this.openFileInput(aFileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

        }catch (IOException e){
            Toast.makeText(this, "There is a problem saving to the internal file", Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public void onClick(View v) {
        idNumber=(EditText) findViewById(R.id.sfIdEdit);
        notes=(EditText) findViewById(R.id.sfNotesEdit);
        updatedText=(TextView) findViewById(R.id.sfInfoStorage);
        Intent intent=new Intent(this, DataForm.class);
        Bundle bundle=new Bundle();
        bundle.putString("idNumber", idNumber.getText().toString());
        bundle.putString("munsellChip", munsellValueText.getText().toString());
        bundle.putString("notes", notes.getText().toString());
//        if(updatedText.equals("")){
            intent.putExtras(bundle);
            startActivity(intent);
//        }else{
//            bundle.putString("dataList", updatedText.getText().toString());
//            intent.putExtras(bundle);
            startActivity(intent);
        }

//        idNumber = (EditText) findViewById(R.id.sfIdEdit);
//        munsellChip = (TextView) findViewById(R.id.sfMunsellChip);
//        notes = (EditText) findViewById(R.id.sfNotesEdit);
//        saveInInternalFolder((idNumber+" , "+munsellChip+" , "+ notes), "data.txt");



//            case R.id.emailButton:
//                Intent emailIntent = new Intent(Intent.ACTION_SEND);
//                emailIntent.setData(Uri.parse("mailto:"));
//                emailIntent.setType("text/plain");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ });
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
//                startActivity(emailIntent);
//                break;


    }


