package horses.Controllers;

import horses.Dtos.LoginResponse;
import horses.Dtos.RegisterDto;
import horses.Services.RegisterService;
import horses.configurations.JwtUtil;
import horses.databases.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final RegisterService registerService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public RegisterController(RegisterService registerService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.registerService = registerService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            logger.debug("Fetching current user info: {}", user.getUsername());
            return ResponseEntity.ok(new LoginResponse(null, user.getUsername(), user.getPoints()));
        }
        return ResponseEntity.status(401).body("Not authenticated");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        logger.info("Registration attempt for username: {}", registerDto.username());
        try {
            registerService.create(registerDto);
            logger.info("User registered successfully: {}", registerDto.username());
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            logger.warn("Registration failed for username {}: {}", registerDto.username(), e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisterDto registerDto) {
        logger.info("Login attempt for username: {}", registerDto.username());
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerDto.username(), registerDto.password())
            );

            User user = (User) authentication.getPrincipal();
            String token = jwtUtil.generateToken(user);

            logger.info("User logged in successfully: {}", registerDto.username());
            return ResponseEntity.ok(new LoginResponse(token, user.getUsername(), user.getPoints()));
        } catch (AuthenticationException e) {
            logger.warn("Login failed for username {}: {}", registerDto.username(), e.getMessage());
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}