package com.raj.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileUploadController {
	public static String uploadDirectory="C://Raj//";
	@RequestMapping("/")
	public String UploadPage(Model model) {
		return "uploadview";
		
	}
	
	
	@RequestMapping("/video")
	public String Uploadvideo(Model model) {
		return "video";
		
	}
	 @RequestMapping(value = "getimage/{fileName}", method = RequestMethod.GET)
	           @ResponseBody
	    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("fileName") String fileName) throws IOException {

	      if(!fileName.equals("")|| fileName!=null) {
	    	  try {

	  			Path fileNameAndPath = Paths.get(uploadDirectory,fileName);
	  			byte[] buffer=Files.readAllBytes(fileNameAndPath);
	  			ByteArrayResource byteArrayResource=new ByteArrayResource(buffer);
	  			return ResponseEntity
	  	                .ok()
	  	                .contentLength(buffer.length)
	  	                .contentType(MediaType.parseMediaType("image/png"))
	  	                .body(byteArrayResource);
	    	  }catch(Exception e) {
	    		  
	    	  }
	      }
	      return ResponseEntity.badRequest().build();
	 }

	 
	    
	
	@RequestMapping("/show")
	public String showFiles(Model model) {
		File folder=new File(uploadDirectory);
		File[] listOfFiles=folder.listFiles();
		
		model.addAttribute("files", listOfFiles);
		return "showfiles";
		
		
	}
	@RequestMapping("/file/{fileName}")
	@ResponseBody
	public void show(@PathVariable("fileName") String fileName,HttpServletResponse response) {
		if(fileName.indexOf(".csv")>-1) response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition","attachment; filename="+fileName);
		response.setHeader("Content-Transfer-Encoding","binary");
		try {
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			FileInputStream fis=new FileInputStream(uploadDirectory+fileName);
			int len;
			byte [] buf=new byte[1024];
			while((len=fis.read(buf))>0) {
				bos.write(buf,0,len);
			}
			bos.close();
			response.flushBuffer();
		}		
		 catch (IOException e) {
				e.printStackTrace();
			}
	}
	@RequestMapping("/upload")
	public String upload(Model model,@RequestParam("files") MultipartFile[] files) {
		 Date date = new Date();
		    String strDateFormat = "hh:mm:ss a";
		    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		    String formattedDate= dateFormat.format(date);
		    System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
             if(files.length>7) {
	          throw new RuntimeException("Too many files");
             }
		 List<String> fileNames = new ArrayList<>();
		
		for(MultipartFile file:files) {
			Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename());
			 fileNames.add(file.getOriginalFilename());
			try {
			Files.write(fileNameAndPath, file.getBytes()); 
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
		
	}
		model.addAttribute("msg","Successfully uploaded file "+fileNames.toString());
		return"uploadstatusview";

	}
}
