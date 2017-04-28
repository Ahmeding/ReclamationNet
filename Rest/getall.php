<?php
require_once 'include/DB_RecFunction.php';
$db = new DB_RecFunction();

$response= array("error"=>FALSE);
$reclamations = array();

$reslt= $db->getRecs();
if($reslt->num_rows > 0){

while($row=$reslt->fetch_array()){
	array_push($reclamations,array(
			
			"idReclamation"=>$row['_id'],
			"titre"=>$row['titre'],
			"dateRec"=>$row['dateRec'], 
			"etat"=>$row['etat'],
			"refRec"=>$row['refRec'] ,
			"_client"=>$row['_client'] , 
			"adresse"=>$row['adresse'],
	   		"contactTel"=>$row['contactTel'],
	       	"rg_Rec"=>$row['RG_Rec'], 
	    	"sr_Rec"=>$row['SR_Rec'], 
	    	"tete_TRec"=>$row['tete_TRec'], 
	    	"amorce_TRec"=>$row['amorce_TRec'], 
	    	"couleur_TRec"=>$row['couleur_TRec'] , 
	    	"tete_DRec"=>$row['tete_DRec'], 
	    	"amorce_DRec"=>$row['amorce_DRec'], 
	    	"couleur_DRec"=>$row['couleur_DRec'], 
	    	"sgnlPar"=>$row['sgnlPar'],
	    	"observInit"=>$row['observInit'], 
			"observeTech"=>$row['observeTech'], 
			"description"=>$row['description']
			 
		));
			

}
echo json_encode($reclamations);
//echo json_encode(array("reclamations"=>$reclamations));

}else{
	$response=array("isEmpty"=>true);
	echo json_encode($response);
}
?>