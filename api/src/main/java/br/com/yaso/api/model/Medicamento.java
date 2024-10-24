package br.com.yaso.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "YASO_MEDICAMENTO")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento")
    private Long id;

    @Column(name = "nome_medicamento", nullable = true)
    private String nome;

    @Column(name = "tipo_medicamento", nullable = true)
    private String tipo;

    @Column(name = "data_medicamento", nullable = true)
    private String data;

    @Column(name = "dosagem_exame", nullable = true)
    private String dosagem;

    @Column(name = "periodo_exame", nullable = true)
    private String periodo;

    @Column(name = "medico_exame", nullable = true)
    private String medico;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
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
