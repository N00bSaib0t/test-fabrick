package pozzi.valerio.testfabrick.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pozzi.valerio.testfabrick.model.generic.ErrorModel;
import pozzi.valerio.testfabrick.model.generic.ResponseModel;

import java.util.LinkedList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception e) {
        LinkedList<ErrorModel> errors = new LinkedList<>();
        errors.add(
                ErrorModel.builder()
                        .description(e.getMessage())
                        .defaultMessage("Generic error")
                        .build());

        ResponseModel response = ResponseModel.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .errors(errors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<Object> handleUnauthorized(UnauthorizedException e){
        return returnError("Unauthorized", e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequest(BadRequestException e){
        return returnError("Bad Request", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e) {

        LinkedList<ErrorModel> errors = new LinkedList<>();

        errors.add(ErrorModel.builder()
                .description(e.getMessage())
                .defaultMessage("Problem with internal Http call")
                .build());

        ResponseModel response = ResponseModel.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .errors(errors)
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<ErrorModel> errors = new LinkedList<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                String code = error.getCode();
                errors.add(
                        ErrorModel.builder()
                                .fieldName(fieldName)
                                .defaultMessage(errorMessage)
                                .code(code)
                                .build()
                );
            } else {
                String name = error.getObjectName();
                String errorMessage = error.getDefaultMessage();
                String code = error.getCode();
                errors.add
                        (ErrorModel.builder()
                                        .objectName(name)
                                        .defaultMessage(errorMessage)
                                        .code(code)
                                        .build());
            }
        });

        return new ResponseEntity<>(errors, status);
    }

    private ResponseEntity returnError(String errorMessage, String description, HttpStatus status) {
        ErrorModel error = ErrorModel.builder()
                .defaultMessage(errorMessage)
                .description(description)
                .build();
        LinkedList<ErrorModel> errors = new LinkedList<>();
        errors.add(error);
        ResponseModel<Object> response = ResponseModel.builder().status("KO").errors(errors).build();
        return new ResponseEntity<Object>(response, status);
    }

}
