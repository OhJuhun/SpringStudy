package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("books")
@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("bookForm",new BookForm());
        return "books/createBookForm";
    }

    @PostMapping("/new")
    public String create(@Validated BookForm bookForm, BindingResult result){
        if(result.hasErrors()){ //Valid 동작 안한다 ->스프링으로 바꾸자
            return "books/createBookForm";
        }
        Book book = new Book();
        book.setQuantity(bookForm.getQuantity());
        book.setIsbn(bookForm.getIsbn());
        book.setName(bookForm.getName());
        //setter없이 createBook Method 생성하는것이 더 좋은 설계
        bookService.insertBook(book);

        return "redirect:/books";
    }
    @GetMapping
    public String list(Model model){
        List<Book> books = bookService.getBooks();
        List<BookForm> bookForms = new ArrayList<>();
        for(Book book : books){
            BookForm bookForm = new BookForm();
            bookForm.setId(book.getId());
            bookForm.setIsbn(book.getIsbn());
            bookForm.setName(book.getName());
            bookForm.setQuantity(book.getQuantity());
            bookForms.add(bookForm);
            System.out.println(book.getName());
        }
        model.addAttribute("bookForm",bookForms);
        return "books/bookList";
    }
}
