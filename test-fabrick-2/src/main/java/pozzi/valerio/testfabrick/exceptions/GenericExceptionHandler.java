package pozzi.valerio.testfabrick.exceptions;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pozzi.valerio.testfabrick.model.generic.ErrorModel;
import pozzi.valerio.testfabrick.model.generic.ResponseModel;

import java.util.LinkedList;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception e) {
        return returnError("Generic error", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
        return returnError("Problem with internal Http call", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Bad Request", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Request method not supported", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Media type not supported", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Http media type not acceptable", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Missing path variable", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Missing request parameter", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Request binding exception", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Conversion not supported", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Type mismatch", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Message not writable", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Missing servlet request part", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Bind exception", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("No handler found", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return returnError("Async request timeout", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnError("Internal error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        LinkedList<ErrorModel> errors = new LinkedList<>();

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

        ResponseModel<Object> response = ResponseModel.builder().status("KO").errors(errors).build();
        return new ResponseEntity<>(response, status);
    }

    private ResponseEntity<Object> returnError(String errorMessage, String description, HttpStatus status) {
        ErrorModel error = ErrorModel.builder()
                .defaultMessage(errorMessage)
                .description(description)
                .build();
        LinkedList<ErrorModel> errors = new LinkedList<>();
        errors.add(error);
        ResponseModel<Object> response = ResponseModel.builder().status("KO").errors(errors).build();
        return new ResponseEntity<>(response, status);
    }

}
