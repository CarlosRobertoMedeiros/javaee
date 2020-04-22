package br.com.roberto.negocio.impl;

import br.com.roberto.controller.dto.UsuarioDTO;
import br.com.roberto.dao.UsuarioDaoImpl;
import br.com.roberto.excecao.DadosDoUsuarioMalFormadosException;
import br.com.roberto.excecao.DadosNaoEncontradosException;
import br.com.roberto.model.Usuario;
import br.com.roberto.negocio.UsuarioNegocio;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
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
	private UsuarioDaoImpl usuarioDao;

	/**
	 * @param inicio
	 * @param tamanho
	 * @return
	 * @throws DadosNaoEncontradosException
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<UsuarioDTO> listarUsuarios(int inicio, int tamanho) throws DadosNaoEncontradosException{

		List<Usuario> todosUsuarios = usuarioDao.buscaTodos(inicio, tamanho);

		if (todosUsuarios.size() == 0) {
			throw new DadosNaoEncontradosException("Não foram encontrados informações para os parâmetros de usuário fornecidos.");
		}

		List<UsuarioDTO> usuariosDTO = new ArrayList<>();

		todosUsuarios.forEach(
				usuario -> usuariosDTO.add(new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getUsuario())));

		return usuariosDTO;

	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public UsuarioDTO listaPorId(Long id) {
		Usuario usuario = usuarioDao.buscaPorId(id);
		if (usuario == null) {
			throw new DadosNaoEncontradosException("O Usuário com ID "+id+" Não foi encontrado !");
		}
		return new UsuarioDTO(usuario.getId(),usuario.getNome(),usuario.getUsuario());
	}

	/**
	 * @param usuario
	 * @return
	 */
	@Override
	public UsuarioDTO adiciona(Usuario usuario) {
		this.verificaCamposObrigatorios(usuario);
		Usuario usuarioInterno = usuarioDao.salvar(usuario);
		return new UsuarioDTO(usuarioInterno.getId(), usuarioInterno.getNome(), usuarioInterno.getUsuario());
	}

	/**
	 * @param id
	 * @param usuario
	 * @return
	 */
	@Override
	public UsuarioDTO atualiza(Long id, Usuario usuario) {
		this.verificaCamposObrigatorios(usuario);
		Usuario usuarioInterno = usuarioDao.buscaPorId(id);

		if (usuarioInterno==null){
			throw new DadosNaoEncontradosException("O Usuário Pesquisado "+usuario+" não foi encontrado !");
		}

		try {
			BeanUtils.copyProperties(usuarioInterno,usuario);
			usuarioInterno.setId(id);
		} catch (IllegalAccessException e) {
			throw new  RuntimeException(e.getCause()+"-"+e.getMessage());
		} catch (InvocationTargetException e) {
			throw new  RuntimeException(e.getCause()+"-"+e.getMessage());
		}
		return new UsuarioDTO(usuarioInterno.getId(), usuarioInterno.getNome(), usuarioInterno.getUsuario());

	}

	/**
	 * @param id
	 * @return
	 */
	@Override

	public boolean remove(Long id) {
		Usuario usuarioInterno = usuarioDao.buscaPorId(id);
		if (usuarioInterno==null){
			throw new DadosNaoEncontradosException("O Usuário Pesquisado "+usuarioInterno+" não foi encontrado !");
		}
		usuarioDao.remove(id);
		return true;
	}

	/**
	 * @param usuario
	 */
	private void verificaCamposObrigatorios(Usuario usuario) {
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
