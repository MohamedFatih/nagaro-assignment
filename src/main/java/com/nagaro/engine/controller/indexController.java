package com.nagaro.engine.controller;

import com.nagaro.engine.model.FilterDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
public class indexController {

  @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("filterobject",new FilterDto());
        return "search";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/";
    }

//     @RequestMapping(value = "/403", method = RequestMethod.GET)
// @ResponseBody
//     public ResponseEntity error403() {
//         return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
//     }

}