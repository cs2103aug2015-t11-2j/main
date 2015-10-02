package main;

import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogicStump {

	private static Scanner sc = new Scanner(System.in);
	private static String input = "blah";
	private static ArrayList<String> arr = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		// initialize
		String fileName = sc.nextLine();
		Path folderToTest = Paths.get("C:\\Users\\Le Nguyen\\Desktop\\cs2103\\main\\testfolder\\main");
		Path fileToTest = Paths.get(folderToTest.toString() + File.separator + fileName);

		// testing
		Storage storage = new Storage(fileName, folderToTest);// folder with main file

		System.out.println(storage.mainDir);

		arr = storage.load(storage.mainDir);
		// use storage.load(Path dir) to import or load temp file
		// careful not to use wrong dir

		System.out.println(storage.load(storage.mainDir));

		while (!input.isEmpty()) {
			input = sc.nextLine();
			arr.add(input);
			System.out.println("Next input: ");
		}

		System.out.println("Finish input");

		storage.save(arr);
		// or
		// storage.saveAs(arr, fileToTest) to export file

		System.out.println(arr);
		System.out.println(storage.load());
	}

}
