package main;

import java.io.IOException;
import java.text.ParseException;

//data from TodoList go through here before GUI to handle all exception
//rename IOException into ParserIOException and so on to differentiate between each catch
public class GlobalExceptionHandler {
	private static final String ERROR_STUMP = "STUMP";

	public Object handleException(Object o){
		try {
			//todo
		}
		catch (IOException bugStorage){
			return ERROR_STUMP;
		}
		catch (ParseException bugParser){
			return ERROR_STUMP;
		}
		catch (Exception everythingelse){
			return ERROR_STUMP;
		}

		return o;

	}
}
