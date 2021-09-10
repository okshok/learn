package ok.stock.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ok.stock.type.StockConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DailyChartRepository {

    private final RestTemplate restTemplate;

    public String getDailyChart(String symbol, LocalDate date) {
        UriComponentsBuilder uriBuilder = getUriComponentsBuilder(symbol, date);
        HttpEntity<String> httpEntity = new HttpEntity<>("default");

        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.build().toUri(), HttpMethod.GET, httpEntity, String.class);

        return response.getBody();
    }



    private UriComponentsBuilder getUriComponentsBuilder(String symbol, LocalDate date) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(StockConstants.TEST_URL.getValue());
        String formattedDate = DateTimeFormatter.BASIC_ISO_DATE.format(date);
        uriBuilder.path("/stable/stock/")
                  .path(symbol)
                  .path("/chart/date/")
                  .path(formattedDate);
        uriBuilder.queryParam("token", StockConstants.TEST_TOKEN.getValue());
        return uriBuilder;
    }
}
