
document.addEventListener("DOMContentLoaded", function () {
    setTimeout(function () {
        var options = document.querySelectorAll(".option");
        console.log("hi");
        options.forEach(function (option) {
            option.style.opacity = 1;  /* Hiển thị các thẻ lên */
        });
    }, 500);  // Sau 3 giây (3000 miligiây)


    document.getElementById('btnTaoBaoCao').addEventListener('click', function () {
        // Ngày đầu tiên của tháng
        var today = new Date();
        var startDate = new Date(today.getFullYear(), today.getMonth(), 1);

        // Ngày cuối cùng của tháng
        var endDate = new Date(startDate.getFullYear(), startDate.getMonth() + 1, 0);

        // Format ngày tháng năm theo định dạng 'dd/MM/yyyy'
        var formattedStartDate = formatDate(startDate);
        var formattedEndDate = formatDate(endDate);

        // Gửi dữ liệu POST đến server
        fetch('taobaocaohd', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                startDate: formattedStartDate,
                endDate: formattedEndDate
            })
        })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log('Response from server:', data);
                    // Xử lý kết quả từ server
                    if (data.success) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Tạo báo cáo tháng thành công!',
                            text: data.message
                        });
                    } else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Tạo báo cáo tháng thất bại!',
                            text: data.message
                        });
                    }
                })
                .catch(error => {
                    console.error('Error sending data to server:', error);
                    // Xử lý lỗi nếu có
                    Swal.fire({
                        icon: 'error',
                        title: 'Lỗi!',
                        text: 'Đã xảy ra lỗi khi gửi dữ liệu đến server.'
                    });
                });
    });

// Hàm để định dạng ngày tháng năm thành 'dd/MM/yyyy'
    function formatDate(date) {
        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear();
        return `${day}/${month}/${year}`;
    }

});