package com.efecto.web.mercado.victoria.controllers;

import com.efecto.web.mercado.victoria.dto.local.LocalRequest;
import com.efecto.web.mercado.victoria.dto.local.LocalResponse;
import com.efecto.web.mercado.victoria.dto.local.LocalUpdateRequest;
import com.efecto.web.mercado.victoria.services.LocalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/locales")
public class LocalController {

    private final LocalService localService;

    @GetMapping
    @PreAuthorize("hasAuthority('LOCAL_LISTAR')")
    public ResponseEntity<List<LocalResponse>> listarLocales() {
        return ResponseEntity.ok(localService.obtenerLocales());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('LOCAL_LISTAR')")
    public ResponseEntity<LocalResponse> obtenerLocal(@PathVariable Long id) {
        return ResponseEntity.ok(localService.obtenerLocalPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('LOCAL_CREAR')")
    public ResponseEntity<LocalResponse> crearLocal(@Valid @RequestBody LocalRequest localRequest) {
        return ResponseEntity.ok(localService.registrarLocal(localRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('LOCAL_ACTUALIZAR')")
    public ResponseEntity<LocalResponse> actualizarLocal(
            @PathVariable Long id,
            @Valid @RequestBody LocalUpdateRequest request) {
        return ResponseEntity.ok(localService.actualizarLocal(id, request));
    }

    @PatchMapping("/{id}/desactivar")
    @PreAuthorize("hasAuthority('LOCAL_DESACTIVAR')")
    public ResponseEntity<Void> desactivarLocal(@PathVariable Long id) {
        localService.desactivarLocal(id);
        return ResponseEntity.noContent().build();
    }
}
