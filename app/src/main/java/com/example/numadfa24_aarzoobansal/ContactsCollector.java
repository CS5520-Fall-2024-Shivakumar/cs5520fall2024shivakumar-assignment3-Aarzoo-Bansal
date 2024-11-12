package com.example.numadfa24_aarzoobansal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.numadfa24_aarzoobansal.adapter.ContactAdapter;
import com.example.numadfa24_aarzoobansal.model.ContactDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class ContactsCollector extends AppCompatActivity {

    private static final int ADD_CONTACT_REQUEST = 1;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    ContactsDataBase db;
    Toolbar toolbar;

    private ContactAdapter contactAdapter;
    private FloatingActionButton addContactsFab;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contacts_collector);
        db = new ContactsDataBase(this);

        addContactsFab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.contactListRecyclerView);
        recyclerView.setHasFixedSize(true);

        toolbar = findViewById(R.id.contactCollectorToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //This is to launch the create contacts activity once the fab button is clicked
        addContactsFab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddContacts2.class);
            startActivity(intent);
        });
    }


    public void initializeData(){
        Cursor cursor = db.readAllData();

        Log.println(Log.WARN, "datacount" ,"hello11222 " +String.valueOf(cursor.getCount()));

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data has been entered yet" , Toast.LENGTH_SHORT).show();
        }else{
            List<ContactDetails> contactList = new ArrayList<>();
            while (cursor.moveToNext()){
                Log.println(Log.INFO, "datacount id of data", cursor.getString(0));
                contactList.add(new ContactDetails(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)) );
            }

            contactAdapter = new ContactAdapter(this, contactList, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(contactAdapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //onResume function is called after onCreate function
    @Override
    protected void onResume() {
        super.onResume();
        initializeData();
    }

    public void onContactDelete(int contactId) {
       int rows =  db.deleteRecord(String.valueOf(contactId));
       if(rows > 0){
           initializeData();
           Snackbar.make(findViewById(R.id.contactListRecyclerView), "Contact deleted", Snackbar.LENGTH_SHORT).show();
       }
    }

    public void onContactEdit(int contactId){}

}