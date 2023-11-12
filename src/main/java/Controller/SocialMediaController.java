package Controller;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

     private AccountService accountService;
     private MessageService messageService;

    public SocialMediaController(){
        accountService =  new AccountService();
        messageService =  new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::addNewUser);
        app.post("/login", this::loginUser);
        app.get("/messages", this::getAllMessages);
        app.get("/messages/{message_id}", this::getMessageById);
        app.post("/messages", this::createMessage);
        app.delete("/messages/{message_id}", this::deleteMessageById);
        app.patch("/messages/{message_id}", this::updateMessageById);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void addNewUser(Context context) throws JsonProcessingException{
        
        //Read value
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addNewUser(account);
        if(addedAccount != null){
            context.json(mapper.writeValueAsString(addedAccount));
            context.status(200);
        }else{
            context.status(400);
        }
    }

    private void loginUser(Context context) throws JsonProcessingException{
        
        //Read value
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account verifiedAccount = accountService.loginUser(account);
        if(verifiedAccount != null){
            context.json(mapper.writeValueAsString(verifiedAccount));
            context.status(200);
        }else{
            context.status(401);
        }
    }

    private void getAllMessages(Context context){
         //Read value
         ArrayList <Message> messages = messageService.getAllMessages();
         context.json(messages);
         context.status(200);
    }


    private void getMessageById(Context context){
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.getMessageById(messageId);
        context.status(200);
        if(message.getMessage_text()!= null){
            context.json(message);
        }
       
    }

    private void createMessage(Context context) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message bodyMessage = mapper.readValue(context.body(), Message.class);
        Message newMessage = messageService.createMessage(bodyMessage);
        if(newMessage != null){
            context.json(newMessage);
            context.status(200);
        }
        else{
            context.status(400);
        }
        
    }

    private void deleteMessageById(Context context){
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.deleteMessageById(messageId);
        context.status(200);
        if(message.getMessage_text()!= null){
            context.json(message);
        }
       
    }

    private void updateMessageById(Context context) throws JsonMappingException, JsonProcessingException{
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        ObjectMapper mapper = new ObjectMapper();
        Message bodyMessage = mapper.readValue(context.body(), Message.class);
        String text = bodyMessage.getMessage_text();

        Message message = messageService.updateMessageById(messageId, text);

        if(message.getMessage_text() != null){
            context.status(200);
            context.json(message);
        }
        else{
            context.status(400);
        }
            
       
    }
}