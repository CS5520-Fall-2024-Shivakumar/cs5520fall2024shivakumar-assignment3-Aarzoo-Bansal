package com.example.numadfa24_aarzoobansal;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.numadfa24_aarzoobansal.model.ContactDetails;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class AddContacts extends AppCompatActivity {

    private List<ContactDetails> contactDetailsList;
    private EditText userName;
    private EditText userPhoneNo;
    private EditText userAlternateNo;
    private EditText userEmail;
    private ContactsDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts2);

        db = new ContactsDataBase(this);
        userName = findViewById(R.id.editUserName);
        userPhoneNo = findViewById(R.id.editUserPhoneNo);
        userEmail = findViewById(R.id.userEmailAddress);
        userAlternateNo = findViewById(R.id.editAlternatePhoneNo);


        Toolbar toolbar = (Toolbar) findViewById(R.id.editContactsToolbar);
        setSupportActionBar(toolbar); //calling this function so that toolbar works as an action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    //We need to override this method as we have added back button in the toolbar which is used to go back to the previous activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) { //android.R.id.home is the system-defined ID for home and up (usually represented by back) button
            finish(); //In this line of code, we are finishing the current activity and returning the next activity in the stack (i.e., the activity which was running previously)
            return true;
        }
        return super.onOptionsItemSelected(item); //If there was not a back/home button that whatever behavior is defined in the super class will be executed
    }

    //function that will run when I click on Add contact button - here we need to send data from the child activity to the parent activity
    public void addContact(View v){
        String user_name = userName.getText().toString().trim();
        String user_phone = userPhoneNo.getText().toString().trim();
        String user_email = userEmail.getText().toString().trim();
        String user_alternate_number = userAlternateNo.getText().toString().trim();

        if(user_name.isEmpty() || user_phone.isEmpty()){
            Snackbar.make(v, "Please ensure that name and phone number are added.", Snackbar.LENGTH_SHORT).show();
        }else{
            //adding the data into our database
            if(user_email.isEmpty()){
               user_email = "";
            }

            if(user_alternate_number.isEmpty()){
                user_alternate_number = "";
            }
            long resultId = db.addContacts(user_name, user_phone, user_email, user_alternate_number);

            if(resultId == -1){
                Toast.makeText(v.getContext(), "Unable to add to the database. Please try again later.", Toast.LENGTH_SHORT).show();
            }else{
                //Toast.makeText(v.getContext(), "Contact added successfully.", Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "Contact added successfully." , Snackbar.LENGTH_SHORT).show();

            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1000);

        }

    }

}