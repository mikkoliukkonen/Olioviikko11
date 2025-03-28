package com.example.viikko112;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.contactNameText.setText(contact.getFullName());
        holder.contactNumberText.setText(contact.getNumber());
        holder.contactGroupText.setText(contact.getContactGroup());
        holder.contactNumberText.setVisibility(View.GONE);
        holder.contactGroupText.setVisibility(View.GONE);
        holder.contactDetailsButton.setText("Näytä tiedot");

        holder.contactDetailsButton.setOnClickListener(v -> {
            if (holder.contactNumberText.getVisibility() == View.GONE) {
                holder.contactNumberText.setVisibility(View.VISIBLE);
                holder.contactGroupText.setVisibility(View.VISIBLE);
                holder.contactDetailsButton.setText("Piilota tiedot");
            } else {
                holder.contactNumberText.setVisibility(View.GONE);
                holder.contactGroupText.setVisibility(View.GONE);
                holder.contactDetailsButton.setText("Näytä tiedot");
            }
        });

        holder.contactDeleteButton.setOnClickListener(v -> {
            ContactStorage.getInstance().removeContact(holder.getAdapterPosition());
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView contactNameText, contactNumberText, contactGroupText;
        Button contactDetailsButton, contactDeleteButton;

        public ContactViewHolder(View itemView) {
            super(itemView);
            contactNameText = itemView.findViewById(R.id.ContactNameText);
            contactNumberText = itemView.findViewById(R.id.ContactNumberText);
            contactGroupText = itemView.findViewById(R.id.ContactGroupText);
            contactDetailsButton = itemView.findViewById(R.id.ContactDetailsButton);
            contactDeleteButton = itemView.findViewById(R.id.ContactDeleteButton);
        }
    }
}

