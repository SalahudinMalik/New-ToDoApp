package com.example.gallantgames.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditFieldClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_layout);

    }

    public void saveButtonClicked(View v)
    {
        String msgText = ((EditText) findViewById(R.id.message)).getText().toString();

        if(msgText.equals("")){

        }
        else
        {
            Intent intent = new Intent();
            intent.putExtra(Intents_Constants.INTENT_MESSAGE_FIELD , msgText);
            setResult(Intents_Constants.INTENT_RESULT_CODE , intent);
            finish();

        }

    }
}
