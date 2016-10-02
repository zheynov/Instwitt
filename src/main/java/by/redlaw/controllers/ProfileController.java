package by.redlaw.controllers;

import by.redlaw.dto.UserDTO;
import by.redlaw.entity.Profile;
import by.redlaw.entity.Sex;
import by.redlaw.entity.UserEntity;
import by.redlaw.service.profile.ProfileService;
import by.redlaw.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 */

@Controller
public class ProfileController extends ExceptionsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    /**
     * Adding profile object to model before visiting profile.jsp
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String displayProfile(Model model, HttpSession session) {

        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
        UserEntity userEntity = userService.getUserByLogin(userDTO.getLogin());

        Profile profile = profileService.getProfileById(userEntity.getProfile().getProfileID());

        model.addAttribute("profile", profile);
        return "/profile";
    }

    /**
     * Adding profile object to model before visiting profileedit.jsp (before profile edition)
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/profileedit", method = RequestMethod.GET)
    public String editProfile(Model model, HttpSession session) {

        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
        UserEntity userEntity = userService.getUserByLogin(userDTO.getLogin());
        Profile profile = profileService.getProfileById(userEntity.getProfile().getProfileID());

        model.addAttribute("sex", Sex.values());
        model.addAttribute("profileedit", profile);

        return "/profileedit";
    }

    /**
     * Profile edition
     *
     * @param model
     * @param session
     * @param newProfile
     * @return
     */
    @RequestMapping(value = "/profileedit", method = RequestMethod.POST)
    public String profileEdit(Model model, HttpSession session,
                              @ModelAttribute("profileedit") Profile newProfile) {
        profileService.updateProfile(newProfile);
        session.setAttribute("profile", newProfile);
        return "/profile";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    // Getting profile by profileID (using it for delete/update operations)
    @ResponseBody
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String respBodyProfile(@PathVariable(value = "id") String id) {
        Profile profile = profileService.getProfileById(Long.valueOf(id));
        return profile.toString();
    }
}
