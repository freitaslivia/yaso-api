package br.com.yaso.api.service;

import br.com.yaso.api.dto.AlergiaResponse;
import br.com.yaso.api.model.Alergia;
import org.springframework.stereotype.Service;

@Service
public class AlergiaService {
    public AlergiaResponse alergiaToResponse(Alergia obj) {

        AlergiaResponse response = new AlergiaResponse(obj);

        response.setId(obj.getId());
        response.setNome(obj.getNome());
        response.setTipo(obj.getTipo());
        response.setEscala(obj.getEscala());
        response.setDescricao(obj.getDescricao());
        response.setCuidados(obj.getCuidados());
        response.setFile(obj.getFile());
        response.setIdUsuario(obj.getUsuario().getId());

        return response;
    }
}