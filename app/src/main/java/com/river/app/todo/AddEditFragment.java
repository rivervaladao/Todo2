package com.river.app.todo;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.river.app.todo.dao.TarefaDao;
import com.river.app.todo.model.CategoriaTarefa;
import com.river.app.todo.model.Tarefa;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEditFragment extends Fragment {

    public interface AddEditFragmentListener{
        public void onSaveFABClick();
    }

    public AddEditFragment() {
        // Required empty public constructor
    }

    public static final String ARG_ID="_ID";
    public static final String ARG_MODE="novo_ou_edit";

    private AddEditFragmentListener listener; // MainActivity
    private boolean addingNewTask = true; // adding (true) or editing

    private TextInputLayout resumeTextInputLayout;
    private TextInputLayout descriptionTextInputLayout;
    //private TextInputLayout categoryTextInputLayout;
    private String selectedCategory;
    private TextInputLayout dateTextInputLayout;
    private AppCompatSpinner categorySpinner;
    private FloatingActionButton saveTaskFAB;
    private CoordinatorLayout coordinatorLayout;
    private Tarefa tarefa;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (AddEditFragmentListener) context;
        Bundle args = getArguments();
        int mode = args.getInt(ARG_MODE);
        if(mode == 1){
            loadData(args.getLong(ARG_ID));
            addingNewTask = false;
        }else{
            addingNewTask = true;
        }
        //carraga dados registro selecionado
    }

    // remove AddEditFragmentListener when Fragment detached
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        //desabilita menu
        setHasOptionsMenu(false);

        View view = inflater.inflate(R.layout.fragment_add_edit, container, false);

        saveTaskFAB = (FloatingActionButton) view.findViewById(R.id.editButton);
        saveTaskFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });

        categorySpinner = (AppCompatSpinner) view.findViewById(R.id.categorySpinner);

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item
                , CategoriaTarefa.names());

        categorySpinner.setAdapter(arrayAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = (String) parent.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        resumeTextInputLayout = (TextInputLayout) view.findViewById(R.id.resumeTextInputLayout);

        descriptionTextInputLayout = (TextInputLayout) view.findViewById(R.id.descriptionTextInputLayout);

        dateTextInputLayout = (TextInputLayout) view.findViewById(R.id.dateTextInputLayout);
        //
        if(! addingNewTask){
            resumeTextInputLayout.getEditText().setText(tarefa.getResumo());
            descriptionTextInputLayout.getEditText().setText(tarefa.getDecricao());
            dateTextInputLayout.getEditText().setText(tarefa.getQuando().toString());
            // setting spinner
            int categoryPosition = arrayAdapter.getPosition(tarefa.getCategoria().toString());
            categorySpinner.setSelection(categoryPosition,true);
        }

        return view;
    }

    private void saveTask() {

        tarefa.setCategoria(CategoriaTarefa.valueOf(selectedCategory));
        tarefa.setResumo(resumeTextInputLayout.getEditText().getText().toString());
        tarefa.setDecricao(descriptionTextInputLayout.getEditText().getText().toString());
        tarefa.setQuando(new Date());

        TarefaDao dao = new TarefaDao(getContext());

        if (addingNewTask) {
            //save new task

            dao.inserir(tarefa);
            listener.onSaveFABClick();

        } else {
            //save edit
            dao.updateTarefa(tarefa);
            listener.onSaveFABClick();;

        }
    }

    private void loadData(long id){
        TarefaDao dao = new TarefaDao(getContext());
        tarefa = dao.buscarTarefaPorId((int) id);
     }
}
