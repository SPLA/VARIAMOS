productline(L):-
fd_set_vector_max(10010),
L=[Root,F1,F2,F3,F4],
fd_domain([Root,F1,F2,F3,F4], 0, 1),
Root#=1,
Root#<=>F1,
F1#<=>F2,
F1#<=>F4,
F1#>=F3,
(1-F4)+(1-F3)#>0,
(1-F3)+(1-F2)#>0,
fd_labeling(L).
