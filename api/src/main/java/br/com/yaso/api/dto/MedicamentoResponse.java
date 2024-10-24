package br.com.yaso.api.dto;

import br.com.yaso.api.model.Medicamento;

public class MedicamentoResponse {
    private Long id;

    private String nome;

    private String tipo;

    private String data;

    private String dosagem;

    private String periodo;

    private String medico;

    private byte[] file;

    private Long IdUsuario;

    public MedicamentoResponse(Medicamento obj) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getMedico(String medico) {
        return this.medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public byte[] getFile(byte[] file) {
        return this.file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Long getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        IdUsuario = idUsuario;
    }
}
