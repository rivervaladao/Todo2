package com.river.app.todo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.river.app.todo.helper.DatabaseHelper;
import com.river.app.todo.model.Tarefa;
import com.river.app.todo.helper.DatabaseHelper.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cezar on 23/02/16.
 */
public class TarefaDao {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public TarefaDao(Context context) {
        helper = new DatabaseHelper(context);
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public void close() {
        helper.close();
    }

    private Tarefa criarTarefa(Cursor cursor) {
        Tarefa tarefa = new Tarefa(
                cursor.getLong(cursor.getColumnIndex(
                        Todo.COLUMN_ID)),

                cursor.getString(cursor.getColumnIndex(
                        Todo.COLUMN_RESUMO)),

                cursor.getString(cursor.getColumnIndex(
                        Todo.COLUMN_DESC)),

                cursor.getString(cursor.getColumnIndex(
                        Todo.COLUMN_CATEGORIA)),

                new Date(cursor.getLong(cursor.getColumnIndex(
                        Todo.COLUMN_DATA)))
        );
        return tarefa;
    }

    public List<Tarefa> listarTarefas() {
        Cursor cursor = getDb().query(Todo.TABLE_NAME,
                Todo.COLUMNS,
                null, null, null, null, null);

        List<Tarefa> tarefas = new ArrayList<Tarefa>();

        while (cursor.moveToNext()) {
            Tarefa tarefa = criarTarefa(cursor);
            tarefas.add(tarefa);
        }
        cursor.close();
        return tarefas;
    }

    public Tarefa buscarTarefaPorId(Integer id) {

        Cursor cursor = getDb().query(Todo.TABLE_NAME,
                Todo.COLUMNS,
                Todo.COLUMN_ID + " = ?",
                new String[]{id.toString()},
                null, null, null);

        if (cursor.moveToNext()) {
            Tarefa tarefa = criarTarefa(cursor);
            cursor.close();
            return tarefa;
        }

        return null;
    }

    public long inserir(Tarefa tarefa) {
        ContentValues values = new ContentValues();
        values.put(Todo.COLUMN_RESUMO,
                tarefa.getResumo());

        values.put(Todo.COLUMN_DESC,
                tarefa.getDecricao());

        values.put(Todo.COLUMN_CATEGORIA,
                tarefa.getCategoria().toString());

        values.put(Todo.COLUMN_DATA,
                tarefa.getQuando().getTime());

        return getDb().insert(Todo.TABLE_NAME,
                null, values);
    }

    public boolean removerTarefa(Long id) {
        String whereClause = Todo.COLUMN_ID + " = ?";
        String[] whereArgs = new String[]{id.toString()};
        int removidos = getDb().delete(Todo.TABLE_NAME,
                whereClause, whereArgs);
        return removidos > 0;
    }

    public void removerTodasAsTarefas() {
        getDb().execSQL("DELETE FROM " + Todo.TABLE_NAME);
    }
}
