package com.river.app.todo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.river.app.todo.model.DBData;
import com.river.app.todo.model.Tarefa;

/**
 * Created by cezar on 22/02/16.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    private Context mContext;

    public TodoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.row_tarefa,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoAdapter.ViewHolder holder, int position) {
        final Tarefa tarefa = DBData.tarefaList().get(position);
        holder.resumoTextView.setText(tarefa.getResumo());
    }

    @Override
    public int getItemCount() {
        return DBData.tarefaList().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView resumoTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.resumoTextView = (TextView) itemView.findViewById(R.id.resumoTarefa);
        }
    }
}
