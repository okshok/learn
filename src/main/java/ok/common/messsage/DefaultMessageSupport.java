package ok.common.messsage;

import ok.common.exception.OkErros;

public class DefaultMessageSupport {

    private DefaultMessageSupport(){
    }

    public static OkDefaultMessage getDefaultMessage(Object body) {
        OkDefaultMessage message = OkDefaultMessage.builder()
                .message("ok").code(200)
                .header(null)
                .body(body)
                .build();
        return message;
    }
    public static OkDefaultMessage getDefaultMessage(Object body, OkErros errorCode) {
        OkDefaultMessage message = OkDefaultMessage.builder()
                .message(errorCode.getMessage()).code(errorCode.getCode())
                .header(null)
                .body(body)
                .build();
        return message;
    }
}
