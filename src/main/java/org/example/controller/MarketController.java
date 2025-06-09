package org.example.controller;

import org.example.entity.Advertisement;
import org.example.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping
    public String getAllAdvertisements(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Advertisement> ads = advertisementService.getAll(PageRequest.of(page, 6));
        model.addAttribute("ads", ads.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ads.getTotalPages());
        return "index";
    }

    @GetMapping("/item/{id}")
    public String getAdvertisementById(@PathVariable Long id, Model model) {
        Advertisement ad = advertisementService.getById(id);
        model.addAttribute("ad", ad);
        return "item";
    }

    @GetMapping("/new")
    public String newAdvertisementForm() {
        return "new-item";
    }

    @PostMapping("/new")
    public String createAdvertisement(@RequestParam String name,
                                      @RequestParam double price,
                                      @RequestParam String description,
                                      @RequestParam("photo") MultipartFile file) throws IOException {
        String photoUrl = null;

        if (!file.isEmpty()) {
            String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
            Path path = Paths.get("uploads/" + filename);
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            photoUrl = "/uploads/" + filename;
        }

        Advertisement ad = new Advertisement();
        ad.setName(name);
        ad.setPrice(price);
        ad.setDescription(description);
        ad.setSubmittionTime(LocalDateTime.now());
        ad.setPhotoUrl(photoUrl);

        advertisementService.save(ad);
        return "redirect:/market";
    }
}
