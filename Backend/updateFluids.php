<?php

if(isset($_POST['eo']) && isset($_POST['ec']) && isset($_POST['tf']) && isset($_POST['psf']) 
    && isset($_POST['bf']) && isset($_POST['user_id']) && isset($_POST['car_id'])){

    require_once "connection.php";
    require_once "validate.php";

    $eo = validate($_POST['eo']);
    $ec = validate($_POST['ec']);
    $tf = validate($_POST['tf']);
    $psf = validate($_POST['psf']);
    $bf = validate($_POST['bf']);
    $user_id = validate($_POST['user_id']);
    $car_id = validate($_POST['car_id']);

    $query = $mysqli-> prepare("UPDATE cars SET engine_oil = $eo, engine_coolant = $ec, transmission_fluid = $tf, 
                                power_steering_fluid = $psf, breaks_fluid = $bf WHERE id = $car_id AND user_id = $user_id");
    $query->execute();

    echo 'success';   
} else{
    echo 'failure';
}
