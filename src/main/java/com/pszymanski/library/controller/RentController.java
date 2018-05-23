package com.pszymanski.library.controller;

import com.pszymanski.library.dto.RentDTO;
import com.pszymanski.library.service.impl.RentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RentController {

    @Autowired
    RentServiceImpl rentService;

    @RequestMapping(value = "/admin/rents", method = RequestMethod.GET)
    public String getRentPage(Model model) {

        model.addAttribute("rentList", rentService.findAll());

        return "rent/rents";
    }

    @RequestMapping(value = "/admin/rents/search", method = RequestMethod.GET)
    public String searchEmployee(@RequestParam(value = "value", required = false) String value, @RequestParam(value = "option", required = false) String option, Model model) {

        model.addAttribute("rentList", rentService.search(value, option));
        return "rent/rents";
    }


    @RequestMapping(value = "/admin/rent/add/{idUser}")
    public String addRent(@PathVariable Integer idUser, Model model) {
        model.addAttribute("rent", new RentDTO());
        model.addAttribute("userid", idUser);

        return "rent/addRent";
    }

    @RequestMapping(value = "/admin/rent/add/save")
    public String saveRent(@Valid @ModelAttribute("rent") RentDTO rent, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "rent/addRent";
        }
        rentService.addRent(rent, principal.getName());

        return "redirect:/admin/rents?add";
    }

    @RequestMapping(value = "/admin/rent/return/{idRent}")
    public String returnRent(@PathVariable Integer idRent, Model model) {
        Double penetly = rentService.bookReturn(idRent);
        return "redirect:/admin/rents?penetly=" + penetly;
    }

    @RequestMapping(value = "/admin/rent/edit/{id}", method = RequestMethod.GET)
    public String editRent(@PathVariable Integer id, Model model) {

        model.addAttribute("rent", rentService.findOne(id));
        return "/rent/editRent";
    }


    @RequestMapping(value = "/admin/rent/delete/{id}", method = RequestMethod.GET)
    public String deleteRent(@PathVariable Integer id) {

        rentService.delete(id);
        return "redirect:/admin/rents?delete";
    }

    @RequestMapping(value = "/admin/rent/cancel", method = RequestMethod.GET)
    public String cancelRent() {

        return "redirect:/admin/rents";
    }

}
