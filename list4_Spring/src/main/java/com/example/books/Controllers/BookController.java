package com.example.books.Controllers;

import com.example.books.Entities.Book;
import com.example.books.Exceptions.InvalidObjectException;
import com.example.books.Exceptions.ObjectNotFoundException;
import com.example.books.Services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    IBookService bookService;
    @RequestMapping(value = "/get/books", method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/book/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable("id") int id){
        try {
            return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/add/book/", method = RequestMethod.POST)
    public ResponseEntity<Object> addBook(@RequestBody Book book){
        try {
            return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);
        } catch (InvalidObjectException e) {
            return new ResponseEntity<>("Invalid object", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update/book/", method = RequestMethod.PATCH)
    public ResponseEntity<Object> updateBook(@RequestBody Book book){
        try {
            return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);
        } catch (InvalidObjectException e) {
            return new ResponseEntity<>("Invalid object", HttpStatus.BAD_REQUEST);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/delete/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable int id){
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}