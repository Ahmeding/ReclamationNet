<?php


require_once 'include/DB_functions.php';
$db=new DB_functions();
$response = array ("error" => FALSE);

if(isset($_POST['email'] ) && isset($_POST['password'] )){
 
 //reciving the post params
  $email = $_POST['email']; 
  $password = $_POST['password'];

//get the user by email and password 

  $user = $db->getUserByEmailAndPassword($email,$password);

   if($user !=false){
   //user is found
    $response['error']=FALSE;
    $response ['user']["name"]=$user["name"];
    $response ['user']["password"]=$user["password"];
    $response ['user']["email"]=$user["email"];

    echo json_encode($response);
  }else {
//user is not found with the credentials
    $response["error"]=TRUE;
    $response["error_MSG"]="Login credentials are wrong. Please try again!";
    echo json_encode($response);
      }
 }else{
  //required post params is missing
  $response["error"]=TRUE;
  $response["error_MSG"]="Required parameters email or password is missing!";
 }


?>