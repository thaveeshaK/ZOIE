package me.thaveesha.zoie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import me.thaveesha.zoie.models.Mood;

public class retreiveMoodDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_mood_details);

        TextView id = findViewById(R.id.txtIdRet);
        TextView name = findViewById(R.id.txtNameRet);
        Button button = findViewById(R.id.btnRetrive);



        //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("MoodData");
        //Query checkUser = databaseReference.orderByChild("dateTime").equalTo(userEnteredUsername);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retreiveMoodData();


            }
        });
    }

    public void retreiveMoodData(){
        DBHelperData dbHelperData = new DBHelperData(this);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dinosaursRef = database.getReference("MoodData");
        dinosaursRef.orderByChild("dateTime").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Mood mood = dataSnapshot.getValue(Mood.class);
                boolean checkInsertData = dbHelperData.insertData(mood.getDateTime(),  mood.getMoodStatus());
                System.out.println(dataSnapshot.getKey() + " was " + mood.getDateTime() + " meters tall." + mood.getMoodStatus());
                System.out.println("Insert data status : "+checkInsertData);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            // ...
        });
        }
}