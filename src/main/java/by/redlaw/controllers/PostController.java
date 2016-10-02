package by.redlaw.controllers;

import by.redlaw.converter.PostToPostDTOConverter;
import by.redlaw.dto.PostDTO;
import by.redlaw.dto.UserDTO;
import by.redlaw.entity.Post;
import by.redlaw.entity.UserEntity;
import by.redlaw.service.post.PostService;
import by.redlaw.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Redlaw on 09.09.2016.
 */

@Controller
public class PostController {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    /**
     * Making a new post. Binding newPost parameters
     *
     * @param postDTO
     * @param session
     * @return
     */
    @RequestMapping(value = "/makepost", method = RequestMethod.POST)
    public String displayUser(@ModelAttribute("newPost") PostDTO postDTO, HttpSession session, @RequestParam("photo") MultipartFile photo) {

        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
        UserEntity userEntity = userService.getUserByLogin(userDTO.getLogin());

        Post newPost = new Post();
        java.util.Date date = new java.util.Date();
        newPost.setPostDate(new java.sql.Date(date.getTime()));
        newPost.setUserID(userEntity);
        newPost.setText(postDTO.getText());

        try {
            newPost.setPhoto(photo.getBytes());

        } catch (IOException e) {
            LOGGER.error(messageSource.getMessage("dao.post.saveBlob", new Object[]{photo}, Locale.ENGLISH));
        }

        postService.createPost(newPost);

        return "redirect:/postloginpage";
    }

    /**
     * Post edition before visiting JSP page
     *
     * @param model
     * @param id    (post_id)
     * @return
     */
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String postUpdateInit(Model model, @PathVariable(value = "id") Long id, HttpServletResponse response) {
        Post post = postService.getPostById(id);

        // Creating postDTO object using converter and post object
        PostDTO postDTO = PostToPostDTOConverter.convertoEntityToDTO(post);


        model.addAttribute("post", postDTO);
        return "/postedition";
    }

    /**
     * Post edition after visiting JSP page
     *
     * @param model
     * @param postDTO
     * @return
     */
    @RequestMapping(value = "/postedit", method = RequestMethod.POST)
    public String postUpdate(Model model, @ModelAttribute("post") PostDTO postDTO) {

        postService.postEdit(postDTO);
        return "redirect:/postloginpage#"+postDTO.getPostID();
    }

    /**
     * Refreshing POSTS page using model renew
     *
     * @param model
     * @return jsp page
     */
    @RequestMapping(value = "/postloginpage", method = RequestMethod.GET)
    public String displayUser(Model model) {
        model.addAttribute("newPost", new PostDTO());
        return "/postloginpage";
    }

    // Post destroy
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String postDeletiont(Model model, @PathVariable(value = "id") Long id) {
        Post post = postService.getPostById(id);
        postService.deletePost(post);
        return "redirect:/postloginpage";
    }
}
