package Day14;

import java.io.File;
import java.util.*;

public class DAY14A {
    public static void main(String[] args){

        File file = new File("src/Inputs/day14.txt");

        try{

            final int STEPS = 10;

            Scanner sc =new Scanner(file);

            String template = sc.nextLine();
            Map<String, String> pairs = new HashMap<>();

            //Skip blank line
            sc.nextLine();


            while(sc.hasNextLine()){
                String[] strArray = sc.nextLine().split(" -> ");
                pairs.put(strArray[0],strArray[1]);
            }

            for(int i=0; i<STEPS; i++){
                String nextStep = template;
                String polymerPair = template.charAt(0) + String.valueOf(template.charAt(0));
                int totalInserts = 0;

                for(int j=1; j<template.length();j++){
                    polymerPair = String.valueOf(polymerPair.charAt(1)) + template.charAt(j);
                    if(pairs.containsKey(polymerPair)){
                        nextStep = insertString(nextStep, pairs.get(polymerPair), j+totalInserts);
                        totalInserts++;
                    }
                }

                template = nextStep;
                System.out.println(template);
            }

            Map<Character,Integer> charCount = new HashMap<>();
            for(int i=0; i < template.length();i++){

                char c = template.charAt(i);
                if(charCount.containsKey(c)){
                    int val = charCount.get(c);
                    charCount.put(c, val + 1);
                }else{
                    charCount.put(c, 1);
                }
            }

            Map.Entry<Character, Integer> maxEntry = null;
            Map.Entry<Character, Integer> minEntry = null;

            for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }

                if(minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0){
                    minEntry = entry;
                }
            }

            System.out.println(maxEntry.getValue() - minEntry.getValue());




        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static String insertString(String original, String insert, int index){
        String newString = "";
        for(int i=0; i<original.length(); i++){
            if(i==index){
                newString+= insert;
            }
            newString += original.charAt(i);
        }
        return newString;
    }
}
