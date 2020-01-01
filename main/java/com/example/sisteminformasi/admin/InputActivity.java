package com.example.sisteminformasi.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sisteminformasi.R;
import com.example.sisteminformasi.model.Requests;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputActivity extends AppCompatActivity {
    private EditText etName, etContact, etAddress, etOperational, etEquipment, etPicture;
    private String name, contact, address, operational, equipment, picture;
    private String idAccepted, nameAccepted, contactAccepted, addressAccepted, operationalAccepted, equipmentAccepted, pictureAccepted;
    private Button btnSave, btnCancel;
    private ProgressDialog loading;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        etName = findViewById(R.id.et_name);
        etContact = findViewById(R.id.et_contact);
        etAddress = findViewById(R.id.et_address);
        etOperational = findViewById(R.id.et_operational);
        etEquipment = findViewById(R.id.et_equipment);
        etPicture = findViewById(R.id.et_picture);

        idAccepted = getIntent().getStringExtra("id");
        nameAccepted = getIntent().getStringExtra("name");
        contactAccepted = getIntent().getStringExtra("contact");
        addressAccepted = getIntent().getStringExtra("address");
        operationalAccepted = getIntent().getStringExtra("operational");
        equipmentAccepted = getIntent().getStringExtra("equipment");
        pictureAccepted = getIntent().getStringExtra("picture");

        etName.setText(nameAccepted);
        etContact.setText(contactAccepted);
        etAddress.setText(addressAccepted);
        etOperational.setText(operationalAccepted);
        etEquipment.setText(equipmentAccepted);
        etPicture.setText(pictureAccepted);

        btnSave = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel);

        database = FirebaseDatabase.getInstance().getReference();

        if (idAccepted.equals("")) {
            btnSave.setText("Save");
            btnCancel.setText("Cancel");
        } else {
            btnSave.setText("Edit");
            btnCancel.setText("Delete");
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnCancel.getText().equals("Cancel")) {
                    finish();
                } else {
                    database.child("Request")
                            .child(idAccepted)
                            .removeValue()
                            .addOnSuccessListener(InputActivity.this, new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(InputActivity.this, "Data is deleted", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                contact = etContact.getText().toString();
                address = etAddress.getText().toString();
                operational = etOperational.getText().toString();
                equipment = etEquipment.getText().toString();
                picture = etPicture.getText().toString();

                if (btnSave.getText().equals("Save")) {
                    if (name.equals("")) {
                        etName.setError("Fill the name");
                        etName.requestFocus();
                    } else if (contact.equals("")) {
                        etContact.setError("Fill the contact");
                        etContact.requestFocus();
                    } else if (address.equals("")) {
                        etAddress.setError("Fill the address");
                        etAddress.requestFocus();
                    } else if (operational.equals("")) {
                        etOperational.setError("Fill the operational");
                        etOperational.requestFocus();
                    } else if (equipment.equals("")) {
                        etEquipment.setError("Fill the equipment");
                        etEquipment.requestFocus();
                    } else if (picture.equals("")) {
                        etPicture.setError("Fill the picture");
                        etPicture.requestFocus();
                    } else {
                        loading = ProgressDialog.show(InputActivity.this, null, "Please Wait", true, false);
                        submituser(new Requests(name.toLowerCase(), contact.toLowerCase(), address.toLowerCase(), operational.toLowerCase(), equipment.toLowerCase(), picture.toLowerCase()));
                    }
                } else {
                    if (name.equals("")) {
                        etName.setError("Fill the name");
                        etName.requestFocus();
                    } else if (contact.equals("")) {
                        etContact.setError("Fill the contact");
                        etContact.requestFocus();
                    } else if (address.equals("")) {
                        etAddress.setError("Fill the address");
                        etAddress.requestFocus();
                    } else if (operational.equals("")) {
                        etOperational.setError("Fill the operational");
                        etOperational.requestFocus();
                    } else if (equipment.equals("")) {
                        etEquipment.setError("Fill the equipment");
                        etEquipment.requestFocus();
                    } else if (picture.equals("")) {
                        etPicture.setError("Fill the picture");
                        etPicture.requestFocus();
                    } else {
                        loading = ProgressDialog.show(InputActivity.this, null, "Please Wait", true, false);
                        edituser(new Requests(name.toLowerCase(), contact.toLowerCase(), address.toLowerCase(), operational.toLowerCase(), equipment.toLowerCase(), picture.toLowerCase()), idAccepted);
                    }
                }
            }
        });
    }

    public void submituser(Requests requests) {
        database.child("Request")
                .push()
                .setValue(requests)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        loading.dismiss();
                        etName.setText("");
                        etContact.setText("");
                        etAddress.setText("");
                        etOperational.setText("");
                        etEquipment.setText("");
                        etPicture.setText("");

                        Toast.makeText(InputActivity.this, "Data is added", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void edituser(Requests requests, String id) {
        database.child("Request")
                .child(id)
                .setValue(requests)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        loading.dismiss();
                        etName.setText("");
                        etContact.setText("");
                        etAddress.setText("");
                        etOperational.setText("");
                        etEquipment.setText("");
                        etPicture.setText("");

                        Toast.makeText(InputActivity.this, "Data is edited", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
