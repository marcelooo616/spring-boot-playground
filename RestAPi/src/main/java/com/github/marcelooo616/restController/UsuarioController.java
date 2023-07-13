package com.github.marcelooo616.restController;


import com.github.marcelooo616.domain.entity.Usuario;
import com.github.marcelooo616.exception.SenhaInvalidaException;
import com.github.marcelooo616.rest.dto.CredenciaisDTO;
import com.github.marcelooo616.rest.dto.TokenDTO;
import com.github.marcelooo616.security.jwt.JwtService;
import com.github.marcelooo616.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {



    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salva(@RequestBody @Valid Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.salva(usuario);

    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try {

            Usuario usuario =  Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .build();

           UserDetails usuarioAutenticado =  usuarioService.autenticar(usuario);

           String token = jwtService.gerarToken(usuario);
           return new TokenDTO(usuario.getLogin(), token);
        }catch (UsernameNotFoundException  | SenhaInvalidaException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());

        }

    }
}
