package com.javguerra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    // En el controlador, poner:
    // .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));

}
