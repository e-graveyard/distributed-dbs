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

    private void query(String statement)
    {
    }

    public void create(Book book)
    {
    }

    public void read(int id)
    {
    }

    public void update(Book book)
    {
    }

    public void delete(int id)
    {
    }
}
