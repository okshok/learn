package ok.common.type;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public enum OkMarkerType {

    REQUEST_BEGIN(MarkerFactory.getMarker("REQUEST_BEGIN"), "begin"),
    RESPONSE_END(MarkerFactory.getMarker("RESPONSE_END"), "end");


    private String name;
    private Marker marker;

    public Marker getMarker(){
        return this.marker;
    }

    OkMarkerType(Marker marker, String name) {
        this.marker = marker;
        this.name = name;
    }
}