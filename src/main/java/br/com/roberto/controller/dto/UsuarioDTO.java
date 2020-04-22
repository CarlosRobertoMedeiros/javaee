package br.com.roberto.controller.dto;
/*
 *  @criado em: 15/04/2020 - {18:34}
 *  @projeto  : cdiexample
 *  @autor    : roberto
 */

import br.com.roberto.hateos.Link;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String usuario;
    private List<Link> links = new ArrayList<>();

    public UsuarioDTO() { }

    public UsuarioDTO(Long id, String nome, String usuario) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void addLink(String url, String acao) {
        Link link = new Link(url,acao);
        this.links.add(link);
    }

    public void addLink(String url, String acao, String observacao) {
        Link link = new Link(url,acao,observacao);
        this.links.add(link);
    }
}
