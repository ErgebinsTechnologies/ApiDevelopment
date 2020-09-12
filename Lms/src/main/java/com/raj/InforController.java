package com.raj;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class InforController {
	
	@RequestMapping("/")
	public String register() {
		return"infor";
	}
	
	@RequestMapping("/save")
     public String save(@RequestParam("content") String content ,@RequestParam("photo") MultipartFile photo,ModelMap model) {
		 DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	       Date dateobj = new Date();
	       System.out.println(df.format(dateobj));
		Infor infor=new Infor();
		infor.setContent(content);
		
		
		if(photo.isEmpty()||content.isEmpty()) {
			return"infor";
		}
		Path path=Paths.get("uploads/");
		try {
			InputStream inputStream =photo.getInputStream();
			Files.copy(inputStream,  path.resolve(photo.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			infor.setPhoto(photo.getOriginalFilename().toLowerCase());
		model.addAttribute("INFOR", infor);
		model.addAttribute("msg",photo.getOriginalFilename());
		model.addAttribute("msg1", dateobj);
		System.out.println(photo.getOriginalFilename());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return"view-infor";
    	 
     }
	 @RequestMapping(value = "getimage/{photo}", method = RequestMethod.GET)
     @ResponseBody
public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo") String photo, HttpServletRequest request) throws IOException {
      String mimeType;
if(!photo.equals("")|| photo !=null) {
	
	

		Path fileNameAndPath = Paths.get("uploads",photo);
		byte[] buffer=Files.readAllBytes(fileNameAndPath);
		ByteArrayResource byteArrayResource=new ByteArrayResource(buffer);
		try {
		mimeType=request.getServletContext().getMimeType(byteArrayResource.getFile().getAbsolutePath());
		}catch(IOException e){
			mimeType=MediaType.APPLICATION_OCTET_STREAM_VALUE;
			
		}
		
		return ResponseEntity
                .ok()
                .contentLength(buffer.length)
                .contentType(MediaType.parseMediaType(mimeType))
                .body(byteArrayResource);

}
return ResponseEntity.badRequest().build();
	 }

}
