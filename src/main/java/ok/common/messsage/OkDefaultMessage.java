package ok.common.messsage;

import lombok.Builder;

@Builder
public class OkDefaultMessage<OkDefaultHeader, T> implements OkMessage{

    String message;
    int code;

    OkDefaultHeader header;
    T body;


    @Override
    public OkDefaultHeader getHeader() {
        return header;
    }

    @Override
    public T getBody() {
        return body;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }
}
