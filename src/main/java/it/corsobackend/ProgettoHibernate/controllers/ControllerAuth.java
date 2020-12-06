package it.corsobackend.ProgettoHibernate.controllers;

import it.corsobackend.ProgettoHibernate.repositories.CookieRepository;
import it.corsobackend.ProgettoHibernate.repositories.UserRepository;
import it.corsobackend.ProgettoHibernate.services.SecurityService;
import it.corsobackend.ProgettoHibernate.services.UserService;
import it.corsobackend.ProgettoHibernate.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ControllerAuth {
    @Autowired private UserRepository ur;
    @Autowired private CookieRepository cr;

    @PostMapping("/registrazione")
    ResponseEntity<String> registrazione (@RequestBody UserView userview,
                                          @Autowired UserService us,
                                          @Autowired SecurityService securityService){
        if(us.registration(userview, ur, securityService)){
            return new ResponseEntity<>("Registrazione avvenuta con successo!", new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Registrazione fallita: username già presente.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody UserView userview,
                                 HttpServletResponse response,
                                 @Autowired UserService us,
                                 @Autowired SecurityService securityService){
        String cookieValue = us.login(userview, ur, securityService);
        if(cookieValue != null){
            Cookie auth = new Cookie("auth", cookieValue);
            response.addCookie(auth);
            return new ResponseEntity<>("Login effettuato.",new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Login fallito.",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/logout")
    ResponseEntity<String> logout(@CookieValue(value = "auth", defaultValue = "") String auth,
                                  @Autowired UserService us){
        if(auth.equals("")) return new ResponseEntity<>("",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        else{
            if(us.logout(auth, cr)) return new ResponseEntity<>("Logout effettuato.",new HttpHeaders(), HttpStatus.OK);
            else return new ResponseEntity<>("",new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }
}
