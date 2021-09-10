package ok.stock.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ok.common.exception.OkDefaultException;
import ok.common.exception.OkErros;
import ok.stock.model.Company;
import ok.stock.type.StockConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CompanyRepository {


    private final RestTemplate restTemplate;


    public String getCompanyInfo(String symbol) {
        UriComponentsBuilder uriBuilder = getUriComponentsBuilder(symbol);
        HttpEntity<String> httpEntity = new HttpEntity<>("default");

        URI uri = uriBuilder.build().toUri();
        log.debug(uri.toString());
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

        return response.getBody();
    }



    private UriComponentsBuilder getUriComponentsBuilder(String symbol ) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(StockConstants.TEST_URL.getValue());
        uriBuilder.path("/stable/stock/")
                .path(symbol)
                .path("/company");
        uriBuilder.queryParam("token", StockConstants.TEST_TOKEN.getValue());
        return uriBuilder;
    }
}
