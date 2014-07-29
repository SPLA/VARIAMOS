productline(L):-
fd_set_vector_max(10010),
L=[Root,F1,F4,F3,F2],
fd_domain([Root,F1,F4,F3,F2], 0, 1),
Root#=1,
F1#<=>F4,
Root#<=>F2,
Root#>=F1,
Root#>=F3,
(1-F4)+(1-F3)#>0,
(1-F1)+(1-F2)#>0,
fd_labeling(L).