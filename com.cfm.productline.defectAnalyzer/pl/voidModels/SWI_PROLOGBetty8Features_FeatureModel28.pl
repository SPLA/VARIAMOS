:-use_module(library(clpfd)).
productline(L):-
L=[Root,F1,F2,F7,F3,F4,F5,F6],
L ins 0..1,
Root #= 1,
Root#<==>F1,
F2#<==>F7,
F3#<==>F4,
F3#<==>F5,
Root#>=F2,
Root#>=F3,
F3#>=F6,
(1 - F2) + (1 - F3) #> 0,
(1 - F5) + (1 - F4) #> 0,
(1 - F1) + F5 #> 0,
label(L).
