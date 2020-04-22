package br.com.roberto.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.roberto.controller.dto.UsuarioDTO;
import br.com.roberto.model.Usuario;

@Local
public interface UsuarioNegocio{

	List<UsuarioDTO> listarUsuarios(int inicio, int tamanho);

	UsuarioDTO listaPorId(Long id);

	UsuarioDTO adiciona(Usuario usuario);

	UsuarioDTO atualiza(Long id, Usuario usuario);

	boolean remove(Long id);
}
