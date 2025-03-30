package com.Symple.Door.Controller;


import com.Symple.Door.DTO.Devolvo.ErroDTO;
import com.Symple.Door.Entity.CredenciaisE;
import com.Symple.Door.Exceptions.RegraNegocioException;
import com.Symple.Door.Service.CredenciaisS;
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

        try {
            return ResponseEntity.status(HttpStatus.OK).body(credenciaisS.loginEsp(senha));
        }catch (RegraNegocioException e){
            ErroDTO erroDTO = new ErroDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDTO);
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<CredenciaisE>> todasCredenciais(){
        return ResponseEntity.status(HttpStatus.OK).body(credenciaisS.todasCredenciais());
    }
}
