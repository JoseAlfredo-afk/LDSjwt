package br.fai.lds.projetolds2026.ports_and_adapters.port.dao.rest_controllers;

import br.fai.lds.projetolds2026.domain.UserModel;
import br.fai.lds.projetolds2026.dto.AuthenticationDto;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationRestController {

    @PostMapping
    ResponseEntity<UserModel> authenticate (@RequestBody final AuthenticationDto AuthenticationDto);
}
