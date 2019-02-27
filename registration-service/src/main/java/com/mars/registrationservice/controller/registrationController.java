package com.mars.registrationservice.controller;

import com.mars.registrationservice.beans.LocationdetailsBean;
import com.mars.registrationservice.dao.UserDao;
import com.mars.registrationservice.exceptions.*;
import com.mars.registrationservice.model.Locations;
import com.mars.registrationservice.model.Roles;
import com.mars.registrationservice.model.User;
import com.mars.registrationservice.proxies.Locationproxy;
import com.mars.registrationservice.utils.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class registrationController {
    private HttpServletRequest request, requestlocation;
    @Autowired
    private MessageSource messages;
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    UserDao userDao;

    @Autowired
    Locationproxy locationproxy;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setRequest(HttpServletRequest request, HttpServletRequest requestlocation){
        this.request=request;
        this.requestlocation = requestlocation;
    }


    @GetMapping(value = "/userlist")
    public List<User> listOfUsers() {
        return userDao.findAll();
    }


    //User registration
    @PostMapping(value = "/users/registration")
    public User createUser(@RequestBody User user){

        User registered = createUserAccount(user);
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (registered, request.getLocale(), appUrl));
        } catch (Exception me) {
            return null;
        }
        return null;
    }
    private User createUserAccount(User user) {
        User registered = null;
        if ((emailExist(user.getEmail())&&(pseudoExist(user.getPseudo())))){
            throw new EmailAndPseudoExistsException("Email and Pseudo already exist");
        }else {
            if((emailExist(user.getEmail())||(pseudoExist(user.getPseudo())))){
                if(emailExist(user.getEmail())){
                    throw new EmailExistsException("Email already exists");
                }else{
                    throw new PseudoExistsException("Pseudo already exists");
                }
            }
        }
        try {
            LocationdetailsBean bean =locationproxy.getLocation() ;

            user.setPublisherId(UUID.randomUUID());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setStartDate(new Date().toString());
            user.setRole(Arrays.asList(new Roles("USER_ROLE")));
            user.setLocations(Arrays.asList(new Locations(
                    ""+bean.getIpAddress(),
                    ""+bean.getCity(),
                    ""+bean.getCountry(),
                    ""+bean.getLatitude(),
                    ""+bean.getLongitude())));

            registered = userDao.save(user);

        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }


    //// Registration confirmation
    @RequestMapping(value = "/registration_Confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam("token") String token) {

        Locale locale = request.getLocale();
        User user =userDao.findByToken(token);
        Boolean validate = user.getEnabled();
        if(validate== true)
            throw new UserEnabledException("Your account has already been activated");

        if (user == null)
            throw  new UserNotFoundException("" +
                "\"Le compte que vous voulez activer n'existe pas ou a ete supprime\\n\" +\n" +
                "                   \"Veuiller recreer votre compte s'il vous plait \\n\" +\n" +
                "                   \"vous aurez une duree de 24h pour l'activer\";");



        Calendar cal = Calendar.getInstance();
        if ((user.getVerificationToken().getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            try {
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                        (user, request.getLocale(), appUrl));
            } catch (Exception me) {
                return null;
            }
            /*System.out.println( "/////////////" + user);
            System.out.println( cal.getTime().getTime());
            System.out.println( user.getVerificationToken().getExpiryDate().getTime() - cal.getTime().getTime());*/

        }
        user.setEnabled(true);
        user.setBids(5);
        userDao.save(user);
        return null;
    }

    // Methods utils pour verification email existe ou pseudo existe
    private boolean emailExist(String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    private boolean pseudoExist(String pseudo) {
        User user = userDao.findByPseudo(pseudo);
        if (user != null) {
            return true;
        }
        return false;
    }

    private boolean usernotexist(UUID publish){
        Optional<User> user = userDao.findById(publish);
        if (user!=null){
            return true;
        }
        return false;
    }
}
