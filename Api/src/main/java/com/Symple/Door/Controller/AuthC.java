package com.Symple.Door.Controller;

import com.Symple.Door.DTO.Devolvo.ErroDTO;
import com.Symple.Door.DTO.Recebo.RegistroDTO;
import com.Symple.Door.Entity.LoginE;
import com.Symple.Door.Exceptions.RegraNegocioException;
import com.Symple.Door.Service.LoginS;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthC {

    @Autowired
    private LoginS loginService;

    @PostMapping("/registro")
    public ResponseEntity<Object> registro(@RequestBody @Valid RegistroDTO registroDTO) throws RegraNegocioException {

        try {

            LoginE recebeDTO = new LoginE();

            BeanUtils.copyProperties(registroDTO, recebeDTO);

            return ResponseEntity.status(HttpStatus.OK).body(loginService.criarConta(recebeDTO));

        } catch (RegraNegocioException error) {
            ErroDTO erroDTO = new ErroDTO(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDTO);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid RegistroDTO loginDTO) throws RegraNegocioException{

        try {

            LoginE recebeDTO = new LoginE();

            BeanUtils.copyProperties(loginDTO, recebeDTO);

            return ResponseEntity.status(HttpStatus.OK).body(loginService.login(recebeDTO));

        } catch (Exception | RegraNegocioException error) {
            ErroDTO erroDTO = new ErroDTO(error.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDTO);
        }

    }
}
