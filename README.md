La BD esta en postgresql con el nombre de examen (puede cambiarlo) y tener en consideración el password 
Para hacer pruebas en el postman tener en consideración los estados

Para los estados colocarlo de acuerdo
al Enum de EstadoPedido y EstadoPersona, colocarlo en mayuscula

La estructura es:

{
"id": ,
"nombres": "",
"apellidos": "",
"numDocumento": 1234567,
"estado": "ACTIVO",
"direccionEntity": {
"id": ,
"avenida": "",
"numero": "",
"distrito": "",
"provincia": "",
"departamento": ""
},
"pedido": [
{
"id": ,
"descripcion": "",
"cantidad": ,
"estado": "CONFIRMADO"
},
{
"id": ,
"descripcion": "",
"cantidad": ,
"estado": "PROCESO"
}
]
}

Con eso me retiro, de parte de:
Ian Zahir De la Cruz Sayago