<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <h1>Role Add</h1>
        <button type="button" class="btn btn-primary mt-2"
                onclick="window.location.href = 'control-servlet?action=listAccount'">
            <i class="fas fa-arrow-left"></i> Back
        </button>
    </div>
    <div class="row">
        <div class="col-md-2">
            <!-- Menu bên trái -->
            <ul class="list-group">
                <li class="list-group-item">
                    <a href="control-servlet?action=dashboard">Dashboard</a>
                </li>
                <li class="list-group-item">
                    <a href="control-servlet?action=listAccount">Account</a>
                </li>
                <li class="list-group-item">
                    <a href="control-servlet?action=listRole">Role</a>
                </li>
                <li class="list-group-item">
                    <a href="control-servlet?action=listLog">Log</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10">
            <div class="row">
                <div class="col-md-10">
                    <div class="card">
                        <div class="card-header">Add Account</div>
                        <div class="card-body">
                            <form action="control-servlet" method="post">
                                <div class="form-group">
                                    <label for="account_id">Account Id</label>
                                    <input type="text" class="form-control" id="account_id" name="account_id" value="">
                                </div>
                                <div class="form-group">
                                    <label for="full_name">Account Name</label>
                                    <input type="text" class="form-control" id="full_name" name="full_name" value="">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" id="password" name="password" value="">
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" value="">
                                </div>
                                <div class="form-group">
                                    <label for="phone">Phone</label>
                                    <input type="number" class="form-control" id="phone" name="phone" value="">
                                </div>
                                <div class="form-group">
                                    <label for="status">Status</label>
                                    <select class="form-control" id="status" name="status">
                                        <option value="1">Active</option>
                                        <option value="0">DEACTIVATE</option>
                                        <option value="-1">Delete</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
                                <input type="hidden" name="action" value="addAccount">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
