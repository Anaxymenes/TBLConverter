package com.tbl.converter.Service.util;

public class StringMethods {

    static public String replaceAMoreThenTwoSpaceByTab(String txt){
        return txt.trim().replaceAll("  +","\t");
    }

    static public String[] splitStringByTab(String txt){
        return txt.split("\t");
    }

    static public String[] replaceAndSplitString(String txt){
        return splitStringByTab(replaceAMoreThenTwoSpaceByTab(txt));
    }

    static public String removeSpecialCharsFromDetails(String txt){
        txt = txt.replaceAll("/","");
        txt = txt.replaceAll("\"","");
        return txt;
    }
}
