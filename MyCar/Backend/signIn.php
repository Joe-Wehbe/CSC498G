<?php
if(isset($_POST['email']) && isset($_POST['password'])) {
    require_once "connection.php";
    require_once "validate.php";

    $email = validate($_POST['email']);
    $password = validate(sha1($_POST['password']));

    $query = $mysqli->prepare("SELECT * FROM users WHERE email = ? AND password = ?");
    $query->bind_param("ss",$email, $password);
    $query->execute();
    $result = $query->get_result();

    if($result->num_rows > 0){

        $row = $result->fetch_assoc(); 

        $query1 = $mysqli->prepare("SELECT * FROM cars WHERE user_id = ?");
        $query1->bind_param("s", $row['id']);
        $query1->execute();
        $result1 = $query1->get_result();

        if($result1->num_rows > 0){
            echo 1 . $row['id'];
        }
        else{
            echo 0 . $row['id'];
        }
    }
    else{
        echo 'failure';
    }
}
?>

