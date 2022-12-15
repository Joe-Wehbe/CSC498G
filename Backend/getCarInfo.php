<?php
include("connection.php");

$user_id = (int)$_GET["id"];
$car_id = (int)$_GET["car_id"];

$query = $mysqli->prepare("SELECT * FROM cars WHERE id = ? AND user_id = ?");
$query->bind_param("ss", $car_id, $user_id);
$query->execute();
$result = $query->get_result();

$cars = array();

if($result->num_rows > 0){

    $row = $result->fetch_assoc();

    $temp = array();
 
    $temp['brand'] = $row['brand'];
    $temp['model'] = $row['model'];
    $temp['color'] = $row['color'];
    $temp['plate'] = $row['plate'];

    array_push($cars, $temp);
    echo json_encode($cars);  
}
?>

