package ok.common.exception;

import lombok.extern.slf4j.Slf4j;
import ok.common.annotation.OkJsonResponse;
import ok.common.messsage.DefaultMessageSupport;
import ok.common.messsage.OkDefaultMessage;
import ok.common.messsage.OkMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.charset.Charset;

@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class ControllerAdvice {


    @ExceptionHandler(OkDefaultException.class)
    public ResponseEntity<OkMessage> handleOkDefaultException(OkDefaultException e) {

        OkDefaultMessage message = DefaultMessageSupport.getDefaultMessage(null, e.getError());
        return makeHttpMessage(message);

    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<OkMessage> handleRuntimeException(RuntimeException e) {

        OkDefaultMessage message = DefaultMessageSupport.getDefaultMessage(null, OkErros.RUNTIME_EXCEPTION);

        return makeHttpMessage(message);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<OkMessage> handleException(Exception e) {

        OkDefaultMessage message = DefaultMessageSupport.getDefaultMessage(null, OkErros.EXCEPTION);

        return makeHttpMessage(message);

    }

    private ResponseEntity<OkMessage> makeHttpMessage(OkDefaultMessage body) {

        HttpHeaders httpHeaders = new HttpHeaders();
        Charset utf8 = Charset.forName("UTF-8");
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, utf8));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(httpHeaders).body(body);
    }
}
