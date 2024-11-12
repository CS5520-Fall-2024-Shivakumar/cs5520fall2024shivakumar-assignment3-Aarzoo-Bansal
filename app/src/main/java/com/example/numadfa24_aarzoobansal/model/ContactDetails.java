package com.example.numadfa24_aarzoobansal.model;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import android.util.Log;
import android.util.Log;

import java.util.Arrays;
import java.util.List;


public class ContactDetails {

    private int contactId;
    private String name;
    private String phoneNo;
    private String email = "";
    private String alternatePhone = "";


    public ContactDetails(int id, String name, String phoneNo, String email, String alternatePhone){
        this.contactId = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.alternatePhone = alternatePhone;
        Log.println(Log.INFO, "Contact details list: name " , this.name);
        Log.println(Log.INFO, "Contact details list: email " , this.email);
        Log.println(Log.INFO, "Contact details list: phone " , this.phoneNo);
        Log.println(Log.INFO, "Contact details list: alternate phone " , this.alternatePhone);
    }

    public int getContactId(){
        return this.contactId;
    }

    public void setContactId(int contactId){
        this.contactId = contactId;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhoneNo(){
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getAlternatePhone(){
        return this.alternatePhone;
    }

    public void setAlternatePhone(String alternatePhone){
        this.alternatePhone = alternatePhone;
    }
}
