package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.reponsitories.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountService {
    private static AccountRepository accRep = new AccountRepository();

    public static boolean insertAccount(Account account) {
        return accRep.insertAccount(account);
    }

    public static Optional<Account> findAccount(String id) {
        return accRep.findAccount(id);
    }

    public static boolean delAccount(String id) {
        return accRep.delAccount(id);
    }

    public static List<Account> getAllAcc() {
        return accRep.getAllAcc();
    }
    public  static boolean updateAccount(Account account) {return accRep.updateAccount(account);}
    public  static Optional<Account> login(String accountId, String password) throws NullPointerException{ return  accRep.login(accountId,password);}

}
