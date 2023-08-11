package org.example.exception;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    static Logger logger = Logger.getLogger(ConstraintViolationExceptionMapper.class.getName());

    @Override
    public Response toResponse(ConstraintViolationException e) {
        StackTraceElement[] stackTraceArray = e.getStackTrace();
        String logMessage = String.format("%s:%d - %s",
                stackTraceArray[0].getClassName(),
                stackTraceArray[0].getLineNumber(),
                e.getMessage());
        logger.info(logMessage);

        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<ResponseBody> responses = new ArrayList<>();
        for (ConstraintViolation<?> constraint : violations) {
            String errorMessage = constraint.getMessage();
            Response.Status status = Response.Status.BAD_REQUEST;
            String errorKey = ErrorMessage.errorKeyAndMessageMap().get(errorMessage);
            ResponseBody response = new ResponseBody(status, errorKey, errorMessage);
            responses.add(response);
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(responses)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
