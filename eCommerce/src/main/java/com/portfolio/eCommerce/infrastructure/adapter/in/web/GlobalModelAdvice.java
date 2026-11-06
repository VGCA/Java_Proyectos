package com.portfolio.ecommerce.infrastructure.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/** Expone "sesion" (id del usuario logueado o null) a todas las plantillas Thymeleaf. */
@ControllerAdvice
public class GlobalModelAdvice {

    @ModelAttribute("sesion")
    public Object sesion(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session == null ? null : session.getAttribute(SesionKeys.ID_USUARIO);
    }
}
