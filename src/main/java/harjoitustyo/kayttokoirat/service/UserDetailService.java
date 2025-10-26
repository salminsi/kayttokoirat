package harjoitustyo.kayttokoirat.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import harjoitustyo.kayttokoirat.domain.AppUser;
import harjoitustyo.kayttokoirat.domain.AppUserRepository;

//käyttäjän hallinta.
@Service
public class UserDetailService implements UserDetailsService {

    AppUserRepository repository;

    public UserDetailService(AppUserRepository appUserRepository) {
        this.repository = appUserRepository;
    }

    @Override // kirjautuessa tämä metodi hakee käyttäjän roolin ja salasanan 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }

}
