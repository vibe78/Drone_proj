/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package drone;
import java.util.Scanner;
import java.util.*;
import org.json.simple.JSONObject;
import java.sql.*;
import java.io.*;
import java.util.regex.*;
/**
 *
 * @author VIBE
 */
  /* 
   *This is a CLI project explaining Work of Drone. 
 */
public class Drone {
    int enter_data;
    int enter_drone;

    String Load_m1;
    Statement stmt; 
    HashMap<String,String> loading = new HashMap<String,String>();
    HashMap<String,String> Meication = new HashMap<String,String>();

    HashMap<String,String> Item_list = new HashMap<String,String>();
    //String []Item_list;
    public Drone(){
      Menu();


   }

   public void Menu(){
     /* 
      *This 
     */
      try{
      System.out.println("Enter 1 load a drone with medication:");
      System.out.println("Enter 2 checking loaded medication:");
      System.out.println("Enter 4 List of drone:");
      System.out.println("");
      Scanner sc= new Scanner(System.in); 
      System.out.println("___________");
      enter_data = sc.nextInt();
     //int list_picker=Integer.parseInt(enter_data);
     
        if (enter_data == 4){
         List_of_drone();
        }
         else if(enter_data == 1){
          load_medication();
        }
        else if(enter_data == 2){
         check_medication();
        }else{
        Menu();
        }
      }
     catch(Exception e){
      //System.out.println(e);
      System.out.println("Only Number can be passed Exit:");
      }

   }

   public void load_medication(){



     System.out.println();
     String q_name="Drug Name";
     String drug_code ="Drug Code";
     String weight_d = "Drug weight";
     System.out.println(q_name);
     
     System.out.println("------");
        Scanner pri= new Scanner(System.in); 
        Load_m1 = pri.nextLine();


        System.out.println(drug_code);
        String d_code = pri.nextLine();

        String regex = "^(?=.*\\d)"+ "(?=.*[-+!@#$%^&*., ?]).+$";
        Pattern p = Pattern.compile(regex);

        System.out.println(weight_d);
        String d_weight = pri.nextLine();
        int count = 0;
        int count_second = 0;
        String s = Load_m1;
        
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))
                && !Character.isLetter(s.charAt(i))
                && !Character.isWhitespace(s.charAt(i))) {
                count++;
                //System.out.println(Item);
            }
            
        }

        if (count == 0){
            System.out.println("No Special Characters found.");
                HashMap<String,String> Meication = new HashMap<String,String>();
                Meication.put(q_name,s);
                Meication.put(weight_d,d_weight);
               //if (m.matches()) {
                 //System.out.println("Drug code can only contain: uppercase letters, underscore and numbers.");
                // load_medication();
                //}else{
                 Matcher m = p.matcher(d_code);
                if (!m.matches()){
                 Meication.put(drug_code,d_code);
                 System.out.println("Yes");
                }else{
                  System.out.println("Drug code can only contain: uppercase letters, underscore and numbers ");
                 Meication.clear();
                 load_medication();
                
                }        

                for(Map.Entry<String,String>set : Meication.entrySet()){
                    System.out.println(set.getKey() +" = " + set.getValue());
                    
                 }
                 System.out.println("Above is the drugs you wish to transport !");
                 all_drone_display();
                 System.out.println("Pick A Drone By Name And Load the Above Drug To The Drone Picked!");
                 System.out.print("------------------");
                  
                 String drugs_dron = pri.nextLine();
                 all_dr(drugs_dron,d_weight,s);

               
        }

           //Matcher m = p.matcher(d_code);
           //if (!m.matches()){
           //      System.out.println("Yes");
           //  }

       //if (!m.matches()) {
       //     System.out.println("yes");
       //     return;
        //}


        else{
            System.out.println(
                "String has Special Characters\n" + count + " "
                + "Special Characters found.\n"+"Drug name can only contain Alphabet or Numbers No Character. While Drug code can only contain: uppercase letters, underscore and numbers."+" Please Enter 1 To go back To Drug Entry :" );
            System.out.println("--------");
            Scanner back_med= new Scanner(System.in); 
            int b_med;
            b_med = back_med.nextInt();
            if(b_med == 1){
             load_medication();
            }else{
              System.out.println("Incorrect Pass :");
              Menu();
             }
            
        }
    
    }






   public void check_medication(){
   if(Item_list.equals("")){
    for(Map.Entry<String,String>set : Item_list.entrySet()){
         System.out.println("List of Item that has been Loaded"+set.getKey() +" : " + set.getValue());
                    
      }
    }else{
    //System.out.println("No Loaded Item");
     }
    }
   public void check_available_drone(){}
 







   public void List_of_drone(){
    int dron_id;
     try{
          Class.forName("com.mysql.jdbc.Driver");     
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/drone_db","root","my-secret-pw");
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT * FROM air_d");
       while(rs.next()){
         System.out.println(rs.getInt(1)+""+rs.getString(2)+""+" ======== weight limit: "+rs.getString(4)+" Gr");
            }
        System.out.println("Enter 2 to go back to the main Menu :");
        Scanner snn= new Scanner(System.in); 
        enter_drone = snn.nextInt();
         if(enter_drone == 2){
          Menu();
         }
         else{
          System.out.println("Incorrect Entry, Try Again:");
              List_of_drone();
         }
         
      
      }
    catch(Exception e){
       e.printStackTrace();
       System.out.println("Error "+e);
    }
    
   }


    public void all_dr(String x,String y,String t){
         try{
          Class.forName("com.mysql.jdbc.Driver");     
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/drone_db","root","my-secret-pw");
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT * FROM air_d");
       while(rs.next()){
            System.out.println(rs.getInt(1)+""+rs.getString(2)+""+" ======== weight limit: "+rs.getString(4)+" Gr");
            if(rs.getString(2).equals(x)){
              System.out.println(rs.getString(2)+" Drone Confirmed=> State: LOADED");
                loading.put("Drone Weight",rs.getString(4));
                loading.put("Drug Weight",y);
                int drone_Weight = Integer.parseInt(rs.getString(4));
                int drug_Weight = Integer.parseInt(y);

                if(drug_Weight>=drone_Weight){
                  System.out.println("Can't load drug in Drone weight of Drug is higher");
                  Meication.clear();
                  loading.clear();
                  load_medication();
                }
                else{

                 System.out.println("Successdful Load. ");
                 int all_wight = drug_Weight - drone_Weight;
                 System.out.println("Available Drone Weight=> "+all_wight+" Present Sate: LOADED");
                  //System.out.println("Enter 1 To Load More Drugs Enter Any Key To Deliver Close Drone For Deliver!:");
                 // Item_list
                 Item_list.put("Items ",t);
                 for(Map.Entry<String,String>set : Item_list.entrySet()){
                    System.out.println(set.getKey() +" : " + set.getValue());
                    
                 }
                   System.out.println("ITEM DELIVERED! :");
                  System.out.println();
                  System.out.println("Enter 1 To View Loaded Drugs! Or Any Key To Go Back To Menu :");
           
                 Scanner More= new Scanner(System.in); 
                 int enter_drone = More.nextInt();
                 if (enter_drone == 1){
                    for(Map.Entry<String,String>set : Item_list.entrySet()){
                    System.out.println("List of Item that has been Loaded"+set.getKey() +" : " + set.getValue());
                    
                 }
                 }else{
                 Menu();
                  System.out.println("ITEM DELIVERED! :");
                }
                 
                }
                for(Map.Entry<String,String>set : loading.entrySet()){
                 System.out.println(set.getKey() +" : " + set.getValue());
                    
                 }
                 
            }else{
             System.out.println(rs.getString(2)+"Not Confirmed=> State: IDLE:");
             //System.out.println("Drone Name is Incorrect");
             load_medication();
             }
            } 
          //System.out.println("Load The Above Drug To The Drone!");
         //System.out.print("------------------")
      }
    catch(Exception e){
       e.printStackTrace();
       System.out.println("Error "+e);
    }
    }


    public void all_drone_display(){
         try{
          Class.forName("com.mysql.jdbc.Driver");     
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/drone_db","root","my-secret-pw");
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT * FROM air_d");
       while(rs.next()){
            System.out.println(rs.getInt(1)+""+rs.getString(2)+""+" ======== weight limit: "+rs.getString(4)+" Gr");
    
            } 
          //System.out.println("Load The Above Drug To The Drone!");
         //System.out.print("------------------")
      }
    catch(Exception e){
       e.printStackTrace();
       System.out.println("Error "+e);
    }
    }




    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       Drone myobj = new Drone();
       JSONObject obj = new JSONObject();

      obj.put("name", "foo");
      obj.put("num", new Integer(100));
      obj.put("balance", new Double(1000.21));
      obj.put("is_vip", new Boolean(true));

      //System.out.print(obj);
    }

}
