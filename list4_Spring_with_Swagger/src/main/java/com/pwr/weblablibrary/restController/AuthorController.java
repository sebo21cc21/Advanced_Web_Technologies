package com.pwr.weblablibrary.restController;

import com.pwr.weblablibrary.entity.Author;
import com.pwr.weblablibrary.exception.InvalidObjectException;
import com.pwr.weblablibrary.exception.ObjectNotFoundException;
import com.pwr.weblablibrary.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    @Autowired
    IAuthorService authorService;

    @RequestMapping(value = "/get/authors", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthors(){
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/author/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthor(@PathVariable("id") int id){
        try {
            return new ResponseEntity<>(authorService.getAuthor(id), HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/add/author/", method = RequestMethod.POST)
    public ResponseEntity<Object> addAuthor(@RequestBody Author author){
        try {
            return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.OK);
        } catch (InvalidObjectException e) {
            return new ResponseEntity<>("Invalid object", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update/author/", method = RequestMethod.PATCH)
    public ResponseEntity<Object> updateAuthor(@RequestBody Author author){
        try {
            return new ResponseEntity<>(authorService.updateAuthor(author), HttpStatus.OK);
        } catch (InvalidObjectException e) {
            return new ResponseEntity<>("Invalid object", HttpStatus.BAD_REQUEST);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/delete/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAuthor(@PathVariable int id){
        try {
            authorService.deleteAuthor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
