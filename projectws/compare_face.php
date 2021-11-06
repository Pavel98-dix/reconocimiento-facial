<?php
require 'vendor/autoload.php';
$link = mysqli_connect('localhost', 'root','','proyect');
  
// esta hara la comparación
  /*   */
    /* echo $result;
    
    
     */
    //{   
                
    //}else
    //{
    switch ($_POST['opcion']){
        
        case 'reconocimiento':
            //Primero obtenemos la cantidad de fotos en la base de datos.
            $rs = mysqli_query($link,"SELECT MAX(id_foto)FROM foto");
            if ($row = mysqli_fetch_row($rs)) {
            $id = trim($row[0]);

            } 
            //Esto permite que podamos hacer el reconocimiento. Son las credenciales
            $args=[
                'credentials'=>[
                    'key'=> 'AKIAJ7TTB2DJVZZ3MO5Q',
                    'secret'=>'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',
                ],
                'region'=>'us-east-2',
                'version'=>'latest'
                
            ];
            //Esta variable nos permitira observar que usuario es 
            $ultimo=0;
            
            //El ciclo nos permitira comparar con cada usuario que contamos
            for ($i = 1; $i <=$id; $i++) {
                $ultimo++;
                $client= new Aws\Rekognition\RekognitionClient($args);
                $ultimo++;
                $foto_bd=mysqli_query($link,"SELECT * FROM foto WHERE id_foto='$i'");
                $muestra_foto=mysqli_fetch_array($foto_bd);
                $temporal=$muestra_foto['imagen'];
                //$id_devuelto=$muestra_foto['id_user']; 
                //Mandamos a llamar al sdk y ocupamos el método de comparar rostros 
                $result= $client->compareFaces([
                    'SimilarityThreshold'=>70,
                    'SourceImage' =>[
                        'Bytes'=>file_get_contents($temporal),
                    ],
                    'TargetImage'=>[
                        'Bytes'=>file_get_contents("0.jpg"),
                    ],    
                    ]);
                    /* Cuando hay la persona es la misma, se podemos observar la 
                    palabra similary dentro de la cadena*/
                    $palabra_buscar='Similarity';
                    //Compaaramos si es que se encuentra la palabra
                    $posicion_coincidencia = strpos($result,$palabra_buscar);
                    if($posicion_coincidencia==false){
                        $error=false;
                        $mensaje="No se enceuntra en la base de datos";
                    }else{
                        $error=true;
                        $mensaje="Se ha encontrado la persona, exito";
                        break;
                    }


            
            }
            $response['error']=$error;
            $response['mensaje']=$mensaje;
            $response['id']=$ultimo;

        }
        mysqli_close($link);
        echo json_encode($response);
           /* 

        break;
        
    }
    //}
/* } */

echo json_encode($response);

?>