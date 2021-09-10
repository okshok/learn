package ok.stock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import ok.common.exception.OkDefaultException;
import ok.common.exception.OkErros;
import ok.stock.model.DailyChart;
import ok.stock.model.ElkDocument;
import ok.stock.repository.DailyChartRepository;
import ok.stock.repository.ElasticSearchRepository;
import ok.stock.type.IndexType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartService {

    private final DailyChartRepository chartRepository;
    private final ElasticSearchRepository elasticSearchRepository;
    private final ObjectMapper objectMapper;
    private final ElkDocumentSupport elkDocumentSupport;


    public List<DailyChart> getDailyChart(String symbol, LocalDate date) {
        return parseJsonDataForApi(chartRepository.getDailyChart(symbol, date));
    }

    public void updateDailyChart(String symbol, LocalDate date) {
        String chartJsonData = chartRepository.getDailyChart(symbol, date);
        elasticSearchRepository.insertDocuments(IndexType.DAILY_CHART, elkDocumentSupport.parseJsonListData(chartJsonData) );
    }



    private List<DailyChart> parseJsonDataForApi(String dailyCharts) {
        try {
            return objectMapper.readValue(dailyCharts, new TypeReference<List<DailyChart>>(){});
        } catch (JsonProcessingException e) {
            throw new OkDefaultException(OkErros.JSON_PARSING);
        }
    }


}
