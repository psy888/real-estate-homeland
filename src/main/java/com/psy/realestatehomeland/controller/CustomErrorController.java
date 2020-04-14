package com.psy.realestatehomeland.controller;

import com.psy.realestatehomeland.utils.WebUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static java.util.Objects.nonNull;

public class CustomErrorController implements ErrorController {
    @GetMapping("/error")
    public String error (HttpServletRequest request, Model model){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(nonNull(status)){
            model.addAttribute("status", status.toString());
        }else {
            model.addAttribute("status", "Oooops");
        }
        return "errorPage";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403";
    }

}
