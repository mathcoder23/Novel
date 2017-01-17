package com.mt23.novel;

import com.mt23.novel.novel.service.Novel;
import com.mt23.novel.utils.ListMapBean;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test(){
        ListMapBean.ListToListmap(null,Novel.class);
    }
}