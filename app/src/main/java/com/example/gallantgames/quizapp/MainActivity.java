package com.example.gallantgames.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList ;
    ListAdapters arrayAdapter  ;
    String msgText ;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        arrayList = new ArrayList<>();
        arrayAdapter = new ListAdapters(arrayList , this);
        listView.setAdapter(arrayAdapter);
        listView.setBackgroundResource(R.drawable.customshape);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this , EditMessageClass.class);
                intent.putExtra(Intents_Constants.INTENT_MESSAGE_DATA,arrayList.get(position).toString());
                intent.putExtra((Intents_Constants.INTENT_ITEM_POSITION) , position);
                startActivityForResult(intent , Intents_Constants.INTENT_REQUEST_CODE_TWO);

            }
        });


        try {

            //PrintWriter printWriter = new PrintWriter(openFileOutput("todo.txt" , Context.MODE_PRIVATE));
            //printWriter.write("");
            Scanner scanner = new Scanner(openFileInput("todo.txt"));
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                arrayList.add(data);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    public void onStop()
    {
        super.onStop();
        try {
            PrintWriter printWriter = new PrintWriter(openFileOutput("todo.txt" , Context.MODE_PRIVATE));
            for(String data : arrayList){
                printWriter.println(data);

            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finish();

    }
    public void onPause(){
        super.onPause();
        try {
            PrintWriter printWriter = new PrintWriter(openFileOutput("todo.txt" , Context.MODE_PRIVATE));
            for(String data : arrayList){
                printWriter.println(data);

            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }
    public void onBackPressed()
    {
        try {
            PrintWriter printWriter = new PrintWriter(openFileOutput("todo.txt" , Context.MODE_PRIVATE));
            for(String data : arrayList){
                printWriter.println(data);

            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finish();

    }

    public void onClick(View v)
    {
        boolean insert = true;
        String etdata = ((EditText)findViewById(R.id.etBox)).getText().toString().trim();
        try {
            Scanner scanner = new Scanner(openFileInput("todo.txt"));
            if(!scanner.hasNextLine())
            {
                try {
                    PrintWriter printWriter = new PrintWriter(openFileOutput("todo.txt" , Context.MODE_PRIVATE));
                    for(String data : arrayList){
                        printWriter.println(data);

                    }
                    printWriter.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                data = data.trim();
                if(!data.isEmpty()) {
                    if (data.equals(etdata)) {
                        Toast.makeText(this, "Task is already available ", Toast.LENGTH_LONG).show();
                        insert = false;
                        break;
                    } else {
                        insert = true;
                    }
                }
            }
            if(insert == true)
            {
                onClickCheck(etdata);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
    public void onClickCheck(String ebdata){
        //listView.setVisibility(listView.isShown() ? View.GONE : View.VISIBLE);


        if(ebdata.equals(""))
        {
            Toast.makeText(this ,  "Please Enter Task...", Toast.LENGTH_LONG).show();
        }
        else
        {
            arrayList.add(ebdata);
            arrayAdapter.notifyDataSetChanged();
        }
        EditText editText = (EditText)findViewById(R.id.etBox);
        editText.setText("");
        //Intent intent = new Intent();
        // intent.setClass(MainActivity.this , EditFieldClass.class);
        //startActivityForResult(intent , Intents_Constants.INTENT_REQUEST_CODE);
    }
    public void onActivityResult(int requestCode , int resultCode , Intent data)
    {

        if (resultCode == Intents_Constants.INTENT_RESULT_CODE)
        {
            msgText = data.getStringExtra(Intents_Constants.INTENT_MESSAGE_FIELD);
            arrayList.add(msgText);
            arrayAdapter.notifyDataSetChanged();
        }
        else if (resultCode == Intents_Constants.INTENT_RESULT_CODE_TWO)
        {
            msgText = data.getStringExtra(Intents_Constants.INTENT_CHANGED_MESSAGE);
            position =  data.getIntExtra(Intents_Constants.INTENT_ITEM_POSITION, -1);
            arrayList.remove(position);
            arrayList.add(position , msgText);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
