package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.models.Role;
import vn.edu.iuh.fit.reponsitories.RoleRepository;

import java.util.List;
import java.util.Optional;

public class RoleService {
    private static RoleRepository roleRep = new RoleRepository();

    public static boolean insertRole(Role role) {
        return roleRep.insertRole(role);
    }

    public static Optional<Role> findRole(String id) {
        return roleRep.findRole(id);
    }

    public static boolean delRole(String id) {
        return roleRep.delRole(id);
    }

    public static List<Role> getAllRole() {
        return roleRep.getAllRole();
    }
    public static boolean updateRole(Role role) {return  roleRep.updateRole(role);}
    public static List<Role> getRoleByAccount(String accountId) {return roleRep.getRoleByAccount(accountId);}
}
