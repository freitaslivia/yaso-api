package br.com.yaso.api.dto;

import br.com.yaso.api.model.Exame;

public class ExameResponse {
    private Long id;

    private String nome;

    private String tipo;

    private String escala;

    private String descricao;

    private byte[] file;

    private Long idUsuario;

    public ExameResponse(Exame obj) {
    }


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

    public String getDescricao(String descricao) {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getFile(byte[] file) {
        return this.file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
