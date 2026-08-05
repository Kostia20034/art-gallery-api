package com.example.art_gallery.service;

import com.example.art_gallery.dto.ContactRequestDTO;
import com.example.art_gallery.dto.ContactResponseDTO;
import com.example.art_gallery.model.Contact;
import com.example.art_gallery.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactResponseDTO submitContact(ContactRequestDTO contactRequestDTO) {
        Contact contact = new Contact();
        contact.setName(contactRequestDTO.getName());
        contact.setEmail(contactRequestDTO.getEmail());
        contact.setMessage(contactRequestDTO.getMessage());

        Contact savedContact = contactRepository.save(contact);
        return toResponseDTO(savedContact);
    }

    public List<ContactResponseDTO> getContactInfo() {
        return contactRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private ContactResponseDTO toResponseDTO(Contact contact) {
        ContactResponseDTO responseDTO = new ContactResponseDTO();
        responseDTO.setName(contact.getName());
        responseDTO.setEmail(contact.getEmail());
        responseDTO.setMessage(contact.getMessage());
        responseDTO.setCreatedAt(contact.getCreatedAt());
        return responseDTO;
    }
}