package com.example.dac.app_moki.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dac.app_moki.R;

/**
 * Created by Dac on 10/20/2017.
 */
public class Search_Activity extends AppCompatActivity {
    private Button btnSearch;
    private ImageButton btnBack;
    private View lineBrand;
    private View lineSize;
    private View lineCategories;
    private View lineState;

    private EditText keyword;
    private TextView nameCategory;
    private TextView nameBrand;
    private TextView nameSize;
    private TextView nameCondition;

    private String categoryId;
    private String brandId;
    private String sizeId;
    private String priceMin;
    private String priceMax;
    private String conditionId;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        addControls();
        addEvents();
    }

    private void addControls() {
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        lineBrand = findViewById(R.id.line_brand);
        lineSize = findViewById(R.id.line_size);
        lineCategories = findViewById(R.id.line_categories);
        lineState = findViewById(R.id.line_state);
        keyword = (EditText) findViewById(R.id.txt_keyword);

        keyword = (EditText) findViewById(R.id.txt_keyword);
        nameCategory = (TextView) findViewById(R.id.name_category_search);
        nameBrand = (TextView) findViewById(R.id.name_brand_search);
        nameSize = (TextView) findViewById(R.id.name_size_search);
        nameCondition = (TextView) findViewById(R.id.name_condition_search);
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lineCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Search_Activity.this, SearchCategories_Activity.class);
                startActivityForResult(intent, 1);
            }
        });

        lineBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Search_Activity.this, SearchBrand_Activity.class);
                startActivityForResult(intent, 2);
            }
        });
        lineSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Search_Activity.this, SearchSize_Activity.class);
                startActivityForResult(intent, 3);
            }
        });
        lineState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Search_Activity.this, SearchCondition_Activity.class);
                startActivityForResult(intent, 4);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search_Activity.this, ResultSearch_Activity.class);
                intent.putExtra("category_id", (categoryId + "").toString() );
                intent.putExtra("brand_id", (brandId + "").toString() );
                intent.putExtra("size_id", (sizeId + "").toString() );
                intent.putExtra("condition_id", (conditionId + "").toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                nameCategory.setText(data.getStringExtra("category_name"));
                categoryId = data.getStringExtra("category_id");
                btnSearch.setBackgroundResource(R.color.red_button);
            }
        }
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                nameBrand.setText(data.getStringExtra("brand_name"));
                brandId = data.getStringExtra("brand_id");
                btnSearch.setBackgroundResource(R.color.red_button);
            }
        }
        if (requestCode == 3) {
            if(resultCode == RESULT_OK) {
                nameSize.setText(data.getStringExtra("size_name"));
                sizeId = data.getStringExtra("size_id");
                btnSearch.setBackgroundResource(R.color.red_button);
            }
        }
        if (requestCode == 4) {
            if(resultCode == RESULT_OK) {
                nameCondition.setText(data.getStringExtra("condition_name"));
                conditionId = data.getStringExtra("condition_id");
                btnSearch.setBackgroundResource(R.color.red_button);
            }
        }
    }
}
