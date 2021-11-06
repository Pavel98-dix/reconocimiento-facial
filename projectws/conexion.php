<?php

// credenciales
$hostname='localhost';
$database='proyect';
$username='root';
$password='';

// nos permitira establecer conexion 
$conexion=new mysqli($hostname,$username,$password,$database);
// validar la conexion con la base de datos 
if($conexion->connect_errno){
    echo "lo sentimos, el sitio web TIENE PROBLEMAS";
}
?>