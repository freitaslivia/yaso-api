package br.com.yaso.api.service;

import br.com.yaso.api.dto.VacinaRequest;
import br.com.yaso.api.dto.VacinaResponse;
import br.com.yaso.api.model.User;
import br.com.yaso.api.model.Vacina;
import br.com.yaso.api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VacinaService {

    @Autowired
    private UserRepository userRepository;

    public Vacina requestToVacina(@Valid VacinaRequest vacinaRequest) throws IOException {
        Vacina vacina = new Vacina();
        vacina.setNome(vacinaRequest.getNome());
        vacina.setDataAplicacao(vacinaRequest.getDataAplicacao());

        if (vacinaRequest.getComprovante() != null && !vacinaRequest.getComprovante().isEmpty()) {
            vacina.setComprovante(vacinaRequest.getComprovante().getBytes());
        }
       //vacina.setComprovante(vacinaRequest.getComprovante());

        User usuario = userRepository.findById(vacinaRequest.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + vacinaRequest.getIdUsuario()));
        vacina.setUsuario(usuario);

        return vacina;
    }

    public VacinaResponse vacinaToResponse(Vacina vacina) {

        VacinaResponse vacinaResponse = new VacinaResponse(vacina);

        vacinaResponse.setId(vacina.getId());
        vacinaResponse.setNome(vacina.getNome());
        vacinaResponse.setDataAplicacao(vacina.getDataAplicacao());

        vacinaResponse.setComprovante(vacina.getComprovante());

        vacinaResponse.setDataRecorrente(vacinaResponse.getDataRecorrente());

        vacinaResponse.setIdUsuario(vacina.getUsuario().getId());

        return vacinaResponse;
    }
}
