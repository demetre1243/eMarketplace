package org.example.service;

import org.example.entity.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdvertisementService {
    void save(Advertisement ad);
    Page<Advertisement> getAll(Pageable pageable);
    Advertisement getById(Long id);
}
