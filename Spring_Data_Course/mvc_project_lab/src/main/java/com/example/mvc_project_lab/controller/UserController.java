package com.example.mvc_project_lab.controller;

import com.example.mvc_project_lab.dto.UserLoginDto;
import com.example.mvc_project_lab.dto.UserRegisterDto;
import com.example.mvc_project_lab.service.UserService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
public class UserController extends BaseController{

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String register(){
        return "user/register";
    }

    @PostMapping("/users/register")
    public String register(UserRegisterDto userRegisterDto, Model model){
        if (this.userService.register(userRegisterDto)){
            return "redirect:/users/login";
        }

        model.addAttribute("error", "There is an error");

        return "user/register";
    }

    @GetMapping("/users/login")
    public String login(UserLoginDto user, Model model){
        model.addAttribute("userLoginForm", user);
        return "user/login";
    }

    @PostMapping("/users/login")
    public String login(@Valid UserLoginDto dto, BindingResult result, Model model, HttpServletRequest req){
        if (result.hasErrors()){
            model.addAttribute("errors", result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
            return this.login(dto, model);
        }

        var userId = this.userService.validateUserLoginDetails(dto);

        if (userId == null) {
            model.addAttribute("error", "There is an error");

            return "user/login";
        }

        req.getSession().setAttribute(
                "userId",
                userId
        );

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        if (this.isLogged(request)) {
            request.getSession().setAttribute(
                    "userId",
                    null
            );
        }

        return "redirect:/";
    }
}
