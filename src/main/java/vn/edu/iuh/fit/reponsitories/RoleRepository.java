package vn.edu.iuh.fit.reponsitories;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.models.Role;
import vn.edu.iuh.fit.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepository {
    private final EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction trans;
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public RoleRepository() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
        trans = em.getTransaction();
    }

    public boolean insertRole(Role role) {
        try {
            trans.begin();
            em.persist(role);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return false;
    }
    public boolean updateRole(Role role) {
        try {
            trans.begin();
            em.merge(role);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return false;
    }
    public Optional<Role> findRole(String id) {
        TypedQuery<Role> query = em.createQuery("select r from Role r where r.role_id=:id", Role.class);
        query.setParameter("id", id);
        Role role = query.getSingleResult();
        return role == null ? Optional.empty() : Optional.of(role);
    }

    public boolean delRole(String id) {
        try {
            trans.begin();
            Optional<Role> op = findRole(id);
            Role role = op.isPresent() ? op.get() : null;
            if (role != null) {
                role.setStatus(Status.DELETE);
                em.merge(role);
            }
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return false;
    }

    public List<Role> getAllRole() {
        try {
            trans.begin();
            String sql = "Select * from role";
            List<Role> l = em.createNativeQuery(sql, Role.class).getResultList();
            trans.commit();
            return l;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Role> getRoleByAccount(String accountId) {
        try {
            trans.begin();
            TypedQuery<Role> query = em.createQuery("SELECT a FROM Account a JOIN GrantAccess ga on a.account_id= ga.account.account_id \n" +
                    "JOIN Role r on ga.role.role_id=r.role_id WHERE a.account_id = :accountId", Role.class);
           query.setParameter("accountId",accountId);
            List<Role> listRole=query.getResultList();
           return listRole;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
