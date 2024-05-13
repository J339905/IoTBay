// package uts.unit;

// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.sql.Connection;
// import java.sql.SQLException;
// import java.util.ArrayList;

// import org.junit.jupiter.api.Test;

// import uts.isd.model.Staff;
// import uts.isd.model.User;
// import uts.isd.model.dao.DBConnector;
// import uts.isd.model.dao.UserDAO;
// import uts.isd.model.dao.StaffDAO;
// import uts.isd.model.dao.ProductDAO;


// public class DAOTest {


// 	private DBConnector connector;
// 	private Connection conn;
// 	private UserDAO userDAO;
// 	private StaffDAO StaffDAO;
// 	// private ProductDAO ProductDAO;

// 	public DAOTest() throws ClassNotFoundException, SQLException {
// 		connector = new DBConnector();
// 		conn = connector.openConnection();
// 		userDAO = new UserDAO(conn);
// 		StaffDAO = new StaffDAO(conn);
// 		// ProductDAO = new ProductDAO(conn);
// 	}

// 	@Test
// 	public void testConnection() throws SQLException {
// 		assertNotNull(conn);
// 	}
// 	@Test
// 	public void testSelectAllTables() throws SQLException {
// 		ArrayList<User> users = userDAO.fetchUsers();
// 		ArrayList<Staff> staff = StaffDAO.fetchStaff();
// 		// ArrayList<Staff> products = products.fetchStaff();
// 		assertTrue(users.size()>0);
// 		assertTrue(staff.size()>0);
// 	}
// 	@Test
// 	public void testcreateuser() throws SQLException{
// // Correct usage with an integer phone number
// 	userDAO.createUser("John", "Doe", "john.doe@example.com", 1234567890, "password123", "Male", "Customer");

// 	}
// }