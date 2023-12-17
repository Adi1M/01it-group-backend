package com.itgroup.controllers;

import com.itgroup.models.User;
import com.itgroup.repositories.UserRepository;
import com.itgroup.service.AWSS3Service;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private final AWSS3Service awsS3Service;
    private final UserRepository userRepository;

    @PostMapping("/{userId}/uploadImage")
    public ResponseEntity<String> uploadImageToS3(@PathVariable Long userId,
                                                  @RequestParam("image") MultipartFile file) {

        String imageUrl = awsS3Service.uploadImageAndGetUrl(file);
        if (!imageUrl.isEmpty()) {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                user.setAvatar(imageUrl);
                userRepository.save(user);
                return ResponseEntity.ok().body(imageUrl);
            }
        }
        return ResponseEntity.badRequest().body("Unable to upload image");
    }

    @GetMapping("/{userId}/downloadImage")
    public ResponseEntity<String> downloadImageFromS3(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && user.getAvatar() != null) {
            return ResponseEntity.ok(user.getAvatar());
        }
        return ResponseEntity.notFound().build();
    }
}