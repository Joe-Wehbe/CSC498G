<?php
if(isset($_POST['brand']) && isset($_POST['model']) && isset($_POST['color']) && isset($_POST['plate']) && isset($_POST['user_id'])){
    require_once "connection.php";
    require_once "validate.php";

    $brand = validate($_POST['brand']);
    $model = validate($_POST['model']);
    $color = validate($_POST['color']);
    $plate = validate($_POST['plate']);
    $user_id = validate($_POST['user_id']);

    $query = $mysqli->prepare("SELECT * FROM cars WHERE plate = ?");
    $query->bind_param("s", $plate);
    $query->execute();
    $result = $query->get_result();

    if($result->num_rows > 0){
        echo 'exists';
    }
    else{
        $query1 = $mysqli->prepare("INSERT INTO cars(brand, model, color, plate, engine_oil, engine_coolant, transmission_fluid,
        power_steering_fluid, breaks_fluid, user_id) VALUES(?,?,?,?,100,100,100,100,100,?)");
        $query1->bind_param("sssss", $brand, $model, $color, $plate, $user_id);
        $query1->execute();

        echo 'success';

    }
}
?>