package br.com.roberto.excecao;

import javax.ejb.ApplicationException;

/**
 *  @criado em: 14/04/2020 - {21:14}
 *  @projeto  : cdiexample
 *  @autor    : roberto
 */

@ApplicationException
public class DadosDoUsuarioMalFormadosException extends RuntimeException {

    public DadosDoUsuarioMalFormadosException(String mensagem) {
        super(mensagem);
    }
}
