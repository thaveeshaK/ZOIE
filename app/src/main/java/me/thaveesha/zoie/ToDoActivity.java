package me.thaveesha.zoie;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ToDoActivity extends AppCompatActivity {

    List<String> toDoList;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        toDoList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,R.layout.list_view_layout,toDoList);
        listView = findViewById(R.id.id_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView)  view;
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });

        listView.setAdapter(arrayAdapter);

        editText = findViewById(R.id.id_edit_text);


    }

    public void addItemToList(View view){
        if(validate()){
            toDoList.add(editText.getText().toString());
            arrayAdapter.notifyDataSetChanged();

            editText.setText("");
        }

    }
    private boolean validate() {

        String val = editText.getText().toString();
        if (val.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please enter a To Do to add",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }


}