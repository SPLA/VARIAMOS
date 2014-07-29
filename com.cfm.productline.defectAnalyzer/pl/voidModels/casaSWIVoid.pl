:-use_module(library(clpfd)).
productline(L):- L = [Casa, Parqueadero, Zona_recreacion, Piscina, Gimnasio], L ins 0..1,
Casa #= 1,
Casa #<==> Parqueadero,
Casa #<==> Zona_recreacion,
Zona_recreacion*1 #=< Piscina + Gimnasio,
Piscina + Gimnasio #=< Zona_recreacion*1,
Parqueadero #==>Piscina,
Parqueadero * Zona_recreacion #=0,
label(L).