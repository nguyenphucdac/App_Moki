package com.example.dac.app_moki.view.search;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.fragment.FragmentDialogError;

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
    private TextView nameCategory;
    private EditText keyword;


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

        nameCategory = (TextView) findViewById(R.id.name_category_search);
        Intent myIntent = getIntent();
        String nameCaterogyIntent = myIntent.getStringExtra("Category_name");
        if(nameCaterogyIntent != null ){
            nameCategory.setText(nameCaterogyIntent);
            btnSearch.setBackgroundResource(R.color.red_button);
        }
    }

    private void addEvents() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = getIntent();
                String categoryId = myIntent.getStringExtra("Category_name");
                if(categoryId !=null){
                    Intent intent = new Intent(Search_Activity.this, ResultSearch_Activity.class);
                    intent.putExtra("cateroy_id", categoryId);
                    intent.putExtra("keyword", (keyword.getText()).toString());
                    startActivity(intent);
                }
                else{
                    FragmentDialogError fragmentDialogError = new FragmentDialogError();
                    FragmentManager fm = getFragmentManager();
                    fragmentDialogError = FragmentDialogError.newInstance();
                    fragmentDialogError.show(fm, "Sample Fragment");
                }

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lineBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Search_Activity.this, SearchBrand_Activity.class);
                startActivity(intent);
            }
        });
        lineSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Search_Activity.this, SearchSize_Activity.class);
                startActivity(intent);
            }
        });
        lineCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Search_Activity.this, SearchCategories_Activity.class);
                startActivity(intent);
            }
        });
        lineState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Search_Activity.this, SearchState_Activity.class);
                startActivity(intent);
            }
        });
    }
}
