package br.com.yaso.api.dto;


import br.com.yaso.api.model.User;
import br.com.yaso.api.model.Vacina;

public class VacinaResponse {
    private Long id;

    private String nome;

    private String dataAplicacao;

    private int aplicada;

    private byte[] comprovante;

    private String recorrente;

    private String dataRecorrente;

    private String observacoes;

    private Long idUsuario;

    public VacinaResponse(Vacina vacina) {
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

    public String getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(String dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public int getAplicada() {
        return aplicada;
    }

    public void setAplicada(int aplicada) {
        this.aplicada = aplicada;
    }

    public byte[] getComprovante() {
        return comprovante;
    }

    public void setComprovante(byte[] comprovante) {
        this.comprovante = comprovante;
    }

    public String getRecorrente() {
        return recorrente;
    }

    public void setRecorrente(String recorrente) {
        this.recorrente = recorrente;
    }

    public String getDataRecorrente() {
        return dataRecorrente;
    }

    public void setDataRecorrente(String dataRecorrente) {
        this.dataRecorrente = dataRecorrente;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Long getIdUsuario(User id) {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
