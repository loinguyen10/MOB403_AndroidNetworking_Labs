<?php
$server="localhost";  // thông tin server 
$u="id20982211_ljlj";  // tên user
$p="LLLL.llll.1234"; // mât khẩu
$db="id20982211_test"; // tên database

$conn = new mysqli($server,$u,$p,$db);
if($conn->connect_error){
    echo"connect error: ";
}
if(isset($_GET['id'])){
        $id = $_GET['id'];
    $sql="DELETE from user where id='$id'";

if($conn->query($sql)===true){
echo "delete thanh cong";
}
else{
echo "delete that bai: ".$conn->error;
}
}
$conn->close();
?>