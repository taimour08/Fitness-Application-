//package com.i180686_i181657.splashscreen;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.database.Cursor;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ArrayAdapter;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//
//public class ListDataActivity extends AppCompatActivity {
//
//    ListView listView ;
//    private static final String  TAG = "ListDataActivity" ;
//    DatabaseHelper databaseHelper ;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_data);
//
//        listView = findViewById(R.id.listdata) ;
//
//        databaseHelper = new DatabaseHelper(this) ;
//
//        populateListView();
//    }
//
//    private void populateListView()
//    {
//
//        Log.d(TAG, "populateListView: Displaying Data in List View");
//
//        Cursor data = databaseHelper.getData();
//        ArrayList<String> arrayList = new ArrayList<>();
//
//        while(data.moveToNext())
//        {
//            arrayList.add(data.getString(0));
//            arrayList.add(data.getString(1));
//            arrayList.add(data.getString(2));
//
//        }
//
//        ListAdapter listAdapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1, arrayList) ;
//
//        listView.setAdapter(listAdapter);
//
//
//
//    }
//}