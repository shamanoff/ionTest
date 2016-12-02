package com.ion.controller;

import com.ion.model.FileDTO;
import com.ion.repository.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.sql.SQLException;

@Controller
public class FileUploadCtrl {
    @Autowired
    FileRepo fileRepo;

    @PostMapping("/upload")
    public View handleFileUpload(@RequestParam("file") MultipartFile file
                                   ) throws IOException, SQLException {
        FileDTO fileDTO = new FileDTO();
        byte[] bytes = file.getBytes();
        fileDTO.setFilecontent(bytes);
        fileDTO.setFilename(file.getOriginalFilename());
        fileRepo.save(fileDTO);

        RedirectView view = new RedirectView("/?success");
       return view;
    }

}
