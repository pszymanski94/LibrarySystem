package com.pszymanski.library.controller;

import com.pszymanski.library.dto.NewsletterDTO;
import com.pszymanski.library.service.impl.NewsletterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class NewsletterController {

    @Autowired
    NewsletterServiceImpl newsletterService;

    @RequestMapping(value = "/admin/newsletters", method = RequestMethod.GET)
    public String getNewsletterPage(Model model) {

        model.addAttribute("newsletterList", newsletterService.findAll());

        return "newsletter/newsletters";
    }

    @RequestMapping(value = "/admin/newsletter/search", method = RequestMethod.GET)
    public String searchNewsletter(@RequestParam(value = "value", required = false) String value, Model model) {

        model.addAttribute("newsletterList", newsletterService.findOne(value));
        return "/newsletter/newsletters";
    }


    @RequestMapping(value = "/newsletter/add", method = RequestMethod.POST)
    public String addToNewsletter(@Valid @ModelAttribute("newsletter") NewsletterDTO newsletterDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "index";
        }
        newsletterService.dtoToEntity(newsletterDTO);

        return "redirect:/?add";
    }

    @RequestMapping(value = "/admin/newsletter/delete/{id}", method = RequestMethod.GET)
    public String deleteNewsletter(@PathVariable Integer id) {

        newsletterService.delete(id);
        return "redirect:/admin/newsletters?delete";
    }
}
