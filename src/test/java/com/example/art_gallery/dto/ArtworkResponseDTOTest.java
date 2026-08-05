package com.example.art_gallery.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ArtworkResponseDTOTest {

    @Test
    void responseDtoShouldNotExposeStoreLikeFields() throws Exception {
        ArtworkResponseDTO dto = new ArtworkResponseDTO();
        dto.setTitle("Sunset Study");
        dto.setDescription("A calm landscape piece");
        dto.setImageUrl("https://example.com/sunset.jpg");

        String json = new ObjectMapper().writeValueAsString(dto);

        assertFalse(json.contains("medium"));
        assertFalse(json.contains("price"));
        assertFalse(json.contains("available"));
    }
}
