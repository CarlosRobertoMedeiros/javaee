package br.com.roberto.excecao;

/**
 *  @criado em: 14/04/2020 - {21:15}
 *  @projeto  : cdiexample
 *  @autor    : roberto
 */

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class DadosDoUsuarioMalFormadosExceptionMapper implements ExceptionMapper<DadosDoUsuarioMalFormadosException> {

    private Logger logger = Logger.getLogger(DadosNaoEncontradosException.class.getName());

    @Override
    public Response toResponse(DadosDoUsuarioMalFormadosException exception) {
        MensagemExcecao mensagemExcecao = new MensagemExcecao(400,exception.getMessage());

        logger.warning(mensagemExcecao.getMensagem());//TODO: Implementar por fora um conjunto específico de looger que respeite o tratamento específico das classes
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(mensagemExcecao)
                .build();
    }
}
