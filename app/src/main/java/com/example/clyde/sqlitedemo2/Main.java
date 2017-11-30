package com.example.clyde.sqlitedemo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    private static final String TAG = "Main";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

    }

    public void addButtonClicked(View view){
        Log.d(TAG, "addButton Clicked");
        String newEntry = editText.getText().toString();
        if(editText.length() !=0 ){
            AddData(newEntry);
            editText.setText("");
        }else{
            toastMessage("You must put something in the text field!");
        }
    }

    public void viewButtonClicked(View view){
        Intent intent = new Intent(Main.this, ListDataActivity.class);
        startActivity(intent);
    }

    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if(insertData){
            toastMessage("Data Successfully Inserted!");
        }else{
            toastMessage("Something went wrong!");
        }
    }

    public void toastMessage(String message){
        Toast.makeText(this, message ,Toast.LENGTH_SHORT).show();
    }
}
