productline(L):-
fd_set_vector_max(10010),
L = [Var_Car, Var_Automated_Driving_Controller, Var_Collision_Avoidance_Braking, Var_Standard_Avoidance, Var_Enhanced_Avoidance, Var_Parallel_Parking, Var_Sensors, Var_Lateral_Range_Finder, Var_Forward_Range_Finder],
fd_domain([Var_Car, Var_Automated_Driving_Controller, Var_Collision_Avoidance_Braking, Var_Standard_Avoidance, Var_Enhanced_Avoidance, Var_Parallel_Parking, Var_Sensors, Var_Lateral_Range_Finder, Var_Forward_Range_Finder], 0, 1),
Var_Car #= 1, Var_Automated_Driving_Controller #<=> Var_Collision_Avoidance_Braking,
Var_Car #<=> Var_Sensors,
Var_Car #<=> Var_Automated_Driving_Controller #>= 0,
Var_Automated_Driving_Controller #<=> Var_Parallel_Parking #>= 0,
Var_Sensors #<=> Var_Lateral_Range_Finder #>= 0,
Var_Sensors #<=> Var_Forward_Range_Finder #>= 0,
(1 - Var_Enhanced_Avoidance) + Var_Forward_Range_Finder #> 0,
(1 - Var_Parallel_Parking) + Var_Lateral_Range_Finder #> 0,
1 * Var_Collision_Avoidance_Braking #= Var_Enhanced_Avoidance + Var_Standard_Avoidance,
fd_labeling(L).
