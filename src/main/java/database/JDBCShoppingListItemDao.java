package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCShoppingListItemDao implements ShoppingListItemDao {
	private final String URL = "jdbc:sqlite:C:\\tietokanta\\shoppingList.sqlite";

	@Override
	public List<ShoppingListItem> getAllItems() {
		List<ShoppingListItem> items = new ArrayList<ShoppingListItem>();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection(URL);
			String sql = "SELECT * FROM ShoppingListItem";
			PreparedStatement listAll = connection.prepareStatement(sql);
			ResultSet results = listAll.executeQuery();
			while (results.next()) {
				Long id = results.getLong("id");
				String itemName = results.getString("title");
				ShoppingListItem item = new ShoppingListItem(id, itemName);
				items.add(item);
			}
			results.close();
			listAll.close();
			connection.close();

		} catch (Exception e) {
			System.out.println("error!");
		}

		return items;
	}

	@Override
	public ShoppingListItem getItem(long id) {
		try {
			Connection connection = DriverManager.getConnection(URL);
			PreparedStatement selectItem = connection.prepareStatement("SELECT * FROM ShoppingListItem WHERE id = ?");
			selectItem.setLong(1, id);
			ResultSet results = selectItem.executeQuery();
			String itemName = results.getString("title");
			ShoppingListItem item = new ShoppingListItem(id, itemName);
			connection.close();
			selectItem.close();
			return item;
		} catch (Exception e) {
			System.out.println("Could not find item with id: " + id);
			return null;
		}

	}

	@Override
	public boolean addItem(ShoppingListItem newItem) {
		try {
			Connection connection = DriverManager.getConnection(URL);
			String sql = "INSERT INTO ShoppingListItem (title) VALUES (?)";
			PreparedStatement insertQuery = connection.prepareStatement(sql);
			insertQuery.setString(1, newItem.getTitle());
			insertQuery.executeUpdate();
			insertQuery.close();
			connection.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeItem(ShoppingListItem item) {
		try {
			Connection connection = DriverManager.getConnection(URL);
			PreparedStatement checkIfExists = connection
					.prepareStatement("SELECT * FROM ShoppingListItem WHERE title = ?");
			checkIfExists.setString(1, item.getTitle());
			ResultSet success = checkIfExists.executeQuery();
			if (success.next()) {
				PreparedStatement deleteQuery = connection
						.prepareStatement("DELETE FROM ShoppingListItem WHERE title = ?");
				deleteQuery.setString(1, item.getTitle());
				deleteQuery.executeUpdate();
				connection.close();
				deleteQuery.close();
				checkIfExists.close();
				return true;
			} else {
				connection.close();
				checkIfExists.close();
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
