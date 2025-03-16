package com.Symple.Door.Service;

import com.Symple.Door.DTO.Devolvo.CodigoLoginDTO;
import com.Symple.Door.Entity.CredenciaisE;
import com.Symple.Door.Entity.LoginE;
import com.Symple.Door.Exceptions.RegraNegocioException;
import com.Symple.Door.InfraSecurity.TokenService;
import com.Symple.Door.Repository.CredenciaisR;
import com.Symple.Door.Repository.LoginR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginS {

    @Autowired
    private LoginR loginRepository;
    @Autowired
    private CredenciaisR credenciaisRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public LoginE criarConta(LoginE login) throws RegraNegocioException {

        Optional<LoginE> jaExiste = loginRepository.findLoginBySenha(login.getSenha());

        if(jaExiste.isPresent()){

            throw new RegraNegocioException("Tente outra senha");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(login.getSenha());
        login.setSenha(encryptedPassword);

        CredenciaisE credenciaisE = new CredenciaisE(login.getLogin(), login.getSenha());
        credenciaisRepository.save(credenciaisE);

        return loginRepository.save(login);
    }

    public CodigoLoginDTO login(LoginE login) throws RegraNegocioException{

        Optional<LoginE> encontreUsuario = loginRepository.findLoginBySenha(login.getSenha());

        if(encontreUsuario.isEmpty()){
            throw new RegraNegocioException("Senha Incorreta");
        }

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(encontreUsuario.get().getUsername(),
                login.getPassword());

        var auth = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var token = tokenService.generateToken((LoginE) auth.getPrincipal());

        CodigoLoginDTO codigoLoginDTO = new CodigoLoginDTO(token, encontreUsuario.get().getLogin(),
                encontreUsuario.get().getSenha());

        return codigoLoginDTO;
    }

}

