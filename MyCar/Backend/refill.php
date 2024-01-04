<?php

if(isset($_POST['user_id']) && isset($_POST['car_id']) && isset($_POST['column'])){
    require_once "connection.php";
    require_once "validate.php";

    $user_id = validate($_POST['user_id']);
    $car_id = validate($_POST['car_id']);
    $column = validate($_POST['column']);

    $query = $mysqli-> prepare("UPDATE cars SET $column = 100 WHERE id = $car_id AND user_id = $user_id");
    $query->execute();

    $query1 = $mysqli->prepare("SELECT * FROM cars WHERE id = ? AND user_id = ?");
    $query1->bind_param("ss", $car_id, $user_id);
    $query1->execute();
    $result1 = $query1->get_result();
    $row1 = $result1->fetch_assoc();

    if($row1[$column] == 100){
        echo 'success';
    }
    else{
        echo 'failure';
    }
}
