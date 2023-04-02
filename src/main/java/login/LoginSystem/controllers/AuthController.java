package login.LoginSystem.controllers;

import login.LoginSystem.entities.Role;
import login.LoginSystem.entities.User;
import login.LoginSystem.jwt.JwtUtils;
import login.LoginSystem.repositories.RoleRepository;
import login.LoginSystem.repositories.UserRepository;
import login.LoginSystem.requests.LoginRequest;
import login.LoginSystem.requests.SignUpRequest;
import login.LoginSystem.response.BaseResponse;
import login.LoginSystem.response.JwtResponse;
import login.LoginSystem.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//hello world

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
@Autowired
    JwtUtils jwtUtils;

@PostMapping("/signin")
public BaseResponse authenticateUser(@Valid @RequestBody  LoginRequest loginRequest){
Authentication authentication=authenticationManager.authenticate(
new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

SecurityContextHolder.getContext().setAuthentication(authentication);
String jwt=jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails=(UserDetailsImpl)authentication.getPrincipal();
    List<String> roles=userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

return new BaseResponse(HttpStatus.OK.value(), "Token Generated Successfully",
        new JwtResponse(jwt, userDetails.getId(),  userDetails.getUsername(), userDetails.getEmail(),
                ,roles), null);
}
@PostMapping("/signup")
public BaseResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest){

if(userRepository.existsByUsername(signUpRequest.getUsername())){

return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "Username is already taken!" , null, null );
if(userRepository.existsByEmail(signUpRequest.getEmail())){
    return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Email is already taken!" , null, null);

    //creating new user account

    User user=new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getPhonenumber());
    Set<String> strRoles= new signUpRequest
}
}
}
}

}
