package com.mitocode.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitocode.exception.CustomErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

//Clase S6
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String message = (String) request.getAttribute("error");

        if (message == null ){
            message = "Authentication failed";
        }

        CustomErrorResponse errorResponse =
                new CustomErrorResponse(LocalDateTime.now(), message, request.getRequestURI());

        response.setStatus(HttpStatus.UNAUTHORIZED.value()); // response 401
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(convertObjectToJson(errorResponse));
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException{
        if (object == null){
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return  mapper.writeValueAsString(object);
    }
}
