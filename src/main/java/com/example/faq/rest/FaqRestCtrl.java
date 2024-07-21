package com.example.faq.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.faq.entity.Faq;
	

@RestController
@RequestMapping(value = "/faq-api", headers="Accept=application/json")
public class FaqRestCtrl {
	
	@GetMapping("")
	public ArrayList<Faq> getThemes() {
		return getAllThemes();
	}
	
	public ArrayList<Faq> getAllThemes() {
		ArrayList<Faq> faqs = new ArrayList<Faq>();
		String directoryPath = ".";
		
		String spath = System.getProperty("os.name").indexOf("Windows") != -1 ? ".\\FAQ_" : "./FAQ_";

        try {
            try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
                paths
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                    	String pathString = path.toString();
                    	if(pathString.startsWith(spath)) {
                    		String theme = pathString.substring(pathString.indexOf("FAQ_")+4);
                    		theme = theme.substring(0, theme.length()-4);
                    		ArrayList<String> questions = new ArrayList<String>();
                    		ArrayList<String> reponses = new ArrayList<String>();
                    		try {
                    			File file = new File(path.toString());
								Scanner scanner = new Scanner(file);
								
								while(scanner.hasNextLine()) {
									String line = scanner.nextLine();
									if (line.endsWith("?")) {
										questions.add(line);
										String reponse = "";
										while(scanner.hasNextLine()) {
											String noteLine = scanner.nextLine();
											if(noteLine!="") {
												reponse+= noteLine + "\n";												
											} else {
												break;
											}
										}
										reponses.add(reponse);
									}
								}
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
                    		faqs.add(new Faq(theme, questions, reponses));
                    	}
                    });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return faqs;
	}
	
	@GetMapping("/help")
	public String help() {
		return getHelp();
	}
	
	public String getHelp() {
		File file = new File(".");
		return file.getAbsolutePath();
	}
}