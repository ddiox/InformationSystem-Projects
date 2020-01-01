package com.example.sisteminformasi.user;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sisteminformasi.R;

public class UserDetailActivity extends AppCompatActivity {
    private String idAccepted, nameAccepted, contactAccepted, addressAccepted, operationalAccepted, equipmentAccepted, pictureAccepted;
    private TextView tvName, tvName2, tvContact, tvAddress, tvOperational, tvEquipment;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        idAccepted = getIntent().getStringExtra("id");
        nameAccepted = getIntent().getStringExtra("name");
        contactAccepted = getIntent().getStringExtra("contact");
        addressAccepted = getIntent().getStringExtra("address");
        operationalAccepted = getIntent().getStringExtra("operational");
        equipmentAccepted = getIntent().getStringExtra("equipment");
        pictureAccepted = getIntent().getStringExtra("picture");

        tvName = findViewById(R.id.tv_title_detail);
        tvName2 = findViewById(R.id.tv_title2_detail);
        tvContact = findViewById(R.id.tv_contact_value);
        tvAddress = findViewById(R.id.tv_address_value);
        tvOperational = findViewById(R.id.tv_opertational_value);
        tvEquipment = findViewById(R.id.tv_equipment_value);
        imgPhoto = findViewById(R.id.img_studio_detail);

        tvName.setText(nameAccepted);
        tvName2.setText(nameAccepted);
        tvContact.setText(contactAccepted);
        tvAddress.setText(addressAccepted);
        tvOperational.setText(operationalAccepted);
        tvEquipment.setText(equipmentAccepted);

        Glide.with(getApplicationContext())
                .load(pictureAccepted)
                .apply(new RequestOptions().override(55, 55))
                .into(imgPhoto);

    }
}
