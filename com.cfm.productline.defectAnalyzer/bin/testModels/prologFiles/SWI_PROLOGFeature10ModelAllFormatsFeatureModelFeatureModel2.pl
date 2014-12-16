:-use_module(library(clpfd)).
productline(L):-
L=[Root,F1,F2,F3,F4,F5,F6,F8,F9,F7],
L ins 0..1,
Root#=1,
label(L).
