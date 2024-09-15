package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO  contactData) {
        var contact = toEntity(contactData);
        contactRepository.save(contact);
        var contactDTO = toDTO(contact);
        return contactDTO;
    }

    private ContactDTO toDTO(Contact contact) {
        var dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setCreatedAt(contact.getCreatedAt());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setUpdatedAt(contact.getUpdatedAt());
        dto.setPhone(contact.getPhone());
        return dto;
    }

    private Contact toEntity(ContactCreateDTO contactDto) {
        var contact = new Contact();
        contact.setFirstName(contactDto.getFirstName());
        contact.setPhone(contactDto.getPhone());
        contact.setLastName(contactDto.getLastName());
        return contact;
    }
    // END
}
