package com.example.clyde.sqlitedemo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Clyde on 11/29/2017.
 */

public class EditDataActivity extends AppCompatActivity{

    private static final String TAG = "EditDataActivity";
    private Button btnSave, btnDelete;
    private EditText editable_item;
    DatabaseHelper mDatabaseHelper;
    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        mDatabaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id",-1);
        selectedName = receivedIntent.getStringExtra("name");
        editable_item.setText(selectedName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item, selectedID, selectedName);
                    toastMessage("Name has been saved");
                    Intent listScreenIntent = new Intent(EditDataActivity.this, ListDataActivity.class);
                    startActivity(listScreenIntent);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID, selectedName);
                editable_item.setText("");
                toastMessage("removed from database");
                Intent listScreenIntent = new Intent(EditDataActivity.this, ListDataActivity.class);
                startActivity(listScreenIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditDataActivity.this,Main.class));
        finish();
    }

    public void toastMessage(String message){
        Toast.makeText(this, message ,Toast.LENGTH_SHORT).show();
    }
}
