package ok.common.exception;

import lombok.Getter;

@Getter
public class OkDefaultException extends RuntimeException{

    private OkErros error;
    private String publicMessage;
    private boolean hiddenRealMessage;
    private String[] messageArguments;

    public OkDefaultException(OkErros error, String... messageArguments) {
        this.error = error;
        this.messageArguments = messageArguments;
    }

    public OkDefaultException(OkErros error, boolean hideRealMessage, String... messageArguments) {
        this.error = error;
        this.messageArguments = messageArguments;
        this.hiddenRealMessage = hideRealMessage;
    }


    @Override
    public String getMessage() {
        return getPublicMessage();
    }
}
