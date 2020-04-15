package br.com.roberto.dao;

import br.com.roberto.excecao.DadosNaoEncontradosException;
import br.com.roberto.model.UsuarioModel;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Local
@Stateless
public class UsuarioDao {
	private final String LISTA_USUARIOS = "select u from UsuarioModel u";

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public EntityManager getManager() {
		return manager;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<UsuarioModel> buscaTodos(int inicio, int tamanho) {
			List<UsuarioModel> usuarios = this.manager.createQuery(LISTA_USUARIOS, UsuarioModel.class)
					.setFirstResult(inicio)
					.setMaxResults(tamanho)
					.getResultList();
			return usuarios;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public UsuarioModel buscaPorId(Long id) {
		return this.manager.find(UsuarioModel.class, id);
	}

	public UsuarioModel save(UsuarioModel usuario) {
		return this.manager.merge(usuario);
	}

	public boolean remove(Long id) {
		UsuarioModel usuario = this.buscaPorId(id);
		if (usuario == null){
			throw new DadosNaoEncontradosException("Não Foi Possível Remover o Usuário de id "+id);
		}
		this.manager.remove(usuario);
		return true;

	}

}
