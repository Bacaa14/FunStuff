import java.sql.*;

public class JDBCPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Register the driver.
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String url = "jdbc:postgresql://hopper/cs335";

		try {
			Connection con = DriverManager.getConnection(url, "bacaa", "");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM majors");

			while (rs.next()) {
				String name = rs.getString("name");
				String dept = rs.getString("department");
				System.out.println(name + "\t" + dept);
			}

			stmt.executeUpdate("INSERT INTO majors (name, department) values ('CSCI-BA', 'CSCI')");

			String majors[] = { "PHYS-BA", "MATH-BA" };
			String depts[] = { "PHYS", "MATH" };

			stmt.close();

			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO majors (name, department) values(?,?)");

			for (int i = 0; i < majors.length; i++) {
				pstmt.setString(1, majors[i]);
				pstmt.setString(2, depts[i]);
				pstmt.executeUpdate();
			}
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
