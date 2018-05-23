package com.pszymanski.library.controller;

import com.pszymanski.library.dto.UserDTO;
import com.pszymanski.library.service.impl.RentServiceImpl;
import com.pszymanski.library.service.impl.ReservationServiceImpl;
import com.pszymanski.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ReservationServiceImpl reservationService;
    @Autowired
    RentServiceImpl rentService;

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsersPage(Model model) {

        model.addAttribute("userList", userService.findAll());

        return "user/users";
    }

    @RequestMapping(value = "/admin/users/search", method = RequestMethod.GET)
    public String searchUser(@RequestParam(value = "value", required = false) String value, @RequestParam(value = "option", required = false) String option, Model model) {

        model.addAttribute("userList", userService.search(value, option));
        return "user/users";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String addUser(Model model) {

        model.addAttribute("user", new UserDTO());
        return "user/addUser";
    }


    @RequestMapping(value = "/registration/save", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult result) {

        if (result.hasErrors()) {
            return "/user/addUser";
        }
        userService.save(user);

        return "redirect:/?adduser";
    }

    @RequestMapping(value = "/registration/cancel", method = RequestMethod.GET)
    public String cancel() {

        return "redirect:/";
    }

    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable Integer id, Model model) {

        model.addAttribute("user", userService.findOne(id));
        return "/user/addUser";
    }

    @RequestMapping(value = "/admin/useraccount/{id}", method = RequestMethod.GET)
    public String userAccountByEmployee(@PathVariable Integer id, Model model) {

        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("reservationList", reservationService.getReservationByUser(id));
        model.addAttribute("rentList", rentService.getRentsByUser(id));
        return "/user/userAccount";
    }

    @RequestMapping(value = "/myaccount/", method = RequestMethod.GET)
    public String userAccount(Model model, Principal principal) {

        Integer idUser = userService.getUserId(principal.getName());

        model.addAttribute("user", userService.findOne(idUser));
        model.addAttribute("reservationList", reservationService.getReservationByUser(idUser));
        model.addAttribute("rentList", rentService.getRentsByUser(idUser));
        return "/user/userAccount";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin/user/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id) {

        userService.delete(id);
        return "redirect:/admin/users?delete";
    }


}
