package com.example.mvc_project_lab.controller;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    protected boolean isLogged(HttpServletRequest request){
        var userId = request.getSession().getAttribute("userId");

        return userId != null;
    }

}
