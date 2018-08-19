package com.tbl.converter.Service;

import com.tbl.converter.Models.Analyze;
import com.tbl.converter.Models.Detail;
import com.tbl.converter.Models.Row;
import com.tbl.converter.Models.Sequence;
import com.tbl.converter.Service.util.StringMethods;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
                //String after = line.trim().replaceAll("  +","\t");
                //String[] tempTab = after.split("\t");
                String[] tempTab = StringMethods.replaceAndSplitString(line);
                if(tempTab.length > 1){
                    if(start)
                        lista.add(tempTab[0]);
                    if(tempTab[0].contains("FEATURES"))
                        start = true;
                }
                if(     tempTab[0].contains("\\\\") ||
                        tempTab[0].contains("//") ||
                        tempTab[0].contains("LOCUS"))
                    start = false;
            }
        }catch (Exception e){

        }
        return Analyze.builder()
                .atributes(lista)
                .build();
    }

    public boolean runConvertToTBL(String path, Analyze analyze){
        File file = new File(path);
        String sequenceName = "";
        List<Detail> details = new ArrayList<>();
        List<Row> rows = new ArrayList<>();
        Sequence currentSequence = null;
        List<Sequence> sequences = new ArrayList<>();
        Row currentRow = null;
        Detail currentDetail = null;
        try(BufferedReader br = new BufferedReader(new FileReader(new File(path)))){
            String line;
            boolean start = false;
            boolean exist = false;
            while((line = br.readLine()) != null){
                String[] tempTab = StringMethods.replaceAndSplitString(line);
                if(tempTab.length > 1){
                    if(start && analyze.getAtributes().contains(tempTab[0])){
                        int a = 0;
                    }
                }
                switch (tempTab[0]){
                    case "LOCUS":
                        sequenceName = tempTab[1];

                    case "//" :
                    case "\\\\":
                            start = false;
                            details = new ArrayList<>();
                        break;
                    case "FEATURES":
                        start = true;
                        break;
                } // End switch
                if(start && tempTab.length == 1 && exist){
                    char[] chars = tempTab[0].toCharArray();
                        if(chars[0] == '/'){
                            if(currentDetail != null)
                                details.add(currentDetail);
                            currentDetail = getDetail(tempTab[0]);
                            int b = 0;
                        }else{
                            currentDetail.setValue(
                                    currentDetail.getValue() +
                                            " " +
                                            StringMethods.removeSpecialCharsFromDetails(tempTab[0])
                            );
                        }
                }
                if(tempTab.length>=2 && start){
                    if(analyze.getAtributes().contains(tempTab[0])){
                        if(currentRow != null){
                            details.add(currentDetail);
                            currentRow.setDetails(details);
                            rows.add(currentRow);
                            details = new ArrayList<>();
                        }
                    }
                } // End if(tempTab.length>=2)

            } //End while loop
        }catch (Exception e){

        }

        return false;
    }
    protected Detail getDetail(String txt){
        txt = StringMethods.removeSpecialCharsFromDetails(txt);
        String[] temp = txt.split("=");
        return Detail.builder()
                .name(temp[0])
                .value(temp[1])
                .build();
    }
    //protected Detail getDetail(String )
}
