package com.Symple.Door.Controller;


import com.Symple.Door.DTO.Devolvo.ErroDTO;
import com.Symple.Door.DTO.Recebo.CredencialFull;
import com.Symple.Door.Entity.CredenciaisE;
import com.Symple.Door.Exceptions.RegraNegocioException;
import com.Symple.Door.Service.CredenciaisS;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/credenciais")
public class CredenciaisC {

    @Autowired
    private CredenciaisS credenciaisS;

    @PostMapping("/entrar/{senha}")
    public ResponseEntity<Object> loginEsp(@PathVariable String senha) throws RegraNegocioException {

        boolean senhaCorreta = credenciaisS.loginEsp(senha);

        return ResponseEntity.status(HttpStatus.OK).body((senhaCorreta)?"Senha válida":"Senha inválida");

    }

    @GetMapping("/todos")
    public ResponseEntity<List<CredenciaisE>> todasCredenciais(){
        return ResponseEntity.status(HttpStatus.OK).body(credenciaisS.todasCredenciais());
    }

    @DeleteMapping("{id}/{nome}")
    public ResponseEntity<Object> deleteCredencial(@PathVariable Long id, @PathVariable String nome) throws RegraNegocioException{

        try {

            CredenciaisE credenciaisE = new CredenciaisE();
            credenciaisE.setIdCredencial(id);
            credenciaisE.setNome(nome);

            return ResponseEntity.status(HttpStatus.OK).body(credenciaisS.excluirCredencial(credenciaisE));

        } catch (Exception | RegraNegocioException error) {
            ErroDTO erroDTO = new ErroDTO(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDTO);
        }
    }

    @PutMapping
    public ResponseEntity<Object> atualizarCredencial(@RequestBody @Valid CredencialFull credencialFull) throws RegraNegocioException {

        try {
            CredenciaisE credenciaisE = new CredenciaisE();
            BeanUtils.copyProperties(credencialFull, credenciaisE);

            return ResponseEntity.status(HttpStatus.OK).body(credenciaisS.atualizarCredencial(credenciaisE));

        } catch (Exception | RegraNegocioException error) {
            ErroDTO erroDTO = new ErroDTO(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDTO);
        }
    }
}
