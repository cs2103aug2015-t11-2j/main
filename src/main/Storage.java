package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Storage class. Contains save, saveAs, load, load from different dir. Also
 * generate a temp file saved in temp folder only for undo cmd
 *
 *
 */
public class Storage {
	// edit be4 use, these 2 are for default setting
	private static final Path DEFAULT_MAIN_DIRECTORY = Paths
			.get("C:\\Users\\Le Nguyen\\Desktop\\cs2103\\main\\testfolder\\main");
	private static final Path DEFAULT_TEMP_DIRECTORY = Paths
			.get("C:\\Users\\Le Nguyen\\Desktop\\cs2103\\main\\testfolder\\temp");

	public static Path mainDir;
	public static Path tempDir;

	// constructor
	public Storage(String fileName) throws IOException {
		mainDir = Paths.get(DEFAULT_MAIN_DIRECTORY + File.separator + fileName);
		tempDir = Paths.get(DEFAULT_TEMP_DIRECTORY + File.separator + fileName);
		createFile(mainDir);
		createFile(tempDir);
	}

	public Storage(String fileName, Path folderDir) throws IOException {
		mainDir = Paths.get(folderDir.toString() + File.separator + fileName);
		tempDir = Paths.get(DEFAULT_TEMP_DIRECTORY + File.separator + fileName);
		createFile(mainDir);
		createFile(tempDir);
	}

	// public method, no static one so need to use constructor be4hand
	public static void save(ArrayList<String> arr) throws IOException {
		backup();
		writeIntoFile(arr);
	}

	// switch mainDir to the new one
	public void saveAs(ArrayList<String> arr, Path dir) throws IOException {
		backup();
		mainDir = dir;
		writeIntoFile(arr);
	}

	public ArrayList<String> load() throws IOException {
		return readFile(mainDir);
	}

	public ArrayList<String> loadAs(Path dir) throws IOException {
		mainDir = dir;
		return readFile(mainDir);
	}

	// private method

	private boolean createFile(Path dir) throws IOException {

		File newFile = new File(dir.toString());
		newFile.getParentFile().mkdirs();
		return newFile.createNewFile();
	}

	private static void backup() throws IOException {
		Files.copy(mainDir, tempDir, StandardCopyOption.REPLACE_EXISTING);
	}

	private static ArrayList<String> readFile(Path dir) throws IOException {
		List<String> preCopy = Files.readAllLines(dir);
		ArrayList<String> copy = new ArrayList<String>();
		for (String line : preCopy) {
			copy.add(line);
		}
		return copy;
	}

	private static void writeIntoFile(ArrayList<String> arr) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(mainDir.toString()));
		for (String line : arr) {
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	}

}
