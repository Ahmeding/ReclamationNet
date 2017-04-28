		<?php

		require_once 'include/DB_functions.php';
		$db = new DB_functions();

		$response = array("error"=>FALSE);

		if(isset($_POST['name']) && isset($_POST['password']) && isset($_POST['email']))
		{
		    // receiving the post params
			$name=$_POST['name'];
			$email=$_POST['email'];
			$password=$_POST['password'];
		    // check if user is already existed with the same email
			if($db->isUserExisted($email)){
				// user already existed
				$response["error"]=TRUE;
				$response["error_MSG"]="User already existedwhith ".$email;
				echo json_encode($response);
			}else{
				  // create a new user

		$user = $db->storeUser($name,$email,$password);
		if($user){

			$response[""]=FALSE;
			$response["user"]["name"]=$user["name"];
			$response["user"]["email"]=$user["email"];
			$response["user"]["password"]=$user["password"];
			echo json_encode($response);

		}else{
			// User failed to Store
			$response["error"]=TRUE;
			$response["error_MSG"]="Unknown error occurred in registration";
			echo json_decode($response);
		}
	}
}else{
	$response["error"]=TRUE;
	$response["error_MSG"]="Required parameters(name,email,password)is missing!";
	echo json_encode($response);
}

		?>