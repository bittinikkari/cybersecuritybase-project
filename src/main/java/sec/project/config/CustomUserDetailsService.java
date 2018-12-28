package sec.project.config;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    
    //private Map<String, String> accountDetails;

    @PostConstruct
    public void init() {
        // this data would typically be retrieved from a database
        /*
        this.accountDetails = new TreeMap<>();
        this.accountDetails.put("ted", "$2a$06$rtacOjuBuSlhnqMO2GKxW.Bs8J6KI0kYjw/gtF0bfErYgFyNTZRDm");
        this.accountDetails.put("john", "$2a$10$nKOFU.4/iK9CqDIlBkmMm.WZxy2XKdUSlImsG8iKsAP57GMcXwLTS");
        this.accountDetails.put("admin", "$2a$04$Bv5g.NGnIyaTPS7Lz78hV.q06oYK4/T/Ivl5qk3JBSYn2j4QZMQFy");
        */
        
        
        
        // username and password is "ted"
        Account ted = new Account();
        ted.setUsername("ted");
        ted.setPassword("$2a$06$rtacOjuBuSlhnqMO2GKxW.Bs8J6KI0kYjw/gtF0bfErYgFyNTZRDm");
        accountRepository.save(ted);
        
        // username "john" password "president"
        Account john = new Account();
        john.setUsername("john");
        john.setPassword("$2a$10$nKOFU.4/iK9CqDIlBkmMm.WZxy2XKdUSlImsG8iKsAP57GMcXwLTS");
        accountRepository.save(john);
        
        // username and password "admin"
        Account admin = new Account();
        admin.setUsername("admin");
        admin.setPassword("$2a$04$Bv5g.NGnIyaTPS7Lz78hV.q06oYK4/T/Ivl5qk3JBSYn2j4QZMQFy");
        accountRepository.save(admin);
        
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        
        if (account == null) {
        //if (!this.accountDetails.containsKey(username)) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        if (username.equals("admin")) {
            return new org.springframework.security.core.userdetails.User(
                username,
                account.getPassword(),
                //this.accountDetails.get(username),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
        }
        
        else return new org.springframework.security.core.userdetails.User(
                //username,
                //this.accountDetails.get(username),
                username,
                account.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
