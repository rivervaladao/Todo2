package com.river.app.todo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.CharArrayReader;

/**
 * Created by cezar on 22/02/16.
 */
public class TodoFragmentDetail extends Fragment {

    private TodoFragmentDetailListener listener;
    public static final String ARG_ID = "_id";
    public static final String ARG_CATEGORIA = "categoria";
    public static final String ARG_DESCRICAO = "descricao";
    public static final String ARG_RESUMO = "resumo";
    public static final String ARG_DATA = "data";
    private long ID;
    public interface TodoFragmentDetailListener {
        public void onEditTodoDetail(long tarefaId);
        public void onRemoveTodoDetail();
    }

    public TodoFragmentDetail() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TodoFragmentDetailListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_todo_details,container,false);

        setHasOptionsMenu(true);

        final TextView categoriaView = (TextView) view.findViewById(R.id.categoriaDetail);
        final TextView resumoView = (TextView) view.findViewById(R.id.resumoDetail);
        final TextView descricaoView = (TextView) view.findViewById(R.id.descricaoDetail);
        final TextView dataView = (TextView) view.findViewById(R.id.dataDetail);

        final Bundle args = getArguments();

        categoriaView.setText(args.getString(ARG_CATEGORIA));
        descricaoView.setText(args.getString(ARG_DESCRICAO));
        resumoView.setText(args.getString(ARG_RESUMO));
        dataView.setText(args.getString(ARG_DATA));
        this.ID = args.getLong(ARG_ID);
        //

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_edit:
                listener.onEditTodoDetail(ID);
                return  true;
            case R.id.action_delete:
                confirmDeleteTask();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void confirmDeleteTask(){
        confirmDelete.show(getFragmentManager(),"confirme delete");

    }

    private final DialogFragment confirmDelete = new DialogFragment(){
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.confirm_title)
                    .setMessage(R.string.confirm_message)
                    .setPositiveButton(R.string.button_delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            listener.onRemoveTodoDetail();
                        }
                    })
            .setNegativeButton(R.string.button_cancel, null);


            return builder.create();
        }
    };
}
