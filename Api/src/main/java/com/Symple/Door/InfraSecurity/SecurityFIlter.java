package com.Symple.Door.InfraSecurity;

import com.Symple.Door.Repository.LoginR;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import com.Symple.Door.InfraSecurity.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFIlter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final LoginR loginRepository;

    @Autowired
    public SecurityFIlter(TokenService tokenService, LoginR loginRepository) {
        this.tokenService = tokenService;
        this.loginRepository = loginRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoreverToken(request);
        if (token != null) {
            var login = tokenService.validationToken(token);
            UserDetails user = loginRepository.findUserDetailsByName(login);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoreverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("authorization");
        if (authHeader == null) {
            return null;
        } else {
            return authHeader.replace("Bearer", "").trim();
        }
    }
}
