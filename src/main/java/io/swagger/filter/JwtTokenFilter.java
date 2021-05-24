package io.swagger.filter;

import io.swagger.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // Retrieve the token from the request
        String token = jwtTokenProvider.resolveToken(httpServletRequest);

        try {
            // Check if the token is valid
            if (token != null && jwtTokenProvider.validateToken(token)) {
                // retrieve the user form the database
                Authentication auth = jwtTokenProvider.getAuthentication(token);

                // Apply the user to the security context of the request
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (ResponseStatusException ex){
            // If token is invalid, clear the security context
            SecurityContextHolder.clearContext();

            httpServletResponse.sendError(ex.getStatus().value(), ex.getMessage());
            return;
        }

        // Move on to the next filter
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
