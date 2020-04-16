package com.yil3.controller;

import com.yil3.pojo.Books;
import com.yil3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook")
    public String listBook(Model model){
        List<Books> books = bookService.queryBookAll();
        model.addAttribute("books",books);
//        System.out.println(books);
        return "allBook";
    }

    @RequestMapping("/toAddBook")
    public String toAddBook(){
        return "addBook";
    }

    @RequestMapping("/addBook")
    public String addBook(Books book){
        bookService.addBook(book);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpDate(@PathVariable int id, Model model){
        Books book = bookService.queryBookById(id);
        model.addAttribute("Qbook",book);
        return "updateBook";
    }

    @RequestMapping("/update")
    public String updateBook(Books book){
        bookService.updateBook(book);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(int id){
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }
}
