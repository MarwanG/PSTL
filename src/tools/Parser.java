package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import struct.Composant;

public class Parser {

	
	static ArrayList<String> rules = new ArrayList<String>();
	static ArrayList<String> labels = new ArrayList<String>();
	
	static HashMap<String,String> hash = new HashMap<String,String>();
	static String main ;
	static String base;
	
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
		
		//System.out.println(rules.toString());
		//System.out.println(labels.toString());
		//System.out.println(hash.toString());
		//System.out.println(main);
		
		normalize();
		
		createComposants();
	}
	
	
	private static void parseLine(String l){
		String obj;
		if(l.contains("::=")){
			obj = l.substring(0, l.indexOf("::="));
			labels.add(obj);
			l = l.substring(l.indexOf("::=")+4);
			String[] split = l.split("\\+");
			hash.put(obj, l);
			//for(int i = 0 ; i < split.length ; i++){
				//rules.add(split[i]);
				//createComposant(split[i],obj);
			//}	
			if(main == null){
				main = l;
				base = obj;
				labels.remove(base);
			}
		}else{
			System.out.println("not yet");
		}
	}
	
	private static void createComposants(){
		for(int i = 0 ; i < rules.size() ; i++){
			String[] split = rules.get(i).split("\\*");
			int nb = 0;
			int w = 0;
			for(int j = 0 ; j < split.length ; j++){
				if(labels.contains(split[j])){
					nb++;
				}else if(split[j].contains("<")){
					w = Integer.parseInt(split[j].charAt(2)+"");
				}
			}
			Config.list.add(new Composant(w,nb));
		}
		
	}


	
	//NEED TO REALLY REPLACE PROPERLLY
	private static void normalize(){
		//String s = main;
		System.out.println(main);
		String[] split = main.split("\\+");
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0 ; i < split.length ; i++){
			String s1 = split[i];
			for(int j = 0 ; j < labels.size() ; j++){
				s1 = s1.replace(labels.get(j), hash.get(labels.get(j)));
			}
			System.out.println("s1 = " + s1);	
			
			list.add(s1);
		
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
		Config.list.add(new Composant(w,nb));
		
	}
	
	
	
}
