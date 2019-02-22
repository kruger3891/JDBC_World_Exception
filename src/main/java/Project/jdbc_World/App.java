package Project.jdbc_World;

import java.sql.SQLException;
import java.util.Scanner;

import Project.jdbc_World.data.CityDaoImpl;
import Project.jdbc_World.data.models.City;

public class App {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {

		CityDaoImpl dao = new CityDaoImpl();

		System.out.println("\n===== Welcome to the city search =====\n");
		boolean start = true;
		while (start) {
			System.out.println("Find By Id   n = 1\n\nFind By Code n = 2\n\nFind By Name n = 3\n\n"
					+ "Find All     n = 4\n\nAdd City     n = 5\n\nUpdate City  n = 6\n\nDelete City  n = 7");
			int op = sc.nextInt();

			switch (op) {
			case 1:
				System.out.print("ID: \n");
				System.out.println(dao.findById(sc.nextInt()));
				break;
			case 2:
				System.out.print("Enter country code:  \n");
				dao.findByCode(sc.next()).forEach(System.out::println);
				break;
			case 3:
				System.out.print("Enter country Name:  \n");
				dao.findByName(sc.next()).forEach(System.out::println);
				break;
			case 4:
				dao.findAll().forEach(System.out::println);
				break;
			case 5:
				System.out.println("\nCity Has been added \n\n" + dao.add(createNew()));
				break;
			case 6:
				System.out.println("\nCity has been updated\n\n" + dao.update(createNew()));
				break;
			case 7:
				System.out.print("Enter the ID you want to delete:  \n");
				City temt = dao.findById(sc.nextInt());
				System.out.println("\nCity has been Deleted\n" + temt);
				dao.delete(temt);
				break;

			default:
				break;
			}
			System.out.println("Do you want Search more [ y ]");
			String searchAgain = sc.next().toLowerCase();
			if (searchAgain.equals("y")) {
				start = true;
			} else {
				System.out.println("Program is closed");
				start = false;
			}
		}
	}

	private static City createNew() {
		// int id, String name, String countryCode, String district, int population
		int id, population;
		String name, countryCode, district;
		System.out.print("ID: ");
		id = sc.nextInt();
		System.out.print("name: ");
		name = sc.next();
		System.out.print("Country Code: ");
		countryCode = sc.next();
		System.out.print("District: ");
		district = sc.next();
		System.out.print("Population: ");
		population = sc.nextInt();
		return new City(id, name, countryCode.toUpperCase(), district, population);

	}
}
