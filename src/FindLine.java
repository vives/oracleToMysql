import java.io.*;

/**
 * Created by vive on 1/16/17.
 */
public class FindLine {

	public static void main(String[] args) throws IOException {

		// The name of the file to open.
		String fileName = "/home/vive/export6.sql";

		// This will reference one line at a time
		String line = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		String output = "";
		String Month [] = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AGU","SEP","OCT","NOV","DEC"};

		try {
			// FileReader reads text files in the default encoding.
			fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				if (line.contains("Insert into")) {
					//System.out.println(line.toString());
					if(line.contains("to_timestamp(")){
						line = line.replace("to_timestamp(", "");
						line = line.replace(",'DD-MON-RR HH.MI.SSXFF AM')", "");
						for(int i=0;i<Month.length; i++){
							if(line.contains("-" + Month[i] + "-")){
								line = line.replace(Month[i], Integer.toString(i+1));
							}
						}

					}
					output += line + "\n";

				}
			}
			byte data[] = output.getBytes();
			FileOutputStream out = new FileOutputStream("/home/vive/Desktop/SupportDocDetails/output/output1.sql");
			out.write(data);
			System.out.println("Successfully created.");
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" +
			                   fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		} finally {
			if (fileReader != null) {
				// Always close files.
				bufferedReader.close();
			}
		}
	}
}
