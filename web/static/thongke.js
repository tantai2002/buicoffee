document.addEventListener("DOMContentLoaded", function () {
    // Lấy tất cả các thẻ <td> với class 'ngay'
    const ngayElements = document.querySelectorAll('td.ngay');

    // Hàm chuyển đổi định dạng ngày tháng
    function formatDate(dateString) {
        const [year, month, day] = dateString.split('-');
        // Chuyển đổi định dạng từ YYYY-MM-DD thành DD/MM/YYYY
        return `${day}/${month}/${year}`;
    }

    // Duyệt qua tất cả các thẻ và cập nhật nội dung
    ngayElements.forEach(function (td) {
        const originalDate = td.textContent.trim();
        const formattedDate = formatDate(originalDate);
        td.textContent = formattedDate;
    });


    

});


