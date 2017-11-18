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

        System.out.println(picture);

        btnBack = (ImageButton) findViewById(R.id.post_product_back);

        imageProductSale = (ImageView) findViewById(R.id.image_product_sale);
        Picasso.with(AddProduct.this).load(picture).into((ImageView) imageProductSale);

        nameProduct = (EditText) findViewById(R.id.txt_name_product);
        describedProduct = (EditText) findViewById(R.id.txt_described_product);

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
                        "mouse new men",
                        "0",
                        "1",
                        "1",
                        "1",
                        "test",
                        "test",
                        "1",
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
}
