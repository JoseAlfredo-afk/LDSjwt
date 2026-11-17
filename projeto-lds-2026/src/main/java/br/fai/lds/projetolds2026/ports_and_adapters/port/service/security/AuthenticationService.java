package br.fai.lds.projetolds2026.ports_and_adapters.port.service.security;

import br.fai.lds.projetolds2026.domain.UserModel;

public interface AuthenticationService {

    UserModel authentication(final String email, final String password);
}
