package br.com.yaso.api.service;

import br.com.yaso.api.dto.MedicamentoResponse;
import br.com.yaso.api.model.Medicamento;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService {

    public MedicamentoResponse medicamentoResponse(Medicamento obj) {

        MedicamentoResponse response = new MedicamentoResponse(obj);

        response.setId(obj.getId());
        response.setNome(obj.getNome());
        response.setTipo(obj.getTipo());
        response.setData(obj.getData());
        response.setDosagem(obj.getDosagem());
        response.setPeriodo(obj.getPeriodo());
        response.setMedico(obj.getMedico());
        response.setFile(obj.getFile());
        response.setIdUsuario(obj.getUsuario().getId());

        return response;
    }
}
