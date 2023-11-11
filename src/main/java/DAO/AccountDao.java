package DAO;

import static org.mockito.ArgumentMatchers.nullable;

import java.security.DrbgParameters.Reseed;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDao {

    // Add a new account

    public Account addNewUser(Account account){

        Connection con = ConnectionUtil.getConnection();

            //Validate password and username

            if(account.getPassword().length() >= 4 & account.getUsername() != ""){
                try{
                    String sqlInsert = "INSERT INTO account (username, password) VALUES (?, ?);";
                    PreparedStatement preparedStatement = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, account.getUsername());
                    preparedStatement.setString(2, account.getPassword());
                    preparedStatement.executeUpdate();
                    ResultSet result = preparedStatement.getGeneratedKeys();
                    if(result.next()){
                        int generated_account_id = result.getInt(1);
                        return new Account(generated_account_id, account.getUsername(), account.getPassword());
                    }
                    else{
                        return null;
                    }
               
            }
            catch(SQLException exc){
                System.out.println(exc.getMessage());
            }
            }

            
            return null;

       
    }
}



    //Verify User

    
    