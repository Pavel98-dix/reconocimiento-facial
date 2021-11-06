<?php

// incluimos el archivo conexion.php
include 'conexion.php';

$id_user = $_POST['id'];

$consultaName = "SELECT * FROM users WHERE id_user = '$id_user'";
$resultadoName = mysqli_fetch_array(mysqli_query($conexion,$consultaName));
$consultaCI = "SELECT * FROM clinical_information WHERE id_user = '$id_user'";
$resultadoCI = mysqli_fetch_array(mysqli_query($conexion,$consultaCI));


if(isset($resultadoCI)){
    $response["error"] = false;
    $response["name"] = $resultadoName[1];
    $response["lastname"] = $resultadoName[2];
    $response["suffering"] = $resultadoCI[1];
    $response["medicine"] = $resultadoCI[2];
    $response["indications"] = $resultadoCI[3];
    $response["name_clinic"] = $resultadoCI[4];
    $response["note"] = $resultadoCI[5];

}else {
    $response["error"] = true;

}

mysqli_close($conexion);
echo json_encode($response);

?>