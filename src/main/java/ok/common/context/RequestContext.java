package ok.common.context;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestContext {

    private Long userId;  // 사용자
    private String globalId; //http Request마다 유일한 객체, 주로 클라이언트에서 생성
    private String serviceId; // 고유할 api 명
    private String targetMethodNameWithClassName; // 호출할 메소드 명( 클래스 명 포함)
}
