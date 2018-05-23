package com.pszymanski.library.controller;

import com.pszymanski.library.dto.NewsletterDTO;
import com.pszymanski.library.service.impl.NotificationsServiceImpl;
import com.pszymanski.library.service.impl.StatisticServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @Autowired
    NotificationsServiceImpl notificationsService;
    @Autowired
    StatisticServiceImpl statisticService;

    @RequestMapping(value = "/")
    public String getHomeSite(Model model) {
        model.addAttribute("newsletter", new NewsletterDTO());

        return "index";
    }

    @RequestMapping(value = "/login")
    public String getLogin() {
        return "site/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logoff";
    }

    @RequestMapping(value = "/403")
    public String getAccessDenided() {
        return "site/403";
    }

    @RequestMapping(value = "/admin/statistic/")
    public String getStatisticPage(Model model) {
        model.addAttribute("staticList", statisticService.getStatistic());

        return "site/static";
    }

    @RequestMapping(value = "/contact")
    public String getContactPage() {

        return "site/contact";
    }

    @RequestMapping(value = "/admin/site")
    public String getAdminSite() {

        return "site/adminSite";
    }

}

