package br.com.yaso.api.service;

import br.com.yaso.api.dto.LoginRequest;
import br.com.yaso.api.dto.UserRequest;
import br.com.yaso.api.dto.UserResponse;
import br.com.yaso.api.model.User;
import br.com.yaso.api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
/*
    @Autowired
    private NumeroService numeroService;
*/
    public User requestToUser(@Valid UserRequest userRequest)  {
        verificarEmail(userRequest.getEmail());
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setTelephone(userRequest.getTelephone());
    /*
        String numeroUnico = (String) numeroService.gerarNumeroUnico();
        user.setNumero(numeroUnico);

        // Verifique se a foto não é nula antes de tentar acessá-la
        if (userRequest.getPhoto() != null && !userRequest.getPhoto().isEmpty()) {
            user.setPhoto(userRequest.getPhoto().getBytes());
        }
    */
        return user;
    }

    public void verificarEmail(String email) {

        if (userRepository.existsByEmail(email)) {
            throw new ErroNegocioException("Já existe um usuário com o mesmo EMAIL");
        }
    }

    public UserResponse userToResponse(User user) {
        UserResponse userResponse = new UserResponse(user);
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());

        return userResponse;
    }

    @Transactional
    public UserResponse logar(@Valid LoginRequest loginRequest) {
        User user = verificarSenhaEmailLogin(loginRequest);

        return new UserResponse(user);
    }

    public User verificarSenhaEmailLogin(LoginRequest login) {
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
        if(user == null) {
            throw new ErroNegocioException("Usuario não encontrado");
        }

        if (!user.getPassword().equals(login.getPassword())) {
            throw new ErroNegocioException("Senha Incorreta");
        }

        return user;
    }


}
