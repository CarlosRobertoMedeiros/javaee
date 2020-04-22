package br.com.roberto.dao;
/*
 *  @criado em: 21/04/2020 - {19:31}
 *  @projeto  : cdiexample
 *  @autor    : roberto
 */

import javax.ejb.Local;

@Local
public interface GenericCrudDAO<T,Long> {
    T buscaPorId(Long id);
    T salvar(T entidade);
    boolean remove(Long id);
}
