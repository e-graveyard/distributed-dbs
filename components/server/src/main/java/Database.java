package main;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

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

    private boolean query(String verb, String statement, Object params)
    {
        boolean status = false;

        Connection conn = getConnection();
        if(conn == null)
            return status;

        PreparedStatement pst = conn.prepareStatement(statement);

        switch(verb)
        {
            case "create":
                break;

            case "read":
                pst.setInt(1, (int)params);
                break;

            case "update":
                break;

            case "delete":
                break;
        }
    }

    public void create(Book book)
    {
        String statement = "INSERT INTO books (title, author, publication, isbn, pages) VALUES (?, ?, ?, ?, ?)";
        boolean success = query("create", statement, book);
    }

    public void read(int isbn)
    {
        String statement = "SELECT * FROM books WHERE isbn = ?";
        boolean success = query("read", statement, isbn);
    }

    public void update(Book book)
    {
        String statement = "UPDATE book SET title = ?, author = ?, publication = ?, isbn = ?, pages = ? WHERE isbn = ?";
        boolean success = query("update", statement, book);
    }

    public void delete(int isbn)
    {
        String statement = "DELETE FROM books WHERE id = ?";
        boolean success = query("delete", statement, isbn);
    }
}
