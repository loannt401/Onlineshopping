package com.springboot.onlineshopping.dao.itemDAO;

import java.util.List;

import com.springboot.onlineshopping.model.item.Brand;
import com.springboot.onlineshopping.model.item.Phone;

public interface PhoneDAO {
	boolean addPhone(Phone phone);
	boolean updatePhone(Phone phone);
	boolean deletePhone(Phone phone);
	List<Phone> getAllPhone();
	Phone getPhone(int phoneID);
	List<Phone> getBrand(Brand brand);
	List<Phone> findPhone(String key);
	boolean addBrand(Brand brand);
	boolean deleteBrand(Brand brand);
	boolean updateBrand(Brand brand);
	Brand getBrand(int brandID);
}