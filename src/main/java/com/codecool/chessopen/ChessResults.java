package com.codecool.chessopen;

import java.io.*;
import java.util.*;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        List<String> fileData = readLinesFromFile(fileName);
        return analyzeDataAndMakeOrderedResultList(fileData);
    }

    public static List<String> analyzeDataAndMakeOrderedResultList(List<String> fileData) {
        List<String> result = new ArrayList<>();
        HashMap<String, Integer> competitors = new HashMap<>();
        for (String competitorDataStr : fileData) {
            String[] datas = competitorDataStr.split(",");
            int points = 0;
            for (int matchID = 1; matchID < datas.length; matchID++) {
                points += Integer.parseInt(datas[matchID]);
            }
            competitors.put(datas[0], points);
        }
        competitors.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach((competitor) -> result.add(competitor.getKey()));
        return result;
    }

    private static List<String> readLinesFromFile(String filename) {
        File file = new File(filename);
        List<String> lines = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                lines.add(fileReader.nextLine());
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return lines;
    }
}

