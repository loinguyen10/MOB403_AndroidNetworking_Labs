<?php
$server = "localhost";  // thông tin server
$u="id20982211_ljlj";  // tên user
$p="LLLL.llll.1234"; // mât khẩu
$db="id20982211_test"; // tên database

// Tạo kết nối tới CSDL
$conn = new mysqli($server, $u, $p, $db);

// Kiểm tra kết nối
if ($conn->connect_error) {
    die("Error connecting to database: " . $conn->connect_error);
}
// Thực hiện câu lệnh truy vắn
$sql = "SELECT * FROM user";
//tạoh biến chứa kết quả sau khi kết nối db và tiến hannf query dữ liệu
$result = $conn->query($sql);

if ($result->num_rows > 0) { 
    $rows = array();// tạo mảng chứa kết quả
    while ($row = $result->fetch_assoc()) {
        $rows[] = $row;
    }
// mã hoá lại kết quả và đưa về định dạng json
    $json = json_encode($rows);
    echo '{"User":' . $json . '}';
} else {
    echo "No users found.";
}

$conn->close();
?>
