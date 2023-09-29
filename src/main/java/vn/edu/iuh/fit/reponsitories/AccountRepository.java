package vn.edu.iuh.fit.reponsitories;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.Status;

import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private final EntityManager em;
    private EntityManagerFactory emf;
    private final EntityTransaction trans;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public AccountRepository() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
        trans = em.getTransaction();
    }

    public boolean insertAccount(Account account) {
        try {
            trans.begin();
            em.persist(account);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return false;
    }
    public boolean updateAccount(Account account) {
        try {
            trans.begin();
            em.merge(account);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return false;
    }
    public Optional<Account> findAccount(String id) {
        TypedQuery<Account> query = em.createQuery("select a from Account a where a.account_id=:id", Account.class);
        query.setParameter("id", id);
        Account acc = query.getSingleResult();
        return acc == null ? Optional.empty() : Optional.of(acc);
    }

    public boolean delAccount(String id) {
        try {
            trans.begin();
            Optional<Account> op = findAccount( id);
            Account acc = op.isPresent() ? op.get() : null;
            if (acc != null){
                acc.setStatus(Status.DELETE);
                em.merge(acc);
            }
            trans.commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            trans.rollback();;
        }
        return false;
    }
    public List<Account> getAllAcc(){
//        return em.createNamedQuery("Account.findAll",Account.class).getResultList();
        try {
            trans.begin();
            String sql ="select * from account";
            List<Account> rl = em.createNativeQuery(sql, Account.class).getResultList();
            trans.commit();
            return rl;
        } catch (Exception e){
            trans.rollback();
            e.printStackTrace();
        }
        return null;
    }
    public Optional<Account> login(String accountId, String password) throws NullPointerException{
        String sqlQuery = "select * from account a where a.account_id  = ?1 and a.password  = ?2 limit 1";
        List<Account> accounts = em.createNativeQuery(sqlQuery, Account.class).setParameter(1, accountId).setParameter(2, password).getResultList();

        return Optional.of(accounts.size() > 0 ? accounts.get(0) : null);
    }
}
