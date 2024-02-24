package com.springboot.onlineshopping.dao.itemDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.springboot.onlineshopping.model.item.Brand;
import com.springboot.onlineshopping.model.item.Item;
import com.springboot.onlineshopping.model.item.Phone;

@Service
public class PhoneDAOImpl implements PhoneDAO {
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private ItemDAOImpl itemDAOImpl;

	@Override
	public boolean addPhone(Phone phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePhone(Phone phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePhone(Phone phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Phone> getAllPhone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Phone getPhone(int phoneID) {
		String sql = "SELECT * FROM onlineshopping.phone where PhoneID=?";
		Phone phone = jdbc.queryForObject(sql, this::mapPhone, phoneID);
		return phone;
	}

	@Override
	public List<Phone> getBrand(Brand brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Phone> findPhone(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addBrand(Brand brand) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBrand(Brand brand) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBrand(Brand brand) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Brand getBrand(int brandID) {
		String sql = "SELECT * FROM onlineshopping.brand where BrandID=?";
		Brand brand = jdbc.queryForObject(sql, this::mapBrand, brandID);
		return brand;
	}
	
	private Brand mapBrand (ResultSet rs, int rowNum) throws SQLException {
		return new Brand(rs.getInt(1), rs.getString(2));
	}
	
	private Phone mapPhone (ResultSet rs, int rowNum) throws SQLException {
		Brand brand = getBrand(rs.getInt(6));
		Item item = itemDAOImpl.getItem(rs.getInt(1));
		return new Phone(rs.getInt(1), brand, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), item);
	}
}