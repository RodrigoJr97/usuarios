package com.rdg.pratica.usuarios.exceptions.handler;

public class ValidacaoErro {

    private String field;
    private String mensagem;
    private Object valorRejeitado;

    public ValidacaoErro() {
    }

    public ValidacaoErro(String field, String mensagem, Object valorRejeitado) {
        this.field = field;
        this.mensagem = mensagem;
        this.valorRejeitado = valorRejeitado;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getValorRejeitado() {
        return valorRejeitado;
    }

    public void setValorRejeitado(Object valorRejeitado) {
        this.valorRejeitado = valorRejeitado;
    }
}
