package com.example.dac.app_moki.view.product;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.fragment.FragmentDialogError;
import com.example.dac.app_moki.view.search.SearchCategories_Activity;
import com.example.dac.app_moki.view.search.SearchCondition_Activity;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Dac on 11/11/2017.
 */

public class AddProduct extends AppCompatActivity {

    private ImageButton btnBack;
    private ImageView imageProductSale;
    private EditText nameProduct;
    private EditText describedProduct;
    private EditText price;
    private EditText shipFrom;

    private Button btnGetCategoryId;
    private Button btnGetCoditionId;

    private String categoryId;
    private String sizeId;
    private String weight;
    private String coditionId;

    private Button btnSaleProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_add);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        Intent intent = getIntent();
        final File picture = (File)intent.getExtras().get("image");

        btnBack = (ImageButton) findViewById(R.id.post_product_back);

        imageProductSale = (ImageView) findViewById(R.id.image_product_sale);
        Picasso.with(AddProduct.this).load(picture).into((ImageView) imageProductSale);

        nameProduct = (EditText) findViewById(R.id.txt_name_product);
        describedProduct = (EditText) findViewById(R.id.txt_described_product);
        price = (EditText) findViewById(R.id.txt_price_product);
        shipFrom = (EditText) findViewById(R.id.txt_ship_from);

        btnGetCategoryId = (Button) findViewById(R.id.button_get_category);


        btnGetCategoryId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProduct.this, SearchCategories_Activity.class);
                startActivityForResult(intent, 1);
            }
        });

        btnGetCoditionId = (Button) findViewById(R.id.button_get_condition);
        btnGetCoditionId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProduct.this, SearchCondition_Activity.class);
                startActivityForResult(intent, 4);
            }
        });



        btnSaleProduct = (Button) findViewById(R.id.button_sale_product);

        String filePath = picture.getPath();
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        imageProductSale.setImageBitmap(bitmap);

        System.out.println(" value of bitmap : " + bitmap);


        btnSaleProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PresentationProduct presentationProduct = new PresentationProduct();
                Boolean check = presentationProduct.addProduct(
                        nameProduct.getText()+"",
                        price.getText()+"",
                        "1",
                        "1",
                        categoryId + "",
                        describedProduct.getText()+"",
                        shipFrom.getText()+"",
                        coditionId+"",
                        "1,1,1",
                        "100",
                         picture
                );


                if(check == true){
                    FragmentDialogError fragmentDialogError = new FragmentDialogError();
                    FragmentManager fm = getFragmentManager();
                    fragmentDialogError = FragmentDialogError.newInstance("Đăng sản phầm thành công !!!");
                    fragmentDialogError.show(fm, "Sample Fragment");
                }
                else {
                    FragmentDialogError fragmentDialogError = new FragmentDialogError();
                    FragmentManager fm = getFragmentManager();
                    fragmentDialogError = FragmentDialogError.newInstance("Đăng sản phẩm không thành công !!!");
                    fragmentDialogError.show(fm, "Sample Fragment");
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                btnGetCategoryId.setText(data.getStringExtra("category_name"));
                categoryId = data.getStringExtra("category_id");
            }
        }
        if (requestCode == 4) {
            if(resultCode == RESULT_OK) {
                btnGetCoditionId.setText(data.getStringExtra("condition_name"));
                coditionId = data.getStringExtra("condition_id");
            }
        }
    }
}
