package ok.stock.type;

import lombok.Getter;

public enum IndexType {

    DAILY_CHART("daily_chart"),
    COMPANY("company");

    @Getter
    private final String code;

    IndexType (String code) {
        this.code = code;
    }
}
