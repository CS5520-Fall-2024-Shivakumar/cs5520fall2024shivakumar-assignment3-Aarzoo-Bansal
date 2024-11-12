package com.example.numadfa24_aarzoobansal;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class singleContactView extends AppCompatActivity {

    TextView contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.single_contact_recycler_view_row);

        contactId = findViewById(R.id.contactId);
        contactId.setVisibility(View.INVISIBLE);
    }


}