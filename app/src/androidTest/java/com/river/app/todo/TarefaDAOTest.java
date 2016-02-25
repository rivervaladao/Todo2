package com.river.app.todo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.SmallTest;

import com.river.app.todo.dao.TarefaDao;
import com.river.app.todo.model.Tarefa;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by cezar on 24/02/16.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class TarefaDAOTest {


    private TarefaDao dao;

    @Before
    public void setUp() throws Exception {
        Context context = new RenamingDelegatingContext(
                InstrumentationRegistry.getInstrumentation().getTargetContext(), "test_");
        dao = new TarefaDao(context);
    }

    @After
    public void tearDown() throws Exception {
        dao.close();
    }

    @Test
    public void inserir() {
        for (Tarefa tarefa : DBData.tarefaList()) {
            dao.inserir(tarefa);
        }
        Assert.assertEquals(
                dao.listarTarefas().size(),
                DBData.tarefaList().size());
    }


}
