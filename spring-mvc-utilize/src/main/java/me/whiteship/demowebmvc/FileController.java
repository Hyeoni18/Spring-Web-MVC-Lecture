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

    //파일을 읽어오는 방법
    @Autowired
    private ResourceLoader resourceLoader;

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

    @GetMapping("/file/{filename}")
    @ResponseBody //ResponseEntity 자체가 응답 본문이기에 ResponseBody 어노테이션을 주던 말던 상관없음.
    public ResponseEntity<Resource> fileDownload(@PathVariable String filename) throws IOException { //ResponseEntity 안에 응답의 본문에 대한 타입을 정의할 수 있음.
        Resource resource = resourceLoader.getResource("classpath:" + filename+".jpg"); //클래스패스 기준으로 파일 읽어오면 돼.
        File file = resource.getFile();

        Tika tika = new Tika(); //재사용이 가능하니 bean 으로 등록해도 괜찮을 거 같음.
        String mediaType = tika.detect(file);

        return ResponseEntity.ok() //상태코드 200으로 응답을 하는거고.
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachement; filename\"" + resource.getFilename() + "\"") //사용자가 이 파일을 다운로드 받을 때 팝업창이 뜨면서 어떤 이름으로 저장할건지 이름을 정해줄 수 있음.
                .header(HttpHeaders.CONTENT_TYPE, mediaType) //어떤 파일인지 알아야 해. 근데 이 타입을 알려주는 라이브러리가 있어. tika.
                .header(HttpHeaders.CONTENT_LENGTH, file.length()+"")
                .body(resource);
    }
}
