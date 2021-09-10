package ok.common.exception;


import lombok.Getter;

/**
 * 100000  business exception
 * 200000  checked exception 에서 발생한 경우.
 * 300000  연계에서 에러가 발생한 경우
 * 400000  시스템 에러
 */


@Getter
public enum OkErros {

    Ok(0, "success"),
    RUNTIME_EXCEPTION(200_00, "runtime Exception"),
    EXCEPTION(400_00, "Exception"),
    JSON_PARSING(200_01, "json parsing error"),
    ELASTIC_SEARCH(300_00, "elastic search error");


    int code;
    String message;

    OkErros (int code, String message) {
        this.code = code;
        this.message = message;
    }
}
