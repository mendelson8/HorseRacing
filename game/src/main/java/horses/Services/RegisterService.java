package horses.Services;

import horses.Dtos.RegisterDto;
import horses.Repositories.RegisterInterface;
import horses.databases.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    private RegisterInterface registerInterface;

    public String create(RegisterDto dto) {
        logger.info("Creating new user: {}", dto.username());

        if (registerInterface.findByUsername(dto.username()) != null) {
            logger.warn("User already exists: {}", dto.username());
            throw new RuntimeException("User already exists");
        }

        User info = User.builder()
                .username(dto.username())
                .password(new BCryptPasswordEncoder().encode(dto.password()))
                .authorities("USER")
                .points(1000)
                .build();
        registerInterface.save(info);

        logger.info("User created successfully: {}", dto.username());
        return "Create Successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Loading user by username: {}", username);
        User user = registerInterface.findByUsername(username);
        if (user == null) {
            logger.warn("User not found: {}", username);
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }

    public User findByUsername(String username) {
        logger.debug("Finding user by username: {}", username);
        return registerInterface.findByUsername(username);
    }

    public User updateUser(User user) {
        logger.debug("Updating user: {}", user.getUsername());
        return registerInterface.save(user);
    }
}
