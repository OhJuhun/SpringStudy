package com.example.demo.controller;

import com.example.demo.dto.BookForm;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("books")
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
    public String create(@Valid BookForm bookForm, BindingResult result){
        if(result.hasErrors()){
            return "books/createBookForm";
        }
        Book book = Book.createBook(bookForm.getName(),bookForm.getIsbn(),bookForm.getQuantity());
        //setter없이 createBook Method 생성하는것이 더 좋은 설계

        bookService.insertBook(book);

        return "redirect:/books";
    }
    @GetMapping
    public String list(Model model){
        List<Book> books = bookService.getBooks();
        List<BookForm> bookForms = new ArrayList<>();
        for(Book book : books){
            bookForms.add(new BookForm(book.getId(),book.getName(),book.getIsbn(),book.getQuantity()));
        }
        model.addAttribute("bookForm",bookForms);
        return "books/bookList";
    }

    @GetMapping("/{bookId}/edit")
    public String updateBookForm(@PathVariable("bookId") Long bookId,
                                 Model model){

        Optional<Book> one = bookService.findOne(bookId);
        BookForm bookform = new BookForm(one.get().getId(),one.get().getName(),one.get().getIsbn(),one.get().getQuantity());
        model.addAttribute("bookForm",bookform);

        return "books/updateBookForm";
    }

    @PostMapping("/{bookId}/edit")
    public String updateBook(@PathVariable("bookId") Long bookId,@ModelAttribute("bookForm") BookForm bookForm ){
        Book book = Book.createBook(bookForm.getName(),bookForm.getIsbn(),bookForm.getQuantity());
        bookService.modifyBook(bookId,book);
        //값을 변경할 method를 하나 구현해 두는 것이 좋다
        return "redirect:/books";
    }
}
