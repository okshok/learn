package ok.common.messsage;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class OkDefaultHeader implements OkHeader{

    ZonedDateTime requestTime;

    String globalId;

    @Override
    public ZonedDateTime getRequestTime() {
        return null;
    }

    @Override
    public String getGlobalId() {
        return null;
    }
}
