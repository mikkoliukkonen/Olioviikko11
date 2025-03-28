package com.example.viikko112;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listContactsRV;
    private ContactAdapter contactAdapter;
    private Button addContactActivityButton, sortAlphabeticallyButton, sortByGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listContactsRV = findViewById(R.id.ListContactsRV);
        addContactActivityButton = findViewById(R.id.AddContactActivityButton);
        sortAlphabeticallyButton = findViewById(R.id.SortAlphabeticallyButton);
        sortByGroupButton = findViewById(R.id.SortByGroupButton);

        contactAdapter = new ContactAdapter(ContactStorage.getInstance().getContacts());
        listContactsRV.setLayoutManager(new LinearLayoutManager(this));
        listContactsRV.setAdapter(contactAdapter);

        addContactActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);
        });

        sortAlphabeticallyButton.setOnClickListener(v -> {
            Collections.sort(ContactStorage.getInstance().getContacts(),
                    (c1, c2) -> c1.getFirstName().compareToIgnoreCase(c2.getFirstName())
            );
            contactAdapter.notifyDataSetChanged();
        });

        sortByGroupButton.setOnClickListener(v -> {
            ArrayList<Contact> allContacts = ContactStorage.getInstance().getContacts();
            ArrayList<Contact> sortedList = new ArrayList<>();
            Iterator<Contact> it = allContacts.iterator();
            while (it.hasNext()) {
                Contact c = it.next();
                if (c.getContactGroup().equals("Työ")) {
                    sortedList.add(c);
                }
            }
            it = allContacts.iterator();
            while (it.hasNext()) {
                Contact c = it.next();
                if (c.getContactGroup().equals("Henkilökohtainen")) {
                    sortedList.add(c);
                }
            }
            allContacts.clear();
            allContacts.addAll(sortedList);
            contactAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactAdapter.notifyDataSetChanged();
    }
}
