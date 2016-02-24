package com.river.app.todo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.river.app.todo.model.DBData;
import com.river.app.todo.model.Tarefa;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private TodoAdapter mAdapter;
    Toolbar toolbar;
    private void setUpActionBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setElevation(7);
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar();

        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new TodoAdapter(this);

        TodoAdapter.OnItemClick onItemClickListener = new TodoAdapter.OnItemClick() {

             @Override
            public void onItemClick(View view, int position) {

                 Tarefa tarefa = DBData.tarefaList().get(position);

                 TodoFragmentDetail detailFragment =
                         TodoFragmentDetail.newInstance(tarefa.getCategoria().toString(),
                                 tarefa.getDecricao());

                if (savedInstanceState == null) {


                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .replace(R.id.main_layout, detailFragment, "todoDetailFragment")
                            .addToBackStack(null)
                            .commit();
                }

            }
        };

        mAdapter.setOnItemClickListener(onItemClickListener);

        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"FAB button clicked", Toast.LENGTH_LONG).show();
            }
        });

    }
}
