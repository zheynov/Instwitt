package by.redlaw.controllers;

import by.redlaw.dto.UserDTO;
import by.redlaw.service.post.PostService;
import by.redlaw.service.user.UserService;
import by.redlaw.validators.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Login controller
 */

@Controller
public class LoginController {

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    /** Checking user's data before login
     * @param model
     * @param session
     * @param userDTO
     * @param result
     * @return string value, postloginpage.jsp
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String logginProcess(Model model, HttpSession session,
                                @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result) {

        loginValidator.validate(userDTO, result);

        if (result.hasErrors()) {
            return "/index";
        } else {
            if (userService.isLoginExists(userDTO.getLogin())) {

                if (userService.isPasswpodlCorrect(userDTO)) {
                    if (session.getAttribute("userDTO") == null) { // if session doesnt contain userDTO object
                        session.setAttribute("userDTO", userDTO);
                        session.setAttribute("postService", postService);
                        return "redirect:/postloginpage";
                    }
                    model.addAttribute("formNotification", "It's impossible to login. Try to exit first!");
                    return "/index";

                } else {
                    model.addAttribute("formNotification", "Incorrect password");
                    return "/index";
                }
            } else {
                model.addAttribute("formNotification",
                        String.format("User %s doesn't exist", userDTO.getLogin()));
                return "/index";
            }
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayUser(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "/index";
    }

    /** Exit prom the site, refreshing all the session's data, redirecting to login page
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String exit(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("userDTO", new UserDTO());
        return "/index";
    }
}
