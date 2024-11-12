package com.example.numadfa24_aarzoobansal.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.numadfa24_aarzoobansal.R;
import com.example.numadfa24_aarzoobansal.ContactsCollector;
import com.example.numadfa24_aarzoobansal.model.ContactDetails;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolderClass> {

    private List<ContactDetails> contactDetailsList;
    Context context;
    private ContactsCollector obj;

    public ContactAdapter(Context context, List<ContactDetails> contactDetailsArrayList, ContactsCollector obj){
        this.context = context;
        this.contactDetailsList = contactDetailsArrayList;
        this.obj = obj;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.single_contact_recycler_view_row, parent, false);
        return new ContactAdapter.ContactViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder (@NonNull ContactAdapter.ContactViewHolderClass holder, int position){
        //assigning values to each row
        holder.contactName.setText(contactDetailsList.get(position).getName());
        holder.contactNumber.setText(contactDetailsList.get(position).getPhoneNo());
        holder.contactId.setText(String.valueOf(contactDetailsList.get(position).getContactId()));

        holder.delete.setOnClickListener(v-> {
            obj.onContactDelete(Integer.parseInt(String.valueOf(contactDetailsList.get(position).getContactId())));
        });

        holder.edit.setOnClickListener(v -> {
            obj.onContactEdit(Integer.parseInt(String.valueOf(contactDetailsList.get(position).getContactId())));
        });

        holder.contactPic.setOnClickListener(v -> {
            String phoneNo = contactDetailsList.get(position).getPhoneNo();
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + phoneNo));
            context.startActivity(dialIntent);
        });

    }

    @Override
    public int getItemCount() {
        //get the count of  number of items we have
        return contactDetailsList.size();
    }



    //Grab the view from recycler view row and assign them
    public static class ContactViewHolderClass extends RecyclerView.ViewHolder{

        ImageView contactPic;
        ImageButton edit;
        ImageButton delete;
        TextView contactName;
        TextView contactNumber;
        TextView contactId;

        public ContactViewHolderClass(@NonNull View itemView) {
            super(itemView);
            contactPic = itemView.findViewById(R.id.contactPic);
            edit = itemView.findViewById(R.id.editButton);
            contactId = itemView.findViewById(R.id.contactId);
            delete = itemView.findViewById(R.id.deleteButton);
            contactName = itemView.findViewById(R.id.contactName);
            contactNumber = itemView.findViewById(R.id.contactNumber);
        }
    }

}
