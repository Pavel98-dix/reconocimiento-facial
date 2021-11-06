<?php

// incluimos el archivo conexion.php
include 'conexion.php';

$id_user = $_GET['id_user'];

$consulta = "select * from clinical_information where id_user = '$id_user'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
    $clinical_information[] = array_map('utf8_encode', $fila);
}

echo json_encode($clinical_information);
$resultado -> close();

?>