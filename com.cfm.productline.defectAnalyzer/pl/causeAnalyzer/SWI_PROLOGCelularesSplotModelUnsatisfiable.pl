:-use_module(library(clpfd)).
productline(L):-
L=[Celular,Utilidades,LLamadas,Mensajes,MSN,SMS,
Juegos,Configuracion,SO,Soporte_Java,Multimedia,MP3,MP4,Camara,VGA,Mexapixeles],
L ins 0..1,
Mexapixeles#= 1,
Celulares#<==>Configuracion,
Configuracion#<==>SO,
label(L).
