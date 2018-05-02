package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
** EDGE CASE - 1
** I think the edge case here was what if we sent the same message to a kingdom
** again and again, for example (Air, “OaaaWaaLa”)
** How I handle it? I just checked in starting (line 45), whether its true 
** from any other earlier messages or not, if true, no processing for that message.
**
** EDGE CASE - 2
** What if there is no ruler and we tried to show its allies?
** It will show allies only when there is a ruler, in this case KING SHAN.
*/

public class z001_GeekTrust_1 {
    static Shan shan;
 
    static class Shan{
    ArrayList<Kingdom> allies = new ArrayList<>();
    Boolean is_ruler = false;
    Boolean is_ruler_now(){
        if(allies.size()>2){is_ruler = true; return true;}
        return false;
    }
    String print_allies(){
        String all = "";
        for(Kingdom i : allies){
            all = all + i.name + ", ";
        }
        all = all.substring(0,all.length()-2);
        return all;
    }
}

    static class Kingdom{
        static int ascii[] = new int[255];
        String name ;
        String emblem;
        Boolean won;
        Kingdom(String name,String embl){
            won = false; 
            emblem = embl.toLowerCase(); 
            this.name = name.substring(0,1).toUpperCase()+name.substring(1,name.length()).toLowerCase();
        }
        Boolean check_won(String message ){
            if(this.won == true){return true;}
            for(int i = 0; i<255; i++){ascii[i]=0;}
            message = message.toLowerCase();
            for(int i = 0; i<message.length(); i++){
                this.ascii[message.charAt(i)]++;
            }
            for(int i = 0; i<this.emblem.length(); i++){
                if(this.ascii[this.emblem.charAt(i)]==0){return false;}
                else{this.ascii[this.emblem.charAt(i)]--;}
            }
            shan.allies.add(this);
            this.won = true;
            return true;
        }
    }
    
    
    static void send_message(String kingdom, String message,ArrayList<Kingdom> kingdoms ){
        for(Kingdom i : kingdoms){
            if(i.name.toLowerCase().equals(kingdom.toLowerCase().trim())){
                i.check_won(message);
                break;
            }
        }
    }
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Kingdom air = new Kingdom("air","owl");
        Kingdom ice = new Kingdom("ice","mammoth");
        Kingdom water = new Kingdom("water","octopus");
        Kingdom space = new Kingdom("space","gorilla");
        Kingdom land = new Kingdom("land","panda");
        Kingdom fire = new Kingdom("fire","dragon");
        ArrayList<Kingdom> kingdoms = new ArrayList<>();
        kingdoms.add(ice);
        kingdoms.add(water);
        kingdoms.add(space);
        kingdoms.add(land);
        kingdoms.add(air);
        kingdoms.add(fire);
        
        shan = new Shan();
        
        System.out.println("Who is the ruler of Southeros?");
        Boolean is_shan_ruler = shan.is_ruler_now();
        System.out.println((shan.is_ruler==true)?"King Shan":"None");
        System.out.println("Allies of Ruler?");
        System.out.println((shan.is_ruler==true)?shan.print_allies():"None");
        
        System.out.println("Input Messages to kingdoms from King Shan:");
        int num = Integer.parseInt(br.readLine());
        while(num-->0){
            String str = br.readLine().trim();
            String sttr[] = str.split(" ");
            String kingdom = sttr[0].substring(0,sttr[0].length()-1).toLowerCase(); 
            String message = sttr[1].substring(1,sttr[1].length()-1).toLowerCase();
            send_message(kingdom,message,kingdoms);
        }
        
        System.out.println("Who is the ruler of Southeros?");
        is_shan_ruler = shan.is_ruler_now();
        System.out.println((shan.is_ruler==true)?"King Shan":"None");
        System.out.println("Allies of Ruler?");
        System.out.println((shan.is_ruler==true)?shan.print_allies():"None");
        
    }
}
