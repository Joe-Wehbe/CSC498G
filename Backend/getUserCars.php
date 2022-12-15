<?php
include("connection.php");

$user_id = (int)$_GET["id"];

$query = $mysqli->prepare("SELECT * FROM cars WHERE user_id = ?");
$query->bind_param("s", $user_id);
$query->execute();
$result = $query->get_result();

$cars = array();

if($result->num_rows > 0){

    while ($row = $result->fetch_assoc()){

        $temp = array();
        
        $temp['brand'] = $row['brand'];
        $temp['plate'] = $row['plate'];
        $temp['id'] = $row['id'];

        array_push($cars, $temp);
    }
    echo json_encode($cars);  
}
?>

