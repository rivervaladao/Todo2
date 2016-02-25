package com.river.app.todo.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by cezar on 22/02/16.
 */
public class Tarefa {
    private Long id;
    private String decricao;
    private Date quando;
    private String resumo;
    private CategoriaTarefa categoria;

    public Tarefa() {
    }

    public Tarefa(String decricao, String resumo, CategoriaTarefa categoria) {
        this();
        this.decricao = decricao;
        this.resumo = resumo;
        this.categoria = categoria;
        this.quando = Calendar.getInstance().getTime();

    }

    public Tarefa(Long id, String resumo, String descricao, String categoria, Date date) {
        this(descricao, resumo, CategoriaTarefa.valueOf(categoria));
        this.quando = date;
    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
    }

    public Date getQuando() {
        return quando;
    }

    public void setQuando(Date quando) {
        this.quando = quando;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public CategoriaTarefa getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaTarefa categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}