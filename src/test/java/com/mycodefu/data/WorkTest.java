package com.mycodefu.data;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkTest {

    @Test
    void load() {
        List<Work> workList = Work.load();

        assertEquals(43, workList.size());
        assertEquals("12night", workList.get(0).workID());
        assertEquals("Twelfth Night", workList.get(0).title());
        assertEquals(24905, workList.get(2).totalWords());
        assertEquals(1344, workList.get(2).totalParagraphs());
    }

}