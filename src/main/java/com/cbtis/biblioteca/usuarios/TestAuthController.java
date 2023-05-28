package com.cbtis.biblioteca.usuarios;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test/")
@AllArgsConstructor
public class TestAuthController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String,Object> body = new HashMap<>();
        body.put("usuario", "Tu usuario es: " + username);
        body.put("mensaje", "Tienes permiso de administrador");
        return ResponseEntity.ok(body.toString());
    }

    @PreAuthorize("hasRole('ROLE_LIBRARIAN') or hasRole('ROLE_ADMIN')")
    @GetMapping("/librarian")
    public ResponseEntity<String> librarian() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String,Object> body = new HashMap<>();
        body.put("usuario", "Tu usuario es: " + username);
        body.put("mensaje", "Tienes permiso de bibliotecario (o administrador)");
        return ResponseEntity.ok(body.toString());
    }

}
