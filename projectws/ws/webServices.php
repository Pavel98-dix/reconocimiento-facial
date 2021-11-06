<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    include('conexion.php');
    $conn = mysqli_connect($hostname,$username,$password,$database);

    switch ($_POST['opcion']){

        // ======================== LOGIN - INICIO DE SESION JEJE OBVI =========================================================================================================================================================================
        case 'login':
            $email = $_POST['email'];
            $password = $_POST['password'];

            $query = "SELECT * FROM users WHERE email = '$email' AND password ='$password'";
            $res = mysqli_fetch_array(mysqli_query($conn,$query));

            if(isset($res)){
                $response["error"] = false;
                $response["mensaje"] = "Datos correctos";
                $response["id"] = $res[0];
                $response["nombre"] = $res[1];
                $response["apellidos"] = $res[2];
            }else {
                $response["error"] = true;
                $response["mensaje"] = "Datos in-correctos";
            }
        break;


        // ======================== REGISTRO I - DATOS PERSONALES =========================================================================================================================================================================
        case 'registroI':
            $name = $_POST['name'];
            $last_name = $_POST['last_name'];
            $email = $_POST['email'];
            $type_user = $_POST['type_user'];
            $password = $_POST['password'];
            
            $queryy = "INSERT INTO users VALUES('','".$name."','".$last_name."','".$email."','".$type_user."','".$password."')";
            $ress = mysqli_query($conn,$queryy);
            $query = "SELECT * FROM users WHERE email = '$email' AND password ='$password'";
            $res = mysqli_fetch_array(mysqli_query($conn,$query));

            if(isset($res)){
                $response["error"] = false;
                $response["mensaje"] = "Datos correctos";
                $response["id"] = $res[0];
                $response["nombre"] = $res[1];
                $response["apellidos"] = $res[2];
            }else {
                $response["error"] = true;
                $response["mensaje"] = "Datos in-correctos";
            }
        break;

        
        // ======================== REGISTRO II - DATOS CLINICOS =========================================================================================================================================================================
        case 'registroII':
            $suffering = $_POST['suffering'];
            $medicine = $_POST['medicine'];
            $indications = $_POST['indications'];
            $name_clinic = $_POST['name_clinic'];
            $notes = $_POST['notes'];
            
            $query = "INSERT INTO clinical_information VALUES('','".$suffering."','".$medicine."','".$indications."','".$name_clinic."','".$notes."')";
            $res = mysqli_query($conn,$query);
            

            if(isset($res)){
                $response["error"] = false;
                $response["mensaje"] = "Datos correctos";
        
            }else {
                $response["error"] = true;
                $response["mensaje"] = "Datos in-correctos";
            }
        break;


        // ======================== REGISTRO III - PHOTO =========================================================================================================================================================================
        case 'registroIII':
            $suffering = $_POST['suffering'];
            $medicine = $_POST['medicine'];
            $indications = $_POST['indications'];
            $name_clinic = $_POST['name_clinic'];
            $notes = $_POST['notes'];
            
            $query = "INSERT INTO clinical_information VALUES('','".$suffering."','".$medicine."','".$indications."','".$name_clinic."','".$notes."')";
            $res = mysqli_query($conn,$query);
            

            if(isset($res)){
                $response["error"] = false;
                $response["mensaje"] = "Datos correctos";
        
            }else {
                $response["error"] = true;
                $response["mensaje"] = "Datos in-correctos";
            }
        break;
    }
    /* cerrar la conexion a la base de datos */
    mysqli_close($conn);
    echo json_encode($response);
}