package sec.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;
    
    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/landing";
    }

    @RequestMapping(value = "/landing", method = RequestMethod.GET)
    public String displayLanding() {
        return "landing";
    }
    
    /*
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String loadPasswdForm() {
        return "password";
    }
    */
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String loadAdminPage(Model model) {
        model.addAttribute("admin", signupRepository.findAll());
        return "admin";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        signupRepository.save(new Signup(name, address));
        /*List<Signup> registeredUsers = signupRepository.findAll();
        Long n = signupRepository.count();
        for (int x=0; x<n; x++) {
           System.out.println(registeredUsers.get(x).getAddress());
        }*/
        return "redirect:/done";
    }

    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public String list(Model model) {
        //model.addAttribute("done", signupRepository.findAll());
        return "done";
    }
    
    @RequestMapping(value = "/done", method = RequestMethod.POST)
    public String submitQuery(Model model, @RequestParam String name) {
        //List<Signup> registered = signupRepository.findByName(name);
        model.addAttribute("done", signupRepository.findByName(name));
        return "done";
    }
    
    //@RequestMapping(value = "/password", method = RequestMethod.POST)
    //@PreAuthorize("hasRole('READ_PRIVILEGE')")
    //@ResponseBody
    //public String changeUserPassword(Locale locale, @RequestParam("oldpass") String oldpass, @RequestParam("password") String password) {
        //User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //User user = userDetails.getUsername();
        //userService.changeUserPassword(user, password);
        //return new GenericResponse(messages.getMessage("message.updatePasswordSuc", null, locale));
        
    //    userDetailsManager.changePassword(oldpass, password);
    //    return "passwddone";
    
    //}
}
