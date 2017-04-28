<?php

class DB_functions{
private $conn;

function __construct(){

	require_once 'DB_connect.php';

	$db = new DB_connect();
	$this->conn= $db->connect();

}

function __destruct(){

}

/**
     * Storing new user
     * returns user details
     */

public function storeUser($name,$email,$password){

	if( ! $stmt = $this->conn->prepare("INSERT INTO users(name,email,password) VALUES(?,?,?)"))
	{
		echo 'Error:'. $this->conn->error;
		return false;
	}
	else{
	$stmt->bind_param("sss",$name,$email,$password);
	$reslt=$stmt->execute();
	$stmt->close();

	if($reslt){
	$stmt= $this->conn->prepare("SELECT * FROM users WHERE email=?");
	$stmt->bind_param("s",$email);
	$stmt->execute();
	$user=$stmt->get_result()->fetch_assoc();
	$stmt->close();
	return $user;	
	}else{
		return false;
	}
	}
}

/**
     * Get user by email and password
     */

public function getUserByEmailAndPassword($email,$password){
	$stmt=$this->conn->prepare("SELECT * FROM users WHERE email=?");
	$stmt->bind_param("s",$email);

	if($stmt->execute()){
		$user = $stmt->get_result()->fetch_assoc();
		$stmt->close();

		if($password == $user['password']){
			return $user;
		}else{
			return NULL;
		}
	}
}

	/**
     * Check user is existed or not
     */
public function isUserExisted($email){
	$stmt = $this->conn->prepare("SELECT email FROM users WHERE email = ?");
	$stmt->bind_param("s",$email);
	$stmt->execute();
	$stmt->store_result();
	if($stmt->num_rows>0){
		$stmt->close();
		return true;
	}else{
		$stmt->close();
		return false;
	}
}


}