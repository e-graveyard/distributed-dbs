package main;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

class Database
{
    private int port;
    private String url;
    private String base;
    private String hostname;
    private String username;
    private String password;

    public Database()
    {
        this.port = Config.PORT;
        this.base = Config.BASE;
        this.hostname = Config.HOSTNAME;
        this.username = Config.USERNAME;
        this.password = Config.PASSWORD;

        this.url = "jdbc:h2:tcp://@hostname:@port/@base"
            .replace("@hostname", this.hostname)
            .replace("@port", Integer.toString(this.port))
            .replace("@base", this.base);
    }

    private Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(this.url, this.username, this.password);
        }
        catch(SQLException ex)
        {
            return null;
        }
    }

    public Boolean create(Book book)
    {
        boolean success;

        Connection conn = getConnection();
        if(conn == null)
            return null;

        String statement = "INSERT INTO books (title, author, publication, isbn, pages) VALUES (?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement pst = conn.prepareStatement(statement);
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getPublication());
            pst.setString(4, book.getIsbn());
            pst.setInt(5, book.getPages());

            pst.executeUpdate();
            pst.close();

            success = true;
        }
        catch(SQLException ex)
        {
            success = false;
        }

        return Boolean.valueOf(success);
    }

    public Book read(String isbn)
    {
        Book book = null;

        Connection conn = getConnection();
        if(conn == null)
            return null;

        String statement = "SELECT * FROM books WHERE isbn = ?";

        try
        {
            PreparedStatement pst = conn.prepareStatement(statement);
            pst.setString(1, isbn);

            ResultSet result = pst.executeQuery();
            while(result.next())
            {
                book = new Book(
                        result.getString("title"),
                        result.getString("author"),
                        result.getString("publication"),
                        result.getString("isbn"),
                        result.getInt("pages"));
            }
        }
        catch (SQLException ex)
        {
            book = null;
        }

        return book;
    }

    public Boolean update(Book book)
    {
        boolean success;

        Connection conn = getConnection();
        if(conn == null)
            return null;

        String statement = "UPDATE book SET title = ?, author = ?, publication = ?, pages = ? WHERE isbn = ?";

        try
        {
            PreparedStatement pst = conn.prepareStatement(statement);
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setString(3, book.getPublication());
            pst.setInt(4, book.getPages());
            pst.setString(5, book.getIsbn());

            pst.executeUpdate();
            pst.close();

            success = true;

        }
        catch(SQLException ex)
        {
            success = false;
        }

        return Boolean.valueOf(success);
    }

    public Boolean delete(String isbn)
    {
        boolean success;

        Connection conn = getConnection();
        if(conn == null)
            return null;

        String statement = "DELETE FROM books WHERE isbn = ?";

        try
        {
            PreparedStatement pst = conn.prepareStatement(statement);
            pst.setString(1, isbn);

            pst.executeUpdate();
            pst.close();

            success = true;
        }
        catch(SQLException ex)
        {
            success = false;
        }

        return Boolean.valueOf(success);
    }
}
