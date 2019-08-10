package wam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wam.repository.UserRepository;
import wam.repository.WorkshopRepository;

/**
 * Controller for home page
 */
@Controller
public class HomePageController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WorkshopRepository workshopRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("workshops", workshopRepository.findAll());
        return "index";
    }
}
