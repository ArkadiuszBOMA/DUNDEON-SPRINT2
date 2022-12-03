package com.codecool.dungeoncrawl.utils;

public class UtilsTextHandling {
    public static String capitalized(String name){
        char[] chars = name.toLowerCase().toCharArray();
        boolean found = false;
        for(int i=0; i<chars.length; i++){
            if(!found && Character.isLetter(chars[i])){
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\''){
                found = false;
            }
        }
        return String.valueOf(chars);
    }
}
