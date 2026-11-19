package com.efecto.web.mercado.victoria.services;



import com.efecto.web.mercado.victoria.dto.local.LocalResponse;
import com.efecto.web.mercado.victoria.dto.local.LocalRequest;
import com.efecto.web.mercado.victoria.dto.local.LocalUpdateRequest;

import java.util.List;

public interface LocalService {

    List<LocalResponse> obtenerLocales();
    LocalResponse obtenerLocalPorId(Long id);
    LocalResponse registrarLocal(LocalRequest localRequest);
    LocalResponse actualizarUbicacionLocal(Long id, LocalRequest localRequest);
    LocalResponse actualizarLocal(Long id, LocalUpdateRequest request);
    void desactivarLocal(Long id);
}
