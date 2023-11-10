package DAO;

import static org.mockito.ArgumentMatchers.nullable;

import java.security.DrbgParameters.Reseed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDao {

    public boolean findAccountbyUsername(Account account){
        Connection con = ConnectionUtil.getConnection();
        try{

            String sql = "SELECT * from account WHERE username = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, account.getUsername());
            ResultSet result = statement.executeQuery();
            if(result.next()){
                return true;
                //return new Account(account.getAccount_id(), account.getUsername(), account.getPassword());
            }
            else{
                return false;
            }
        }
        catch(SQLException exc){
            System.out.println(exc.getMessage());
        }

        return true;
    }
    //Add a new account/user
    public Account addNewUser(Account account){

        //if(findAccountbyUsername(account)) {

            Connection con = ConnectionUtil.getConnection();
            try{

                String sql = "INSERT INTO account (username, password) VALUES (?, ?);";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, account.getUsername());
                statement.setString(2, account.getPassword());
                statement.executeUpdate();
                ResultSet result = statement.getGeneratedKeys();
                if(result.next()){
                    int generated_account_id = result.getInt(1);
                    return new Account(generated_account_id, account.getUsername(), account.getPassword());
                }
            }
            catch(SQLException exc){
                System.out.println(exc.getMessage());
            }
        //}

        return null;
    }
}



    //Verify User

    
    