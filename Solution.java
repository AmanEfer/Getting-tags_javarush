package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String tag = args[0];
        String beginning = "<" + tag;
        String ending = String.format("</%s>", tag);

        String fileName;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = reader.readLine();
        }

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);
        }

        ArrayList<String> list = new ArrayList<>();
        String data = builder.toString();
        int startIndex = data.indexOf(beginning);
        int endIndex = data.indexOf(ending);
        String researchString = data.substring(startIndex);
        String tagLine;

        while (researchString.contains(beginning)) {
            int tempStartIndex = data.indexOf(beginning, startIndex + beginning.length());
            tempStartIndex = tempStartIndex > 0 ? tempStartIndex : endIndex;
            researchString = data.substring(tempStartIndex);

            if (tempStartIndex < endIndex) {
                String tempString = data.substring(tempStartIndex, endIndex);
                while (tempString.contains(beginning)) {
                    endIndex = data.indexOf(ending, endIndex + ending.length());
                    tempStartIndex = data.indexOf(beginning, tempStartIndex + beginning.length());

                    if (tempStartIndex < 0 || tempStartIndex >= endIndex)
                        break;
                    else
                        tempString = data.substring(tempStartIndex, endIndex);
                }
            }

            tagLine = data.substring(startIndex, endIndex + ending.length());
            list.add(tagLine);

            startIndex = data.indexOf(beginning, startIndex + beginning.length());
            endIndex = data.indexOf(ending, startIndex + beginning.length());
        }

        list.forEach(System.out::println);
    }
}