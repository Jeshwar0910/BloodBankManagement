package BloodGroupManagementJDBC;

import java.util.*;
import java.sql.*;
import java.time.format.DateTimeParseException;

public class BloodGrp {

	public static void main(String[] args) throws Exception {
		ArrayList<Register> people = new ArrayList<Register>();
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		int choice = 0;
		// JDBC Connection
		String url = "jdbc:mysql://localhost:3306/bloodGroupDB";
		String userName = "root";
		String password = "Jeshu0910!!";

		do {
			System.out.println("!---------Blood Management---------!");
			System.out.println("1.Enter Donor Details :");
			System.out.println("2.Update Donor Details By ID :");
			System.out.println("3.Delete Donor Details By ID :");
			System.out.println("4.Find Donor's By Blood Group :");
			System.out.println("5.Find Donors's By City :");
			System.out.println("6.View All the List of Donor's : ");
			System.out.println("7.Exit");
			System.out.println("!----------------------------------!");
			choice = sc.nextInt();
			switch (choice) {

			case 1:

				int idCount = Register.idCounter();
				System.out.println("Enter Donor Name :");
				String name = sc.next();
				System.out.println("Enter Donor 10-Digit Contact Number :");
				long contact = sc.nextLong();

				System.out.println("Enter Donor State :");
				String state = sc.next();
				System.out.println("Enter Donor City :");
				String city = sc.next();
				System.out.println("Enter Donor BloodGroup :");
				String bloodGroup = sc.next();

				System.out.println("Enter Donor DOB in YYYY-MM-DD Format:");
				String dob = sc.next();

				int agecalc = Register.ageCalculation(dob);
				people.add(new Register(idCount, name, contact, dob, agecalc, state, city, bloodGroup));
				System.out.println(people);
				System.out.println();
				for (Register peoples : people) {

					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection Connection = DriverManager.getConnection(url, userName, password);
						String query = "INSERT INTO bloodGrpInfo VALUES(?,?,?,?,?,?,?,?)";
						PreparedStatement ps = Connection.prepareStatement(query);

						ps.setInt(1, idCount);
						ps.setString(2, name);
						ps.setLong(3, contact);
						ps.setString(4, dob);
						ps.setInt(5, agecalc);
						ps.setString(6, state);
						ps.setString(7, city);
						ps.setString(8, bloodGroup);

						int i = ps.executeUpdate();
						if (i > 0) {
							System.out.println("Added Successfully");
							Connection.close();
						} else {
							System.out.println("Failed");
							Connection.close();
						}

					} catch (SQLException e) {
//						e.printStackTrace();
					}

				}

				break;

			case 2:
				System.out.println("Enter ID To Update :");
				int updateId = sc1.nextInt();

				System.out.println("Enter Donor Name :");
				String updateName = sc.next();
				System.out.println("Enter Donor 10-Digit Contact Number :");
				long updateContact = sc.nextLong();

				System.out.println("Enter Donor State :");
				String updateState = sc.next();
				System.out.println("Enter Donor City :");
				String updateCity = sc.next();
				System.out.println("Enter Donor BloodGroup :");
				String updateBloodGroup = sc.next();

				System.out.println("Enter Donor DOB in YYYY-MM-DD Format:");
				String updateDob = sc.next();

				int updateAgecalc = Register.ageCalculation(updateDob);

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Connection = DriverManager.getConnection(url, userName, password);
					String query = "UPDATE bloodGrpInfo SET id=" + updateId + ",name=" + "'" + updateName + "'"
							+ ",contact=" + updateContact + ",dob=" + "'" + updateDob + "'" + ",age=" + updateAgecalc
							+ ",state=" + "'" + updateState + "'" + ",city=" + "'" + updateCity + "'" + ",bloodGroup="
							+ "'" + updateBloodGroup + "'" + "WHERE id=" + updateId;
					PreparedStatement ps = Connection.prepareStatement(query);

					int i = ps.executeUpdate();
					if (i > 0) {
						System.out.println("Updated Successfully");
						Connection.close();
					} else {
						System.out.println("Failed");
						Connection.close();
					}

				} catch (SQLException e) {
					System.out.println(e);
				}

//				people.add(new Register(updateId,updateName, updateContact, updateDob, updateAgecalc, updateState, updateCity, updateBloodGroup));

				break;

			case 3:
				System.out.println("Enter Donor ID :");
				int donorId = sc1.nextInt();
				try {

					Class.forName("com.mysql.cj.jdbc.Driver");

					Connection con = DriverManager.getConnection(url, userName, password);
					Statement statement = con.createStatement();
					String query = "DELETE FROM bloodgrpinfo WHERE id=" + donorId;
					int result = statement.executeUpdate(query);

					System.out.println("!!!!!!!!!!Record Deleted Successfully!!!!!!!!!!");

				} catch (Exception e) {
					System.out.println(e);
				}

				break;

			case 4: // Find Donor Details By Blood Group

				System.out.println("Enter Blood Group : ");
				String bloodgrp = sc1.next();
//				String[] groups = { "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-" };
//
//				for (String group : groups) {
//					if (bloodgrp == group) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");

					Connection con = DriverManager.getConnection(url, userName, password);
					Statement statement = con.createStatement();
					String query = "SELECT * FROM bloodGrpInfo WHERE bloodGroup = " + "'" + bloodgrp + "'";
					ResultSet resultSet = statement.executeQuery(query);

					int columnCount = resultSet.getMetaData().getColumnCount();

					for (int i = 1; i <= columnCount; i++) {

						System.out.print("[" + resultSet.getMetaData().getColumnName(i) + "\t" + "]");

					}
					System.out.println();
					while (resultSet.next()) {

						for (int i = 1; i <= columnCount; i++) {
							System.out.print("[" + resultSet.getString(i) + "\t" + "]");
						}
						System.out.println();
					}

				} catch (SQLException e) {
					System.out.println(e);
//						}
//
//					}

				}

				break;

			case 5:

				System.out.println("Enter City Name :");
				String Donorcity = sc1.next();
				try {

					Class.forName("com.mysql.cj.jdbc.Driver");

					Connection con = DriverManager.getConnection(url, userName, password);
					Statement statement = con.createStatement();
					String query = "SELECT * FROM bloodGrpInfo WHERE city = " + "'" + Donorcity + "'";
					ResultSet resultSet = statement.executeQuery(query);

					int columnCount = resultSet.getMetaData().getColumnCount();

					for (int i = 1; i <= columnCount; i++) {

						System.out.print("[" + resultSet.getMetaData().getColumnName(i) + "\t" + "]");

					}
					System.out.println();

					while (resultSet.next()) {
						for (int i = 1; i < columnCount; i++) {
							System.out.print("[" + resultSet.getString(i) + "\t" + "]");
						}
						System.out.println();
					}

				} catch (Exception e) {
					System.out.println(e);
				}

				break;

			case 6:// View All List Of Donors
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Connection = DriverManager.getConnection(url, userName, password);
					String query = "SELECT * FROM bloodGrpInfo";

					Statement s = Connection.createStatement();
					ResultSet resultSet = s.executeQuery(query);

					int columnCount = resultSet.getMetaData().getColumnCount();

					// Print the column names
					for (int i = 1; i <= columnCount; i++) {
						System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
					}
					System.out.println();

					// Iterate through the result set and print each row
					while (resultSet.next()) {
						for (int i = 1; i <= columnCount; i++) {
							System.out.print("[" + resultSet.getString(i) + "]");
						}
						System.out.println();
					}

//					result.next();
//
//					int id = result.getInt("id");
//					String Dname = result.getString("name");
//					String Dcontact = result.getString("contact");
//					String Ddob = result.getString("dob");
//					int Dage = result.getInt("age");
//					String Dstate = result.getString("state");
//					String Dcity = result.getString("city");
//					String Dbloodgrp = result.getString("bloodGroup");
//
//					System.out.println("List Of All Donor Details :" + "\n" + "{" +  id + ","				+ "Donor Name :" + Dname + " " + "Donor Contact :" + "Donor DOB :" + Ddob + " "
//							 + Dage + "," +  Dstate + "," + Dcity+ "," + Dbloodgrp + "}" + "\n");

					Connection.close();
				} catch (SQLException e) {
					System.out.println(e);

				}
				break;

			case 7:

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Connection = DriverManager.getConnection(url, userName, password);
					String dropquery = "DROP TABLE bloodGrpInfo";

					Statement s = Connection.createStatement();
					s.executeUpdate(dropquery);
					String createquery = "CREATE TABLE bloodGrpInfo(id int ,name varchar(200),contact bigint,dob date,age int,state varchar(200),city varchar(200),bloodGroup varchar(10),primary key(id))";
					s.executeUpdate(createquery);

					System.out.println("TABLE DROPPED");
				} catch (SQLException e) {
					System.out.println(e);
				}

				System.exit(choice);
				break;

			default:
				break;

			}

		} while (choice != 0);

	}

}
