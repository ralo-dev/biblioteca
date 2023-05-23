package com.cbtis.biblioteca.usuarios;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.cbtis.biblioteca.jwt.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger log = Logger.getLogger(AuthController.class.getName());

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/ingresar")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getNombre(),
                userDetails.getApellidos(),
                roles));
    }
    @PostMapping("/registrar")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El nombre de usuario ya está siendo utilizado!"));
        }
        // Create new usuario's account
        Usuario usuario = new Usuario(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));
        String strRoles = signUpRequest.getRole();
        usuario.setNombre(signUpRequest.getNombre());
        usuario.setApellidos(signUpRequest.getApellidos());
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El rol no puede ser nulo!"));
        } else {
                switch (strRoles) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: No se encontró el rol "+strRoles+"!"));
                        roles.add(adminRole);
                        break;
                    case "librarian":
                        Role modRole = roleRepository.findByName(ERole.ROLE_LIBRARIAN)
                                .orElseThrow(() -> new RuntimeException("Error: No se encontró el rol "+strRoles+"!"));
                        roles.add(modRole);
                        break;
                    default:
                        return ResponseEntity
                                .badRequest()
                                .body(new MessageResponse("Error: No se encontró el rol "+strRoles+"!"));
                }
        }
        usuario.setRoles(roles);
        userRepository.save(usuario);
        return ResponseEntity.ok(new MessageResponse("Usuario registrado satisfactoriamente!"));
    }

    /**
     * Actualiza la contraseña de un usuario
     * @Requiere: Un usuario autenticado con cualquier rol
     * @Restricciones: Un administrador puede cambiar la contraseña de cualquier usuario; Un usuario solo puede cambiar su propia contraseña
     */
    @PostMapping("/actualizarContrasena")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody Usuario data) {
        Optional<Usuario> user = userRepository.findByUsername(data.getUsername());
        if (user.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Usuario no encontrado!"));
        }
        user.get().setPassword(encoder.encode(data.getPassword()));
        userRepository.save(user.get());
        return ResponseEntity.ok(new MessageResponse("Usuario actualizado!"));
    }

    /**
     * Obtiene la lista de usuarios
     * @Requiere: Un usuario autenticado con rol de administrador
     * @Restricciones: Solo un administrador puede obtener la lista de usuarios
     * @return Lista de usuarios
     */
    @GetMapping("/usuarios")
    public ResponseEntity<?> getUsers() {
        List<Usuario> usuarios = userRepository.findAll();
        usuarios.forEach(usuario -> usuario.setPassword(""));
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        Optional<Usuario> usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Usuario no encontrado!"));
        }
        userRepository.delete(usuario.get());
        return ResponseEntity.ok(new MessageResponse("Usuario eliminado!"));
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @Valid @RequestBody Usuario data) {
        Optional<Usuario> usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Usuario no encontrado!"));
        }
        usuario.get().setUsername(data.getUsername());
        usuario.get().setNombre(data.getNombre());
        usuario.get().setApellidos(data.getApellidos());
        usuario.get().setRoles(data.getRoles());
        userRepository.save(usuario.get());
        return ResponseEntity.ok(new MessageResponse("Usuario actualizado!"));
    }


}