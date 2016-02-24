package com.river.app.todo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.river.app.todo.helper.ListViewHelper;
import com.river.app.todo.model.Tarefa;

public class MainActivity extends AppCompatActivity implements
        AddEditFragment.AddEditFragmentListener,
        ListViewFragment.ListViewFragmentListener {

    Toolbar toolbar;


    private void setUpActionBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setElevation(1);
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar();

        ListViewFragment listViewFragment = new ListViewFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_layout, listViewFragment, "list_view_fragment_key")
                    .commit();

        }
    }

    @Override
    public void onAddEditFABClick() {
        AddEditFragment fragment = new AddEditFragment();

        getSupportActionBar().hide();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, fragment)
                .addToBackStack(null).
                commit();
    }

    @Override
    public void onCardRecyclerViewClick(View view,int position) {

        Tarefa tarefa = ListViewHelper.getInstance(this).tarefaList().get(position);

        TodoFragmentDetail detailFragment =
                TodoFragmentDetail.newInstance(tarefa.getCategoria().toString(),
                        tarefa.getDecricao());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_layout, detailFragment, "todoDetailFragment")
                    .addToBackStack(null)
                    .commit();


    }

    @Override
    public void onSaveFABClick() {
        // removo do back history
        getSupportFragmentManager().popBackStack();
        getSupportActionBar().show();
    }
}
