package ok.stock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ok.common.exception.OkDefaultException;
import ok.common.exception.OkErros;
import ok.stock.model.Company;
import ok.stock.repository.CompanyRepository;
import ok.stock.repository.ElasticSearchRepository;
import ok.stock.type.IndexType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundamentalService {


    private final ObjectMapper objectMapper;
    private final CompanyRepository companyRepository;
    private final ElasticSearchRepository elasticSearchRepository;
    private final ElkDocumentSupport elkDocumentSupport;;

    public Company getCompanyInfo(String symbol) {
        String companyInfo = companyRepository.getCompanyInfo(symbol);
        return parseJsonData(companyInfo);
    }

    public void insertCompanyInfo(String symbol) {
        String companyInfo = companyRepository.getCompanyInfo(symbol);
        elasticSearchRepository.insertDocument(IndexType.COMPANY, elkDocumentSupport.parseJsonData(companyInfo));
    }




    private Company parseJsonData(String data) {
        try {
            return objectMapper.readValue(data, Company.class);
        } catch (JsonProcessingException e) {
            throw new OkDefaultException(OkErros.JSON_PARSING);
        }
    }

}
