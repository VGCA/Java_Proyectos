package com.example.demo.controlador;

import com.example.demo.entidades.Cuenta;
import com.example.demo.entidades.DTO;
import com.example.demo.servicio.CuentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CuentaController {

    @Autowired
    private CuentaServicio cuentaServicio;

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Cuenta verDetalles(@PathVariable Long id) {
        return cuentaServicio.buscarCuentaID(id);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Cuenta> verCuentas() {
        return cuentaServicio.listarCuentas();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cuenta guardarCuenta(@RequestBody Cuenta cuenta){
        return cuentaServicio.guardarCuentaID(cuenta);
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferirDinero(@RequestBody DTO dto) {
        cuentaServicio.transferirDinero(dto.getCuentaOrigenId(),
                dto.getCuentaDestinoId(), dto.getMonto(), dto.getBancoId());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("fecha", LocalDate.now().toString());
        respuesta.put("estado", "OK");
        respuesta.put("mensaje", "Transferencia realizada");
        respuesta.put("transaccionDTO", dto);
        return ResponseEntity.ok(respuesta);
    }
}
