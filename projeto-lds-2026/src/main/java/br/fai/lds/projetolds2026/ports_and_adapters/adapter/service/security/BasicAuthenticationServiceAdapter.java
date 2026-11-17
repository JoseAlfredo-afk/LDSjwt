package br.fai.lds.projetolds2026.ports_and_adapters.adapter.service.security;

import br.fai.lds.projetolds2026.domain.UserModel;
import br.fai.lds.projetolds2026.ports_and_adapters.port.service.security.AuthenticationService;
import br.fai.lds.projetolds2026.ports_and_adapters.port.service.user.UserService;

public class BasicAuthenticationServiceAdapter implements AuthenticationService {

    private  final UserService userService;

    public BasicAuthenticationServiceAdapter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserModel authentication(String email, String password) {

        if(email == null || email.isEmpty()){
            return null;
        }

        UserModel user = userService.findByEmail(email);

        if (user == null){
            return null;
        }

        if (user.getPassword().equals(password)){
            return user;
        }

        return null;
    }
}
