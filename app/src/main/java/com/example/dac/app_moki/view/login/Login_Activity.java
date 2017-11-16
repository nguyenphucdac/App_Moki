package com.example.dac.app_moki.view.login;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.User;
import com.example.dac.app_moki.presentation.acount.PresentationLogin;
import com.example.dac.app_moki.view.fragment.FragmentDialogError;
import com.example.dac.app_moki.view.home.Home_Activity;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Dac on 10/12/2017.
 */
public class Login_Activity extends AppCompatActivity {

    private Button btn_skip, btn_signup, btn_login, btn_forgot;
    private EditText txt_phone, txt_password;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        addControls();
        addEvents();

    }

    private void addEvents() {
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, Home_Activity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PresentationLogin presentationLogin = new PresentationLogin();
                user = presentationLogin.login(txt_phone.getText().toString(), txt_password.getText().toString());
                if(user == null){
                    FragmentDialogError fragmentDialogError = new FragmentDialogError();
                    FragmentManager fm = getFragmentManager();
                    fragmentDialogError = FragmentDialogError.newInstance("Tài khoản hoặc mật khẩu không đúng !!!");
                    fragmentDialogError.show(fm, "Sample Fragment");
                }
                else{
                    Intent intent = new Intent(Login_Activity.this, Home_Activity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);

                }
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    private void addControls() {
        System.out.println("token curent : "+ FirebaseInstanceId.getInstance().getToken());
        Intent myIntent = getIntent();
        boolean logined = myIntent.getBooleanExtra("logined", true);
        if(!logined){
            FragmentDialogError fragmentDialogError = new FragmentDialogError();
            FragmentManager fm = getFragmentManager();
            fragmentDialogError = FragmentDialogError.newInstance("Bạn cần đăng nhập để sử dụng chức năng này");
            fragmentDialogError.show(fm, "Sample Fragment");
        }

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_skip = (Button) findViewById(R.id.btn_skip);
        btn_forgot = (Button) findViewById(R.id.btn_forgot);

        txt_phone = (EditText) findViewById(R.id.txt_Phone);
        txt_password = (EditText) findViewById(R.id.txt_password);
    }
}
