package com.nasa.pictures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.nasa.pictures.Adapter.User_data_Adapter;
import com.nasa.pictures.Model.User_Model;
import com.nasa.pictures.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding; //used binding
    ArrayList<User_Model> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        list = new ArrayList<>(); //Create object from ArrayList
        User_data_Adapter userDataAdapter = new User_data_Adapter(this, list); //Create object from User_data_Adapter
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);  //Create object from GridLayoutManager
        binding.DataRV.setLayoutManager(gridLayoutManager);  //set layout manager in RecyclerView
        binding.DataRV.setAdapter(userDataAdapter); //set adapter from recyclerView


        AddItemFromJson(); //create new method get json data

    }

    private void AddItemFromJson() {
        try {
            String jsonDataString = readJsonDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                User_Model userModel = new User_Model();

                userModel.setName(itemObj.getString("title"));
                userModel.setImage(itemObj.getString("url"));
                userModel.setDescriptions(itemObj.getString("explanation"));
                userModel.setDate(itemObj.getString("date"));
                userModel.setCopyright(itemObj.getString("copyright"));

                list.add(userModel);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readJsonDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.data);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                stringBuilder.append(jsonString);
            }


        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new String(stringBuilder);
    }
}