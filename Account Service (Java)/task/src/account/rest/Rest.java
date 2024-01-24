package account.rest;


import account.customExeptions.NewPasswordLengthException;
import account.entity.AppUser;
import account.entity.UserChangePassReqDTO;
import account.entity.UserChangePassResDTO;
import account.entity.UserSignupDTO;
import account.repository.AppUserRepository;
import account.service.UserRegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Rest {

    private final UserRegisterService userRegisterService;
    private final AppUserRepository appUserRepository;

    public Rest(UserRegisterService userRegisterService, AppUserRepository appUserRepository) {
        this.userRegisterService = userRegisterService;
        this.appUserRepository = appUserRepository;
    }


    @PostMapping("/api/auth/signup")
    @ResponseBody
    public ResponseEntity<AppUser> singUp(@Valid @RequestBody UserSignupDTO userSignup) {


        AppUser savedAppUser = userRegisterService.register(userSignup);


        return ResponseEntity.ok(savedAppUser);
    }

    @GetMapping("/api/empl/payment")
    public ResponseEntity<AppUser> payment(@AuthenticationPrincipal UserDetails user) {

        Optional<AppUser> logUser = appUserRepository.findAppUserByEmailIgnoreCase(user.getUsername().toLowerCase());


        return ResponseEntity.ok(logUser.get());
    }

    @PostMapping("/api/auth/changepass")
    @ResponseBody
    public ResponseEntity<UserChangePassResDTO> changePass(@RequestBody @Valid UserChangePassReqDTO userChangePass, @AuthenticationPrincipal UserDetails user) {

        if(userChangePass.newPassword().length() <12){

            throw new NewPasswordLengthException();
        }

        String username = user.getUsername();
        userRegisterService.changePassword(userChangePass.newPassword(), username);





        return ResponseEntity.ok(new UserChangePassResDTO().setEmail(username));
    }


}
