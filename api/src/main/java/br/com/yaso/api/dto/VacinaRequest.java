package br.com.yaso.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class VacinaRequest {
    @Schema(description = "Nome", example = "vacina1")
    private String titulo;

    @Schema(description = "Data aplicação", example = "28/10/2024")
    private String dataAplicacao;

    private MultipartFile comprovante;

    @Schema(description = "nome", example = "nome")
    private String nome;

    @Schema(description = "responsavel", example = "responsavel")
    private String responsavel;

    @Schema(description = "Id usuario", example = "1")
    private Long idUsuario;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(String dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public MultipartFile getComprovante() {
        return comprovante;
    }

    public void setComprovante(MultipartFile comprovante) {
        this.comprovante = comprovante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
