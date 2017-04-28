<?php 
class DB_RecFunction{
	
	private $conn;


	function __construct(){
		require_once 'DB_connect.php';
		$db = new DB_connect();
		$this->conn=$db->connect();
	}

	function __destruct(){

}
public function updateRec($idReclamation, $tete_TRec,$tete_DRec, $amorce_TRec, $amorce_DRec ,$couleur_TRec, $couleur_DRec, $observeTech, $etat,$sync){

$query= "UPDATE reclamation SET tete_TRec = ?, amorce_TRec = ?, couleur_TRec = ?, tete_DRec = ?, amorce_DRec = ?, couleur_DRec = ?,observeTech = ? , etat = ? , sync = ? WHERE _id = ?;";
		
if( ! $stmt = $this->conn->prepare($query))
{
		echo 'Error:'. $this->conn->error;
		return false;
	}
else
{
$stmt->bind_param('ssssssssii', $tete_TRec, $amorce_TRec, $couleur_TRec, $tete_DRec, $amorce_DRec, $couleur_DRec, $observeTech, $etat,$sync , $idReclamation);
$reslt=$stmt->execute();
$stmt->close();
if($reslt){
	$stmt=$this->conn->prepare("SELECT * FROM reclamation WHERE _id=?");
	$stmt->bind_param("i",$idReclamation);
	$stmt->execute();
	$reclamation = $stmt->get_result()->fetch_assoc();
	$stmt->close();
	return $reclamation;
}else{
	return false;
}

}
}
/***********************************************/

public function getRecs(){

$reslt = array();
	

	$query="SELECT * FROM reclamation WHERE etat=?";


	if(!$stmt=$this->conn->prepare($query)){

		echo 'Error:'. $this->conn->error;
		return false;
	}


	else{
		   $etat="En attente";
		    $stmt->bind_param("s",$etat);
		    $stmt->execute();
			$reslt = $stmt->get_result();
			//$stmt->close();
			return $reslt;
		}
}


}



?>