package br.com.roberto.model;

import br.com.roberto.hateos.Link;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_usuario", schema = "sistemab")
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long codUsuario;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "usuario", nullable = false, length = 100)
	private String usuario;

	@Column(name = "senha", nullable = false, length = 20)
	private String senha;

	@Column(name = "ativo", nullable = false, length = 1)
	private boolean ativo = true;

	@Transient
	private List<Link> links = new ArrayList<>(); //Mover para o DTO


	public UsuarioModel() {
		}

	public UsuarioModel(Long codUsuario, String nome, String usuario, String senha) {
			super();
			this.codUsuario = codUsuario;
			this.nome = nome;
			this.usuario = usuario;
			this.senha = senha;
		}

	public Long getId() {
		return codUsuario;
	}

	public void setId(Long id) {
		this.codUsuario = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codUsuario == null) ? 0 : codUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioModel other = (UsuarioModel) obj;
		if (codUsuario == null) {
			if (other.codUsuario != null)
				return false;
		} else if (!codUsuario.equals(other.codUsuario))
			return false;
		return true;
	}

	//todo: mover os próximos 3 métodos para o dto
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
