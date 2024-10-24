package br.com.yaso.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class VacinaRequest {
    @NotEmpty(message = "nome nao pode ser vazio")
    @Schema(description = "Nome", example = "vacina1")
    private String nome;

    @NotEmpty(message = "dataAplicacao nao pode ser vazio")
    @Schema(description = "Data aplicação", example = "28/10/2024")
    private String dataAplicacao;

    @Schema(description = "aplicada", example = "1")
    private int aplicada;

    //@Schema(description = "comprovante", example = "comprovante")
    //private String comprovante;

    private MultipartFile comprovante;

    @Schema(description = "recorrente", example = "sim/nao")
    private String recorrente;

    @Schema(description = "Data aplicação", example = "28/10/2024")
    private String dataRecorrente;

    @Schema(description = "observacoes", example = "observacoes")
    private String observacoes;

    @Schema(description = "Id usuario", example = "1")
    private Long idUsuario;

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


    public MultipartFile getComprovante() {
        return comprovante;
    }

    public void setComprovante(MultipartFile comprovante) {
        this.comprovante = comprovante;
    }

    public String getDataRecorrente() {
        return dataRecorrente;
    }

    public void setDataRecorrente(String dataRecorrente) {
        this.dataRecorrente = dataRecorrente;
    }

    public String getRecorrente() {
        return recorrente;
    }

    public void setRecorrente(String recorrente) {
        this.recorrente = recorrente;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
