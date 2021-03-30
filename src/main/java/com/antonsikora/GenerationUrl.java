package com.antonsikora;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenerationUrl {

    private static char[] chars = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i',
            'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};

    private static ArrayDeque<String> urlList= new ArrayDeque<>(42);

    public static Collection<String> generationURL (String url, int characters){ //Brute-force for search other link
        addToList(url.substring(0,url.length()-characters), characters);
        return urlList;
    }

    private static void addToList(String partUrl,int iteration){ //recursive method for generation url
        iteration--;
        if(iteration==0){
            for(char s :chars){
                urlList.add(partUrl+s);
            }
        }else {
            for(char s :chars){
                addToList(partUrl+s,iteration);
            }
        }
    }
}