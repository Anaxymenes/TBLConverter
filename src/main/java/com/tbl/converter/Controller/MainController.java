package com.tbl.converter.Controller;

import com.tbl.converter.Models.Analyze;
import com.tbl.converter.Service.AnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "api")
public class MainController {

    @Autowired
    AnalyzeService _analyzeService;
    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping(value = "analyze", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Analyze analyzeFile(String path){
        return _analyzeService.analyzeFile("C:\\R2D2\\main\\GB\\Another.gb");

    }

    @RequestMapping(value = "attribute")
    public String analyzeFilev2(Map<String,Object> model){

        model.put("attributes", _analyzeService.analyzeFile("C:\\R2D2\\main\\GB\\Another.gb"));
        return "index";
    }

}
