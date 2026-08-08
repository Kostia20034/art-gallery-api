package com.example.art_gallery.controller;

import com.example.art_gallery.controller.S3Controller;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class S3ControllerTest {

    @Test
    void uploadImageReturnsPublicUrlAndCallsS3() throws Exception {
        S3Client s3Client = mock(S3Client.class);
        when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class)))
                .thenReturn(PutObjectResponse.builder().build());

        S3Controller controller = new S3Controller(s3Client);
        ReflectionTestUtils.setField(controller, "bucketName", "test-bucket");
        ReflectionTestUtils.setField(controller, "region", "us-east-2");

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "sunset.png",
                "image/png",
                "fake-image-bytes".getBytes()
        );

        mockMvc.perform(multipart("/api/v1/images/upload").file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url", startsWith("https://test-bucket.s3.us-east-2.amazonaws.com/")));

        verify(s3Client).putObject(any(PutObjectRequest.class), any(RequestBody.class));
    }
}
