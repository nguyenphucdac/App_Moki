package com.example.dac.app_moki.view.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.model.nesworks.ConnectSocket;
import com.example.dac.app_moki.model.object.Conversation;
import com.example.dac.app_moki.presentation.conversation.PresentationConversation;
import com.example.dac.app_moki.view.adapter.AdapterConversation;
import com.github.nkzawa.socketio.client.Socket;

import java.util.List;

/**
 * Created by Dac on 10/24/2017.
 */
public class FragmentDialogMessage extends DialogFragment {

    private AdapterConversation adapterConversation;
    private RecyclerView recyclerViewListConvesation;
    private List<Conversation> lstConversation;

    private Socket mSocket = ConnectSocket.getmSocket();

    public static FragmentDialogMessage newInstance() {
        FragmentDialogMessage frag = new FragmentDialogMessage();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment_dialog_messeage, container, false);

        PresentationConversation presentationConversation = new PresentationConversation();
        lstConversation = presentationConversation.getListConversation();


        recyclerViewListConvesation = (RecyclerView) rootView.findViewById(R.id.recycler_list_conversation);
        recyclerViewListConvesation.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterConversation = new AdapterConversation(getActivity(), lstConversation);
        recyclerViewListConvesation.setAdapter(adapterConversation);
        adapterConversation.notifyDataSetChanged();
        return rootView;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
    public boolean updateListConversation(Conversation conversation){
        this.lstConversation.add(conversation);
        adapterConversation.notifyDataSetChanged();

        return true;
    }
}
