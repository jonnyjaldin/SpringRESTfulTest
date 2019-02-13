package springrestful_test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import springrestful_test.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) throws DataAccessException{
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<User> listAllUser() {
		List<User> list = new ArrayList<User>();
		
		String sql = "SELECT id, firstname, lastname, address, city, state, zip FROM users";
		
		list = jdbcTemplate.query(sql, getSqlParameterByModel(null), new UserMapper());
		
		return list;
	}
	
	private SqlParameterSource getSqlParameterByModel(User user) {
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		
		if( user != null ) {
			param.addValue("id", user.getId());
			param.addValue("firstname", user.getFirstname());
			param.addValue("lastname", user.getLastname());
			param.addValue("address", user.getAddress());
			param.addValue("city", user.getAddress());
			param.addValue("state", user.getAddress());
			param.addValue("zip", user.getAddress());
		}
		
		return param;
	}
	
	private static final class UserMapper implements RowMapper<User>{
		
		public User mapRow(ResultSet rs, int rowNum) throws SQLException{
			
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setAddress(rs.getString("address"));
			user.setCity(rs.getString("city"));
			user.setState(rs.getString("state"));
			user.setZip(rs.getString("zip"));
			
			return user;
		}
	}
	
	public void addUser(User user) {
		String sql = "INSERT INTO users(firstname, lastname, address, city, state, zip) VALUES (:firstname,:lastname,:address,:city,:state,:zip)";
		
		jdbcTemplate.update(sql, getSqlParameterByModel(user));
	}

	public void updateUser(User user) {
		String sql = "UPDATE users SET firstname=:firstname, lastname=:lastname, address=:address, city=:city, state=:state, zip=:zip WHERE id = :id";
		
		
		jdbcTemplate.update(sql, getSqlParameterByModel(user));
		
	}

	public void delete(User user) {
		String sql = "DELETE FROM users WHERE id = :id";
		
		jdbcTemplate.update(sql, getSqlParameterByModel(user));
		
	}

	public User findUserById(User user) {
		String sql = "SELECT * FROM users WHERE id = :id";

		return jdbcTemplate.queryForObject(sql, getSqlParameterByModel(user), new UserMapper());
	}

}
