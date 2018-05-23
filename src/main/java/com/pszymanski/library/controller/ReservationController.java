package com.pszymanski.library.controller;

import com.pszymanski.library.dto.ReservationDTO;
import com.pszymanski.library.service.impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ReservationController {

    @Autowired
    ReservationServiceImpl reservationService;

    @RequestMapping(value = "/admin/reservations", method = RequestMethod.GET)
    public String getReservationPage(Model model) {

        model.addAttribute("reservationList", reservationService.findAll());

        return "reservation/reservations";
    }

    @RequestMapping(value = "/admin/reservations/search", method = RequestMethod.GET)
    public String searchReservations(@RequestParam(value = "value", required = false) String value, @RequestParam(value = "option", required = false) String option, Model model) {

        model.addAttribute("reservationList", reservationService.search(value, option));
        return "reservation/reservations";
    }

    @RequestMapping(value = "/book/reservation-user/{id}", method = RequestMethod.GET)
    public String saveReservationByUser(@PathVariable Integer id, Principal principal) {
        reservationService.reservationByUser(principal.getName(), id);
        return "redirect:/books?res";
    }

    @RequestMapping(value = "/admin/reservation/add/", method = RequestMethod.GET)
    public String addReservationByEmployee(Model model) {
        model.addAttribute("reservation", new ReservationDTO());
        return "reservation/addReservation";
    }

    @RequestMapping(value = "/admin/reservation/save", method = RequestMethod.POST)
    public String saveReservationByEmployee(@Valid @ModelAttribute("reservation") ReservationDTO reservation, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "reservation/addReservation";
        }
        reservationService.reservByEmployee(reservation, principal.getName());
        return "redirect:/admin/reservations?add";
    }

    @RequestMapping(value = "/admin/reservation/cancel", method = RequestMethod.GET)
    public String cancel() {

        return "redirect:/admin/reservations";
    }

    @RequestMapping(value = "/admin/reservation/edit/{id}", method = RequestMethod.GET)
    public String editReservation(@PathVariable Integer id, Model model) {

        model.addAttribute("reservation", reservationService.findOne(id));
        return "reservation/editReservation";
    }


    @RequestMapping(value = "/admin/reservation/delete/{id}", method = RequestMethod.GET)
    public String deleteReservation(@PathVariable Integer id) {

        reservationService.delete(id);
        return "redirect:/admin/reservations";
    }

}
