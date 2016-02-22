package com.river.app.todo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cezar on 22/02/16.
 */
public class DBData {


    public static Tarefa[] tarefasArray =
            {
                    new Tarefa("exame da cabeça", "neurologista", CategoriaTarefa.SAUDE),
                    new Tarefa("comprar carnes e vegetais", "Supermercado", CategoriaTarefa.COMPRAS),
                    new Tarefa("android básico activity", "android aula1", CategoriaTarefa.EDUCACAO),
                    new Tarefa("exame da cabeça", "neurologista", CategoriaTarefa.SAUDE),
                    new Tarefa("comprar carnes e vegetais", "Supermercado", CategoriaTarefa.COMPRAS),
                    new Tarefa("android básico activity", "android aula1", CategoriaTarefa.EDUCACAO),
                    new Tarefa("exame da cabeça", "neurologista", CategoriaTarefa.SAUDE),
                    new Tarefa("comprar carnes e vegetais", "Supermercado", CategoriaTarefa.COMPRAS),
                    new Tarefa("android básico activity", "android aula1", CategoriaTarefa.EDUCACAO),
                    new Tarefa("assistir 3 Episodio Narcos netflix", "Narcos", CategoriaTarefa.LAZER),
                    new Tarefa("elaborar plano de aula android", "Treinament MPGo", CategoriaTarefa.TRABALHO),
                    new Tarefa("assistir 3 Episodio Narcos netflix", "Narcos", CategoriaTarefa.LAZER),
                    new Tarefa("elaborar plano de aula android", "Treinament MPGo", CategoriaTarefa.TRABALHO),
                    new Tarefa("assistir 3 Episodio Narcos netflix", "Narcos", CategoriaTarefa.LAZER),
                    new Tarefa("elaborar plano de aula android", "Treinament MPGo", CategoriaTarefa.TRABALHO)
            };

    public static List<Tarefa> tarefaList() {
        ArrayList<Tarefa> list = new ArrayList<>();
        return (Arrays.asList(tarefasArray));
    }
}

