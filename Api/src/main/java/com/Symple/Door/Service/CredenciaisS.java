package com.Symple.Door.Service;

import com.Symple.Door.Entity.CredenciaisE;
import com.Symple.Door.Entity.LoginE;
import com.Symple.Door.Exceptions.RegraNegocioException;
import com.Symple.Door.Repository.CredenciaisR;
import com.Symple.Door.Repository.LoginR;
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
    private LoginR loginR;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<CredenciaisE> todasCredenciais(){
        return credenciaisR.findAll();
    }

    public String loginEsp(String senha) throws RegraNegocioException {

        String senhaCriptografada = passwordEncoder.encode(senha);

        List<CredenciaisE> todosUsuarios = credenciaisR.findAll();

        for (CredenciaisE usuario : todosUsuarios) {
            if (passwordEncoder.matches(senha, usuario.getSenha())) {
                return "Senha valida";
            }
        }
        throw new RegraNegocioException("Senha invalida");
    }

    public CredenciaisE excluirCredencial(CredenciaisE credenciaisE){

        Optional<CredenciaisE> credencialExiste = credenciaisR.findCredenciaisEByNome(credenciaisE.getNome());

        if(credencialExiste.isPresent()){
            credenciaisR.deleteCredenciaisEByNome(credenciaisE.getNome());
            loginR.deleteLoginEByLogin(credenciaisE.getNome());
        }

        return credencialExiste.get();

    }

    public CredenciaisE atualizarCredencial(CredenciaisE credenciaisE){

        Optional<CredenciaisE> credencialExiste = credenciaisR.findCredenciaisEByNome(credenciaisE.getNome());
        Optional<LoginE> loginExiste = loginR.findLoginEByLogin(credenciaisE.getNome());

        credencialExiste.get().setSenha(passwordEncoder.encode(credenciaisE.getSenha()));
        loginExiste.get().setSenha(passwordEncoder.encode(credenciaisE.getSenha()));

        if(credencialExiste.isPresent()){
            credenciaisR.save(credencialExiste.get());
            loginR.save(loginExiste.get());
        }

        return credenciaisE;
    }


}
