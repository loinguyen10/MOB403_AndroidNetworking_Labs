<?php
//0. tạo mảng chứa kết quả JSON
$response  = array();
//1. Khai báo thông tin kết nối với cơ sở dữ liệu
$server="localhost";  // thông tin server 
$u="id20982211_ljlj";  // tên user
$p="LLLL.llll.1234"; // mât khẩu
$db="id20982211_test"; // tên database
//2. Tạo kết nối tới CSDL
$conn = new mysqli($server,$u,$p,$db);
//3. kiểm tra kết nối
if($conn->connect_error){
    die("Error connecting to database");
 
}
//4. Kiểm tra chuyển dữ liệu vào API
// hàm ( isset ) kiểm tra xem có trống hay không
if(isset($_GET['name']) && isset($_GET['age']) && isset($_GET['id']) && isset($_GET['price'])){
    $id = $_GET['id'];//sau khi lấy dữ liệu từ biến id thành công rồi truyền dữ liêu từ biến id
    $name = $_GET['name'];// tương tự
    $age = $_GET['age'];// tương tự
    $price = $_GET['price'];// tương tự

    // 5. sau khi tiến hành truyền đữ liệu tiến hành thêm dữ liệu vào bảng
    $sql = "UPDATE user SET name='$name', age='$age', price='$price' WHERE id='$id'";
    if($conn->query($sql) == TRUE){
$response['success']=1;
$response['message']='update user successfully';
//chuyển dữ liệu sang json
// in dữ liệu ra màn hình (echo , printf, sout)
echo json_encode($response);
    }
    else {
        $response['success'] = 0;
        $response['message'] = 'Update user unsuccessfully: ' . mysqli_error($conn);
        echo json_encode($response);
    }
}
$conn->close();// sau khi hoàn thành sẽ đóng kết nối
?>