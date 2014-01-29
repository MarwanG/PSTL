package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import struct.Composant;

public class Parser {

	
	public static void readFile(String file){
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(file));
			while ((sCurrentLine = br.readLine()) != null) {
				if(!sCurrentLine.startsWith("//") && !sCurrentLine.isEmpty()){
					parseLine(sCurrentLine);
				}else{			
				}
			}	
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	private static void parseLine(String l){
		String obj;
		if(l.contains("::=")){
			obj = l.substring(0, l.indexOf("::="));
			l = l.substring(l.indexOf("::=")+4);
			String[] split = l.split("\\+");
			for(int i = 0 ; i < split.length ; i++){
				createComposant(split[i],obj);
			}				
		}else{
			System.out.println("not yet");
		}
	}


	private static void createComposant(String s, String obj) {
		String[] split = s.split("\\*");
		int nb = 0;
		int w = 0;
		for(int i = 0 ; i < split.length ; i++){
			if(split[i].contains(obj)){
				nb++;
			}else if(split[i].contains("<")){
				w = Integer.parseInt(split[i].charAt(2)+"");
			}
		}
		Config.list.add(new Composant(nb,w));
		
	}
	
	
	
}
