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
    public Message getMessageById(int messageId) {
        
        Connection con = ConnectionUtil.getConnection();
        Message message = new Message();
        try{
            String sql = "SELECT * from message WHERE message_id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, messageId);
            ResultSet result =  statement.executeQuery();
            if(result.next()){
                message = new Message(result.getInt("message_id"), result.getInt("posted_by"), result.getString("message_text"),result.getLong("time_posted_epoch"));
               
            }   
            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return message;
    }



    //Delete a message based on ID

    //Update message based on ID


    //Get all messages from a given Account ID
}


