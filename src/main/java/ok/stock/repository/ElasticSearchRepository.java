package ok.stock.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ok.common.elk.ElkClient;
import ok.stock.model.ElkDocument;
import ok.stock.type.IndexType;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ElasticSearchRepository {

    private final ElkClient client;

    public void insertDocument(IndexType index, ElkDocument document) {
        IndexRequest request = new IndexRequest(index.getCode());
//        Optional.ofNullable(document.getId()).ifPresent(id -> request.id(id));
        request.source(document.getDocument(), XContentType.JSON  );

        IndexResponse response = client.index(request);

        log.debug(response.toString());

    }

    public void insertDocuments(IndexType index, List<ElkDocument> documents) {
        BulkRequest request = new BulkRequest();
        documents.stream().forEach(document -> {
            IndexRequest indexRequest = new IndexRequest(index.getCode()).source(document.getDocument(), XContentType.JSON);
//            Optional.ofNullable(document.getId()).ifPresent(id -> indexRequest.id(id));
            request.add(indexRequest);
        });

        BulkResponse bulkResponse = client.bulk(request);
        log.debug(bulkResponse.toString());

    }

    public void deleteDocumentsByKey(String index) {

    }

}
