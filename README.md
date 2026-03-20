# Room Rental Management App (MVC)

Ung dung Android Java quan ly phong tro voi du lieu luu tam trong `List` (khong dung SQLite/Room).

## Tinh nang
- CRUD phong tro (them, xem, sua, xoa)
- RecyclerView hien thi danh sach phong
- Trang thai phong:
  - `Con trong` (mau xanh)
  - `Da thue` (mau do)
- Validate du lieu truoc khi luu

## Validate CRUD
- Bat buoc: `Mã phòng`, `Tên phòng`, `Giá thuê` (khong rong).
- `Giá thuê` phai chuyen duoc sang so va > 0.
- Neu `Đã thuê`: bat buoc `Tên người thuê`, `Số điện thoại`; SDT phai 9-11 chu so (`^\d{9,11}$`).
- `Mã phòng` phai duy nhat (bo qua item dang chinh sua).
- Neu chon `Còn trống`: form tu dong xoa va khoa `Tên người thuê` + `Số điện thoại` (chi mo khi chon `Đã thuê`).

## RecyclerView la gi va ung dung o day
- RecyclerView la View nhieu lan su dung view holder de hien thi danh sach lon mot cach toi uu, cho phep scroll muot va tuong tac tung item.
- Trong app nay: `rvRooms` (layout: `app/src/main/res/layout/activity_main.xml`) hien thi danh sach phong; adapter `RoomAdapter` bind du lieu tu `RoomController.getRoomList()`; click vao item de xem chi tiet (view-only), nut "Sửa" de mo form chinh sua, nut "Xóa" de xoa item; du lieu chi o trong List nen RecyclerView duoc cap nhat bang `notifyDataSetChanged()` sau CRUD.

## Cau truc MVC
- Model: `app/src/main/java/com/techja/roomrentalapp/model/RentalRoom.java`
- Controller: `app/src/main/java/com/techja/roomrentalapp/controller/RoomController.java`
- View:
  - `app/src/main/java/com/techja/roomrentalapp/MainActivity.java`
  - `app/src/main/java/com/techja/roomrentalapp/RoomFormActivity.java`
  - Layouts trong `app/src/main/res/layout/`
