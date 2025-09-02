package com.uade.tpo.zapatillasPumba.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProductImageService {

    void uploadImage(Long productId, MultipartFile file);
    byte[] getImage(Long productId);
    void deleteImage(Long productId);

}
