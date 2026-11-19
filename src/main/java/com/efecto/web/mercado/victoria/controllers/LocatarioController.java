package com.efecto.web.mercado.victoria.controllers;


import com.efecto.web.mercado.victoria.dto.locatario.LocatarioRequest;
import com.efecto.web.mercado.victoria.dto.locatario.LocatarioResponse;
import com.efecto.web.mercado.victoria.services.LocatarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/locatarios")
public class LocatarioController {

    private final LocatarioService locatarioService;


    @GetMapping
    @PreAuthorize("hasAuthority('LOCATARIO_LISTAR')")
    public ResponseEntity<List<LocatarioResponse>> listarLocatarios(){
        return  ResponseEntity.ok(locatarioService.obtenerLocatarios());
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LOCATARIO_LISTAR')")
    public ResponseEntity<LocatarioResponse> obtenerLocatario(@PathVariable Long id){
        return ResponseEntity.ok(locatarioService.obtenerLocatario(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LOCATARIO_CREAR')")
    public ResponseEntity<LocatarioResponse> crearLocatario(@RequestBody LocatarioRequest locatarioRequest){
        return ResponseEntity.ok(locatarioService.registrarLocatario(locatarioRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LOCATARIO_ACTUALIZAR')")
    public ResponseEntity<LocatarioResponse> actualizarLocatario(@PathVariable Long id, @RequestBody LocatarioRequest locatarioRequest){
        return ResponseEntity.ok(locatarioService.actualizarLocatario(id, locatarioRequest));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('LOCATARIO_ELIMINAR')")
    public ResponseEntity<LocatarioResponse> eliminarLocatario(@PathVariable Long id){
        locatarioService.eliminarLocatario(id);
        return ResponseEntity.ok().build();
    }








}
