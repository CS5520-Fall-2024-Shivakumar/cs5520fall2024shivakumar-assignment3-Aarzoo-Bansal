//package com.example.numadfa24_aarzoobansal;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import com.example.numadfa24_aarzoobansal.model.ContactDetails;
//import com.google.android.material.snackbar.Snackbar;
//
//import java.util.List;
//
//
//public class EditContacts extends AppCompatActivity {
//
//    private List<ContactDetails> contactDetailsList;
//    private EditText userName;
//    private EditText userPhoneNo;
//    private EditText userAlternateNo;
//    private EditText userEmail;
//    private ContactsDataBase db;
//    private String contact_id;
//    private Context context;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_contact);
//
//        db = new ContactsDataBase(this);
//        userName = findViewById(R.id.editUserName);
//        userPhoneNo = findViewById(R.id.editUserPhoneNo);
//        userEmail = findViewById(R.id.userEmailAddress);
//        userAlternateNo = findViewById(R.id.editAlternatePhoneNo);
//
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.editContactsToolbar);
//        setSupportActionBar(toolbar); //calling this function so that toolbar works as an action bar
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//
////        Intent intent = getIntent();
////        String user_name = intent.getStringExtra("userName");
////        contact_id = intent.getStringExtra("contactID");
////        String user_phone = intent.getStringExtra("userPhoneNo");
////        String user_alternatePhoneNo = intent.getStringExtra("userAlternatePhoneNo");
////        String user_email = intent.getStringExtra("userEmail");
////
////        userName.setText(user_name);
////        userPhoneNo.setText(user_phone);
////        userEmail.setText(user_email);
////        userAlternateNo.setText(user_alternatePhoneNo);
//
//    }
//
//    //We need to override this method as we have added back button in the toolbar which is used to go back to the previous activity
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == android.R.id.home) { //android.R.id.home is the system-defined ID for home and up (usually represented by back) button
//            finish(); //In this line of code, we are finishing the current activity and returning the next activity in the stack (i.e., the activity which was running previously)
//            return true;
//        }
//        return super.onOptionsItemSelected(item); //If there was not a back/home button that whatever behavior is defined in the super class will be executed
//    }
//
//    //function that will run when I click on Add contact button - here we need to send data from the child activity to the parent activity
//    public void saveContact(View v){
//        String contact_id = this.contact_id;
//        String user_name = userName.getText().toString().trim();
//        String user_phone = userPhoneNo.getText().toString().trim();
//        String user_email = userEmail.getText().toString().trim();
//        String user_alternate_number = userAlternateNo.getText().toString().trim();
//
//        if(user_name.isEmpty() || user_phone.isEmpty()){
//            Snackbar.make(v, "Please ensure that name and phone number are added.", Snackbar.LENGTH_SHORT).show();
//        }else{
//            //adding the data into our database
//            if(user_email.isEmpty()){
//                user_email = "";
//            }
//
//            if(user_alternate_number.isEmpty()){
//                user_alternate_number = "";
//            }
//            long resultId = db.updateRecord(contact_id, user_name, user_phone, user_email, user_alternate_number);
//
//            if(resultId < 0){
//                Toast.makeText(v.getContext(), "Unable to add to the database. Please try again later.", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(v.getContext(), "Contact updated successfully.", Toast.LENGTH_SHORT).show();
//            }
//            finish();
//        }
//
//    }
//
//}