package edu.matc.secretkeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSecret extends AppCompatActivity {

    MyDBHandler myDB;
    private Button btnAdd;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_secret);

        editText = (EditText) findViewById(R.id.inputSecretTxt);
        btnAdd = (Button) findViewById(R.id.addSecretBtn);

        myDB = new MyDBHandler(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEntry = editText.getText().toString();

                if(editText.length() != 0) {
                    myDB.storeSecret(newEntry); //pass string to storeSecret
                    editText.setText(""); //set text to ""
                } //end of if
                else {
                    toastMessage("Text field is empty.");
                }//end of else


            }//end of onclick
        }); //end of setonclick

    }//end of OnCreate

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }





}//end of class
