package br.com.roberto.negocio.impl;

import br.com.roberto.dao.UsuarioDao;
import br.com.roberto.excecao.DadosDoUsuarioMalFormadosException;
import br.com.roberto.excecao.DadosNaoEncontradosException;
import br.com.roberto.hateos.MensagensHateos;
import br.com.roberto.model.UsuarioModel;
import br.com.roberto.negocio.UsuarioNegocio;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 *  @criado em: 14/04/2020 - {21:15}
 *  @projeto  : cdiexample
 *  @autor    : roberto
 */
@Stateless
public class UsuarioNegocioImpl implements UsuarioNegocio {

	@Inject
	private UsuarioDao usuarioDao;

	/**
	 * @param inicio
	 * @param tamanho
	 * @return
	 * @throws DadosNaoEncontradosException
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<UsuarioModel> listarUsuarios(int inicio, int tamanho) throws DadosNaoEncontradosException{

		List<UsuarioModel> todosUsuarios = usuarioDao.buscaTodos(inicio, tamanho);

		if (todosUsuarios.size() == 0) {
			throw new DadosNaoEncontradosException("Não foram encontrados informações para os parâmetros de usuário fornecidos.");
		}

		return todosUsuarios;

	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public UsuarioModel listaPorId(Long id) {
		UsuarioModel usuario = usuarioDao.buscaPorId(id);
		if (usuario == null) {
			throw new DadosNaoEncontradosException("O Usuário com ID "+id+" Não foi encontrado !");
		}
		return usuario;
	}

	/**
	 * @param usuario
	 * @return
	 */
	@Override
	public UsuarioModel adiciona(UsuarioModel usuario) {
		this.verificaCamposObrigatorios(usuario);
		return usuarioDao.save(usuario);
	}

	/**
	 * @param id
	 * @param usuario
	 * @return
	 */
	@Override
	public UsuarioModel atualiza(Long id, UsuarioModel usuario) {
		this.verificaCamposObrigatorios(usuario);
		UsuarioModel usuarioInterno = usuarioDao.buscaPorId(id);

		if (usuarioInterno==null){
			throw new DadosNaoEncontradosException("O Usuário Pesquisado "+usuario+" não foi encontrado !");
		}

		try {
			BeanUtils.copyProperties(usuarioInterno,usuario);
			usuarioInterno.setId(id);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return usuarioDao.save(usuarioInterno);

	}

	/**
	 * @param id
	 * @return
	 */
	@Override

	public boolean remove(Long id) {
		UsuarioModel usuarioInterno = usuarioDao.buscaPorId(id);
		if (usuarioInterno==null){
			throw new DadosNaoEncontradosException("O Usuário Pesquisado "+usuarioInterno+" não foi encontrado !");
		}
		usuarioDao.remove(id);
		return true;
	}

	/**
	 * @param usuario
	 */
	private void verificaCamposObrigatorios(UsuarioModel usuario) {
		if (usuario.getNome()==null){
			throw new DadosDoUsuarioMalFormadosException("O nome do Usuário é um campo obrigatório!");
		}

		if (usuario.getUsuario()==null){
			throw new DadosDoUsuarioMalFormadosException("O usuário é um campo obrigatório!");
		}

		if (usuario.getSenha()==null){
			throw new DadosDoUsuarioMalFormadosException("A senha é um campo Obrigatório!");
		}
	}
}
