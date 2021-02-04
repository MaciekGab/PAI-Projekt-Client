package pl.dmcs.response;

import pl.dmcs.enums.ResponseStatus;
import pl.dmcs.model.User;

import java.io.Serializable;

public class UserResponse extends Response implements Serializable {

    private static final long serialVersionUID = 1;

    private User user;

    public UserResponse(ResponseStatus status, String message, User user) {
        super(status, message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "user=" + user +
                '}';
    }
}
