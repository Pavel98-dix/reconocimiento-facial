<?php

// incluimos el archivo conexion.php
include 'conexion.php';

// script encargado de recibir las variables que encargaran de recivir los valores enviados desde la app
$name=$_POST['name'];
$last_name=$_POST['last_name'];
$email=$_POST['email'];
$type_user=$_POST['type_user'];
$password=$_POST['password'];

// variable con la consulta (de insercion) sql a ejecutar
$consulta="insert into users values('','".$name."','".$last_name."','".$email."','".$type_user."','".$password."')";
// $consulta="insert into users values('".$id_user."','".$name."','".$last_name."','".$email."','".$password."')";
// ejecutamos la consulta sql declarada 
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);
?>