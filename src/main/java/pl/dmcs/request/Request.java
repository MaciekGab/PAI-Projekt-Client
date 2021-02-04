package pl.dmcs.request;

import pl.dmcs.enums.RequestType;

import java.io.Serializable;

public class Request implements Serializable {

    private static final long serialVersionUID = 1;

    private RequestType requestType;

    public Request(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public boolean isQuit() {
        return requestType.equals(RequestType.QUIT);
    }
}
