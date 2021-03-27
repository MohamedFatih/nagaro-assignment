package com.nagaro.engine.controller;

import java.text.ParseException;

import com.nagaro.engine.model.FilterDto;
import com.nagaro.engine.model.util.Crypt;
import com.nagaro.engine.services.SearchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StatementController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SearchService searchService;

    @PostMapping("/dosearch")
    public String dosearch(FilterDto filter,Model model) {
        
        logger.info("retrieving statement with parameters: "+filter );
            model.addAttribute("key",Crypt.encodedBase64Key);
            model.addAttribute("filterobject",filter);

            if(! ObjectUtils.isEmpty(filter.getFromdate()) || ! ObjectUtils.isEmpty(filter.getTodate()) ||
            ! ObjectUtils.isEmpty(filter.getHigher()) || ! ObjectUtils.isEmpty(filter.getLower()) ){

                model.addAttribute("statements", searchService.adminSearch(filter.getFromdate()
            ,filter.getTodate(),filter.getHigher(),filter.getLower()));
            
            } 
            else {
               model.addAttribute("statements", searchService.search(filter.getFromdate()
            ,filter.getTodate(),filter.getHigher(),filter.getLower()));
            }
 
        return "search";
    }

}