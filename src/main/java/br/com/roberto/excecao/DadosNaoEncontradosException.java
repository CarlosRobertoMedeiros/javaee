package br.com.roberto.excecao;

/**
 *  @criado em: 14/04/2020 - {21:15}
 *  @projeto  : cdiexample
 *  @autor    : roberto
 */

import javax.ejb.ApplicationException;

@ApplicationException
public class DadosNaoEncontradosException extends RuntimeException{

    public DadosNaoEncontradosException(String message) {
        super(message);
    }
}
