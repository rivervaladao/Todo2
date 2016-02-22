package com.river.app.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.river.app.todo.model.Tarefa;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private TodoAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new TodoAdapter(this);

        TodoAdapter.OnItemClick onItemClickListener = new TodoAdapter.OnItemClick(){
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"clicked: "+ position,Toast.LENGTH_LONG).show();
            }
        };

        mAdapter.setOnItemClickListener(onItemClickListener);

        mRecyclerView.setAdapter(mAdapter);

    }
}
