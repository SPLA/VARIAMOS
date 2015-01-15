:-use_module(library(clpfd)).
productline(L):-
L=[GeneralFeature1_IsRootFeature,GeneralFeature2_IsRootFeature,GeneralFeature3_IsRootFeature,GeneralFeature4_IsRootFeature,GeneralFeature5_IsRootFeature,GeneralFeature6_IsRootFeature,GeneralFeature7_IsRootFeature,GeneralFeature8_IsRootFeature,Model_roots,RootFeature1_IsRootFeature,RootFeature2_IsRootFeature,RootFeature3_IsRootFeature] ,
GeneralFeature1_IsRootFeature in 0..1,GeneralFeature2_IsRootFeature in 0..1,GeneralFeature3_IsRootFeature in 0..1,GeneralFeature4_IsRootFeature in 0..1,GeneralFeature5_IsRootFeature in 0..1,GeneralFeature6_IsRootFeature in 0..1,GeneralFeature7_IsRootFeature in 0..1,GeneralFeature8_IsRootFeature in 0..1,Model_roots in 0..1,RootFeature1_IsRootFeature in 0..1,RootFeature2_IsRootFeature in 0..1,RootFeature3_IsRootFeature in 0..1,
RootFeature2_IsRootFeature #= 1,
RootFeature3_IsRootFeature #= 1,
(Model_roots #<==> (RootFeature1_IsRootFeature + (RootFeature3_IsRootFeature + (RootFeature2_IsRootFeature + (GeneralFeature1_IsRootFeature + (GeneralFeature2_IsRootFeature + (GeneralFeature3_IsRootFeature + (GeneralFeature4_IsRootFeature + (GeneralFeature5_IsRootFeature + (GeneralFeature6_IsRootFeature + (GeneralFeature7_IsRootFeature + (GeneralFeature8_IsRootFeature + 0))))))))))) #>= 2),
Model_roots #=< 0,
labeling( [ff] ,L).