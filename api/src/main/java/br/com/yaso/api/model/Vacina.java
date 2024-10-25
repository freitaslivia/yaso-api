package br.com.yaso.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "YASO_VACINA")
public class Vacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacina")
    private Long id;

    @Column(name = "titulo", nullable = true)
    private String titulo;


    @Column(name = "nome_vacina", nullable = true)
    private String nome;

    @Column(name = "data_aplicacao", nullable = true)
    private String dataAplicacao;

    @Lob
    @Column(name = "comprovante", nullable = true, columnDefinition = "LONGBLOB")
    private byte[] comprovante;

    @Column(name = "responsavel", nullable = true)
    private String responsavel;

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

    public String getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(String dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public byte[] getComprovante() {
        return comprovante;
    }

    public void setComprovante(byte[] comprovante) {
        this.comprovante = comprovante;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

}
