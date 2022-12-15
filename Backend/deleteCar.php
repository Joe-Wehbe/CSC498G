<?php
if(isset($_POST['car_id'])) {
    require_once "connection.php";
    require_once "validate.php";

    $id = validate($_POST['car_id']); 

    $query = $mysqli-> prepare("DELETE FROM cars WHERE id = ?");
    $query->bind_param("s", $id);
    $query->execute();

    echo 'success';
}else{
    echo 'failure';
}