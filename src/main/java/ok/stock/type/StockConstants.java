package ok.stock.type;

import lombok.Getter;

@Getter
public enum StockConstants {

    TEST_URL("https://sandbox.iexapis.com", 0),
    TEST_TOKEN("Tpk_d4b745cefb7c4edfafd695869e16433c", 0);



    String value;
    int code;

    StockConstants(String value, int code) {
        this.value = value;
        this.code = code;
    }
}
