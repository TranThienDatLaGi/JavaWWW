package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.GrantAccess;
import vn.edu.iuh.fit.reponsitories.GrantAccessRepository;

import java.util.List;
import java.util.Optional;

public class GrantAcessService {
    private static GrantAccessRepository G_Rep = new GrantAccessRepository();

    public static boolean insertGrantAccess(GrantAccess grantAccess) {
        return G_Rep.insertGrantAccess(grantAccess);
    }

    public static Optional<GrantAccess> findOneGrantAccess(String accountId, String roleId) {
        return G_Rep.findOneGrantAccess(accountId,roleId);
    }

    public static boolean deleteGrantAccess(String accountId, String roleId) {
        return G_Rep.deleteGrantAccess(accountId,roleId);
    }

    public static List<GrantAccess> findGrantAccessByAccountId(String id) {
        return G_Rep.findGrantAccessByAccountId(id);
    }

    public static List<Account> getAllGrantAccess() {
        return G_Rep.getAllGrantAccess();
    }
    public static boolean updateGrantAccess(GrantAccess grantAccess) { return G_Rep.updateGrantAccess(grantAccess);}
}
