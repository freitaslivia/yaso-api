package br.com.yaso.api.model;

import jakarta.persistence.*;

import java.util.List;


@Entity

@Table(name = "YASO_USUARIO")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPER_ID_USUARIO")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String username;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "SENHA", nullable = false)
    private String password;

   @OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private List<Vacina> vacinas;

    @OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Alergia> alergias;

    @OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exame> exames;

    @OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medicamento> medicamentos;

    @OneToMany(mappedBy="usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consulta> consultas;

    @Column(name = "TELEFONE", nullable = false)
    private String telephone;


    public User() {

    }

    public List<Vacina> getVacinas() {
        return vacinas;
    }

    public void setVacinas(List<Vacina> vacinas) {
        this.vacinas = vacinas;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Alergia> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<Alergia> alergias) {
        this.alergias = alergias;
    }

    public List<Exame> getExames() {
        return exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
