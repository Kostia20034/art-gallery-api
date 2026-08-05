package com.example.art_gallery.controller;

import com.example.art_gallery.dto.ContactRequestDTO;
import com.example.art_gallery.dto.ContactResponseDTO;
import com.example.art_gallery.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> submitContact(@RequestBody ContactRequestDTO payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.submitContact(payload));
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> getContactInfo() {
        return ResponseEntity.ok(service.getContactInfo());
    }
}
