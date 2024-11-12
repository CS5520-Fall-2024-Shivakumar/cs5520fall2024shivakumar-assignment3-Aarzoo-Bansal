package com.example.numadfa24_aarzoobansal.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.numadfa24_aarzoobansal.ContactsDataBase;
import com.example.numadfa24_aarzoobansal.R;
import com.example.numadfa24_aarzoobansal.ContactsCollector;
import com.example.numadfa24_aarzoobansal.model.ContactDetails;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolderClass> {

    private List<ContactDetails> contactDetailsList;
    Context context;
    private ContactListener listener;
    private ContactsCollector obj;

    public ContactAdapter(Context context, List<ContactDetails> contactDetailsArrayList, ContactsCollector obj){
        this.context = context;
        this.contactDetailsList = contactDetailsArrayList;
        this.obj = obj;
    }

    public ContactAdapter(List<ContactDetails> contactDetailsList, ContactListener listener){
        this.contactDetailsList = contactDetailsList;
        this.listener = listener;
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


    public interface ContactListener {
        void onContactAdd(int position);
        void onContactDelete(int position);
    }



//    @NonNull
//    @Override
//    public ContactViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_single_contact_view, parent, false);
//        return new ContactViewHolderClass(view);
//    }

//    @Override
//    //Binding views for each contact item
//    public void onBindViewHolder(@NonNull ContactViewHolderClass holder, int position) {
//        ContactDetails contact = contactDetailsList.get(position);
//        holder.nameTextView.setText(contact.getName());
//        holder.phoneTextView.setText(contact.getPhoneNo());
//
//        //Handle the Visibility
//        holder.editName.setText(contact.getName());
//        holder.editPhone.setText(contact.getPhoneNo());
//        holder.editName.setVisibility(View.GONE);
//        holder.editPhone.setVisibility(View.GONE);
//        holder.saveButton.setVisibility(View.GONE);
//        holder.editButton.setVisibility(View.VISIBLE);
//
//        //Edit Button
//        holder.editButton.setOnClickListener(v -> {
//            holder.nameTextView.setVisibility(View.GONE);
//            holder.phoneTextView.setVisibility(View.GONE);
//            holder.editName.setVisibility(View.VISIBLE);
//            holder.editPhone.setVisibility(View.VISIBLE);
//            holder.saveButton.setVisibility(View.VISIBLE);
//            holder.editButton.setVisibility(View.GONE);
//        });
//
//        //Save Button
//        holder.saveButton.setOnClickListener(v -> {
//            String newName = holder.editName.getText().toString().trim();
//            String newPhone = holder.editPhone.getText().toString().trim();
//
//            if (newName.isEmpty() || newPhone.isEmpty()) {
//                Snackbar.make(holder.itemView, "Name and phone number cannot be empty", Snackbar.LENGTH_SHORT).show();
//                return;
//            }
//
//            //Set value and update visibility
//            contact.setName(newName);
//            contact.setPhoneNo(newPhone);
//            holder.nameTextView.setText(newName);
//            holder.phoneTextView.setText(newPhone);
//
//            holder.nameTextView.setVisibility(View.VISIBLE);
//            holder.phoneTextView.setVisibility(View.VISIBLE);
//            holder.editName.setVisibility(View.GONE);
//            holder.editPhone.setVisibility(View.GONE);
//            holder.saveButton.setVisibility(View.GONE);
//            holder.editButton.setVisibility(View.VISIBLE);
//
//            notifyItemChanged(position);
//            Snackbar.make(holder.itemView, "Contact updated", Snackbar.LENGTH_SHORT).show();
//        });
//
//        //Deletion
//        holder.deleteButton.setOnClickListener(v -> {
//            //forwards the call to the actual method in ContactsActivity
//            listener.onContactDelete(position);
//        });
//    }

//    @Override
//    public int getItemCount() {
//        return contactDetailsList.size();
//    }

//    static class ContactViewHolderClass extends RecyclerView.ViewHolder {
//        TextView nameTextView;
//        TextView phoneTextView;
//        EditText editName;
//        EditText editPhone;
//        Button editButton;
//        Button saveButton;
//        Button deleteButton;
//
//        ContactViewHolderClass(View itemView) {
//            super(itemView);
//           // nameTextView = itemView.findViewById(R.id.user_name);
//           // phoneTextView = itemView.findViewById(R.id.user_number);
////            //editName = itemView.findViewById(R.id.user_name);
////            editPhone = itemView.findViewById(R.id.editContactPhone);
////            editButton = itemView.findViewById(R.id.buttonEdit);
////            saveButton = itemView.findViewById(R.id.buttonSave);
////            deleteButton = itemView.findViewById(R.id.buttonDelete);
//        }
//
//    }




}
