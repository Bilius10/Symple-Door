package com.Symple.Door.Service;

import com.Symple.Door.Entity.CredenciaisE;
import com.Symple.Door.Entity.LoginE;
import com.Symple.Door.Exceptions.RegraNegocioException;
import com.Symple.Door.Repository.CredenciaisR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CredenciaisS {

    @Autowired
    private CredenciaisR credenciaisR;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<CredenciaisE> todasCredenciais(){
        return credenciaisR.findAll();
    }

    public String loginEsp(String senha) throws RegraNegocioException {

        String senhaCriptografada = passwordEncoder.encode(senha);

        Optional<LoginE> encontreUsuario = credenciaisR.findCredenciaisEBySenha(senhaCriptografada);

        if (encontreUsuario.isEmpty()) {
            throw new RegraNegocioException("Senha incorreta");
        }
        return senhaCriptografada;
    }


}
