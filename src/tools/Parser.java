package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import struct.Composant;

public class Parser {

	

	
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
	}
	
	
	private static void parseLine(String l){
		String obj;
		if(l.contains("::=")){
			obj = l.substring(0, l.indexOf("::=")).replace(" ", "");
			Config.labels.add(obj);
			Config.hash.put(obj, new ArrayList<Composant>());
			l = l.substring(l.indexOf("::=")+4);
			String[] split = l.split("\\+");
			for(int i = 0 ; i < split.length ; i++){
				if(split[i].contains("SEQ")){
					seqTreatment(split[i],obj);
				}else{
					createComposant(split[i],obj);
				}
			}
		}
	}

	private static int count = 0 ;
	
	private static void seqTreatment(String s,String obj){
		String [] split = s.split("\\*");
		String newComp = "";
		HashMap<String,String> hash = new HashMap<String,String>(); 
		for(int i = 0 ; i < split.length ; i++){
			if(split[i].contains("SEQ")){
				hash.put("L"+count, split[i]);
				split[i] = "L"+count;
				count++;
			}
			newComp+= split[i] + " *";
		}
		newComp = newComp.substring(0, newComp.length()-1);
		createComposant(newComp,obj);
		for(String e : hash.keySet()){
			String res = hash.get(e);
			res = res.replaceFirst("\\(", "").replaceFirst("SEQ", "").replace(" ", "");
			res = res.substring(0,res.length()-1);
			String newRule = e + "::= " + res + " * <-1> + " + res +" * "+e +" * <-1>;";
			System.out.println(newRule);
			parseLine(newRule);
		}
	}
	
	
	private static void createComposant(String s, String obj) {
		String[] split = s.split("\\*");
		int nb = 0;
		int w = 0;
		ArrayList<String> labels = new ArrayList<String>();
		for(int i = 0 ; i < split.length ; i++){
			if(split[i].contains("<")){
				w = Integer.parseInt(split[i].replace("<", "").replace(">", "").replace(";", "").replace(" ", ""));
			}else{
				labels.add(split[i].replace(" ", "").replace(";",""));
			}
		}
		Composant c = new Composant(w,nb,labels);
		Config.list.add(c);
		Config.hash.get(obj).add(c);
	}
	
	
	
}
