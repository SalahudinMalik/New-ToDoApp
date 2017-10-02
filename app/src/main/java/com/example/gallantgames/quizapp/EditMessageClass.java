package com.example.gallantgames.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditMessageClass extends AppCompatActivity {
    String msgText;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_layout);
        Intent intent = getIntent();
        msgText = intent.getStringExtra(Intents_Constants.INTENT_MESSAGE_DATA);
        position = intent.getIntExtra(Intents_Constants.INTENT_ITEM_POSITION , -1);
        EditText messageData = (EditText)findViewById(R.id.message);
        messageData.setText(msgText);

    }
    public void saveButtonClicked(View v)
    {
        String changedMsgText = ((EditText) findViewById(R.id.message)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Intents_Constants.INTENT_CHANGED_MESSAGE , changedMsgText);
        intent.putExtra(Intents_Constants.INTENT_ITEM_POSITION , position);
        setResult(Intents_Constants.INTENT_RESULT_CODE_TWO , intent);
        finish();
    }
}
