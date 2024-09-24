package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ConstraintViolationException;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private AuthorRepository authorRepository;


    public List<BookDTO> index() {
        var books = bookRepository.findAll();
        return  books.stream()
                .map(bookMapper::map)
                .toList();
    }


    public BookDTO create(@Valid @RequestBody BookCreateDTO bookCreateDTO) {
         authorRepository.findById(bookCreateDTO.getAuthorId())
                .orElseThrow(() -> new ConstraintViolationException("Book not Found: " + bookCreateDTO.getAuthorId()));
        var book = bookMapper.map(bookCreateDTO);
        bookRepository.save(book);
        var bookDto = bookMapper.map(book);
        return bookDto;
    }

    public BookDTO show(@PathVariable Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not Found: " + id));
        var bookDto = bookMapper.map(book);
        return bookDto;
    }

    public BookDTO update(@RequestBody @Valid BookUpdateDTO BookData, @PathVariable Long id) {
        var Book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not Found: " + id));

        bookMapper.update(BookData, Book);
        bookRepository.save(Book);
        var bookDto = bookMapper.map(Book);
        return bookDto;
    }

    public void destroy(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not Found: " + id));
        bookRepository.deleteById(id);
    }
}
