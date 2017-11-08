package com.example.dac.app_moki.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.object.User;
import com.example.dac.app_moki.view.home.Home_Activity;
import com.example.dac.app_moki.view.login.Login_Activity;
import com.example.dac.app_moki.view.menu.Charity_Activity;
import com.example.dac.app_moki.view.menu.IntroMoki_Activity;
import com.example.dac.app_moki.view.menu.ListMyBuy_Activity;
import com.example.dac.app_moki.view.menu.ListMyLike_Activity;
import com.example.dac.app_moki.view.menu.ListMySale_Activity;
import com.example.dac.app_moki.view.menu.News_Activity;
import com.example.dac.app_moki.view.menu.Setup_Activity;
import com.example.dac.app_moki.view.menu.SupportCenter_Activity;
import com.squareup.picasso.Picasso;

/**
 * Created by Dac on 10/28/2017.
 */
public class FragmentMenu extends Fragment {
    private User user;

    private View itemHomeMenu;
    private View itemNewsmenu;
    private View itemListLikeMenu;
    private View itemListSalemenu;
    private View itemListBuyMenu;
    private View itemCharitysmenu;
    private View itemSetupMenu;
    private View itemSupportCentermenu;
    private View itemIntroMenu;
    private View itemLoginsmenu;

    private View avatar;
    private TextView nameUser;
    private TextView loginOut;

    public FragmentMenu(User user){
        this.user = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);

        itemHomeMenu = view.findViewById(R.id.item_home_menu);
        itemNewsmenu = view.findViewById(R.id.item_news_menu);
        itemListLikeMenu = view.findViewById(R.id.item_listlike_menu);
        itemListSalemenu = view.findViewById(R.id.item_listsale_menu);
        itemListBuyMenu = view.findViewById(R.id.item_listbuy_menu);
        itemCharitysmenu = view.findViewById(R.id.item_charity_menu);
        itemSetupMenu = view.findViewById(R.id.item_setup_menu);
        itemSupportCentermenu =  view.findViewById(R.id.item_supportcenter_menu);
        itemIntroMenu = view.findViewById(R.id.item_intro_menu);
        itemLoginsmenu = view.findViewById(R.id.item_login_menu);

        avatar = view.findViewById(R.id.avatar_user);
        nameUser = (TextView) view.findViewById(R.id.name_user);
        loginOut = (TextView) view.findViewById(R.id.login_logout_user);

        if(user != null){
            nameUser.setText(user.getUserName());
            loginOut.setText("Đăng xuất");

            if(user.getImage() != null){
                Picasso.with(view.getContext()).load(user.getImage()).into((ImageView) avatar);
            }
            else {
                Picasso.with(view.getContext()).load(R.drawable.unknown_user).into((ImageView) avatar);
            }

            itemHomeMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Home_Activity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });
            itemNewsmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), News_Activity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });
            itemListLikeMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ListMyLike_Activity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });

            itemListSalemenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ListMySale_Activity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });
            itemListBuyMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ListMyBuy_Activity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });

            itemCharitysmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Charity_Activity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });

            itemSetupMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Setup_Activity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });

            itemSupportCentermenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), SupportCenter_Activity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });
            itemIntroMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), IntroMoki_Activity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });
            itemLoginsmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            nameUser.setText("Đăng nhập");
            loginOut.setText("Đăng nhập");

            Picasso.with(view.getContext()).load(R.drawable.unknown_user).into((ImageView) avatar);

            itemHomeMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Home_Activity.class);
                    startActivity(intent);
                }
            });
            itemNewsmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), News_Activity.class);
                    startActivity(intent);
                }
            });
            itemListLikeMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    intent.putExtra("logined", ("true").toString());
                    startActivity(intent);
                }
            });

            itemListSalemenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    intent.putExtra("logined", ("true").toString());
                    startActivity(intent);
                }
            });
            itemListBuyMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    intent.putExtra("logined", "true");
                    startActivity(intent);
                }
            });

            itemCharitysmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Charity_Activity.class);
                    startActivity(intent);
                }
            });

            itemSetupMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Setup_Activity.class);
                    intent.putExtra("logined", "true");
                    startActivity(intent);
                }
            });

            itemSupportCentermenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), SupportCenter_Activity.class);
                    startActivity(intent);
                }
            });
            itemIntroMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), IntroMoki_Activity.class);
                    startActivity(intent);
                }
            });
            itemLoginsmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Login_Activity.class);
                    startActivity(intent);
                }
            });
        }

        return  view;
    }

}
