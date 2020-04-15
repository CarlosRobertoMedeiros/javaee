package br.com.roberto.Interceptor;

import br.com.roberto.excecao.DadosNaoEncontradosException;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Priorities;
import java.util.logging.Logger;

//@Interceptor
//@Priority(Priorities.HEADER_DECORATOR)
//@br.com.roberto.Interceptor.Logger
public class LoggerInterceptor {

    @AroundInvoke
    public Object threatException(InvocationContext context){

        Logger logger = Logger.getLogger(context.getTarget().getClass().getName());

        try {
            return context.proceed();
        }catch (DadosNaoEncontradosException e) {
            logger.info(e.getMessage());
        }catch (ConstraintViolationException e){
            logger.info(e.getMessage());
        }catch (Exception e){
            logger.severe(e.getMessage());
        }
        return context;
    }

}
