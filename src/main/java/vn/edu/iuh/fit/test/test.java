package vn.edu.iuh.fit.test;

import vn.edu.iuh.fit.models.Log;
import vn.edu.iuh.fit.services.LogService;

import java.sql.Timestamp;
import java.util.Date;

public class test {
    public static void main(String[] args) {
//        Account account1 = new Account("met", "Tran Thi Met", "123", "met@gmail.com", "0904567890", Status.ACTIVE);
//        Account account2 = new Account("teo", "Nguyen Van Teo", "123", "teo@gmail.com", "0903123456", Status.DEACTIVE);
//        Account account3 = new Account("hell", "Nguyen Thanh", "123", "Thanh@gmail.com", "0903123456", Status.DELETE);
//
//        AccountService.insertAccount(account1);
//        AccountService.insertAccount(account2);
//        AccountService.insertAccount(account3);
//
//        AccountService.getAllAcc().forEach(i -> System.out.println(i));
//        AccountService.delAccount("met");
//        System.out.println(AccountService.findAccount("met").get());
//
//        Role role1 = new Role("admin","administrator","admin role", Status.ACTIVE);
//        Role role2 = new Role("user","user","user role",Status.ACTIVE);
//
//        RoleService.insertRole(role1);
//        RoleService.insertRole(role2);
//        RoleService.getAllRole().forEach(i-> System.out.println(i));
//
//        RoleService.delRole("user");
//        System.out.println(RoleService.findRole("user").get());
//
//        GrantAccess grantAccess1 = new GrantAccess(role1,account2,true,"");
//        GrantAccess grantAccess2 = new GrantAccess(role2,account1,true,"");
//
//
//        GrantAcessService.insertGrantAccess(grantAccess1);
//        GrantAcessService.insertGrantAccess(grantAccess2);
//
//        GrantAcessService.getAllGrantAccess().forEach(i-> System.out.println(i));

        Date date = new Date();

        Log log1 = new Log("met",new Timestamp(date.getTime()),new Timestamp(date.getTime()),"oke");

        LogService.insertLog(log1);
    }
}
