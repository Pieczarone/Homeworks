package com.isa.bookcase.controller;

import com.isa.bookcase.dto.BookForm;
import com.isa.bookcase.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("book")
public class AddBookController {

    private final BookService bookService;

    @Autowired
    public AddBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/add")
    public String showNeedRequestForm(Model model) {
        model.addAttribute(new BookForm());
        model.addAttribute("categories", bookService.getBookCategories());
        model.addAttribute("forKids", List.of(true, false));
        return "add-book-form";
    }

    @PostMapping("/submit")
    public String submitNeedRequestForm(@Valid @ModelAttribute("bookForm") BookForm bookForm, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("categories", bookService.getBookCategories());
            model.addAttribute("forKids", List.of(true, false));
            return "add-book-form";
        } else {
            bookService.addBook(bookForm);
            return "redirect:/books";
        }
    }
}
