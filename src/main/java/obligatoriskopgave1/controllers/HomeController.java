package obligatoriskopgave1.controllers;

import jdk.nashorn.internal.objects.annotations.Getter;
import obligatoriskopgave1.models.entities.PwChange;
import obligatoriskopgave1.models.entities.User;
import obligatoriskopgave1.models.repositories.IUserRepository;
import obligatoriskopgave1.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    IUserRepository userRepository = new UserRepository();

    User user = new User();

    // Read All
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/")
    public String index(@ModelAttribute User u, Model model) {
        if (userRepository.checkUser(u)) {
            userRepository.setLogInInfo(u);
            user = u;
            return "/update";
        }
        else return "index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User u){
        //System.out.println("postmapping update method");
        //System.out.println("its starting user: "+u.toString());
        if(!userRepository.checkEmail(u.getEmail())){
            String pw = user.getPassword();
            user = u;
            user.setPassword(pw);
            //System.out.println("new user to update: "+user.toString());
            userRepository.update(user);
        }
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User u) {
        userRepository.create(u);
        return "redirect:/";
    }

    @GetMapping("/password")
    public String password(Model model) {
    model.addAttribute("pwc", new PwChange());
    return "password";
    }

    @PostMapping("/password")
    public String password(@ModelAttribute PwChange pwc) {
        userRepository.changePassword(pwc, ""+user.getUserId());
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        //model.addAttribute("user", user);
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute User u) {
        userRepository.delete(user);
        return "redirect:/";
    }
}