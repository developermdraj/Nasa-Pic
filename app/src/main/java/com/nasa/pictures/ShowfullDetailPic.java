package com.nasa.pictures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.nasa.pictures.Model.User_Model;
import com.nasa.pictures.databinding.ActivityShowfullDetailPicBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShowfullDetailPic extends AppCompatActivity {

    ActivityShowfullDetailPicBinding binding; //used binding
    final List<SlideModel> slideModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowfullDetailPicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        String name = getIntent().getStringExtra("name");
        String descriptions = getIntent().getStringExtra("descriptions");
        String date = getIntent().getStringExtra("date");
        String copyright = getIntent().getStringExtra("copyright");
        String image = getIntent().getStringExtra("image");

        binding.name2.setText(name);
        binding.date2.setText(date);
        binding.descrip.setText(descriptions);


        Glide.with(this)
                .load(image)
                .placeholder(R.drawable.fl)
                .into(binding.image);


        AddItemFromJson();  //create new method get json data

        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private void AddItemFromJson() {
        try {
            String jsonDataString = readJsonDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject itemObj = jsonArray.getJSONObject(i);

                slideModels.add(new SlideModel(itemObj.getString("url"), itemObj.getString("title"), ScaleTypes.FIT));

                binding.imageSlider.setImageList(slideModels, ScaleTypes.FIT);

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