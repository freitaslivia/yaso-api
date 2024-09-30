package br.com.yaso.api.model;

import jakarta.persistence.*;


@Entity

@Table(name = "YASO_USUARIO")
public class User {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_user",
            allocationSize = 1)
    @Column(name = "SUPER_ID_USUARIO")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String username;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "SENHA", nullable = false)
    private String password;


    //@Lob
    //@Column(name = "FOTO", nullable = true)
    //private byte[] photo;


    //@Column(name = "NUMERO_YASO", nullable = true)
    //private String numero;

    @Column(name = "TELEFONE", nullable = false)
    private String telephone;


    public User() {

    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
/*
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
*/
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
