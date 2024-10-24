package br.com.yaso.api.controller;

import br.com.yaso.api.dto.VacinaResponse;
import br.com.yaso.api.model.User;
import br.com.yaso.api.model.Vacina;
import br.com.yaso.api.repository.UserRepository;
import br.com.yaso.api.repository.VacinaRepository;
import br.com.yaso.api.service.VacinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
// localhost:8080/vacinas
@RequestMapping(value = "/vacinas")
@Tag(name = "api-vacinas")

public class VaccineController {
    @Autowired
    private VacinaRepository vacinaRepository;
    @Autowired
    private VacinaService vacinaService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<VacinaResponse> create(
            @RequestParam("nome") String nome,
            @RequestParam("dataAplicacao") String dataAplicacao,
            @RequestParam("titulo") String titulo,
            @RequestParam("responsavel") String responsavel,
            @RequestParam("idUsuario") Long idUsuario,
            @RequestParam(value = "comprovante", required = false) MultipartFile comprovante) throws IOException {

        Vacina vacina = new Vacina();
        vacina.setTitulo(titulo);
        vacina.setNome(nome);
        vacina.setDataAplicacao(dataAplicacao);
        vacina.setResponsavel(responsavel);


        if (comprovante != null && !comprovante.isEmpty()) {
            System.out.println("antes recebido: " + comprovante.getOriginalFilename());
            vacina.setComprovante(comprovante.getBytes());
            System.out.println("Arquivo recebido: " + comprovante.getOriginalFilename());
        }

        User usuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));
        vacina.setUsuario(usuario);

        Vacina vacinaSalva = vacinaRepository.save(vacina);

        VacinaResponse vacinaResponse = vacinaService.vacinaToResponse(vacinaSalva);

        return new ResponseEntity<>(vacinaResponse, HttpStatus.CREATED);
    }

/*
        @PostMapping(value = "/uploadComprovante", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadComprovante(
            @RequestParam("idVacina") Long idVacina,
            @RequestParam("comprovante") MultipartFile file) {

        try {
            // Buscar a vacina pelo ID
            Optional<Vacina> optionalVacina = vacinaRepository.findById(idVacina);
            if (optionalVacina.isPresent()) {
                Vacina vacina = optionalVacina.get();

                // Converte o arquivo em bytes
                vacina.setComprovante(file.getBytes());

                // Salva a vacina no banco de dados
                vacinaRepository.save(vacina);

                return ResponseEntity.ok("Comprovante enviado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacina não encontrada!");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o comprovante");
        }
    }
*/
    @GetMapping(value = "/comprovante/{idVacina}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getComprovante(@PathVariable Long idVacina) {

        // Buscar a vacina pelo ID
        Optional<Vacina> optionalVacina = vacinaRepository.findById(idVacina);
        if (optionalVacina.isPresent()) {
            Vacina vacina = optionalVacina.get();

            // Verificar se a vacina possui comprovante
            byte[] comprovante = vacina.getComprovante();
            if (comprovante != null) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(comprovante);
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
    public ResponseEntity<VacinaResponse> getExameByUserIdAndVacinaId(@PathVariable Long userId, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            Optional<Vacina> vacina = user.get().getVacinas().stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst();

            if (vacina.isPresent()) {
                VacinaResponse response = vacinaService.vacinaToResponse(vacina.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Retorna as vacinas de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vacinas retornadas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{userId}")
    public ResponseEntity<List<VacinaResponse>> getVacinasByUserId(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Vacina> vacinas = user.get().getVacinas();
            List<VacinaResponse> vacinaResponses = vacinas.stream()
                    .map(vacina -> vacinaService.vacinaToResponse(vacina))
                    .toList();
            return new ResponseEntity<>(vacinaResponses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}


