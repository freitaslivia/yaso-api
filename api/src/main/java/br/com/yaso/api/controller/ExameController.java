package br.com.yaso.api.controller;

import br.com.yaso.api.dto.ExameResponse;
import br.com.yaso.api.model.Exame;
import br.com.yaso.api.model.User;
import br.com.yaso.api.repository.ExameRepository;
import br.com.yaso.api.repository.UserRepository;
import br.com.yaso.api.service.ExameService;
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
// localhost:8080/exames
@RequestMapping(value = "/exames")
@Tag(name = "api-exames")
public class ExameController {

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private ExameService exameService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ExameResponse> create(
            @RequestParam(value = "nome", required = false) String nome ,
            @RequestParam(value ="tipo", required = false) String tipo,
            @RequestParam(value ="escala", required = false) String escala,
            @RequestParam(value ="descricao", required = false) String descricao,
            @RequestParam(value ="idUsuario", required = false) Long idUsuario,
            @RequestParam(value = "file", required = true) MultipartFile file) throws IOException {

        Exame exame = new Exame();
        exame.setNome(nome);
        exame.setTipo(tipo);
        exame.setEscala(escala);
        exame.setDescricao(descricao);

        if (file != null && !file.isEmpty()) {
            exame.setFile(file.getBytes());
        }

        User usuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));
        exame.setUsuario(usuario);

        Exame exameSalva = exameRepository.save(exame);

        ExameResponse exameResponse = exameService.exameToResponse(exameSalva);

        return new ResponseEntity<>(exameResponse, HttpStatus.CREATED);
    }


    @GetMapping(value = "/file/{idExame}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getFile(@PathVariable Long idExame) {

        Optional<Exame> optionalExame = exameRepository.findById(idExame);
        if (optionalExame.isPresent()) {
            Exame exame = optionalExame.get();

            byte[] file = exame.getFile();
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
    public ResponseEntity<ExameResponse> getExameByUserIdAndExameId(@PathVariable Long userId, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            Optional<Exame> exame = user.get().getExames().stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst();

            if (exame.isPresent()) {
                ExameResponse response = exameService.exameToResponse(exame.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<ExameResponse>> getExamesByUserId(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Exame> exames = user.get().getExames();
            List<ExameResponse> exameResponses = exames.stream()
                    .map(exame -> exameService.exameToResponse(exame))
                    .toList();
            return new ResponseEntity<>(exameResponses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}