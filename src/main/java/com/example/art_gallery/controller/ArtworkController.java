package com.example.art_gallery.controller;

import com.example.art_gallery.dto.ArtworkRequestDTO;
import com.example.art_gallery.dto.ArtworkResponseDTO;
import com.example.art_gallery.model.Category;
import org.springframework.data.domain.Page;
import com.example.art_gallery.service.ArtworkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artworks")
@CrossOrigin(origins = "*")
public class ArtworkController {

    private final ArtworkService service;

    public ArtworkController(ArtworkService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtworkResponseDTO> getArtwork(@PathVariable int id) {
        return ResponseEntity.ok(service.getArtworkById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ArtworkResponseDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
            return ResponseEntity.ok(service.getAllArtworks(page,size));

    }
    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ArtworkResponseDTO>> getCategoryArtworks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @PathVariable Category category){
        return ResponseEntity.ok(service.getArtworksByCategory(page,size,category));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<ArtworkResponseDTO>> getFeaturedArtwork(){
        return ResponseEntity.ok(service.getFeaturedArtworks());
    }

    @PostMapping
    public ResponseEntity<ArtworkResponseDTO> createNewProduct(@Valid @RequestBody ArtworkRequestDTO p) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createArtwork(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtworkResponseDTO> updateProduct(@PathVariable int id, @Valid @RequestBody ArtworkRequestDTO p) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateArtwork(id,p));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable int id) {
        service.deleteArtwork(id);
        return ResponseEntity.noContent().build();
    }
}
