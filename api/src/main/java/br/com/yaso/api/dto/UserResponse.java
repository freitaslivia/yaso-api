package br.com.yaso.api.dto;

import br.com.yaso.api.model.User;

public class UserResponse {
    private Long id;
    private String username;
    private String email;

    public UserResponse(String username, String email, Long id) {
        this.username = username;
        this.email = email;
        this.id = id;
    }

    public UserResponse(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.id = user.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
