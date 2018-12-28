package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String loadPasswdForm() {
        return "password";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String changePassword(Authentication authentication, @RequestParam String password) {
        Account account = accountRepository.findByUsername(authentication.getName());
        if (account == null) {
            return "redirect:/index";
        }

        account.setPassword(encoder.encode(password));
        accountRepository.save(account);

        return "thanks";
    }
    
    @RequestMapping(value = "/thanks", method = RequestMethod.GET)
    public String thanks() {
        return "thanks";
    }


}
