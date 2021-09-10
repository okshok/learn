package ok.stock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import ok.common.exception.OkDefaultException;
import ok.common.exception.OkErros;
import ok.stock.model.ElkDocument;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ElkDocumentSupport {

    private final ObjectMapper objectMapper;

    public List<ElkDocument> parseJsonListData(String jsonData) {
        List<ElkDocument> documents = Lists.newArrayList();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            if(jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {

                    documents.add(new ElkDocument(node.toString()));
                }
            }
        } catch (JsonProcessingException e) {
            throw new OkDefaultException(OkErros.JSON_PARSING);
        }
        return documents;
    }

    public ElkDocument parseJsonData(String jsonData) {
        return new ElkDocument(jsonData);
    }
}
