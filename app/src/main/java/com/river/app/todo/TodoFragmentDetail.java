package com.river.app.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.CharArrayReader;

/**
 * Created by cezar on 22/02/16.
 */
public class TodoFragmentDetail extends Fragment {

    private static final String ARG_CATEGORIA = "categoria";
    private static final String ARG_DESCRICAO = "descricao";

    public TodoFragmentDetail() {
    }

    public static TodoFragmentDetail newInstance(String categoria, String descricao){

        final Bundle args = new Bundle();

        args.putString(ARG_CATEGORIA, categoria);
        args.putString(ARG_DESCRICAO, descricao);

        TodoFragmentDetail fragment = new TodoFragmentDetail();

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_todo_details,container,false);

        final TextView categoriaView = (TextView) view.findViewById(R.id.categoriaDetail);
        final TextView resumoView = (TextView) view.findViewById(R.id.resumoDetail);
        final TextView descricaoView = (TextView) view.findViewById(R.id.descricaoDetail);
        final TextView dataView = (TextView) view.findViewById(R.id.dataDetail);

        final Bundle args = getArguments();

        categoriaView.setText(args.getString(ARG_CATEGORIA));
        descricaoView.setText(args.getString(ARG_DESCRICAO));
        //

        return view;
    }
}
