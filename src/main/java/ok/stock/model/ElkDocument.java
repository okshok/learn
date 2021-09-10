package ok.stock.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElkDocument {

    String id;
    String document;



    public ElkDocument(String id, String document) {
        this.id = id;
        this.document = document;
    }
    public ElkDocument(String document) {
        this.document = document;
    }
}
