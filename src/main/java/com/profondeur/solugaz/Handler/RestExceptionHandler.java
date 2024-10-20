package com.profondeur.solugaz.Handler;

import com.profondeur.solugaz.Exceptions.EntityNotFoundException;
import com.profondeur.solugaz.Exceptions.InvalidEntityException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice //au lieu de restcontrolleradvise
public class RestExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception, WebRequest webRequest){

        final HttpStatus notFound=HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                .code(exception.getErrorCodes())
                .httpcode(notFound.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(errorDto,notFound);

    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception,WebRequest webRequest){

        final HttpStatus badRequest=HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                .code(exception.getErrorCode())
                .httpcode(badRequest.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();

        return new ResponseEntity<>(errorDto,badRequest);

    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ErrorDto> handleException(BadCredentialsException exception, WebRequest request){
//        final HttpStatus badRequest=HttpStatus.BAD_REQUEST;
//        final ErrorDto errorDto = ErrorDto.builder()
//                .code(ErrorCodes.BAD_CREDENTIAL)
//                .httpcode(badRequest.value())
//                .message(exception.getMessage())
//                .errors(Collections.singletonList("Login et ou mot de passe incorrecte"))
//                .build();
//        return new ResponseEntity<>(errorDto,badRequest);
//    }
//    @ExceptionHandler({ Exception.class })
//    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
//        ApiError apiError = new ApiError(
//                HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
//        return new ResponseEntity<Object>(
//                apiError, new HttpHeaders(), apiError.getStatus());
//    }

//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ModelAndView handle405(HttpServletRequest request, Exception ex) {
//        ModelAndView modelAndView = new ModelAndView("erreur-405");
//        return modelAndView;
//    }
}