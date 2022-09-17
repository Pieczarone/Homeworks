package com.isa.bookcase.controller;

import com.isa.bookcase.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookcaseController {

    private final BookService bookService;

    @Autowired
    public BookcaseController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String printBookList(Model model) {
        model.addAttribute("bookcase", bookService.getAllBooks());
        return "bookcase-view";
    }
}
