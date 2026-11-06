package com.portfolio.ecommerce.infrastructure.adapter.in.security;

import com.portfolio.ecommerce.domain.model.Usuario;
import com.portfolio.ecommerce.infrastructure.adapter.in.web.SesionKeys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/** Tras el login: guarda el id en sesión (lo usan las plantillas) y redirige según el rol. */
@Component
public class LoginExitosoHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UsuarioPrincipal principal = (UsuarioPrincipal) authentication.getPrincipal();
        request.getSession().setAttribute(SesionKeys.ID_USUARIO, principal.getId());

        boolean admin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_" + Usuario.ROL_ADMIN));
        response.sendRedirect(request.getContextPath() + (admin ? "/administrador" : "/"));
    }
}
