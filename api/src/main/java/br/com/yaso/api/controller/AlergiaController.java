package br.com.yaso.api.controller;

import br.com.yaso.api.dto.AlergiaResponse;
import br.com.yaso.api.dto.ConsultaResponse;
import br.com.yaso.api.dto.VacinaResponse;
import br.com.yaso.api.model.Alergia;
import br.com.yaso.api.model.Consulta;
import br.com.yaso.api.model.User;
import br.com.yaso.api.repository.AlergiaRepository;
import br.com.yaso.api.repository.UserRepository;
import br.com.yaso.api.service.AlergiaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
// localhost:8080/alergias
@RequestMapping(value = "/alergias")
@Tag(name = "api-alergias")

public class AlergiaController {

    @Autowired
    private AlergiaRepository alergiaRepository;
    @Autowired
    private AlergiaService alergiaService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<AlergiaResponse> create(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "escala", required = false) String escala,
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "cuidados", required = false) String cuidados,
            @RequestParam(value = "idUsuario", required = false) Long idUsuario,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        Alergia alergia = new Alergia();
        alergia.setNome(nome);
        alergia.setTipo(tipo);
        alergia.setEscala(escala);
        alergia.setDescricao(descricao);
        alergia.setCuidados(cuidados);

        if (file != null && !file.isEmpty()) {
            alergia.setFile(file.getBytes());
        }

        User usuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));
        alergia.setUsuario(usuario);

        Alergia alergiaSalva = alergiaRepository.save(alergia);

        AlergiaResponse alergiaResponse = alergiaService.alergiaToResponse(alergiaSalva);

        return new ResponseEntity<>(alergiaResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/file/{idAlergia}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getFile(@PathVariable Long idAlergia) {


        Optional<Alergia> optionalAlergia = alergiaRepository.findById(idAlergia);
        if (optionalAlergia.isPresent()) {
            Alergia alergia = optionalAlergia.get();

            byte[] file = alergia.getFile();
            if (file != null) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(file);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping("/{userId}/{id}")
    public ResponseEntity<AlergiaResponse> getAlergiaByUserIdAndConsultaId(@PathVariable Long userId, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            Optional<Alergia> alergia = user.get().getAlergias().stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst();

            if (alergia.isPresent()) {
                AlergiaResponse response = alergiaService.alergiaToResponse(alergia.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<AlergiaResponse>> getAlergiasByUserId(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Alergia> alergias = user.get().getAlergias();
            List<AlergiaResponse> alergiaResponses = alergias.stream()
                    .map(alergia -> alergiaService.alergiaToResponse(alergia))
                    .toList();
            return new ResponseEntity<>(alergiaResponses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*
    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Alergia> alergia = user.get().getAlergias().stream()
                    .filter(a -> a.getId().equals(id))
                    .findFirst();

            if (alergia.isPresent()) {
                alergiaRepository.delete(alergia.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    */
}

