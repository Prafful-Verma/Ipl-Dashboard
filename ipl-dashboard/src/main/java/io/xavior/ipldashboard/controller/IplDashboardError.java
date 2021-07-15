package io.xavior.ipldashboard.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IplDashboardError implements ErrorController {
    
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        String error = new String("Error 404 :  Error in IPL Dashboard Service !");
        return error;
    }
}

