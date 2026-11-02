package com.laboratorio.profesores.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    public static final String HEADER_API_KEY = "X-API-KEY";

    private final String apiKeyEsperada;

    public ApiKeyFilter(@Value("${app.api-key}") String apiKeyEsperada) {
        this.apiKeyEsperada = apiKeyEsperada;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String apiKeyRecibida = request.getHeader(HEADER_API_KEY);

        if (!esValida(apiKeyRecibida)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write(
                    "{\"status\":401,\"error\":\"Unauthorized\",\"mensaje\":\"API Key ausente o incorrecta\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean esValida(String apiKeyRecibida) {
        if (apiKeyRecibida == null) {
            return false;
        }
        return MessageDigest.isEqual(
                apiKeyRecibida.getBytes(StandardCharsets.UTF_8),
                apiKeyEsperada.getBytes(StandardCharsets.UTF_8));
    }
}
