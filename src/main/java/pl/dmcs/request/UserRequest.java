package pl.dmcs.request;

import pl.dmcs.enums.RequestType;
import pl.dmcs.model.User;

import java.io.Serializable;

public class UserRequest extends Request implements Serializable {

    private static final long serialVersionUID = 1;

    private User user;

    public UserRequest(RequestType requestType, User user) {
        super(requestType);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
