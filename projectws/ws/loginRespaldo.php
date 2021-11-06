<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    include('conexion.php');
    $conn = mysqli_connect($hostname,$username,$password,$database);

    switch ($_POST['opcion']){
        case 'login':
            $email = $_POST['email'];
            $password = $_POST['password'];

            $query = "SELECT * FROM users WHERE email = '$email' AND password ='$password'";
            $search_id = "SELECT id_user FROM users WHERE email = '$email' AND password ='$password'";
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
    }
    /* cerrar la conexion a la base de datos */
    mysqli_close($conn);
    echo json_encode($response);
}