package com.springboot.onlineshopping.dao.itemDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.springboot.onlineshopping.model.item.Author;
import com.springboot.onlineshopping.model.item.Book;
import com.springboot.onlineshopping.model.item.Genre;
import com.springboot.onlineshopping.model.item.Item;
import com.springboot.onlineshopping.model.item.Pulisher;

@Service
public class BookDAOImpl implements BookDAO {
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
	private ItemDAOImpl itemDAOImpl;

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> getAuthor(Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBook(int bookID) {
		String sql = "SELECT * FROM onlineshopping.book where BookID=?";
		Book book = jdbc.queryForObject(sql, this::mapBook, bookID);
		return book;
	}

	@Override
	public List<Book> findBook(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Genre> getBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addGenre(Genre genre) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteGenre(Genre genre) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateGenre(Genre genre) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAuthor(Author author) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAuthor(Author author) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAuthor(Author author) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAuthor(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPulisher(Pulisher pulisher) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePulisher(Pulisher pulisher) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePulisher(Pulisher pulisher) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Pulisher> findPulisher(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author getAuthor(int authorID) {
		String sql = "SELECT * FROM onlineshopping.author where AuthorID=?";
		Author author = jdbc.queryForObject(sql, this::mapAuthor, authorID);
		return author;
	}

	@Override
	public Pulisher getPulisher(int pulisherID) {
		String sql = "SELECT * FROM onlineshopping.pulisher where PulisherID=?";
		Pulisher pulisher = jdbc.queryForObject(sql, this::mapPulisher, pulisherID);
		return pulisher;
	}

	@Override
	public Genre getGenre(int genreID) {
		String sql = "SELECT * FROM onlineshopping.genre where GenreID=?";
		Genre genre = jdbc.queryForObject(sql, this::mapGenre, genreID);
		return genre;
	}
	
	private Book mapBook (ResultSet rs, int rowNum)throws SQLException {
		Author author = getAuthor(rs.getInt(2));
		Genre genre = getGenre(rs.getInt(4));
		Pulisher pulisher = getPulisher(rs.getInt(3));
		Item item = itemDAOImpl.getItem(rs.getInt(1));
		return new Book(rs.getInt(1), author, genre, pulisher, item, rs.getString(5), rs.getString(6));
	}

	private Author mapAuthor (ResultSet rs, int rowNum)throws SQLException {
		return new Author(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5));
	}
	
	private Pulisher mapPulisher (ResultSet rs, int rowNum)throws SQLException {
		return new Pulisher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
	}
	
	private Genre mapGenre (ResultSet rs, int rowNum)throws SQLException {
		return new Genre(rs.getInt(1), rs.getString(2));
	}
}