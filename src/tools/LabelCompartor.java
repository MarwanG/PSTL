package tools;

import java.util.Comparator;



public class LabelCompartor implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		System.out.println("o1 = " + o1 + " o2 = " + o2);
		if(o1.equals(o2)){
			return 0;
		}
		
		if(o1.length() > o2.length()){
			return 1;
		}
		
		if(o1.length() < o2.length()){
			return -1;
		}
		
		if(o1.charAt(0) == o2.charAt(0)){
			if(o1.length() > 1 && o2.length() > 1){
				System.out.println("here 1");
				return compare(o1.substring(1),o2.substring(1));
			}			
			else if(o1.length() > 1 && o2.length() == 0){
				System.out.println("here 2");
				return 1;
			}else{ 
				System.out.println("here");
				return -1;
			}
		}else{
			if(o1.charAt(0) == '0'){
				System.out.println("here 3");
				return -1;
			}else{
				System.out.println("here 4");
				return 1;
			}
		}
	}

	
}
