package Project.jdbc_World.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Project.jdbc_World.data.models.City;

public class CityDaoImpl implements CityDao {

	@Override
	public void printCities() {
		try (Connection conn = DatabaseAlt.getConnection();
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("SELECT Name FROM city")) {

			while (rs.next()) {
				System.out.println(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//1====================================================================================================

	@Override
	public City findById(int id) {

		City result = null;

		try (Connection conn = DatabaseAlt.getConnection();
				PreparedStatement statement = creatPreparedStatement(conn, id);
				ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				return creatCityFormatResultSet(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return result;
	}

	private PreparedStatement creatPreparedStatement(Connection conn, int number) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city WHERE id=?");
		statement.setInt(1, number);
		return statement;
	}

	private City creatCityFormatResultSet(ResultSet rs) throws SQLException {
		return new City(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	}

//2====================================================================================================

	@Override
	public List<City> findByCode(String code) {

		try (Connection conn = DatabaseAlt.getConnection();
				PreparedStatement statement = creatPreparedStatementFindByCode(conn, code);
				ResultSet rs = statement.executeQuery()) {

			return creatCityFormatResultList(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement creatPreparedStatementFindByCode(Connection conn, String Code) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city WHERE CountryCode like ?");
		statement.setString(1, Code);
		return statement;
	}
//3====================================================================================================

	@Override
	public List<City> findByName(String name) {

		try (Connection conn = DatabaseAlt.getConnection();
				PreparedStatement statement = creatPreparedStatementFindByName(conn, name);
				ResultSet rs = statement.executeQuery()) {

			return creatCityFormatResultList(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement creatPreparedStatementFindByName(Connection conn, String Name) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city WHERE Name like ?");
		statement.setString(1, Name);
		return statement;
	}

//4====================================================================================================
	@Override
	public List<City> findAll() {

		try (Connection conn = DatabaseAlt.getConnection();
				PreparedStatement statement = creatPreparedStatementFindAll(conn);
				ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				return creatCityFormatResultList(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement creatPreparedStatementFindAll(Connection conn) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("SELECT * FROM city ");
		statement.getResultSet();
		return statement;
	}

	private List<City> creatCityFormatResultList(ResultSet rs) throws SQLException {
		List<City> result = new ArrayList<>();
		while (rs.next()) {
			City Found = new City(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
			result.add(Found);
		}
		return result;
	}
//5====================================================================================================

	@Override
	public City add(City city) {

		try (Connection conn = DatabaseAlt.getConnection();
				PreparedStatement statement = creatPreparedStatement(conn, city);) {
			statement.executeUpdate();
			return findById(city.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement creatPreparedStatement(Connection conn, City city) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("insert into city values (?,?,?,?,?)");
		statement.setInt(1, city.getId());
		statement.setString(2, city.getName());
		statement.setString(3, city.getCountryCode());
		statement.setString(4, city.getDistrict());
		statement.setInt(5, city.getPopulation());
		return statement;
	}

//6====================================================================================================

	@Override
	public City update(City city) {

		try (Connection conn = DatabaseAlt.getConnection();
				PreparedStatement statement = creatPreparedStatementUpdateCity(conn, city);) {
			statement.executeUpdate();
			return findById(city.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PreparedStatement creatPreparedStatementUpdateCity(Connection conn, City city) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("update city set name = ? where id = ?");
		statement.setString(1, city.getName());
		statement.setInt(2, city.getId());
		return statement;
	}

//7====================================================================================================

	@Override
	public int delete(City city) {

		try (Connection conn = DatabaseAlt.getConnection();
				PreparedStatement statement = creatPreparedStatementDelit(conn, city);) {
			statement.executeUpdate();
			return city.getId();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private PreparedStatement creatPreparedStatementDelit(Connection conn, City city) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("delete from city where id = ? ");
		statement.setInt(1, city.getId());
		return statement;

	}
}
