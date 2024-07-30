document.addEventListener('DOMContentLoaded', function () {
    var checkIcons = document.querySelectorAll('.btncheck');
    checkIcons.forEach(function(icon) {
        icon.addEventListener('click', function() {
            // Lấy các giá trị từ các ô trong hàng tương ứng
            var tr = this.closest('tr');
            var id = tr.cells[0].textContent;
            var tenSanPham = tr.cells[1].textContent;
            var donViTinh = tr.cells[2].textContent;
            var soLuong = tr.cells[3].textContent;
            var thanhTien = tr.cells[4].textContent;
            var ghiChu = tr.cells[5].textContent;
            console.log(donViTinh);

            // Gán các giá trị vào các trường input trong form
            document.getElementById('idSanPham').value = id;
            document.getElementById('tenSanPham').value = tenSanPham;
            document.getElementById('donViTinh').value = donViTinh;
            document.getElementById('soLuong').value = soLuong;
            document.getElementById('thanhTien').value = thanhTien;
            document.getElementById('ghiChu').value = ghiChu;
        });
    });
});