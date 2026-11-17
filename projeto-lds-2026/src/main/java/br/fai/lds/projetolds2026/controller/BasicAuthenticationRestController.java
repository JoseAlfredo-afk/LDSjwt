package br.fai.lds.projetolds2026.controller;

import br.fai.lds.projetolds2026.domain.UserModel;
import br.fai.lds.projetolds2026.dto.AuthenticationDto;
import br.fai.lds.projetolds2026.dto.CreateUserDto;
import br.fai.lds.projetolds2026.ports_and_adapters.port.dao.rest_controllers.AuthenticationRestController;
import br.fai.lds.projetolds2026.ports_and_adapters.port.service.security.AuthenticationService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/authenticate")
public class BasicAuthenticationRestController implements AuthenticationRestController {

    private final AuthenticationService authenticationService;

    public BasicAuthenticationRestController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @Override
    public ResponseEntity<UserModel> authenticate(@RequestBody final AuthenticationDto authenticationDto){
        UserModel authenticatedUser = authenticationService.authentication(
                authenticationDto.getEmail(),
                authenticationDto.getPassword()
        );

        if (authenticatedUser ==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(authenticatedUser);
    }
}
