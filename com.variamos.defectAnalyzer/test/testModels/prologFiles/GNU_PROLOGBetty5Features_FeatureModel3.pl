productline(L):-
fd_set_vector_max(10010),
L=[Root,F1,F4,F2,F3],
fd_domain([Root,F1,F4,F2,F3], 0, 1),
Root#=1,
Root#<=>F1,
F1#<=>F4,
Root#<=>F3,
Root#>=F2,
(1-F2)+F4#>0,
(1-F4)+(1-F3)#>0,
fd_labeling(L).
