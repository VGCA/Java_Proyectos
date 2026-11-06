package com.portfolio.ecommerce.application;

import com.portfolio.ecommerce.application.port.in.UsuarioUseCase.DatosRegistro;
import com.portfolio.ecommerce.application.service.UsuarioService;
import com.portfolio.ecommerce.domain.exception.EmailYaRegistradoException;
import com.portfolio.ecommerce.domain.model.Usuario;
import com.portfolio.ecommerce.support.Fakes.CodificadorFalso;
import com.portfolio.ecommerce.support.Fakes.UsuarioRepoEnMemoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UsuarioServiceTest {

    private final UsuarioService service = new UsuarioService(new UsuarioRepoEnMemoria(), new CodificadorFalso());

    private DatosRegistro registro(String email) {
        return new DatosRegistro("Ana", null, email, "Calle 1", null, "secreto");
    }

    @Test
    void registrarAsignaRolUserYCodificaLaPassword() {
        Usuario u = service.registrar(registro("ana@mail.com"));
        assertEquals(Usuario.ROL_USER, u.getTipo());
        assertEquals("hash(secreto)", u.getPassword());
    }

    @Test
    void noPermiteDosUsuariosConElMismoEmail() {
        service.registrar(registro("ana@mail.com"));
        assertThrows(EmailYaRegistradoException.class, () -> service.registrar(registro("ana@mail.com")));
    }
}
