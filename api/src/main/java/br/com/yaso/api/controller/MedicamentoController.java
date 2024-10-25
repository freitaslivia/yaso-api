package br.com.yaso.api.controller;

import br.com.yaso.api.dto.MedicamentoResponse;
import br.com.yaso.api.model.Medicamento;
import br.com.yaso.api.model.User;
import br.com.yaso.api.repository.MedicamentoRepository;
import br.com.yaso.api.repository.UserRepository;
import br.com.yaso.api.service.MedicamentoService;
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
@RequestMapping(value = "/medicamentos")
@Tag(name = "api-medicamentos")
public class MedicamentoController {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<MedicamentoResponse> create(
            @RequestParam(value ="nome", required = false) String nome,
            @RequestParam(value ="tipo", required = false) String tipo,
            @RequestParam(value ="data", required = false) String data,
            @RequestParam(value ="dosagem", required = false) String dosagem,
            @RequestParam(value ="periodo", required = false) String periodo,
            @RequestParam(value ="medico", required = false) String medico,
            @RequestParam(value ="idUsuario", required = false ) Long idUsuario,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {


        Medicamento medicamento = new Medicamento();
        medicamento.setNome(nome);
        medicamento.setTipo(tipo);
        medicamento.setData(data);
        medicamento.setDosagem(dosagem);
        medicamento.setPeriodo(periodo);
        medicamento.setMedico(medico);

        if (file != null && !file.isEmpty()) {
            medicamento.setFile(file.getBytes());
        }

        User usuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));
        medicamento.setUsuario(usuario);

        Medicamento salva = medicamentoRepository.save(medicamento);

        MedicamentoResponse response = medicamentoService.medicamentoResponse(salva);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping(value = "/file/{idMedicamento}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getFile(@PathVariable Long idMedicamento) {

        Optional<Medicamento> optional = medicamentoRepository.findById(idMedicamento);
        if (optional.isPresent()) {
            Medicamento medicamento = optional.get();

            byte[] file = medicamento.getFile();
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
    public ResponseEntity<MedicamentoResponse> getExameByUserIdAndExameId(@PathVariable Long userId, @PathVariable Long id) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            Optional<Medicamento> medicamento = user.get().getMedicamentos().stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst();

            if (medicamento.isPresent()) {
                MedicamentoResponse response = medicamentoService.medicamentoResponse(medicamento.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MedicamentoResponse>> getMedicamentosByUserId(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Medicamento> medicamentos = user.get().getMedicamentos();
            List<MedicamentoResponse> medicamentoResponses = medicamentos.stream()
                    .map(medicamento -> medicamentoService.medicamentoResponse(medicamento))
                    .toList();
            return new ResponseEntity<>(medicamentoResponses, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}