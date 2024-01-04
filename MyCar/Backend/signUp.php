<?php
if(isset($_POST['first_name']) && isset($_POST['last_name']) && isset($_POST['country']) && isset($_POST['email']) && isset($_POST['password'])){
    require_once "connection.php";
    require_once "validate.php";

    $first_name = validate($_POST['first_name']);
    $last_name = validate($_POST['last_name']);
    $country = validate($_POST['country']);
    $email = validate($_POST['email']);
    $password = validate(sha1($_POST['password']));

    $query = $mysqli->prepare("SELECT * FROM users WHERE email = ?");
    $query->bind_param("s", $email);
    $query->execute();
    $result = $query->get_result();

    if($result->num_rows > 0){
        echo 'exists';
        
    }else{

        $query1 = $mysqli->prepare("INSERT INTO users(first_name, last_name, country, email, password) VALUES(?,?,?,?,?)");
        $query1->bind_param("sssss", $first_name, $last_name, $country, $email, $password);
        $query1->execute();

        $query2 = $mysqli->prepare("SELECT * FROM users WHERE email = ?");
        $query2->bind_param("s", $email);
        $query2->execute();
        $result2 = $query2->get_result();

        if($result2->num_rows > 0){
            $row = $result2->fetch_assoc();
            echo $row['id'];
        }
        else{
            echo 'failure';
        }
    }  
}
?>