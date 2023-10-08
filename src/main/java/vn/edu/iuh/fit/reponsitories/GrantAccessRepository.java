package vn.edu.iuh.fit.reponsitories;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.GrantAccess;

import java.util.List;
import java.util.Optional;

public class GrantAccessRepository {
    private final EntityManager em;
    private EntityManagerFactory emf;
    private final EntityTransaction trans;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public GrantAccessRepository() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
        trans = em.getTransaction();
    }

    public boolean insertGrantAccess(GrantAccess grantAccess) {
        try {
            trans.begin();
            em.persist(grantAccess);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return false;
    }
    public boolean updateGrantAccess(GrantAccess grantAccess) {
        try {
            trans.begin();
            em.merge(grantAccess);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return false;
    }
    public Optional<GrantAccess> findOneGrantAccess(String accountId, String roleId){
        String sqlQuery = "select * from grant_access ga  where account_id  = ?1 and role_id = ?2";

        Query query = em.createNativeQuery(sqlQuery, GrantAccess.class);
        query.setParameter(1, accountId);
        query.setParameter(2, roleId);
        List<GrantAccess> grantAccesses = query.getResultList();

        if (grantAccesses == null || grantAccesses.isEmpty()){
            return Optional.empty();
        } else {
            return Optional.of(grantAccesses.get(0));
        }
    }
    public boolean deleteGrantAccess(String accountId, String roleId){
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            Optional<GrantAccess> op = findOneGrantAccess(accountId, roleId);
            if (op.isPresent()){
                GrantAccess grantAccess = op.get();
                grantAccess.setIs_grant(false);
                em.merge(grantAccess);
                tr.commit();
                return true;
            } else {
                tr.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();

        }
        return false;
    }
    public List<GrantAccess> findGrantAccessByAccountId(String id){
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            String sql = "select  * from grant_access where account_id=\""+id+"\"";
            List<GrantAccess> list=em.createNativeQuery(sql, GrantAccess.class).getResultList();
            tr.commit();
            return list;
        } catch (Exception e){
            tr.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Account> getAllGrantAccess(){
        try {
            trans.begin();
            String sql ="select * from grant_access";
            List<Account> rl = em.createNativeQuery(sql, Account.class).getResultList();
            trans.commit();
            return rl;
        } catch (Exception e){
            trans.rollback();
            e.printStackTrace();
        }
        return null;
    }

}
