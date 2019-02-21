package Project.jdbc_World;

import java.sql.SQLException;
import java.util.Scanner;

import Project.jdbc_World.data.CityDaoImpl;
import Project.jdbc_World.data.models.City;

public class App 
{
    public static void main( String[] args ) throws SQLException{
    	Scanner sc = new Scanner(System.in);

    	CityDaoImpl dao = new CityDaoImpl();
    	
    	City city = new City(1,"NohChichö","afg","Kabol",1780000);
    	City city1 = new City(4083, "Groznij","afg","Kurchl",205439);
    	while(true) {
    	System.out.println("Find By Id n = 1\n\nFind By Code n = 2\n\nFind By Name n = 3\n\n"
    			+ "Find All n = 4\n\nAdd City n = 5\n\nUpdate City n = 6\n\nDelete City n = 7");
    	int op = sc.nextInt();
    	
    		switch(op) {
            case 1:
            	System.out.println(dao.findById(34));
            	break;
            case 2:
            	dao.findByCode("%BRA%").forEach(System.out::println);
            	break;
            case 3:
            	dao.findByName("%Ti%").forEach(System.out::println);
            	break;
            case 4:
            	dao.findAll().forEach(System.out::println);
            	break;
            case 5:
            	System.out.println(dao.add(city1));
            	break;
            case 6:
            	System.out.println(dao.update(city));
            	break;
            case 7:
            	System.out.println(dao.delete(city1));
            	break;
            
            default: break;          
            }
    	 }

    	
    }
}
