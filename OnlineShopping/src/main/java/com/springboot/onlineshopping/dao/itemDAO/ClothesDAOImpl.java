package com.springboot.onlineshopping.dao.itemDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.springboot.onlineshopping.model.item.Clothes;
import com.springboot.onlineshopping.model.item.Item;
import com.springboot.onlineshopping.model.item.Type;

@Service
public class ClothesDAOImpl implements ClothesDAO {
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private ItemDAOImpl itemDAOImpl;

	@Override
	public boolean addClothes(Clothes clothes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteClothes(Clothes clothes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateClothes(Clothes clothes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Clothes> getAllClothes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clothes getClothes(int clothesID) {
		String sql = "SELECT * FROM onlineshopping.clothes where ClothesID = ?";
		Clothes clothes = jdbc.queryForObject(sql, this::mapClothes, clothesID);
		return clothes;
	}

	@Override
	public List<Clothes> findClothes(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addType(Type type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteType(Type type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateType(Type type) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Type getType(int typeID) {
		String sql = "SELECT * FROM onlineshopping.type where TypeID=?";
		Type type = jdbc.queryForObject(sql, this::mapType, typeID);
		return type;
	}
	
	private Clothes mapClothes (ResultSet rs, int rowNum) throws SQLException {
		Type type = getType(rs.getInt(5));
		Item item = itemDAOImpl.getItem(rs.getInt(1));
		return new Clothes(rs.getInt(1), rs.getString(2), type, rs.getString(3), rs.getString(4), item);
	}
	
	private Type mapType (ResultSet rs, int rowNum) throws SQLException {
		return new Type(rs.getInt(1), rs.getString(2));
	}

	

}