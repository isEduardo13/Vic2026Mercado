package com.efecto.web.mercado.victoria.services;

import com.efecto.web.mercado.victoria.dto.locatario.LocatarioRequest;
import com.efecto.web.mercado.victoria.dto.locatario.LocatarioResponse;

import java.util.List;

public interface LocatarioService {

List<LocatarioResponse> obtenerLocatarios();
LocatarioResponse obtenerLocatario(Long id);
LocatarioResponse registrarLocatario(LocatarioRequest locatarioRequest);
LocatarioResponse actualizarLocatario(Long id, LocatarioRequest locatarioRequest);
void eliminarLocatario(Long id);
}
