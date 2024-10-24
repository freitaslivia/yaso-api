package br.com.yaso.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "YASO_EXAME")
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exame")
    private Long id;

    @Column(name = "nome_exame", nullable = true)
    private String nome;

    @Column(name = "tipo_exame", nullable = true)
    private String tipo;

    @Column(name = "medico_exame", nullable = true)
    private String escala;


    @Column(name = "descricao_exame", nullable = true)
    private String descricao;

    @Lob
    @Column(name = "file", nullable = true, columnDefinition = "LONGBLOB")
    private byte[] file;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="SUPER_ID_USUARIO", nullable=false)
    @JsonIgnore
    private User usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
