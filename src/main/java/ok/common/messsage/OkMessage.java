package ok.common.messsage;

public interface OkMessage <OkHeader, T>{

    OkHeader getHeader();
    T getBody();

    String getMessage();
    int getCode();

}
