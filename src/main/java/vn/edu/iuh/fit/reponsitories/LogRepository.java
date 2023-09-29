package vn.edu.iuh.fit.reponsitories;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.models.Log;

import java.util.List;
import java.util.Optional;

public class LogRepository {
    private EntityManager em;
    private EntityManagerFactory emf;
    private EntityTransaction trans;
    private final Logger logger= LoggerFactory.getLogger(this.getClass().getName());

    public LogRepository() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
        trans = em.getTransaction();
    }
    public boolean insertLog(Log log) {
        try {
            trans.begin();
            em.persist(log);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return false;
    }
    public boolean updateLog(Log log) {
        try {
            trans.begin();
            em.merge(log);
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return false;
    }
    public Optional<Log> findLog(String id) {
        TypedQuery<Log> query = em.createQuery("select l from Log l where l.id =:id", Log.class);
        query.setParameter("id", id);
        Log log = query.getSingleResult();
        return log == null ? Optional.empty() : Optional.of(log);
    }

    public boolean delLog(String id) {
        try {
            trans.begin();
            Optional<Log> op = findLog(id);
            Log log = op.isPresent() ? op.get() : null;
            if (log != null) {
                em.remove(log);
            }
            trans.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return false;
    }

    public List<Log> getAllLog() {
        try {
            trans.begin();
            String sql = "Select * from log";
            List<Log> l = em.createNativeQuery(sql, Log.class).getResultList();
            trans.commit();
            return l;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
