package Service;

import java.sql.SQLException;

import DAO.AccountDao;
import Model.Account;

public class AccountService {
    
    //Dependency injection of DAO 
    private AccountDao accountDao;


    //Connstructors 
    public AccountService(){
        accountDao = new AccountDao();
    }

    public AccountService(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    //Add user

    public Account addNewUser(Account account) {
       Account addedAccount =  accountDao.addNewUser(account);
        return addedAccount;
         
    }

    //Validate User
}
