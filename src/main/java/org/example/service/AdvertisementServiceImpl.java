package org.example.service;

import org.example.entity.Advertisement;
import org.example.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository repository;

    public void save(Advertisement ad) {
        repository.save(ad);
    }

    public Page<Advertisement> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Advertisement getById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
