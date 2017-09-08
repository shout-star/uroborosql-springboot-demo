package jp.co.future.uroborosql.springboot.demo.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.converter.EntityResultSetConverter;
import jp.co.future.uroborosql.mapping.mapper.PropertyMapperManager;
import jp.co.future.uroborosql.springboot.demo.models.Login;
import jp.co.future.uroborosql.springboot.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * LoginController
 *
 * @author Kenichi Hoshi
 * @see jp.co.future.uroborosql.springboot.demo.controllers.BaseController
 */
@RestController
@CrossOrigin
public class LoginController extends BaseController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${petclinic.jwt-secret-key}")
    private String secretKey;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Validated @RequestBody Login login) {
        EntityResultSetConverter<User> converter = new EntityResultSetConverter<>(User.class, new PropertyMapperManager());
        User user;

        try (SqlAgent agent = createAgent()) {
            user = agent.query("login")
                .param("user", login.getUser())
                .stream(converter)
                .findFirst()
                .orElse(null);
            if (user == null || !passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                return new ResponseEntity<>("Invalid user or password.", HttpStatus.BAD_REQUEST);
            }
        }

        String jwtToken = Jwts.builder()
            .claim("roles", "user")
            .claim("userId", user.getId())
            .setSubject(user.getUser())
            .setIssuedAt(new Date())
            .setExpiration(Date.from(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(9))))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();

        Map<String, Object> result = new HashMap<>();
        result.put("token", jwtToken);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
