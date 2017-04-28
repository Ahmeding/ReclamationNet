<?php
require_once 'include/_DB_RecFunction.php';
$db =  new DB_RecFunction();
$response= array();
if(isset($_POST['refRec']))//&& isset($_POST['amorce_TRec']) &&isset($_POST['tete_TRec'])&&isset($_POST['couleur_TRec'])&&isset($_POST['observeTech'])&&isset($_POST['etat'] && isset($_POST['sync']))
{
	
	//amorce_TRec couleur_TRec tete_DRec amorce_DRec couleur_DRec observeTech etat sync tete_TRec 
	$amorce_TRec=$_POST['amorce_TRec'];
	$couleur_TRec=$_POST['couleur_TRec'];	
	$tete_DRec=$_POST['tete_DRec'];
	$amorce_DRec=$_POST['amorce_DRec'];
	$couleur_DRec=$_POST['couleur_DRec'];
	$observeTech=$_POST['observeTech'];
	$etat=$_POST['etat'];
	$refRec=$_POST['refRec'];
	$sync=$_POST['sync'];
	$tete_TRec=$_POST['tete_TRec'];

	/*******************************************/

	$reclamation = $db->updateRec($refRec, $tete_TRec, $amorce_TRec,$couleur_TRec, $tete_DRec, $amorce_DRec, $couleur_DRec, $observeTech, $etat,$sync);
	if($reclamation){
		$response["reclamation"]["refRec"]=$reclamation["refRec"];
		$response["reclamation"]["tete_TRec"]=$reclamation["tete_TRec"];
		$response["reclamation"]["amorce_TRec"]=$reclamation["amorce_TRec"];
		$response["reclamation"]["couleur_TRec"]=$reclamation["couleur_TRec"];
		$response["reclamation"]["tete_DRec"]=$reclamation["tete_DRec"];
		$response["reclamation"]["amorce_DRec"]=$reclamation["amorce_DRec"];
		$response["reclamation"]["couleur_DRec"]=$reclamation["couleur_DRec"];
		$response["reclamation"]["etat"]=$reclamation["etat"];
		$response["reclamation"]["sync"]=$reclamation["sync"];
		$response["reclamation"]["observeTech"]=$reclamation["observeTech"];

		echo json_encode($response);
	}
	else{
		$response["error"]=False;
		$response["error_MSG"]="Unknown error occurred in updatting";
		echo json_encode($response);
	}


}else{

	$response["error"]=TRUE;
	$response["error_MSG"]="Error : POST Method (Parametre is messing)!";
	echo json_encode($response);
}

	/*********************************************/		




	$reclamations=$db->getRecs();
	
?>