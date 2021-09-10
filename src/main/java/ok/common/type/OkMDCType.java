package ok.common.type;

public enum OkMDCType {

    GLOBAL_ID("globalId"),
    USER_ID("userId"),
    SERVICE_ID("serviceId");


    private String mdcName;

    public String getMdcName(){
        return mdcName;
    }

    OkMDCType(String mdcName) {
        this.mdcName = mdcName;

    }
}
