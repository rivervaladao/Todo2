package com.river.app.todo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.river.app.todo.model.DBData;
import com.river.app.todo.model.Tarefa;

public class ListViewFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private TodoAdapter mAdapter;
    Menu mMenu;
    boolean isListView;
    private ListViewFragmentListener listener;

    public interface  ListViewFragmentListener{
        public void onAddEditFABClick();
        public void onCardRecyclerViewClick(View v, int position);
    }

    public ListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        //apresentar
        setHasOptionsMenu(true);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new TodoAdapter(getContext());

        TodoAdapter.OnItemClick onItemClickListener = new TodoAdapter.OnItemClick() {

            @Override
            public void onItemClick(View view, int position) {
                listener.onCardRecyclerViewClick(view,position);
            }
        };

        mAdapter.setOnItemClickListener(onItemClickListener);

        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddEditFABClick();
            }
        });

        isListView = true;
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.menu_main,menu);
        mMenu = menu;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_toggle:
                toggle();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void toggle(){
        MenuItem item = mMenu.findItem(R.id.action_toggle);
        if(isListView){
            staggeredGridLayoutManager.setSpanCount(2);
            item.setIcon(R.drawable.ic_action_list);
            item.setTitle("Ver Lista");
            isListView=false;
        }else{
            staggeredGridLayoutManager.setSpanCount(1);
            item.setIcon(R.drawable.ic_action_grid);
            item.setTitle("Ver Grid");
            isListView=true;
        }
    }

    @Override
    public void onAttach(Context context) {

        if(context instanceof ListViewFragmentListener){
            listener = (ListViewFragmentListener) context;
        }else {
            //gera uma execao
        }
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
