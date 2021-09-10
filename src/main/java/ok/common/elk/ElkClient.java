package ok.common.elk;

import javassist.expr.FieldAccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ok.common.exception.OkDefaultException;
import ok.common.exception.OkErros;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class ElkClient {


    private final RestHighLevelClient client;


    public IndexResponse index(IndexRequest request) {
        IndexResponse response;
        try {
            return  client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("ELK error... ", e.getCause());
            throw new OkDefaultException(OkErros.ELASTIC_SEARCH);
        }
    }

    public BulkResponse bulk(BulkRequest request) {
        BulkResponse response;
        try {
            return  client.bulk(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("ELK error... ", e.getCause());
            throw new OkDefaultException(OkErros.ELASTIC_SEARCH);
        }
    }

}
