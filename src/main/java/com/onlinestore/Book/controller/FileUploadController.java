package com.onlinestore.Book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    // Define the path where the uploaded images will be stored inside the static directory
    private String uploadDir = "src/main/resources/static/images";

    @GetMapping("/uploadFile")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) {
        try {
            // Get the original filename and normalize it
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Ensure the upload directory exists (static/images)
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save the file inside static/images
            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath);

            // Add the image name to the model for rendering in the view
            model.addAttribute("imageName", fileName);

            // Return the view to display the uploaded image
            return "upload";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to upload image");
            return "upload";
        }
    }
}
