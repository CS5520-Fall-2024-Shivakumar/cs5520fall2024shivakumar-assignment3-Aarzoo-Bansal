package com.example.numadfa24_aarzoobansal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class EditContactInfo extends AppCompatActivity {

    private String contactId;
    private EditText userName;
    private EditText userEmail;
    private EditText userPhone;
    private EditText userAlternateNo;
    private ContactsDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_contact_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new ContactsDataBase(this);

        userName = findViewById(R.id.editUserName);
        userEmail = findViewById(R.id.userEmailAddress);
        userPhone = findViewById(R.id.editUserPhoneNo);
        userAlternateNo = findViewById(R.id.editAlternatePhoneNo);

        Intent intent = getIntent();
        userName.setText(intent.getStringExtra("userName"));
        userEmail.setText(intent.getStringExtra("userEmail"));
        userPhone.setText(intent.getStringExtra("userPhoneNo"));
        userAlternateNo.setText(intent.getStringExtra("userAlternatePhoneNo"));
        contactId = intent.getStringExtra("contactID");

        Toolbar toolbar = (Toolbar) findViewById(R.id.editContactsToolbar);
        setSupportActionBar(toolbar); //calling this function so that toolbar works as an action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button save = findViewById(R.id.saveContactDetails);

        save.setOnClickListener(v -> {
            String user_name = userName.getText().toString();
            String user_email = userEmail.getText().toString();
            String user_phone = userPhone.getText().toString();
            String user_alternate = userAlternateNo.getText().toString();

            if (user_name.isEmpty() || user_phone.isEmpty()) {
                Snackbar.make(v, "Please ensure that name and phone number are added.", Snackbar.LENGTH_SHORT).show();
            } else {
                int rows = db.updateRecord(contactId, user_name, user_phone, user_email, user_alternate);
                if (rows > 0) {
                    Toast.makeText(v.getContext(), "Contact Updated Successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Unable to update the contact. Please try again later.", Toast.LENGTH_SHORT).show();

                }
                finish();
            }
        });

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
}