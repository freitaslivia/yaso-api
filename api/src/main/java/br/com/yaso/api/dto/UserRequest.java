package br.com.yaso.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

public class UserRequest {

    @Schema(description = "Nome", example = "Livia")
    private String username;
    @Schema(description = "Senha", example = "123456")
    private String password;
    @Schema(description = "Email", example = "livia@gmail.com")
    private String email;
    /*
    @Schema(description = "Foto do usuário", type = "string", format = "binary")
    private MultipartFile photo;
    */
    @Schema(description = "Telefone", example = "119888")
    private String telephone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
/*
    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
