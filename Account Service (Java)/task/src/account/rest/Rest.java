package account.rest;


import account.customExeptions.UserExistException;
import account.entity.AppUser;
import account.entity.UserLoginDTO;
import account.entity.UserSignupDTO;
import account.repository.AppUserRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Rest {

    private final AppUserRepository appUserRepository;

    private final ModelMapper modelMapper;

    public Rest(AppUserRepository appUserRepository, ModelMapper modelMapper) {
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/api/auth/signup")
    @ResponseBody
    public ResponseEntity<AppUser> singUp(@Valid @RequestBody UserSignupDTO userSignup) {

        Optional<AppUser> appUserByEmail = appUserRepository.findAppUserByEmail(userSignup.getEmail().toLowerCase());

        if(appUserByEmail.isPresent()){
           throw new UserExistException();
        }

        userSignup.setEmail(userSignup.getEmail());
        AppUser appUser = modelMapper.map(userSignup, AppUser.class);



        AppUser savedAppUser = appUserRepository.save(appUser);

        return ResponseEntity.ok(savedAppUser);
    }

    @GetMapping("/api/empl/payment")
    public ResponseEntity<AppUser> payment(@AuthenticationPrincipal UserDetails user) {

        Optional<AppUser> logUser = appUserRepository.findAppUserByEmail(user.getUsername().toLowerCase());


        return ResponseEntity.ok(logUser.get());
    }

}
