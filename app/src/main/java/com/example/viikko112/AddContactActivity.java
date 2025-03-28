package com.example.viikko112;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {
    private EditText firstNameEdit, lastNameEdit, phoneNumberEdit;
    private RadioGroup contactTypeRadioGroup;
    private Button addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        firstNameEdit = findViewById(R.id.FirstNameEdit);
        lastNameEdit = findViewById(R.id.LastNameEdit);
        phoneNumberEdit = findViewById(R.id.PhoneNumberEdit);
        contactTypeRadioGroup = findViewById(R.id.ContactTypeRadioGroup);
        addContactButton = findViewById(R.id.AddContactButton);

        addContactButton.setOnClickListener(v -> {
            String firstName = firstNameEdit.getText().toString();
            String lastName = lastNameEdit.getText().toString();
            String phoneNumber = phoneNumberEdit.getText().toString();
            int checkedId = contactTypeRadioGroup.getCheckedRadioButtonId();
            String contactGroup = "Henkilökohtainen";
            if (checkedId == R.id.WorkRadioButton) {
                contactGroup = "Työ";
            }

            if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(AddContactActivity.this, "Täytä kaikki kentät", Toast.LENGTH_SHORT).show();
            } else {
                Contact newContact = new Contact(firstName, lastName, phoneNumber, contactGroup);
                ContactStorage.getInstance().addContact(newContact);
                finish();
            }
        });
    }
}

