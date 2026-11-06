package com.efecto.web.mercado.victoria.services;



import com.efecto.web.mercado.victoria.dto.local.LocalResponse;
import com.efecto.web.mercado.victoria.dto.locatario.LocatarioRequest;

import java.util.List;

public interface LocalService {

    List<LocalResponse> obtenerLocales();
    LocalResponse obtenerLocalPorId(Long id);
    LocalResponse registrarLocal(LocatarioRequest locatarioRequest);
    LocalResponse actualizarUbicacionLocal(LocatarioRequest locatarioRequest);
}
