package com.springboot.onlineshopping.dao.itemDAO;

import java.util.List;

import com.springboot.onlineshopping.model.item.Clothes;
import com.springboot.onlineshopping.model.item.Type;

public interface ClothesDAO {
	boolean addClothes(Clothes clothes);
	boolean deleteClothes(Clothes clothes);
	boolean updateClothes(Clothes clothes);
	List<Clothes> getAllClothes();
	Clothes getClothes(int clothesID);
	List<Clothes> findClothes(String key);
	boolean addType(Type type);
	boolean deleteType(Type type);
	boolean updateType(Type type);
	Type getType(int typeID);
}