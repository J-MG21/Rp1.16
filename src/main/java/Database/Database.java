package Database;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {
    private Connection con = null;
    private String connectionURL;
    private String password;
    private String username;
    private ResultSet result;
    private PreparedStatement statement;


    public Database(String connectionURL, String username, String password){
        this.connectionURL = connectionURL;
        this.password = password;
        this.username = username;
    }

    public Connection open(){
        try {
            System.out.println("connecting to database");
            con = DriverManager.getConnection(connectionURL, username, password);
            return con;
        } catch (SQLException e) {
            System.err.println("Error: Could not connect to database");
            e.printStackTrace();
        }
        return null;
    }

    public <TResult> List<TResult> query(String query, ResultsetHandler<TResult> rsh, Object... params){
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<TResult> clients = new ArrayList<>();
        try {
            statement = con.prepareStatement(query);
            fillStatement(statement, params);
            rs = statement.executeQuery();
            while(rs.next()){
                clients.add(rsh.handle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(statement);
        }
        return clients;
    }

    public void query(String querry, String... params){
        if(con == null){
            System.out.println("erreur connection is null");
            con = this.open();
            if (con == null) {
                System.out.println("we doom can not connect to database");
                return;
            }
        }
        try{
            close(statement);
            statement = con.prepareStatement(querry);
            fillStatement(statement, params);
            close(result);
            this.result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean resultHasNext(){
        try {
            return result.next();
        }catch (Exception e){
            return false;
        }
    }

    public boolean nextResult(){
        if(result != null){
            try {
                boolean res = result.next();
                System.out.println("has result: " + res);
                return res;
            }catch (Exception e){
                System.out.println("no more results");
                e.printStackTrace();
                return false;
            }
        }
        System.err.println("result = null");
        return false;
    }

    public String getResult(String key){
        try {
            if(result == null){
                return null;
            }
            return this.result.getString(key);
        }catch (Exception e){
            return null;
        }
    }

    public String getResult(int key){
        try {
            if(result == null){
                return null;
            }
            return this.result.getString(key);
        }catch (Exception e){
            return null;
        }
    }

    public void closeResult(){
        System.out.println("closing result");
        close(statement);
        close(result);
    }

    public int update(String query, Object... params){
        PreparedStatement statement = null;
        try{
            statement = con.prepareStatement(query);
            fillStatement(statement, params);
            return statement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(statement);
        }
        return 0;
    }

    public void closeQuietly(){
        close(con);
    }

    private void fillStatement (PreparedStatement statement, Object... params) throws SQLException{
        if(params == null){
            return;
        }
        for(int i=0; i<params.length; ++i){
            statement.setObject(i+1, params[i]);
        }
    }

    private void fillStatement (PreparedStatement statement, String[] params) throws SQLException{
        if(params == null){
            return;
        }
        for(int i=0; i<params.length; ++i){
            statement.setString(i+1, params[i]);
        }
    }

    private void close(AutoCloseable closeable){
        try {
            if(closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}