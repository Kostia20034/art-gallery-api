package com.example.art_gallery.service;

import com.example.art_gallery.dto.ArtworkRequestDTO;
import com.example.art_gallery.dto.ArtworkResponseDTO;
import com.example.art_gallery.exceptions.ArtworkNotFoundException;
import com.example.art_gallery.model.Artwork;
import com.example.art_gallery.model.Category;
import com.example.art_gallery.repository.ArtworkRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArtworkService {

    private final ArtworkRepository repo;

    public ArtworkService(ArtworkRepository repo) {
        this.repo = repo;
    }

    public ArtworkResponseDTO getArtworkById(int id) {
        Artwork artwork = repo.findById(id).orElseThrow(() -> new ArtworkNotFoundException("ArtWork not found"));
        return toResponseDTO(artwork);
    }

    public Page<ArtworkResponseDTO> getAllArtworks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return repo.findAll(pageable).map(this::toResponseDTO);
    }

    public List<ArtworkResponseDTO> getFeaturedArtworks() {
        return repo.findByFeaturedTrue()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Page<ArtworkResponseDTO> getArtworksByCategory(int page, int size, Category category){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return repo.findByCategory(category,pageable).map(this::toResponseDTO);
    }

    public ArtworkResponseDTO createArtwork(ArtworkRequestDTO dto) {
        Artwork artwork = new Artwork();
        artwork.setTitle(dto.getTitle());
        artwork.setDescription(dto.getDescription());
        artwork.setImageUrl(dto.getImageUrl());
        artwork.setCategory(dto.getCategory());
        artwork.setMedium(dto.getMedium());
        artwork.setPrice(dto.getPrice());
        artwork.setFeatured(dto.isFeatured());
        artwork.setAvailable(dto.isAvailable());
        Artwork saved = repo.save(artwork);
        return toResponseDTO(saved);
    }

    public ArtworkResponseDTO updateArtwork(int id, ArtworkRequestDTO dto) {
        Artwork existing = repo.findById(id)
                .orElseThrow(() -> new ArtworkNotFoundException("Artwork not found"));
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setImageUrl(dto.getImageUrl());
        existing.setCategory(dto.getCategory());
        existing.setMedium(dto.getMedium());
        existing.setPrice(dto.getPrice());
        existing.setFeatured(dto.isFeatured());
        existing.setAvailable(dto.isAvailable());
        Artwork saved = repo.save(existing);
        return toResponseDTO(saved);
    }

    public void deleteArtwork(int id) {
        repo.findById(id)
                .orElseThrow(() -> new ArtworkNotFoundException("Artwork not found"));
        repo.deleteById(id);
    }

    private ArtworkResponseDTO toResponseDTO(Artwork artwork) {
        ArtworkResponseDTO dto = new ArtworkResponseDTO();
        dto.setId(artwork.getId());
        dto.setTitle(artwork.getTitle());
        dto.setDescription(artwork.getDescription());
        dto.setImageUrl(artwork.getImageUrl());
        dto.setCategory(artwork.getCategory());
        dto.setMedium(artwork.getMedium());
        dto.setPrice(artwork.getPrice());
        dto.setFeatured(artwork.isFeatured());
        dto.setAvailable(artwork.isAvailable());
        dto.setCreatedAt(artwork.getCreatedAt());
        return dto;
    }
}