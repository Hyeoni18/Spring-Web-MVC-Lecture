package me.whiteship.demowebmvc;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {

    @GetMapping("/file")
    public String fileUploadForm(Model model) { //리다이렉트 된 메세지는 모델에 자동으로 담김.
        return "/files/index";
    }

    @PostMapping("/file")
    public String fileUpload(@RequestParam MultipartFile file, RedirectAttributes attributes) { //form 에서 보내는 이름이 file 이라 그대로 사용.
        //파일을 가져 왔으면 저장을 해줘야 해. 스토리지서비스 등을 사용해서.
        //예시니까 가상으로 저장.
        String message = file.getOriginalFilename()+" is uploaded";
        attributes.addFlashAttribute("message", message); //세션에 담기고 리다이렉트 된 곳에서 사용 후 사라짐.
        return "redirect:/file";
    }

}
