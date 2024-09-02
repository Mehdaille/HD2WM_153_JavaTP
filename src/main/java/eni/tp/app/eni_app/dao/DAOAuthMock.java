package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DAOAuthMock implements IDAOAuth {

    //Deux faux users
    List<User> users = Arrays.asList(
            new User("mehdi@gmail.com","123"),
            new User("rochereau@gmail.com","456")
    );

    @Override
    public User login(String email, String password) {

        User foundUser = users.stream().filter(
                user -> user.email.equals(email) && user.password.equals(password))
                .findFirst().orElse(null);

        return foundUser;
    }
}
