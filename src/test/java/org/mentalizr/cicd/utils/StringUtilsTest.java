package org.mentalizr.cicd.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void concatArrays() {

        String[] strings1 = {"one", "two"};
        String[] strings2 = {"three", "four"};

        String[] stringSum = StringUtils.concatArrays(strings1, strings2);

        assertEquals(4, stringSum.length);
        assertEquals("one", stringSum[0]);
        assertEquals("two", stringSum[1]);
        assertEquals("three", stringSum[2]);
        assertEquals("four", stringSum[3]);
    }
}