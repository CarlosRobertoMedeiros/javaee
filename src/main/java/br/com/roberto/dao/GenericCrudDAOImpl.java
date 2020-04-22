package br.com.roberto.dao;
/*
 *  @criado em: 21/04/2020 - {19:33}
 *  @projeto  : cdiexample
 *  @autor    : roberto
 */

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

@Local
@Stateless
public class GenericCrudDAOImpl<T,Long> implements GenericCrudDAO<T,Long> {

    @PersistenceContext(unitName = "primary")
    private EntityManager manager;

    private Class<T> classEntidade;

    @SuppressWarnings("unchecked")
    public GenericCrudDAOImpl() {
        super();
        this.classEntidade = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public T buscaPorId(Long id) {
        return manager.find(classEntidade, id);
    }

    @Override
    public T salvar(T entidade) {
        T meuObjeto = manager.merge(entidade);
        return meuObjeto;
    }

    @Override
    public boolean remove(Long id) {
        try {
            T minhaEntidade = this.buscaPorId(id);
            manager.remove(minhaEntidade);
            return true;
        }catch (Exception e) {
            throw new RuntimeException(e.getCause()+" "+e.getMessage());
        }
    }

    public EntityManager getManager() {
        return manager;
    }

}
