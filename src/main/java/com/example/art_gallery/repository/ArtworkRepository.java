package com.example.art_gallery.repository;

import com.example.art_gallery.model.Artwork;
import com.example.art_gallery.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Integer> {
    Page<Artwork> findByCategory(Category category, Pageable pageable);
    List<Artwork> findByFeaturedTrue();
}
