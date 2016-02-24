package com.river.app.todo;


import android.content.Context;
import android.net.Uri;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.river.app.todo.R;
import com.river.app.todo.model.CategoriaTarefa;
import com.river.app.todo.model.DBData;
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (AddEditFragmentListener) context;
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

        categorySpinner.setAdapter(
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, CategoriaTarefa.names()));

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing
            }
        });

        //categoryTextInputLayout = (TextInputLayout) view.findViewById(R.id.categoryTextInputLayout);

        resumeTextInputLayout = (TextInputLayout) view.findViewById(R.id.resumeTextInputLayout);

        descriptionTextInputLayout = (TextInputLayout) view.findViewById(R.id.descriptionTextInputLayout);

        dateTextInputLayout = (TextInputLayout) view.findViewById(R.id.dateTextInputLayout);

        return view;
    }

    private void saveTask() {

        Tarefa tarefa = new Tarefa();

        tarefa.setCategoria(CategoriaTarefa.valueOf(selectedCategory));
        tarefa.setResumo(resumeTextInputLayout.getEditText().getText().toString());
        tarefa.setDecricao(descriptionTextInputLayout.getEditText().getText().toString());
        tarefa.setQuando(new Date());



        if (addingNewTask) {
            //save new task
            DBData.tarefaList().add(tarefa);
            listener.onSaveFABClick();

        } else {
            //save edit
            listener.onSaveFABClick();;

        }
    }


}
