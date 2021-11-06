<?PHP
$hostname_localhost="localhost";
$database_localhost="proyect";
$username_localhost="root";
$password_localhost="";

$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

	$id = $_POST["id"];
	$imagen = $_POST["imagen"];

	$path = "imagenes/0.jpg";

	$url = "http://$hostname_localhost/projectws/$path";
	

	file_put_contents($path,base64_decode($imagen));
	$bytesArchivo=file_get_contents($path);

	$sql="INSERT INTO photo_help (id_user,imagen,ruta_imagen)VALUES (?,?,?)";
	$stm=$conexion->prepare($sql);
	$stm->bind_param('iss',$id,$bytesArchivo,$url);
	
		
	if($stm->execute()){
        $response["error"] = false;
        $response["id_help"] = 1;
        $response["nombre_help"] = "cris";
        $response["apellidos_help"] = "pena";
	}else{
		$response["error"] = true;
	}
	
    mysqli_close($conexion);
    echo json_encode($response);
?>