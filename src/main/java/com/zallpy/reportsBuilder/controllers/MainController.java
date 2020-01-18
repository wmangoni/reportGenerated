package com.zallpy.reportsBuilder.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	private String path = "./src/main/resources/data/in";

	@RequestMapping(value = "/files", method = RequestMethod.GET)
	@ResponseBody
	public String getFoosBySimplePath() {

		String _return = "";
		
		final File folder = new File(path);
		List<String> files = listFilesForFolder(folder);
		
		for (String name : files) {
			try {
				_return += readFile(name);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				_return = e.getMessage();
				e.printStackTrace();
			}
		}
		return _return;
	}

	private List<String> listFilesForFolder(final File folder) {

		List<String> files = new ArrayList<String>();

		if (folder != null) {
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isDirectory()) {
					files = listFilesForFolder(fileEntry);
				} else {
					System.out.println(fileEntry.getName());
					files.add(fileEntry.getName());
				}
			}
		}

		return files;
	}

	private String readFile(String fileName) throws FileNotFoundException, IOException {
		
		String _return = "";
		 
		BufferedReader br = new BufferedReader(
			new FileReader(
				new File(path + "/" + fileName)
			)
		); 

		String st;
		
		while ((st = br.readLine()) != null) {
			System.out.println(st);
			_return += st + System.lineSeparator();
		}
		
		br.close();
		
		return _return;
		
	}

}
