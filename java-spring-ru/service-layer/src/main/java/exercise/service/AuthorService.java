package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private AuthorRepository authorRepository;


    public List<AuthorDTO> index() {
        var books = authorRepository.findAll();
        return  books.stream()
                .map(authorMapper::map)
                .toList();
    }


    public AuthorDTO create(@Valid @RequestBody AuthorCreateDTO authorCreateDTO) {
        var author = authorMapper.map(authorCreateDTO);
        authorRepository.save(author);
        var authorDTO = authorMapper.map(author);
        return authorDTO;
    }

    public AuthorDTO show(@PathVariable Long id) {
        var book = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not Found: " + id));
        var bookDto = authorMapper.map(book);
        return bookDto;
    }

    public AuthorDTO update(@RequestBody @Valid AuthorUpdateDTO authorUpdateDTO, @PathVariable Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not Found: " + id));

        authorMapper.update(authorUpdateDTO, author);
        authorRepository.save(author);
        var bookDto = authorMapper.map(author);
        return bookDto;
    }

    public void destroy(@PathVariable Long id) {
        authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ResourceNotFoundException"));
        authorRepository.deleteById(id);

    }
    // END
}
