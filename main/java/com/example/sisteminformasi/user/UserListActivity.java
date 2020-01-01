package com.example.sisteminformasi.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sisteminformasi.R;
import com.example.sisteminformasi.adapter.RequestAdapterRecyclerView;
import com.example.sisteminformasi.adapter.RequestUserAdapterRecyclerView;
import com.example.sisteminformasi.admin.InputActivity;
import com.example.sisteminformasi.model.Requests;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
    private DatabaseReference database;
    private ArrayList<Requests> daftarReq;
    private RequestUserAdapterRecyclerView requestUserAdapterRecyclerView;
    private RecyclerView rc_user_list_request;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        database = FirebaseDatabase.getInstance().getReference();

        rc_user_list_request = findViewById(R.id.rc_user_list_request);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rc_user_list_request.setLayoutManager(mLayoutManager);
        rc_user_list_request.setItemAnimator(new DefaultItemAnimator());

        loading = ProgressDialog.show(UserListActivity.this,
                null,
                "Please wait...",
                true,
                false);

        database.child("Request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                daftarReq = new ArrayList<>();

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Requests requests = noteDataSnapshot.getValue(Requests.class);
                    requests.setKey(noteDataSnapshot.getKey());

                    daftarReq.add(requests);
                }

                requestUserAdapterRecyclerView = new RequestUserAdapterRecyclerView(daftarReq, UserListActivity.this);
                rc_user_list_request.setAdapter(requestUserAdapterRecyclerView);
                loading.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                loading.dismiss();
            }
        });
    }
}
