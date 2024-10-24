package br.com.yaso.api.service;

import br.com.yaso.api.dto.ConsultaRequest;
import br.com.yaso.api.dto.ConsultaResponse;
import br.com.yaso.api.model.Consulta;
import br.com.yaso.api.model.User;
import br.com.yaso.api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsultaService {

    @Autowired
    private UserRepository userRepository;

    public Consulta requestToConsulta(@Valid ConsultaRequest obj) throws IOException {

        Consulta consulta = new Consulta();

        consulta.setData(obj.getData());
        consulta.setHora(obj.getHora());
        consulta.setMedico(obj.getMedico());
        consulta.setCrm(obj.getCrm());
        consulta.setEspecialidade(obj.getEspecialidade());
        consulta.setTelefone(obj.getTelefone());
        consulta.setDescricao(obj.getDescricao());

        User usuario = userRepository.findById(obj.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + obj.getIdUsuario()));
        consulta.setUsuario(usuario);

        return consulta;
    }

    public ConsultaResponse consultaToResponse(Consulta obj) {

        ConsultaResponse response = new ConsultaResponse(obj);

        response.setId(obj.getId());
        response.setData(obj.getData());
        response.setHora(obj.getHora());
        response.setMedico(obj.getMedico());
        response.setCrm(obj.getCrm());
        response.setEspecialidade(obj.getEspecialidade());
        response.setTelefone(obj.getTelefone());
        response.setDescricao(obj.getDescricao());
        response.setIdUsuario(obj.getUsuario().getId());

        return response;
    }


}
