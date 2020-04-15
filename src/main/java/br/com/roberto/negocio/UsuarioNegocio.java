package br.com.roberto.negocio;

import java.util.List;

import javax.ejb.Local;

import br.com.roberto.model.UsuarioModel;

@Local
public interface UsuarioNegocio{

	List<UsuarioModel> listarUsuarios(int inicio, int tamanho);

	UsuarioModel listaPorId(Long id);

	UsuarioModel adiciona(UsuarioModel usuario);

	UsuarioModel atualiza(Long id, UsuarioModel usuario);

	boolean remove(Long id);
}
