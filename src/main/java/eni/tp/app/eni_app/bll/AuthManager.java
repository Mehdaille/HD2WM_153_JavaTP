package eni.tp.app.eni_app.bll;

import eni.tp.app.eni_app.bo.User;
import eni.tp.app.eni_app.dao.IDAOAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthManager {

    @Autowired
    IDAOAuth daoAuth;

    public EniManagerResponse<User> authenticate(String email, String password) {
        //On essaye de trouver le membre qui a l'email et le password envoyés
        User foundUser = daoAuth.login(email, password);

        //Si couple email/mot de passe incorrect => erreur code 756
        if (foundUser == null) {
            return EniManagerResponse.performResponse("756", "Couple email/mot de passe incorrect", null);
        }

        //Sinon code 200
        return EniManagerResponse.performResponse("202", "Vous êtes connecté(e) avec succès", foundUser);
    }
}
