package vn.edu.iuh.fit.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.models.*;
import vn.edu.iuh.fit.services.AccountService;
import vn.edu.iuh.fit.services.GrantAcessService;
import vn.edu.iuh.fit.services.LogService;
import vn.edu.iuh.fit.services.RoleService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
@WebServlet(name = "controlServlet", value = "/control-servlet")
public class ControlServlet extends HttpServlet {
    Timestamp curr= new Timestamp(System.currentTimeMillis());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RoleService roleService = new RoleService();
        AccountService accountService = new AccountService();
        GrantAcessService grantAcessService= new GrantAcessService();

        if (action.equals("listRole")) {
            try {
                List<Role> listRole = roleService.getAllRole();
                req.setAttribute("listRole", listRole);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/role/roles.jsp");
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("editRole")) {
            try {
                Role role = roleService.findRole(req.getParameter("id")).get();
                req.setAttribute("role", role);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/role/edit_role.jsp");
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("deleteRole")) {
                boolean res = roleService.delRole(String.valueOf(req.getParameter("id")));
                if (res) {
                    resp.sendRedirect("control-servlet?action=listRole");
                    //show toast delete success
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Delete success');");
                    out.println("location='control-servlet?action=listRole';");
                    out.println("</script>");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Delete failed');");
                    out.println("location='control-servlet?action=listRole';");
                    out.println("</script>");
                }
        } else if (action.equals("addRole")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/role/add_role.jsp");
            dispatcher.forward(req, resp);
        } else if (action.equals("dashboard")) {
            //get role of account
            try {
                //get value cookie by key account_id
                Cookie[] cookies = req.getCookies();
                String accountId = "";
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("account_id")) {
                        accountId = cookie.getValue();
                    }
                }
                //get role of account
                List<Role> listRoleByAccount = roleService.getRoleByAccount(accountId);
                req.setAttribute("listRoleByAccount", listRoleByAccount);
                RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
                dispatcher.forward(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("logout")) {
            // Lấy danh sách các cookie hiện tại
            Cookie[] cookies = req.getCookies();
            //get account_id cookie
            String accountId = "";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("account_id")) {
                    accountId = cookie.getValue();
                }
            }
            //update logout_time
            Log log = new Log();
            log.setAccount_id(accountId);
            log.setLogin_time(curr);
            log.setLogout_time(new Timestamp(System.currentTimeMillis()));
            log.setNote("logout");
            boolean res = new LogService().updateLog(log);
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        } else if (action.equals("listAccount")) {
            try {
                List<Account> listAccount = accountService.getAllAcc();
                List<Role> listRole = roleService.getAllRole();
                req.setAttribute("listAccount", listAccount);
                req.setAttribute("listRole", listRole);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/account/accounts.jsp");
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("editAccount")) {
            try {
                Optional<Account> account = accountService.findAccount(req.getParameter("id"));
                req.setAttribute("account", account.get());
                RequestDispatcher dispatcher = req.getRequestDispatcher("/account/edit_account.jsp");
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("deleteAccount")) {

                if (req.getParameter("id").equals(req.getCookies()[0].getValue())) {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Can not delete account login');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                }
                boolean res = accountService.delAccount(String.valueOf(req.getParameter("id")));
                if (res) {
                    resp.sendRedirect("control-servlet?action=listAccount");
                    //show toast delete success
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Delete success');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Delete failed');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                }
        } else if (action.equals("addAccount")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/account/add_account.jsp");
            dispatcher.forward(req, resp);
        } else if (action.equals("listLog")) {
            try {
                List<Log> listLog = new LogService().getAllLog();
                req.setAttribute("listLog", listLog);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/log/logs.jsp");
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        RoleService roleService = new RoleService();
        AccountService accountService = new AccountService();
        GrantAcessService grantAcessService= new GrantAcessService();

        if (action.equals("logon")) {
            try {
                Optional<Account> account = accountService.login(req.getParameter("email"), req.getParameter("password"));
                //if account is not exist
                if (account.isEmpty()) {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Account is not exist');");
                    out.println("location='index.jsp';");
                    out.println("</script>");
                } else {
                    System.out.println(account);
                    Log log = new Log();
                    log.setAccount_id(account.get().getAccount_id());
                    //yyyy mm dd hh:mm:ss
                    log.setLogin_time(new Timestamp(System.currentTimeMillis()));
                    curr= log.getLogin_time();
                    log.setLogout_time(new Timestamp(System.currentTimeMillis()));
                    log.setNote("login");
                    new LogService().insertLog(log);
                    //save account to cookie
                    Cookie account_id = new Cookie("account_id", account.get().getAccount_id());
//                    Cookie full_name = new Cookie("full_name", account.get().getFull_name());
                    Cookie email = new Cookie("email", account.get().getEmail());
                    Cookie phone = new Cookie("phone", account.get().getPhone());
                    Cookie status = new Cookie("status", account.get().getStatus().toString());
                    resp.addCookie(account_id);
//                    resp.addCookie(full_name);
                    resp.addCookie(email);
                    resp.addCookie(phone);
                    resp.addCookie(status);
                    //information of account

                    // redirect dashboard and info account
                    req.setAttribute("account", account.get());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
                    dispatcher.forward(req, resp);
                }
            } catch ( ServletException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("register")) {
            Account account = new Account();
            account.setAccount_id(req.getParameter("accountId"));
            account.setPassword(req.getParameter("password"));
            account.setFull_name(req.getParameter("fullName"));
            account.setEmail(req.getParameter("email"));
            account.setPhone(req.getParameter("phone"));
            if (req.getParameter("status").equals("1")) {
                account.setStatus(Status.ACTIVE);
            } else if (req.getParameter("status").equals("0")) {
                account.setStatus(Status.DEACTIVE);
            } else {
                account.setStatus(Status.DELETE);
            }
            boolean res = false;
            res = accountService.insertAccount(account);
            if (res) {
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Register success');");
                out.println("location='index.jsp';");
                out.println("</script>");
            } else {
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Register failed');");
                out.println("location='register.jsp';");
                out.println("</script>");
            }
        } else if (action.equals("editRole")) {
            Role role = new Role();
            role.setRole_id(req.getParameter("role_id"));
            role.setRole_name(req.getParameter("role_name"));
            role.setDescription(req.getParameter("description"));
            if (req.getParameter("status").equals("1")) {
                role.setStatus(Status.ACTIVE);
            } else if (req.getParameter("status").equals("0")) {
                role.setStatus(Status.DEACTIVE);
            } else {
                role.setStatus(Status.DELETE);
            }
                boolean res = roleService.updateRole(role);
                if (res) {
                    resp.sendRedirect("control-servlet?action=listRole");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Update failed');");
                    out.println("location='control-servlet?action=listRole';");
                    out.println("</script>");
                }
        } else if (action.equals("addRole")) {
            Role role = new Role();
            role.setRole_id(req.getParameter("role_id"));
            role.setRole_name(req.getParameter("role_name"));
            role.setDescription(req.getParameter("description"));
            if (req.getParameter("status").equals("1")) {
                role.setStatus(Status.ACTIVE);
            } else if (req.getParameter("status").equals("0")) {
                role.setStatus(Status.DEACTIVE);
            } else {
                role.setStatus(Status.DELETE);
            }
                boolean res = roleService.insertRole(role);
                if (res) {
                    resp.sendRedirect("control-servlet?action=listRole");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Add failed');");
                    out.println("location='control-servlet?action=listRole';");
                    out.println("</script>");
                }
        } else if (action.equals("addAccount")) {
            Account account = new Account();
            account.setAccount_id(req.getParameter("account_id"));
            account.setPassword(req.getParameter("password"));
            account.setFull_name(req.getParameter("full_name"));
            account.setEmail(req.getParameter("email"));
            account.setPhone(req.getParameter("phone"));
            if (req.getParameter("status").equals("1")) {
                account.setStatus(Status.ACTIVE);
            } else if (req.getParameter("status").equals("0")) {
                account.setStatus(Status.DEACTIVE);
            } else {
                account.setStatus(Status.DELETE);
            }
                boolean res = accountService.insertAccount(account);
                if (res) {
                    resp.sendRedirect("control-servlet?action=listAccount");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Add failed');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                }
        } else if (action.equals("editAccount")) {
            Account account = new Account();
            account.setAccount_id(req.getParameter("account_id"));
            account.setPassword(req.getParameter("password"));
            account.setFull_name(req.getParameter("full_name"));
            account.setEmail(req.getParameter("email"));
            account.setPhone(req.getParameter("phone"));
            if (req.getParameter("status").equals("1")) {
                account.setStatus(Status.ACTIVE);
            } else if (req.getParameter("status").equals("0")) {
                account.setStatus(Status.DEACTIVE);
            } else {
                account.setStatus(Status.DELETE);
            }
            boolean res = accountService.updateAccount(account);
                if (res) {
                    resp.sendRedirect("control-servlet?action=listAccount");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Update failed');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                }
        } else if (action.equals("grantPermission")) {
            try {
                Role role= roleService.findRole(req.getParameter("roleIds")).get();
                Account account=accountService.findAccount(req.getParameter("accountId")).get();
                boolean a= false;
                if(req.getParameter("grantType").equals(1)){
                    a=true;
                }
                boolean res = grantAcessService.insertGrantAccess(
                        new GrantAccess( role,account, a, req.getParameter("note")));
                if (res) {
                    resp.sendRedirect("control-servlet?action=listAccount");
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Grant success');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Grant failed');");
                    out.println("location='control-servlet?action=listAccount';");
                    out.println("</script>");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
