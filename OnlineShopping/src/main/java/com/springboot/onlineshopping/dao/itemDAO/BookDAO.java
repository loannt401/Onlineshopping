package com.springboot.onlineshopping.dao.itemDAO;

import java.util.List;

import com.springboot.onlineshopping.model.item.Author;
import com.springboot.onlineshopping.model.item.Book;
import com.springboot.onlineshopping.model.item.Genre;
import com.springboot.onlineshopping.model.item.Pulisher;

public interface BookDAO {
	boolean addBook(Book book);
	boolean deleteBook(Book book);
	boolean updateBook(Book book);
	List<Book> getAuthor(Author author);
	Book getBook(int bookID);
	List<Book> findBook(String key);
	List<Book> getAllBook();
	List<Genre> getBook(Book book);
	boolean addGenre(Genre genre);
	boolean deleteGenre(Genre genre);
	boolean updateGenre(Genre genre);
	boolean addAuthor(Author author);
	boolean updateAuthor(Author author);
	boolean deleteAuthor(Author author);
	boolean findAuthor(String key);
	boolean addPulisher(Pulisher pulisher);
	boolean updatePulisher(Pulisher pulisher);
	boolean deletePulisher(Pulisher pulisher);
	List<Pulisher> findPulisher(String key);
	Author getAuthor(int authorID);
	Pulisher getPulisher(int pulisherID);
	Genre getGenre(int genreID);
}