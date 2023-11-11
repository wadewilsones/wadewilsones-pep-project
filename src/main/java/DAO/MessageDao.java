package DAO;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Message;
import Util.ConnectionUtil;


public class MessageDao {

    //Get all messages
    public ArrayList<Message> getAllMessages(){

        ArrayList <Message> allMessages = new ArrayList<Message>();
        Connection con = ConnectionUtil.getConnection();

        try{
            String sql = "SELECT * FROM message";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet result = stm.executeQuery();
            while(result.next()){
                Message newMessage = new Message(result.getInt("message_id") , result.getInt("posted_by"), result.getString("message_text"), result.getLong("time_posted_epoch"));
                allMessages.add(newMessage);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return allMessages;
    
    }

    //Get message by ID

    //Delete a message based on ID

    //Update message based on ID


    //Get all messages from a given Account ID
}


