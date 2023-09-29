BÀI TẬP THỰC HÀNH TUẦN 1
Yêu cầu
Tạo một csdl có tên mydb với các bảng dữ liệu account (account_id, full_name, password, email,
phone, status), bảng role (role_id, role_name, description, status). Một account thuộc về nhiều
role, mỗi role có thể có nhiều account. Trong đó status là trường để chỉ trạng thái mẫu tin (1-
active, 0-deactive, -1-xóa)
(Tip: tạo 1 bảng thứ ba có tên grant_access (account_id, role_id, is_grant, note) với trường
is_grant nhận giá trị 0-diasable, 1-enable.)
Một bảng có tên log để ghi vết lần đăng nhập. Thông tin bao gồm: id (auto-increment), account
đăng nhập, ngày giờ đăng nhập, ngày giờ đăng xuất, ghi chú.
Tạo một jakartaEE project có tên week01_lab_HotenSv_mssv (Có thể kết nối GitHub/GitLab để
push code). Thực hiện các công việc sau:
- Tạo một servlet có tên ControlServlet (partern cùng tên). Servlet này nhận một tham số
(parameter) có tên là action. Tham số này nhận các giá trị chuỗi để chỉ các hành động
tương ứng.
- Kết nối với csdl, thực hiện các chức năng:
o Thêm, cập nhật, xóa ở các bảng đã cho.
o Đăng nhập
o Hiển thị thông tin tài khoản (nếu đăng nhập thành công)
o Hiển thị các quyền của một account.
o Hiển thị các account của một role
o Cấp quyền cho một account
o Ghi log mỗi lần account đăng nhập, đăng xuất.
- Một trang html hiển thị cửa sổ đăng nhập. Nếu đăng nhập thành công và là quyền admin
thì hiển thị trang dashboard cho phép quản lý các account khác (bao gồm các quyền thêm,
xóa, sửa và cấp quyền). Còn không (không phải admin) thì hiển thị thông tin của người
đăng nhập cùng các quyền mà người đó có.
