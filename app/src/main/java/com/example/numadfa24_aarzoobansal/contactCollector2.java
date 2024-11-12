//package com.example.numadfa24_aarzoobansal;
//
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.Bundle;
//
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.util.Log;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.numadfa24_aarzoobansal.adapter.ContactAdapter;
//import com.example.numadfa24_aarzoobansal.model.ContactDetails;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.android.material.snackbar.Snackbar;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
//
//public class contactsCollector2 extends AppCompatActivity {
//
//    private static final int ADD_CONTACT_REQUEST = 1;
//    private ActivityResultLauncher<Intent> activityResultLauncher;
//    ContactsDataBase db;
//    Toolbar toolbar;
//    private ArrayList<ContactDetails> contactDetailsList = new ArrayList<>(); //ArrayList to store the details of contacts
//    private ContactAdapter contactAdapter;
//    private FloatingActionButton addContactsFab;
//    private RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_contacts_collector);
//        //RecyclerView recyclerView = findViewById(R.id.contactListRecyclerView);
//        db = new ContactsDataBase(this);
//
//        //This function is used when result is returned from the child activity
////        activityResultLauncher = registerForActivityResult(
////                new ActivityResultContracts.StartActivityForResult(),
////                result -> {
////                    Log.v("***resultCode", String.valueOf(result.getResultCode()));
////                    if( result.getResultCode() == RESULT_OK){
////
////                        Intent data = result.getData();
////                        assert data != null;
////
////                        String user_name = data.getStringExtra("user_name");
////                        String user_phone = data.getStringExtra("user_phone");
////                        String user_email = data.getStringExtra("user_email");
////                        String user_alternate_phone = data.getStringExtra("user_alternate_no");
////
////                        boolean add = contactDetailsList.add(new ContactDetails(user_name, user_phone, user_email, user_alternate_phone)); //we are adding the contact returned to the contactList Array
////                        Log.println(Log.INFO, "Contact details list: " , Arrays.toString(new List[]{contactDetailsList}));
////                        Snackbar.make(findViewById(R.id.contactCollectormain), "Contact Created Successfully", Snackbar.LENGTH_LONG).show();
////                    }
////                }
////        );
//
//
//
//
//        recyclerView = findViewById(R.id.contactListRecyclerView);
//        recyclerView.setHasFixedSize(true);
////        recyclerView.setAdapter(contactAdapter);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        toolbar = findViewById(R.id.contactCollectorToolbar);
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//
//        //This is to launch the create contacts activity once the fab button is clicked
//        addContactsFab = findViewById(R.id.fab);
//        addContactsFab.setOnClickListener(v -> {
//            Intent intent = new Intent(this, add_contacts2.class);
////           // startActivityForResult(intent, ADD_CONTACT_REQUEST);
//            //activityResultLauncher.launch(intent);
//            startActivity(intent);
////            Intent intent = new Intent(contactsCollector.this, add_contacts2.class);
////            startActivity(intent);
//        });
//
//
//
//
//
//
//
//
//        // contactAdapter = new ContactAdapter(contactDetailsList, new ContactAdapter.ContactListener() {
//
////            @Override
////            public void onContactAdd(int position) {
////                ContactDetails contact = contactDetailsList.get(position);
////                Intent intent = new Intent(Intent.ACTION_DIAL);
////                intent.setData(android.net.Uri.parse("tel:" + contact.getPhoneNo()));
////                startActivity(intent);
////
////            }
////
////            @Override
////            public void onContactDelete(int position) {
////                contactsCollector.this.onContactDelete(position);
////
////            }
////        });
//
//
//        // contactsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        //  contactsRecyclerView.setAdapter(contactAdapter);
//
//
//
//        initializeData();
//
//    }
//
//
//    public void initializeData(){
//        Cursor cursor = db.readAllData();
//
//        Log.println(Log.WARN, "datacount" ,"hello" +String.valueOf(cursor.getCount()));
//
//        if(cursor.getCount() == 0){
//            Toast.makeText(this, "No data has been entered yet" , Toast.LENGTH_SHORT).show();
//        }else{
//            while (cursor.moveToNext()){
//                Log.println(Log.INFO, "datacount id of data", cursor.getString(0));
//                contactDetailsList.add(new ContactDetails(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)) );
//            }
//            contactAdapter = new ContactAdapter(this, contactDetailsList, this);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(contactAdapter);
//        }
//    }
//
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putSerializable("contactsList", new ArrayList<>(contactDetailsList));
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        contactDetailsList.clear();
//        contactDetailsList.addAll((List<ContactDetails>) savedInstanceState.getSerializable("contactsList"));
//        contactAdapter.notifyDataSetChanged();
//    }
//
//    public void onContactDelete(int position) {
//        contactDetailsList.remove(position);
//        contactAdapter.notifyItemRemoved(position);
//        Snackbar.make(findViewById(R.id.contactListRecyclerView), "Contact deleted", Snackbar.LENGTH_SHORT).show();
//    }
//
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////
////        if (requestCode == ADD_CONTACT_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
////            String name = data.getStringExtra("user_name");
////            String phoneNumber = data.getStringExtra("user_phone");
////            String email = data.getStringExtra("user_email");
////            Log.v("userName", name);
////            contactDetailsList.add(new ContactDetails(name, phoneNumber));
////            contactAdapter.notifyItemInserted(contactDetailsList.size() - 1);
////            Snackbar.make(findViewById(R.id.contactLists), "Contact added", Snackbar.LENGTH_SHORT).show();
////        }
////    }
//
//}