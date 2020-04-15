package br.com.roberto.excecao;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class DadosNaoEncontradosExceptionMapper implements ExceptionMapper<DadosNaoEncontradosException> {


    private Logger logger = Logger.getLogger(DadosNaoEncontradosException.class.getName());

    @Override
    public Response toResponse(DadosNaoEncontradosException exception) {
        MensagemExcecao mensagemExcecao = new MensagemExcecao(204,exception.getMessage());

        logger.warning(mensagemExcecao.getMensagem());//TODO: Implementar por fora um conjunto específico de looger que respeite o tratamento específico das classes
        return Response
                .status(Response.Status.NO_CONTENT)
                .entity(mensagemExcecao)
                .build();
    }
}
