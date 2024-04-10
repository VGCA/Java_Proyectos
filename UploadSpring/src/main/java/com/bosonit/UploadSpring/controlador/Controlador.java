package com.bosonit.UploadSpring.controlador;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Controlador {
    
    @GetMapping("/index")
    public String mostrarIndex(){
        return "uploader";
    }

    @PostMapping("/subir")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        String nombreFichero=file.getOriginalFilename();

        try{
            file.transferTo(new File("/tmp/"+nombreFichero));
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("Archivo subido con éxito");
    }

}
