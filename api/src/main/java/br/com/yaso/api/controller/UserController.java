package br.com.yaso.api.controller;

import br.com.yaso.api.dto.LoginRequest;
import br.com.yaso.api.dto.UserRequest;
import br.com.yaso.api.dto.UserResponse;
import br.com.yaso.api.model.User;
import br.com.yaso.api.repository.UserRepository;
import br.com.yaso.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
// localhost:8080/users
@RequestMapping(value = "/users")
@Tag(name = "api-user")

public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Operation(summary = "Grava um Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            }),
            @ApiResponse(responseCode = "500", description = "Erro de Negocio", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping()
    public ResponseEntity<UserResponse> create(@ModelAttribute @Valid UserRequest userRequest) {
        User userConvertido = userService.requestToUser(userRequest);
        User userPersistido = userRepository.save(userConvertido);
        UserResponse userResponse = userService.userToResponse(userPersistido);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
    @PostMapping("login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        UserResponse user = this.userService.logar(loginRequest);

        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAll() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(userService.userToResponse(user));

        }
        if (users.isEmpty()) {
            return new ResponseEntity<>(userResponses, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(userResponses);
    }

}
