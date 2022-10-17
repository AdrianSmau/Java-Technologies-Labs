package org.laboratories.lab2.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppUtils {
    public static final String COMPULSORY_PARAM_NAME = "word";
    public static final String HOMEWORK_PARAM_NAME = "size";
    private static final String WORDLIST_PATH = "C:\\Users\\adria\\Desktop\\Java-Technologies-Labs\\Lab2\\src\\main\\resources\\wordlist.txt";

    public static final List<String> dictionary = extractDictionary();

    private static List<String> extractDictionary() {
        List<String> tempDict = new ArrayList<>();
        try {
            File file = new File(WORDLIST_PATH);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                tempDict.add(line.toUpperCase());
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempDict;
    }
}
