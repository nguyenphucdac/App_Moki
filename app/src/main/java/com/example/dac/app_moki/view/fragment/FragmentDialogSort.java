package com.example.dac.app_moki.view.fragment;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.local.data.ProductLocal;
import com.example.dac.app_moki.local.value.ValueLocal;
import com.example.dac.app_moki.model.object.Product;
import com.example.dac.app_moki.presentation.product.PresentationProduct;
import com.example.dac.app_moki.view.adapter.AdapterSortValue;
import com.example.dac.app_moki.view.home.Home_Activity;

import java.util.List;

/**
 * Created by Dac on 11/12/2017.
 */

public class FragmentDialogSort extends DialogFragment {

    private RecyclerView recyclerViewValueSort;
    private String[] values = {
            "Giá từ thấp đến cao",
            "giá từ cao đến thấp",
            "Sản phẩm mới nhất",
            };

    public static FragmentDialogSort newInstance(String title) {
        FragmentDialogSort frag = new FragmentDialogSort();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.home_fragment_dialog_sort, container, false);

        recyclerViewValueSort = (RecyclerView) rootView.findViewById(R.id.lits_view_sort);

        recyclerViewValueSort.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdapterSortValue adapterSortValue = new AdapterSortValue(rootView.getContext(), values);
        recyclerViewValueSort.setAdapter(adapterSortValue);
        adapterSortValue.notifyDataSetChanged();

        Button dismiss = (Button) rootView.findViewById(R.id.dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button btnSort = (Button) rootView.findViewById(R.id.button_sort);
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PresentationProduct presentationProduct = new PresentationProduct();
                List<Product> lstProduct = presentationProduct.sortList(ValueLocal.getSort(), ValueLocal.getTypeSort(), ValueLocal.getCurrentCategory());

                ProductLocal.updatelstProduct(lstProduct,ValueLocal.getCurrentCategory());


                Intent intent = new Intent(rootView.getContext(), Home_Activity.class);
                startActivity(intent);

            }
        });
        return rootView;
    }

}
