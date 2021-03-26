package com.nagaro.engine.controller;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.security.RolesAllowed;

import com.nagaro.engine.model.util.Crypt;
import com.nagaro.engine.services.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
   
    @Autowired
    SearchService searchService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/dosearch")
    public String dosearch (
        @RequestParam(value = "fromdate", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromdate,
        @RequestParam(value = "todate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date todate,
        @RequestParam(value = "higher", required = false) Double higher,
        @RequestParam(value = "lower", required = false) Double lower,Model model) throws ParseException{
 
            System.out.println("CCCCCCCCCCCCC: "+SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            model.addAttribute("key",Crypt.encodedBase64Key);
            model.addAttribute("statements", searchService.search(fromdate,todate,higher,lower));

        return "/search";
    }
}