<?php


    include('conexion.php');
    $conn = mysqli_connect($hostname,$username,$password,$database);

            $email = 'cristian@tescha.com';
            $password = 'secreta%';

            $query = "SELECT * FROM users WHERE email = '$email' AND password ='$password'";
            $res = mysqli_fetch_array(mysqli_query($conn,$query));

            if(isset($res)){
                $response["error"] = false;
                $response["mensaje"] = "Datos correctos";
            }else {
                $response["error"] = true;
                $response["mensaje"] = "Datos in-correctos";
            }
        
    
    /* cerrar la conexion a la base de datos */
    mysqli_close($conn);
    echo json_encode($response);
}