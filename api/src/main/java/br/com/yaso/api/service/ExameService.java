package br.com.yaso.api.service;

import br.com.yaso.api.dto.ExameResponse;
import br.com.yaso.api.model.Exame;
import org.springframework.stereotype.Service;

@Service
public class ExameService {

    public ExameResponse exameToResponse(Exame obj) {

        ExameResponse response = new ExameResponse(obj);

        response.setId(obj.getId());
        response.setNome(obj.getNome());
        response.setTipo(obj.getTipo());
        response.setEscala(obj.getEscala());
        response.setDescricao(obj.getDescricao());
        response.setFile(obj.getFile());
        response.setIdUsuario(obj.getUsuario().getId());

        return response;
    }
}
