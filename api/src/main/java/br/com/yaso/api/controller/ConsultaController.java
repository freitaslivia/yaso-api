package br.com.yaso.api.controller;

import br.com.yaso.api.dto.ConsultaRequest;
import br.com.yaso.api.dto.ConsultaResponse;
import br.com.yaso.api.model.Consulta;
import br.com.yaso.api.model.User;
import br.com.yaso.api.repository.ConsultaRepository;
import br.com.yaso.api.repository.UserRepository;
import br.com.yaso.api.service.ConsultaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
// localhost:8080/consultas
@RequestMapping(value = "/consultas")
@Tag(name = "consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    ConsultaRepository consultaRepository;

    @PostMapping()
    public ResponseEntity<ConsultaResponse> create(@ModelAttribute @Valid ConsultaRequest consultaRequest) throws IOException {
        Consulta convertido = consultaService.requestToConsulta(consultaRequest);
        Consulta persistido = consultaRepository.save(convertido);
        ConsultaResponse consultaResponse = consultaService.consultaToResponse(persistido);
        return new ResponseEntity<>(consultaResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/{consultaId}")
    public ResponseEntity<ConsultaResponse> getConsultaByUserIdAndConsultaId(@PathVariable Long userId, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            Optional<Consulta> consulta = user.get().getConsultas().stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst();

            if (consulta.isPresent()) {
                ConsultaResponse consultaResponse = consultaService.consultaToResponse(consulta.get());
                return new ResponseEntity<>(consultaResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ConsultaResponse>> getConsultasByUserId(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Consulta> consultas = user.get().getConsultas();
            List<ConsultaResponse> consultaResponses = consultas.stream()
                    .map(consulta -> consultaService.consultaToResponse(consulta))
                    .toList();
            return new ResponseEntity<>(consultaResponses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
