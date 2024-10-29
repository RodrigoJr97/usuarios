package com.rdg.pratica.usuarios.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private LocalDateTime timesTamp;
    private Integer codigoStatus;
    private String erro;
    private String mensagem;
    private List<String> camposComErro;
    private String path;

    public ExceptionResponse() {
    }

    public ExceptionResponse(LocalDateTime timesTamp, Integer codigoStatus, String erro, String mensagem, String path) {
        this.timesTamp = timesTamp;
        this.codigoStatus = codigoStatus;
        this.erro = erro;
        this.mensagem = mensagem;
        this.path = path;
        this.camposComErro = null;
    }

    public ExceptionResponse(LocalDateTime timesTamp, Integer codigoStatus, String erro, List<String> camposComErro, String path) {
        this.timesTamp = timesTamp;
        this.codigoStatus = codigoStatus;
        this.erro = erro;
        this.camposComErro = camposComErro;
        this.path = path;
        this.mensagem = null;
    }

    public LocalDateTime getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(LocalDateTime timesTamp) {
        this.timesTamp = timesTamp;
    }

    public Integer getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatus(Integer codigoStatus) {
        this.codigoStatus = codigoStatus;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<String> getCamposComErro() {
        return camposComErro;
    }

    public void setCamposComErro(List<String> camposComErro) {
        this.camposComErro = camposComErro;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
