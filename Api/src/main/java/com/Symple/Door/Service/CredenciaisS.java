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

    public boolean loginEsp(String senha) {

        List<CredenciaisE> todosUsuarios = credenciaisR.findAll();

        return todosUsuarios.stream()
                .anyMatch(usuario -> passwordEncoder.matches(senha, usuario.getSenha()));
    }

    public CredenciaisE excluirCredencial(CredenciaisE credenciaisE) throws RegraNegocioException {

        Optional<CredenciaisE> credencialExiste = credenciaisR.findCredenciaisEByNome(credenciaisE.getNome());

        if(credencialExiste.isPresent()){
            credenciaisR.deleteCredenciaisEByNome(credenciaisE.getNome());
            loginR.deleteLoginEByLogin(credenciaisE.getNome());

        }else {
            throw new RegraNegocioException ("Credenciais não encontrados.");
         }

        return credencialExiste.get();

    }

    public CredenciaisE atualizarCredencial(CredenciaisE credenciaisE) throws RegraNegocioException{

        Optional<CredenciaisE> credencialExiste = credenciaisR.findCredenciaisEByNome(credenciaisE.getNome());
        Optional<LoginE> loginExiste = loginR.findLoginEByLogin(credenciaisE.getNome());

        if (credencialExiste.isPresent() && loginExiste.isPresent()) {

            credenciaisE.setSenha(passwordEncoder.encode(credenciaisE.getSenha()));
            credenciaisR.save(credenciaisE);

            loginExiste.get().setSenha(passwordEncoder.encode(credenciaisE.getSenha()));
            loginR.save(loginExiste.get());

            return credenciaisE;
        } else {
            throw new RuntimeException("Credenciais ou login não encontrados.");
        }
    }


}
