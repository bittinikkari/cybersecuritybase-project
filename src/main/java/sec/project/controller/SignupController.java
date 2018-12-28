package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "redirect:/done";
    }

    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public String list(Model model) {
        return "done";
    }
    
    @RequestMapping(value = "/done", method = RequestMethod.POST)
    public String submitQuery(Model model, @RequestParam String name) {
        model.addAttribute("done", signupRepository.findByName(name));
        return "done";
    }
    
}
