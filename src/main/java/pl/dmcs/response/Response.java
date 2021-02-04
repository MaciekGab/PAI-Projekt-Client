package pl.dmcs.response;

import pl.dmcs.enums.ResponseStatus;

import java.io.Serializable;

public class Response implements Serializable {

    private static final long serialVersionUID = 1;

    private ResponseStatus status;
    private String message;

    public Response() {
    }

    public Response(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
