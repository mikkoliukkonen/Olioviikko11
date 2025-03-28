package com.example.viikko112;


import java.util.ArrayList;

public class ContactStorage {
    private static ContactStorage instance;
    private ArrayList<Contact> contacts;

    private ContactStorage() {
        contacts = new ArrayList<>();
    }

    public static ContactStorage getInstance() {
        if (instance == null) {
            instance = new ContactStorage();
        }
        return instance;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(int index) {
        contacts.remove(index);
    }
}

