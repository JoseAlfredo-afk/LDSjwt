package br.fai.lds.projetolds2026.ports_and_adapters.adapter.service.security;

import br.fai.lds.projetolds2026.domain.UserModel;
import br.fai.lds.projetolds2026.ports_and_adapters.port.service.security.AuthenticationService;

public class JwtAuthenticationServiceAdapter implements AuthenticationService {
    @Override
    public UserModel authentication(String email, String password) {

        System.out.println("autentication com jwt");
        return null;
    }
}
