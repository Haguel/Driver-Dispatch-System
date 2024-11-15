package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.SignUpDTO;
import dev.haguel.dds.exception.RoleNotFoundException;
import dev.haguel.dds.service.AuthService;
import dev.haguel.dds.util.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final HttpServletRequest request;

    @GetMapping(EndPoints.GET_LOGIN_PAGE)
    public String getLoginPage(Model model) {
        EndPoints.setAuthMenuEndpoints(model);

        return "login";
    }

    @GetMapping(EndPoints.GET_SIGN_UP_PAGE)
    public String getSignUpPage(Model model) {
        EndPoints.setAuthMenuEndpoints(model);
        model.addAttribute("signUpDto", new SignUpDTO());
        model.addAttribute("signUpEndpoint", EndPoints.SIGN_UP);

        return "signup";
    }

    @PostMapping(EndPoints.SIGN_UP)
    public String signUp(@Valid @ModelAttribute SignUpDTO signUpDto, Model model) {
        try {
            authService.signUp(signUpDto);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    signUpDto.getUsername(),
                    signUpDto.getPassword()
            );
            authManager.authenticate(authToken);

            SecurityContextHolder.getContext().setAuthentication(authToken);

            request.getSession().setAttribute(
                    "SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()
            );

            return "redirect:" + EndPoints.GET_MAIN_PAGE;
        } catch (RoleNotFoundException exception) {
            model.addAttribute("error", "Role not found. Please try again later.");
        } catch (DataIntegrityViolationException exception) {
            model.addAttribute("error", "Username already exists. Please try again with a different username.");
        } catch (Exception exception) {
            model.addAttribute("error", "An unexpected server error occurred. Please try again later.");
        }

        return getSignUpPage(model);
    }
}
