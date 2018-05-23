package com.pszymanski.library.controller;

import com.pszymanski.library.dto.BookDTO;
import com.pszymanski.library.entity.BookCategory;
import com.pszymanski.library.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getBookPage(Model model) {

        model.addAttribute("bookList", bookService.findAll());

        return "book/books";
    }

    @RequestMapping(value = "/books-search", method = RequestMethod.GET)
    public String searchBook(@RequestParam(value = "value", required = false) String value, @RequestParam(value = "option", required = false) String option, Model model) {

        model.addAttribute("bookList", bookService.search(value, option));
        return "/book/books";
    }


    @RequestMapping(value = "/admin/book/add", method = RequestMethod.GET)
    public String addBook(Model model) {

        model.addAttribute("book", new BookDTO());
        model.addAttribute("bookCategory", BookCategory.values());

        return "/book/addBook";
    }


    @RequestMapping(value = "/book/add/save", method = RequestMethod.POST)
    public String saveBook(@Valid @ModelAttribute("book") BookDTO book, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("bookCategory", BookCategory.values());
            return "/book/addBook";
        }
        bookService.save(book);

        return "redirect:/books?adding";
    }

    @RequestMapping(value = "/book-add/cancel", method = RequestMethod.GET)
    public String cancel() {

        return "redirect:/books";
    }

    @RequestMapping(value = "/admin/book/edit", method = RequestMethod.GET)
    public String editBook(@RequestParam(value = "id", required = true) Integer id, Model model) {

        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("bookCategory", BookCategory.values());
        return "/book/addBook";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/admin/book/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable Integer id) {

        bookService.delete(id);
        return "redirect:/books?delete";
    }

}
