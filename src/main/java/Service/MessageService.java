package Service;
import Model.Message;
import java.util.ArrayList;
import DAO.MessageDao;

public class MessageService {

    //Dependency injection of DAO 
    private MessageDao messageDao;

    public MessageService(){
        messageDao = new MessageDao();
    }

    MessageService(MessageDao messageDao ){
        this.messageDao = messageDao ;
    }


    public ArrayList<Message> getAllMessages(){
       return messageDao.getAllMessages();
    }
    
}
