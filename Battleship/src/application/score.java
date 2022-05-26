package application;

import java.io.IOException;
import java.io.RandomAccessFile;

   
public class score {
	public String a[]=new String[11];
	// duong dan history.txt
	public String filePath = getClass().getResource("history.txt").toString().replace("bin", "src").substring(6) ;
	// them score va count vao history.txt
	public void addscore(int score, int count) {
		loadHistory(a);
		Integer y = new Integer(score);
		String z=y.toString();
		if(z.length()==2) z=" "+z;
		if(z.length()==1) z="  "+z;
		Integer t = new Integer(count);
		String w=t.toString();
		if(w.length()==2) w=" "+w;
		if(w.length()==1) w="  "+w;
		for(int j=9;j>=0;j--) {
					a[j+1]=a[j];
					
				}
		a[0]= "score: "+z+",count="+ w;
		storeHistory(a);
		
	}
	// them lose vao history.txt
	public void addlose() {
		loadHistory(a);
		for(int j=9;j>=0;j--) {
			a[j+1]=a[j];
			
		}
       a[0]= "           LOSE     ";
       storeHistory(a);
		//printbxh();
	}
	// reset history.txt
	public void reset() {
		for(int i=0;i<10;i++) {
			a[i]="                    ";
		}
		storeHistory(a);
		
	}
	//thay doi du lieu trong history.txt
	public void storeHistory(String a[]) {
		for(int i=0;i<10;i++) {
			
			try {
				writeData(filePath, a[i], 22*i);
			} catch (IOException e) {
		
				e.printStackTrace();
			}
		}
	}
    // lay du lieu trong history.txt
	public void loadHistory(String a[]){
		for(int i=0;i<10;i++) {
			String s;
			try {
				s = new String(readCharsFromFile(filePath, 22*i,20));
				  a[i] = s;  
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	//thay doi data o vi tri seek trong filepath
	private void writeData(String filePath, String data, int seek) throws IOException {
		RandomAccessFile file = new RandomAccessFile(filePath, "rw");
		file.seek(seek);
		file.write(data.getBytes());
		file.close();
	}
    // doc du lieu tu vi tri seek den vi tri chars trong filepath
	private byte[] readCharsFromFile(String filePath, int seek, int chars) throws IOException {
		RandomAccessFile file = new RandomAccessFile(filePath, "r");
		file.seek(seek);
		byte[] bytes = new byte[chars];
		file.read(bytes);
		file.close();
		return bytes;
	}

}