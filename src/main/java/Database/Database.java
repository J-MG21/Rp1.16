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


    public Database(String connectionURL, String username, String password){
        this.connectionURL = connectionURL;
        this.password = password;
        this.username = username;
    }

    public void open(){
        try {
            con = DriverManager.getConnection(connectionURL, username, password);
        } catch (SQLException e) {
            System.err.println("Error: Could not connect to database");
            e.printStackTrace();
        }
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

    public void query(String querry, Object... params){
        PreparedStatement statement = null;
        try{
            statement = con.prepareStatement(querry);
            fillStatement(statement, params);
            close(result);
            this.result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(statement);
        }
    }

    public boolean resultHasNext(){
        try {
            return result.next();
        }catch (Exception e){
            return false;
        }
    }

    public String getResult(String key){
        try {
            return (result != null)? this.result.getString(key): null;
        }catch (Exception e){
            return null;
        }
    }

    public void closeResult(){
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

    private void fillStatement (PreparedStatement statement, Object[] params) throws SQLException{
        if(params == null){
            return;
        }
        for(int i=0; i<params.length; ++i){
            statement.setObject(i+1, params[i]);
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