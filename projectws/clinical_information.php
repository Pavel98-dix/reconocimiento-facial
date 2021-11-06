<?php

// incluimos el archivo conexion.php
include 'conexion.php';

// script encargado de recibir las variables que encargaran de recivir los valores enviados desde la app
$suffering=$_POST['suffering'];
$medicine=$_POST['medicine'];
$indications=$_POST['indications'];
$name_clinic=$_POST['name_clinic'];
$notes=$_POST['notes'];

// variable con la consulta (de insercion) sql a ejecutar
$consulta="insert into clinical_information values('','".$suffering."','".$medicine."','".$indications."','".$name_clinic."','".$notes."')";
// ejecutamos la consulta sql declarada 
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
?>