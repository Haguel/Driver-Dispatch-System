package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.SignUpDTO;
import dev.haguel.dds.exception.RoleNotFoundException;
import dev.haguel.dds.service.AuthService;
import dev.haguel.dds.util.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    @GetMapping(EndPoints.GET_LOGIN_PAGE)
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(EndPoints.GET_SIGN_UP_PAGE)
    public String getSignUpPage(Model model) {
        model.addAttribute("signUpDto", new SignUpDTO());
        model.addAttribute("signUpEndpoint", EndPoints.SIGN_UP);

        return "signup";
    }

    @PostMapping(EndPoints.SIGN_UP)
    public String signUp(@Valid @ModelAttribute SignUpDTO signUpDto) {
        try {
            authService.signUp(signUpDto);
        } catch (RoleNotFoundException exception) {
            exception.printStackTrace();
        }

        return "redirect:" + EndPoints.GET_LOGIN_PAGE;
    }
}
