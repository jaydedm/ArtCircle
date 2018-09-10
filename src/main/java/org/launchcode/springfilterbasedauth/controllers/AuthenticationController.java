package org.launchcode.springfilterbasedauth.controllers;

import org.launchcode.springfilterbasedauth.models.Category;
import org.launchcode.springfilterbasedauth.models.forms.Opportunity;
import org.launchcode.springfilterbasedauth.models.User;
import org.launchcode.springfilterbasedauth.models.dao.CategoryDao;
import org.launchcode.springfilterbasedauth.models.dao.OpportunityDao;
import org.launchcode.springfilterbasedauth.models.forms.LoginForm;
import org.launchcode.springfilterbasedauth.models.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * Created by LaunchCode
 */
@Controller
public class AuthenticationController extends AbstractController {


    @Autowired
    private OpportunityDao opportunityDao;

    @RequestMapping(value = "")
    public String index() {

        return "index";
    }

    @RequestMapping(value = "/register")
    public String registerForm(Model model) {
        model.addAttribute(new RegisterForm());
        model.addAttribute("title", "Register");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid RegisterForm form, Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "register";
        }

        User existingUser = userDao.findByUsername(form.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            return "register";
        }

        User newUser = new User(form.getUsername(), form.getPassword(), form.getDisplayname(), form.getEmail());
        userDao.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute(new LoginForm());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Valid LoginForm form, Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "login";
        }

        User theUser = userDao.findByUsername(form.getUsername());
        String password = form.getPassword();

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            return "login";
        }

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String catetegoryIndex(Model model, @RequestParam(defaultValue = "0") int id) {
        model.addAttribute("title", "Categories");
        model.addAttribute("categories", categoryDao.findAll());
        return "category";
    }

    @RequestMapping(value = "categoryAdd", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Category());
        model.addAttribute("title", "Add Category");
        return "categoryAdd";
    }

    @RequestMapping(value = "categoryAdd", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Category category, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "categoryAdd";
        }

        categoryDao.save(category);
        return "category";
    }



    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String displayCreateOpportunityForm(Model model, User user) {
        model.addAttribute("title", "Create Opportunity");
        model.addAttribute(new Opportunity());
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("users", userDao.findAll());
        return "create";
    }


    //have to add categories from a hard list to this at some point


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String processCreateOpportunityForm(@ModelAttribute @Valid Opportunity newOpportunity, Errors errors, User user,
                                               Model model, @RequestParam int categoryId, @RequestParam int userId) {



        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Opportunity");
            model.addAttribute("categories", categoryDao.findAll());
            return "create";
        }

        User owner = userDao.findOne(userId);
        String us = user.getDisplayname();
        Category cat = categoryDao.findOne(categoryId);
        newOpportunity.setUser(owner);
        newOpportunity.setAuthor(us);
        newOpportunity.setCategory(cat);
        opportunityDao.save(newOpportunity);
        return "redirect:";

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearch(Model model) {
        model.addAttribute("opportunities", opportunityDao.findAll());
        model.addAttribute("category", categoryDao.findAll());

        return "search";
    }

    @RequestMapping(value = "opportunity", method = RequestMethod.GET)
    public String category(Model model, @RequestParam int id) {

        Opportunity op = opportunityDao.findOne(id);
        model.addAttribute("opportunities", op);
        return "opportunity";

    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String displayUserContact(Model model, @RequestParam String displayname, @RequestParam int id) {

        Opportunity op = opportunityDao.findByUserId(id);
        User user = userDao.findByUsername(displayname);
        model.addAttribute("users", user);
        model.addAttribute("opportunities", op);
        return "user";
    }

    @RequestMapping (value = "map", method = RequestMethod.GET)
    public String displayMap(Model model) {
        return "MapTest";
    }

    @RequestMapping (value = "geocode", method = RequestMethod.GET)
    public String displayGeocodeMap(Model model) {return "geocode";}

    @RequestMapping (value = "bootstrap", method = RequestMethod.GET)
    public String displayBootstrap(Model model) {return "bootstrap";}
}