package csv;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class ReadingCSVFile {
	public static String [] [] readFile(String path) throws IOException{
		String curr="";
		FileReader fileReader=new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		int rows=0;
		while ((curr = br.readLine()) != null) {
			rows++;
			}
		fileReader=new FileReader(path);
		br=new BufferedReader(fileReader);
		String [][] arr= new String [rows][];
		for (int i=0;i<rows;i++) {
			curr=br.readLine();
			String [] result= curr.split(",");
			arr[i]=result;
		}
		return arr;
		
	}


}
