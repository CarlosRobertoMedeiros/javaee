package br.com.roberto.excecao;

import javax.annotation.Priority;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class GenericoExceptionMapper implements ExceptionMapper<Throwable> {

    private Logger logger = Logger.getLogger(GenericoExceptionMapper.class.getName());

    @Override
    public Response toResponse(Throwable exception) {
        Response response = ((WebApplicationException) exception).getResponse();
        MensagemExcecao mensagemExcecao = new MensagemExcecao(response.getStatus(),exception.getMessage());


        if (exception instanceof NotFoundException){
            if(response.getEntity() == null){
                logger.warning(mensagemExcecao.getMensagem());
                return getResponse(mensagemExcecao, response);
            }
        }

        logger.severe(mensagemExcecao.getMensagem());
        return getResponse(mensagemExcecao, response);
        /*return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(mensagemExcecao)
                .build();*/
    }

    private Response getResponse(MensagemExcecao mensagemExcecao, Response response) {
        Response.ResponseBuilder builder = Response.fromResponse(response);
        response = builder.entity(mensagemExcecao)
                .type(MediaType.APPLICATION_JSON)
                .build();
        return response;
    }
}
