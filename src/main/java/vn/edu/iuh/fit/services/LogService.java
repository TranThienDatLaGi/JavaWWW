package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.models.Log;
import vn.edu.iuh.fit.reponsitories.LogRepository;

import java.util.List;
import java.util.Optional;

public class LogService {
    private static LogRepository logRep = new LogRepository();

    public static boolean insertLog(Log log) {
        return logRep.insertLog(log);
    }

    public static Optional<Log> findLog(String id) {
        return logRep.findLog(id);
    }

    public static boolean delLog(String id) {
        return logRep.delLog(id);
    }

    public static List<Log> getAllLog() {
        return logRep.getAllLog();
    }
    public static boolean updateLog(Log log) {return  logRep.updateLog(log);}
}

