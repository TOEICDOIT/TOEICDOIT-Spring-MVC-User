package site.toeicdoit.user.handler;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import site.toeicdoit.user.exception.ExceptionStatus;
import site.toeicdoit.user.exception.UserException;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice(basePackages = "site.toeicdoit.user")
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(
//            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
//            log.info("handleExceptionInternal 내용 : {}, {}, {}, {}, {}", ex, body, headers, statusCode, request);
//            UserException.toUserException(ex, ExceptionStatus.INTERNAL_SERVER_ERROR, "Global Handler Executed");
//
//        return ResponseEntity.status(HttpStatus.valueOf(statusCode.value())).body("에러 발생");
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("@ExceptionHandler(Exception.class) 에러 내용 : {} ,{}", ex, request);
        UserException result = UserException.toUserException(ex, ExceptionStatus.INTERNAL_SERVER_ERROR, "Global Handler Executed");
        return ResponseEntity.status(result.getStatus().getHttpStatus()).body(result.getMessage());
    }

}