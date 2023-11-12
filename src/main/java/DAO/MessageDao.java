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
    public Message deleteMessageById(int messageId) {
        
        Connection con = ConnectionUtil.getConnection();
        Message messageForDeletion = getMessageById(messageId);

        if(messageForDeletion.getMessage_id() >= 0){
            try{
                String sql = "DELETE * from message WHERE message_id = ?;";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, messageId);
                statement.executeUpdate();        
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }

        return messageForDeletion;
    }


        //Update message based on ID

    public Message updateMessageById(int messageId, String text) {

        Connection con = ConnectionUtil.getConnection();
        Message newMessage = new Message();

            if(text.length() < 255 & text != ""){
                try{

                    String sql = "UPDATE message SET message_text = ? WHERE message_id = ?;";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, text);
                    statement.setInt(2, messageId);
                    int rowsAffected = statement.executeUpdate();
                    if(rowsAffected > 0){
                        newMessage = getMessageById(messageId);
                        return newMessage;
                    }
        
                }
                catch(SQLException e){
                    System.out.println(e);
                }
            }
           
        return newMessage;
        
    }




    //Get all messages from a given Account ID
}


