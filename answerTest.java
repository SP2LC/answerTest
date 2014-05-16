
import java.util.StringTokenizer;
import java.io.*;

public class answerTest {

static String[][] csv_array = new String[16][16];
static int choice;
static int Exchange;
static int NowExchange;
static char choice_node_x;
static char choice_node_y;
static int choice_cost;
static int Exchange_cost;
static int x_length;
static int y_length;
static int choice_x;
static int choice_y;
static String[] sinji_go = new String[16];
static String[][] problem = new String[16][16];


    public static void main(String[] args) {
    	
    	
    	csv_Read(args[0]);
    	csv_print();
    	make_array();
    	System.out.println("");
    	csv_print2();
    	file_read(args[1]);
    	csv_print2();
    	Comparison();
    	System .out.println("選択コスト" + Exchange_cost *Exchange);
    	System .out.println("交換コスト" + choice_cost * choice);
    	csv_print();
    	System.out.println("");
    	csv_print2();
    }

    public static void csv_Read(String csv){
    int x = 0,y = 0;
    String d;
    
    try {
    		
            FileReader fr = new FileReader(csv);
            BufferedReader br = new BufferedReader(fr);

            String line;
            StringTokenizer token;

            int num = 0;
            line = br.readLine();
            token = new StringTokenizer(line, ",");
            while(token.hasMoreTokens()){
            	if(num == 0){
            		x_length = Integer.parseInt(token.nextToken());
            	}else if(num == 1){
            		y_length = Integer.parseInt(token.nextToken());
            	}else if(num == 2){
            		choice_cost = Integer.parseInt(token.nextToken());
            	}else if(num == 3){
            		Exchange_cost = Integer.parseInt(token.nextToken());
            	}
            	num += 1;
            }
            

            while ((line = br.readLine()) != null) {

                token = new StringTokenizer(line, ",");
                while (token.hasMoreTokens()) {
                d = token.nextToken();

                    csv_array[x][y] = d;
                    y +=1 ;
                }
                y = 0;
                x += 1;

            }


            br.close();
        } catch (IOException ex) {

            ex.printStackTrace();

        }
    }

    public static void file_read(String txt){
    String line;
    String tmp;
    int num = 0;
    char Direction;
    try{
    	
      FileReader file = new FileReader(txt);
      BufferedReader br  = new BufferedReader(file);
      
      

      while((line = br.readLine())!= null){
    	  if(num == 0){
    		  tmp = line;
    		  choice = Integer.parseInt(tmp);
    		  System.out.println(choice);
    	  }else if(num == 1){
    		  choice_node_x = line.charAt(0);
    		  choice_node_y = line.charAt(1);
		  choice_x =  Integer.parseInt("" + choice_node_x);
		  choice_y =  Integer.parseInt("" + choice_node_y);
    	  }else if(num == 2){
    		  tmp = line;
    		  Exchange += Integer.parseInt(tmp); 
    		  NowExchange = Integer.parseInt(tmp);
    	  }else if(num == 3){
    		  for(int i = 0;i<NowExchange;i++){
    			  Direction = line.charAt(i);
    			  
    			  move(Direction);
    			  csv_print2();
    		  }
    		  num = 0;
    	  }
    	 num+=1;
    	 }
      br.close();
    } catch (IOException ex) {

            ex.printStackTrace();

        }
    }


    public static void csv_print(){
	    for(int x = 0;x < x_length;x++){
		    for(int y = 0;y < y_length;y++){
		    		System.out.print(csv_array[x][y] + ",");
			    }
		    System.out.println();
	    }
    }
    public static void csv_print2(){
	    for(int x = 0;x < x_length;x++){
		    for(int y = 0;y < y_length;y++){
		    		System.out.print(problem[x][y] + ",");
			    }
		    System.out.println();
	    }
    }
    public static void move(char m){
    	System.out.println(m);
    	String str = String.valueOf(m);
    	String tmp;
    	if(str .equals ("U")){
    	tmp =	problem[choice_y][choice_x];
    	System.out.println(choice_y+""+choice_x);
    	problem[choice_y][choice_x] = problem[choice_y-1][choice_x];
    	problem[choice_y-1][choice_x] = tmp;
    	
    	choice_y -= 1;
    	}else if(str .equals ("D")){
    		tmp =	problem[choice_y][choice_x];
        	problem[choice_y][choice_x] = problem[choice_y+1][choice_x];
        	problem[choice_y+1][choice_x] = tmp;
        	choice_y += 1;
    	}else if(str .equals ("R")){
    		tmp =	problem[choice_y][choice_x];
        	problem[choice_y][choice_x] = problem[choice_y][choice_x+1];
        	problem[choice_y][choice_x+1] = tmp;
        	choice_x += 1;
    	}else if(str .equals ("L")){
    		tmp =	problem[choice_y][choice_x];
        	problem[choice_y][choice_x] = problem[choice_y][choice_x-1];
        	problem[choice_y][choice_x-1] = tmp;
        	choice_x -= 1;
    	}
    }
    public static void make_array(){
    	String[] sinji_go = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    	
    	for(int i = 0 ; i < y_length;i++){
    		for(int f = 0; f < x_length;f++){
    			problem[i][f] = sinji_go[f] +""+ sinji_go[i];
    		}   		
    	}
    }
    public static void Comparison(){
    	int a = 0;
    	for(int i = 0; i < x_length;i++){
    		for(int f = 0; f < y_length;f++){
    			if(csv_array[i][f] .equals(problem[i][f]) ){
    			
    			}else{
    				a += 1;
    			}
    		}
    	}
    	System .out.println("不一致画像数" + a);
    	
    	
    }
}