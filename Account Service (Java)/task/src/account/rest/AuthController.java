package account.rest;


import account.entity.AppUser;
import account.entity.DTO.*;
import account.service.UserRegisterService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class AuthController {

    private final UserRegisterService userRegisterService;

    public AuthController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }


    @PostMapping("/api/auth/signup")
    @ResponseBody
    public ResponseEntity<AppUser> singUp(@Valid @RequestBody UserSignupDTO userSignup) {

        AppUser savedAppUser = userRegisterService.register(userSignup);

        return ResponseEntity.ok(savedAppUser);
    }


    @PostMapping("/api/auth/changepass")
    @ResponseBody
    public ResponseEntity<UserChangePassResDTO>
    changePass(@RequestBody @Valid UserChangePassReqDTO userChangePass,
               @AuthenticationPrincipal UserDetails user) {

        return userRegisterService.changePassword(userChangePass.newPassword(), user.getUsername());
    }


}
