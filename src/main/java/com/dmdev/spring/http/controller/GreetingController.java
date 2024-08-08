package com.dmdev.spring.http.controller;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
//чтобы установить аттрибуты на уровне сессии
@SessionAttributes({"user"})
public class GreetingController {

    //вызывается на каждый запрос
    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello")
    public String hello(Model model,
                        HttpServletRequest request,
                        UserReadDto userReadDto) {
        model.addAttribute("user", userReadDto);
        return "greeting/hello";
    }

    @GetMapping("/bye")
    public String bye(ModelAndView modelAndView,
                            //получаем сессионный атрибут
                            @SessionAttribute("user") UserReadDto user) {
        return "greeting/bye";
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView,
                              HttpServletRequest request,
                              //параметр будет обязателен
                              @RequestParam Integer age,
                              @RequestHeader String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable("id") Integer id) {
        modelAndView.setViewName("greeting/hello");
        return modelAndView;
    }
}
