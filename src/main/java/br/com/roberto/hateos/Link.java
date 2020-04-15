package br.com.roberto.hateos;

/**
 * @criado em: 14/04/2020 - {22:07}
 * @projeto : cdiexample
 * @autor : roberto
 */

public class Link {
    private String link;
    private String acao;
    private String observacao = "S/N";

    public Link() {
    }

    public Link(String link, String acao) {
        this.link = link;
        this.acao = acao;
    }

    public Link(String link, String acao, String observacao) {
        this.link = link;
        this.acao = acao;
        this.observacao = observacao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
