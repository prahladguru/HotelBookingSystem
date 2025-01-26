package com.airbnb.Config;

import com.airbnb.entity.AppUser;
import com.airbnb.repository.AppUserRepository;
import com.airbnb.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private AppUserRepository appUserRepository;

    public JWTFilter(JwtService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer")) {
            String tokenval = token.substring(8, token.length() - 1);
            String userName = jwtService.getUserName(tokenval);
            Optional<AppUser> opUser = appUserRepository.findByUsername(userName);
            if (opUser.isPresent()) {
                AppUser appUser = opUser.get();

                UsernamePasswordAuthenticationToken auth = new
                        UsernamePasswordAuthenticationToken(appUser, null, Collections.singleton(
                        new SimpleGrantedAuthority("ADMIN" + appUser.getRole())));

                auth.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }


        }
        filterChain.doFilter(request, response);
    }
}
