package com.river.app.todo.helper;

import android.content.Context;

import com.river.app.todo.dao.TarefaDao;
import com.river.app.todo.model.Tarefa;

import java.util.List;

/**
 * Created by cezar on 23/02/16.
 */
public class ListViewHelper {

    private static Context mContext;

    ;
    private TarefaDao tarefaDao;
    private ListViewHelper() {
    }

    public static ListViewHelper getInstance(Context context) {
        ListViewHelper.mContext = context;
        return new ListViewHelper();
    }

    public List<Tarefa> tarefaList() {
        return new TarefaDao(mContext).listarTarefas();
    }
}
