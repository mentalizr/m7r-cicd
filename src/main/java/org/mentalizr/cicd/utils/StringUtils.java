package org.mentalizr.cicd.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringUtils {

    public static String[] concatArrays(String[] strings1, String... strings2) {
        List<String> list = new ArrayList<>(strings1.length + strings2.length);
        Collections.addAll(list, strings1);
        Collections.addAll(list, strings2);
        return list.toArray(new String[0]);
    }

}
