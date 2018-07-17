package com.tbl.converter.Service;

import com.tbl.converter.Models.Analyze;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;

@Service
public class AnalyzeService {

    public Analyze analyzeFile(String path){
        HashSet<String>  lista = new HashSet<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(path)))){
            String line;
            boolean start = false;
            while((line = br.readLine()) != null){
                String after = line.trim().replaceAll("  +","\t");

                String[] tempTab = after.split("\t");
                if(tempTab.length > 1){
                    if(start)
                        lista.add(tempTab[0]);
                    if(tempTab[0].contains("FEATURES"))
                        start = true;
                }
                if(tempTab[0].contains("\\\\"))
                    start = false;
            }
        }catch (Exception e){

        }
        return Analyze.builder()
                .atributes(lista)
                .build();
    }

}
