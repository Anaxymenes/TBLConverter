package com.tbl.converter.Controller;

import com.tbl.converter.Models.Analyze;
import com.tbl.converter.Service.AnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "api")
public class MainController {

    @Autowired
    AnalyzeService analyzeService;
    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping(value = "analyze", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Analyze analyzeFile(String path){
        return analyzeService.analyzeFile("C:\\R2D2\\Main\\GB\\Another.gb");

    }

    @RequestMapping(value = "attribute/{filename}", method = RequestMethod.GET)
    public String analyzeFilev2(Map<String,Object> model, @PathVariable(name = "filename")String filename){
        Analyze analyze = analyzeService.analyzeFile("C:\\TestGB\\"+filename+".gb");
        model.put("attributes", analyze);
        analyzeService.runConvertToTBL("C:\\TestGB\\"+filename+".gb", analyze);


        return "index";
    }

}
