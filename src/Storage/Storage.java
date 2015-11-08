//@@author A0127821J


package Storage;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Tasks.Event;

/**
 * 
 * Storage class. Contains save, saveAs, load, load from different dir. Also
 * generate a temp file saved in temp folder only for undo cmd
 * 
 *
 */
public class Storage {
	// edit be4 use, these 2 are for default setting
	private static Path DEFAULT_MAIN_DIRECTORY = Paths.get("user.dir");
	// .get("C:\\Users\\Le Nguyen\\Desktop\\cs2103\\main\\testfolder\\main");
	private static Path DEFAULT_TEMP_DIRECTORY = Paths.get("temp.dir");
	private static final Path CONFIG_DIRECTPRY = Paths.get("config");

	// public non-static, so you can get it using s.mainDir (for Storage s)
	public Path mainDir;
	public Path tempDir;
	private final Path CONFIG_DIR = Paths.get(CONFIG_DIRECTPRY + File.separator + "config.Yui");;
	
	// constructor, existing file will not be overwritten
	// must use to initiate the directory use default directory
	public Storage(String fileName) throws IOException {
		mainDir = Paths.get(DEFAULT_MAIN_DIRECTORY + File.separator + fileName);
		tempDir = Paths.get(DEFAULT_TEMP_DIRECTORY + File.separator + fileName);
		createFile(mainDir);
		createFile(tempDir);
		//createFile(configDir);
	}

	// use specified folder directory, create sub-folder to store temp file
	// inside
	public Storage(String fileName, Path folderDir) throws IOException {
		mainDir = Paths.get(folderDir.toString());
		tempDir = Paths.get(DEFAULT_TEMP_DIRECTORY + File.separator + fileName);
		//configDir = Paths.get(CONFIG_DIRECTPRY + File.separator + "config.Yui");
		createFile(mainDir);
		createFile(tempDir);
		//createFile(configDir);
	}

	// public method, non-static

	// save arraylist into main directory
	public void save(ArrayList<String> arr) throws IOException {
		backup();
		writeIntoFile(arr);
	}

	public void saveE(ArrayList<Event> arr) throws IOException {
		save(Converter.eventToString(arr));
	}
	//@@author A0133992X
	public void saveConfig(ArrayList<String> config){
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(CONFIG_DIR.toString()));

			for (String line : config) {
				bw.write(line);
				bw.newLine();
			}
			bw.close();
		} catch (FileNotFoundException e) {
			System.err.println("invalid save directory, unable to create save file in that location");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//@@author A0133992X
	private void initConfig(ArrayList<String> config){
		config.add(mainDir.toString());
		config.add("1");
		saveConfig(config);
	}
	//@@author A0133992X
	public ArrayList<String> loadConfig() throws IOException{
		createFile(CONFIG_DIR);
		List<String> preCopy = Files.readAllLines(CONFIG_DIR, Charset.forName("GBK"));
		ArrayList<String> copy = new ArrayList<String>();
		for (String line : preCopy) {
			copy.add(line);
		}
		if(copy.size() == 0){
			initConfig(copy);
		}
		return copy;
	}
	
	//@@author A0127821J
	// switch main directory to the new one and save
	// to export, saveAs(new location) and then saveAs(old location)
	public void saveAs(ArrayList<String> arr, Path dir) throws IOException {
		backup();
		mainDir = dir;
		writeIntoFile(arr);
	}

	public void saveAsE(ArrayList<Event> arr, Path dir) throws IOException {
		saveAs(Converter.eventToString(arr), dir);
	}

	// load the file from the directory
	public ArrayList<String> load(Path dir) throws IOException {
		return readFile(dir);
	}

	public ArrayList<Event> loadE(Path dir) throws IOException, ParseException {
		try {
			return Converter.stringToEvent(load(dir));
		} catch (IOException | ParseException e) {
			try {
				Files.copy(tempDir, mainDir, StandardCopyOption.REPLACE_EXISTING);
				return Converter.stringToEvent(load(dir));
			} catch (IOException | ParseException f) {
				reset();
				backup();
				createFile(mainDir);
				return Converter.stringToEvent(load(dir));
			}
		}
	}

	// default load
	public ArrayList<String> load() throws IOException {
		return load(mainDir);
	}
	//ParseE wont happen, IOE when do not have permission to save/ load
	public ArrayList<Event> loadE() throws IOException, ParseException {
		return loadE(mainDir);
	}

	// load the file from the directory, change main directory here
	// to import, use loadAs and then saveAs
	public ArrayList<String> loadAs(Path dir) throws IOException {
		mainDir = dir;
		return load(mainDir);
	}

	public ArrayList<Event> loadAsE(Path dir) throws IOException, ParseException {
		mainDir = dir;
		return loadE(mainDir);
	} 
	
	// private method

	// create file, do nothing if file already exist
	private boolean createFile(Path dir) throws IOException {

		File newFile = new File(dir.toString());
		newFile.getParentFile().mkdirs();
		return newFile.createNewFile();
	}

	// read the file into arrayList
	private static ArrayList<String> readFile(Path dir) throws IOException, FileNotFoundException {
		List<String> preCopy = Files.readAllLines(dir, Charset.forName("GBK"));
		ArrayList<String> copy = new ArrayList<String>();
		for (String line : preCopy) {
			copy.add(line);
		}
		return copy;
	}

	// overwrite arrayList into the file for main directory
	private void writeIntoFile(ArrayList<String> arr) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(mainDir.toString()));

			for (String line : arr) {
				bw.write(line);
				bw.newLine();
			}
			bw.close();
		} catch (FileNotFoundException e) {
			System.err.println("invalid save directory, unable to create save file in that location");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// copy file from main directory to temp directory
	private void backup() throws IOException {
		Files.copy(mainDir, tempDir, StandardCopyOption.REPLACE_EXISTING);
	}

	public void reset() throws IOException {
		Files.deleteIfExists(mainDir);
	}
}
