document.addEventListener('DOMContentLoaded', function () {
    const currentDate = new Date();
    const ngayHoaDonInput = document.getElementById('ngayHoaDon');

    // Lấy ngày, tháng, năm hiện tại từ đối tượng Date
    const ngayHienTai = currentDate.getDate();
    const thangHienTai = currentDate.getMonth() + 1; // Tháng bắt đầu từ 0 nên cần +1
    const namHienTai = currentDate.getFullYear();

    // Định dạng chuỗi ngày tháng để gán vào input date (YYYY-MM-DD)
    const formattedDate = `${namHienTai}-${thangHienTai.toString().padStart(2, '0')}-${ngayHienTai.toString().padStart(2, '0')}`;

    // Gán giá trị vào input
    ngayHoaDonInput.value = formattedDate;




    const addProductBtn = document.getElementById('addProduct');
    const tableBody = document.getElementById('productTableBody');
    let stt = 1; // Biến để đếm số thứ tự của sản phẩm

    addProductBtn.addEventListener('click', function () {
        const tenSanPham = document.getElementById('tenSanPham').value;
        const soLuong = document.getElementById('soLuong').value;
        const donViTinh = document.getElementById('donViTinh').value;
        const thanhTien = document.getElementById('thanhTien').value;
        const ghiChu = document.getElementById('ghiChu').value;

        // Kiểm tra nếu các trường không được để trống
        if (tenSanPham.trim() === '' || soLuong.trim() === '' || thanhTien.trim() === '') {
            alert('Vui lòng nhập đầy đủ thông tin sản phẩm');
            return;
        }

        // Tính tổng giá
        const tongGia = parseFloat(soLuong) * parseFloat(thanhTien);

        // Tạo hàng mới trong bảng
        const newRow = document.createElement('tr');
        newRow.innerHTML = `
      <td>${stt}</td>
      <td>${tenSanPham}</td>
      <td>${soLuong}</td>
      <td>${donViTinh}</td>
      <td>${thanhTien}</td>
      <th style="display: none;">${ghiChu}</th>
      <td><i class="fa-solid fa-xmark"></i></td>
    `;

        // Thêm sự kiện xóa hàng khi click vào icon Xóa
        const deleteIcon = newRow.querySelector('.fa-xmark');
        deleteIcon.addEventListener('click', function () {
            newRow.remove();
        });

        // Thêm hàng vào tbody của bảng
        tableBody.appendChild(newRow);

        // Tăng số thứ tự sản phẩm
        stt++;

        // Đặt lại các trường nhập liệu về trạng thái ban đầu
        document.getElementById('tenSanPham').value = '';
        document.getElementById('soLuong').value = '1';
        document.getElementById('donViTinh').value = 'Khác'; // Đơn vị tính mặc định là Kg
        document.getElementById('thanhTien').value = '';
        document.getElementById('ghiChu').value = '';

    });



    const deleteIcons = document.querySelectorAll('.btnXoa');

    // Lặp qua từng biểu tượng Xóa và gắn sự kiện click
    deleteIcons.forEach(function (icon) {
        icon.addEventListener('click', function () {
            // Lấy hàng (tr) chứa biểu tượng Xóa
            const row = icon.closest('tr');

            // Xóa hàng khỏi bảng
            row.remove();

            // Cập nhật lại số thứ tự (STT) của các hàng còn lại
            updateSTT();
        });
    });

    // Hàm cập nhật lại STT của các hàng
    function updateSTT() {
        const rows = document.querySelectorAll('#productTableBody tr');
        let stt = 1;
        rows.forEach(function (row) {
            const sttCell = row.querySelector('td:first-child');
            sttCell.textContent = stt;
            stt++;
        });
    }



    const formNhapHoaDon = document.getElementById('formNhapHoaDon');
    const luuHoaDonBtn = document.getElementById('luuHoaDonBtn');

    luuHoaDonBtn.addEventListener('click', function (event) {
        event.preventDefault(); // Ngăn chặn hành động mặc định của nút submit

        // Lấy ngày hóa đơn
        const ngay = document.getElementById('ngayHoaDon').value;

        // Tách ngày, tháng, năm từ chuỗi ban đầu
        let parts = ngay.split("-");
        let year = parts[0];
        let month = parts[1];
        let day = parts[2];

        // Định dạng lại chuỗi ngày tháng theo định dạng mới
        let ngayHoaDon = year + "-" + month + "-" + day;

        let tenSanPham = null;
        let soLuong = null;
        let donViTinh = null;
        let thanhTien = null;
        let ghiChu = null;

        // Lấy dữ liệu từ bảng sản phẩm
        const tableRows = document.querySelectorAll('#productTableBody tr');
        const products = [];

        tableRows.forEach(function (row) {
            tenSanPham = row.cells[1].textContent;
            soLuong = row.cells[2].textContent;
            donViTinh = row.cells[3].textContent;
            thanhTien = row.cells[4].textContent.trim().split(' ')[0]; // Lấy giá trị đơn giá không có ký tự 'đ'
            ghiChu = row.cells[5].textContent;
            console.log(ghiChu);
            // Tạo object sản phẩm và thêm vào mảng products
            let product = {
                tenSanPham: tenSanPham,
                soLuong: soLuong,
                donViTinh: donViTinh,
                thanhTien: thanhTien,
                ghiChu: ghiChu
            };
            products.push(product);
        });

        // Tạo object chứa dữ liệu để gửi đi
        const data = {
            ngayHoaDon: ngayHoaDon,
            products: products
        };

        // Gửi dữ liệu đi bằng phương thức POST
        fetch('luuhoadon', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
                .then(response => {
                    if (response.ok) {
                        alert('Hóa đơn đã được lưu thành công!');
                        // Sau khi lưu thành công, có thể thực hiện các hành động khác ở đây
                        window.location.reload();
                    } else {
                        throw new Error('Lỗi khi lưu hóa đơn');
                    }
                })
                .catch(error => {
                    console.error('Lỗi:', error);
                    alert('Đã xảy ra lỗi khi lưu hóa đơn');
                });
    });





});


