package br.com.roberto.dao;
/*
 *  @criado em: 21/04/2020 - {19:41}
 *  @projeto  : cdiexample
 *  @autor    : roberto
 */

import br.com.roberto.model.Usuario;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

public class UsuarioDaoImpl extends GenericCrudDAOImpl<Usuario,Long> {

    private final String LISTA_USUARIOS = "select u from Usuario u";

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Usuario> buscaTodos(int inicio, int tamanho) {
        List<Usuario> usuarios = this.getManager().createQuery(LISTA_USUARIOS, Usuario.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();
        return usuarios;
    }


}
