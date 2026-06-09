package com.marchini.paineldetarefas.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroResponse {

    private final int status;
    private final String erro;
    private final String mensagem;
    private final LocalDateTime timestamp;
    private final Map<String, String> campos;

    public ErroResponse(int status, String erro, String mensagem, LocalDateTime timestamp) {
        this(status, erro, mensagem, timestamp, null);
    }

    public ErroResponse(int status, String erro, String mensagem, LocalDateTime timestamp, Map<String, String> campos) {
        this.status = status;
        this.erro = erro;
        this.mensagem = mensagem;
        this.timestamp = timestamp;
        this.campos = campos;
    }

    public int getStatus() { return status; }
    public String getErro() { return erro; }
    public String getMensagem() { return mensagem; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public Map<String, String> getCampos() { return campos; }
}