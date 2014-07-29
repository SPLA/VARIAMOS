:-use_module(library(clpfd)).
productline(L):- L = [Player, HighDefinition, Connectivity, Wifi, _3G], L ins 0..1,
Player #= 1,
Player #<==> HighDefinition,
Player #>= Connectivity,
Connectivity*1 #=< Wifi + _3G,
Wifi + _3G #=< Connectivity*1,
HighDefinition #==>Wifi,
(1 - HighDefinition) + (1- Connectivity) #> 0,
label(L).
