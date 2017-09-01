productline(TabElec):-

TabElec = [Coffret,InterDiff, Repartition, Technologie, Marque, Disjoncteur, Circuit, CommandeEclairage, Programmation, ModuleParRangee, Rangee, TypeInterDiff, CalibreInterDiff, CalibreDisj, TypeDisj, Logement, Rehausse, Porte, DisjDiff, Parafoudre, CalibreDisjDiff, TypeDisjDiff,CommandeChauffageE, Fusible, TypeFusible, CalibreFusible, Encastre, Sailli, AvecRehausse, SansRehausse, AvecPorte, SansPorte, IDAC,IDA,IDAsi, ID25, ID40,ID63, Peigne, PeigneEmb,D2,D6,D10,D16,D20,D25,D32,DAC,DA,DAsi, DD10,DD16,DD20,DD25,DD32,DDAC,DDAsi, CircuitEclairage, CircuitPC16A, CircuitSpecialise,AutreCircuit,Telerupteur,Contracteur,Minuterie,InterrupteurHoraire,Televariateur,Temporisateur,GestionnaireFilPilote,FA,FAsi,F10,F16,F20,F25,FA,FAsi,PeigneVertical,LaveLinge, Four, LaveVaisselle, SecheLinge, Congelateur, CircuitChauffage, CircuitCuisson, CircuitVoletsRoulants, CircuitVMC, CircuitChauffeEau,Chambre,Cuisine,Sejour, WC,Opaque,Transparent, Marge, NbreInterD,NbreModulesID,NbreIDAC,NbreIDA,NbreIDAsi, NbreID25, NbreID40,NBreID63, NbrePeigne, NbrePeigneEmb,NbreModulesPE,Hauteur,NbreDisj,NbreModulesD,NbreD2,NbreD6,NbreD10,NbreD16,NbreD20,NbreD25,NbreD32,NbreDD,NbreModulesDD,NbreDD10,NbreDD16,NbreDD20,NbreDD25,NbreDD32,NbreCircuit,NbreCircuitE,NbreSocles,NbreCircuitPC,NbreCircuitS,PuissanceChE,Puissance,FilPilote,NbreModulesP,NbreModulesTel,NbreModulesCon,NbreModulesMinuterie,NbreModulesTemp,NbreModulesGFP,NbreFusible,NbreModulesF,NbreModulesTelevariateur,NbreModulesIH,TypeLog,SurfaceLog,NbrePieces,Departement,SurfaceCui,SurfaceSej, Manuelle, Automatique, A, M,X, Y],

fd_domain([Coffret, InterDiff, Repartition, Technologie, Disjoncteur, Circuit, CommandeEclairage, Programmation, TypeInterDiff, CalibreInterDiff, CalibreDisj, TypeDisj, Logement], [1]),
fd_domain([Rehausse, Porte, DisjDiff, Parafoudre, CalibreDisjDiff, TypeDisjDiff, CommandeChauffageE, Fusible, TypeFusible, CalibreFusible,Encastre, Sailli, AvecRehausse, SansRehausse, AvecPorte, SansPorte,IDAC,IDA,IDAsi, ID25, ID40,ID63, Peigne, PeigneEmb,D2,D6,D10,D16,D20,D25,D32,DAC,DA,DAsi, DD10,DD16,DD20,DD25,DD32,DDAC,DDAsi, CircuitEclairage, CircuitPC16A, CircuitSpecialise,AutreCircuit,Telerupteur,Contracteur,Minuterie,InterrupteurHoraire,Televariateur,Temporisateur,GestionnaireFilPilote,FA,FAsi,F10,F16,F20,F25,FA,FAsi,LaveLinge, Four, LaveVaisselle, SecheLinge, Congelateur, CircuitChauffage, CircuitCuisson, CircuitVoletsRoulants, CircuitVMC, CircuitChauffeEau,PeigneVertical,Opaque,Transparent,FilPilote, Manuelle, Automatique], [0,1]),
fd_domain(Hauteur, [1,2,3,4,5,6]),
fd_domain(Departement, [1, 4, 5, 6, 7, 13, 21, 22, 24, 25, 26, 30, 33, 34, 38, 39, 40, 42, 43, 47, 48,  63, 66, 69, 71, 73, 74, 83, 84, 971, 972, 973]),
%Dpt1 = 1, Dpt4 = 4, Dpt5 = 5, ... , Dpt2A = 21, Dpt2B = 22, ...
fd_domain(Rangee,[1,2,3,4,5,6]),	
fd_domain(ModuleParRangee, [13,18,24]),
fd_domain(Marque,[1,2,3]),
% schneider = 1
% legrand = 2
% hager = 3
fd_domain(TypeLog,[1,2,3]),
% t1Equipe = 1
% t1NonEquipe = 2
% autre = 3

Coffret #= 1,
Coffret #<=> Encastre + Sailli #= 1,
Rehausse #<=> AvecRehausse + SansRehausse #=1,
Porte #<=> AvecPorte + SansPorte #=1,
TypeInterDiff #<=> IDAC + IDA + IDAsi #=1,
CalibreInterDiff #<=> ID25 + ID40 + ID63 #=1,
Repartition #<=> Peigne + PeigneEmb #=1,
Technologie #<=> MAnuelle + Automatique #=1,
CalibreDisj #<=> D2 + D6 + D10 + D16 + D20 + D25 + D32 #=1,
TypeDisj #<=> DAC + DA + DAsi #=1,
TypeDisjDiff #<=> DDAC + DDAsi #=1,
CalibreDisjDiff #<=> DD10 + DD16 + DD20 + DD25 + DD32 #=1,
Circuit #<=> 1#=< CircuitEclairage + CircuitPC16A + CircuitSpecialise + AutreCircuit,
Circuit #<=> CircuitEclairage + CircuitPC16A + CircuitSpecialise + AutreCircuit #=< 4,
CommandeEclairage #<=> 1 #=< Telerupteur + Contacteur + Minuterie,
CommandeEclairage #<=> Telerupteur + Contacteur + Minuterie #=< 2,
CommandeChauffageE #<=> Temporisateur + GestionnaireFilPilote #=1,
Programmation #<=> 0 #=< InterrupteurHoraire + Televariateur,
Programmation #<=> InterrupteurHoraire + Televariateur #=< 2,
TypeFusible #<=> FA + FAsi #=1,
CalibreFusible #<=> F10 + F16 + F20 + F25 #=1,
Repartition #= PeigneVertical,
Logement #= Chambre,
Logement #= Cuisine,
Logement #= Sejouur,
Logement #= WC,
Logement #==> Coffret,
Coffret #==> Porte,
Coffret #==> Rehausse,
% pour tester l'utilisation d'une contrainte utiliser format avant et aprs, comme a: format('je pose la contrainte ~w\n', [Coffret #==> ModuleParRangee]),
Coffret #==> ModuleParRangee #> 0,%
format('OK\n', []),
Coffret #==> CalibreInterDiff,
Coffret #==> Technologie,
Coffret #==> Marque,
Coffret #==> Repartition,
Coffret #==> Disjoncteur,
Coffret #==> Circuit,
Coffret #==> CommandeEclairage,
Coffret #==> Programmation,
R1 #= LaveLinge + Four + LaveVaisselle + SecheLinge + Congelateur + CircuitChauffage + CircuitCuisson + CircuitVoletsRoulants + CircuitVMC + CircuitChauffeEau,
CircuitCuisson #<=> 1#=< R1,
CircuitCuisson #<=> R1 #=< 200,
AvecPorte #<=> Opaque + Transparent #=1,
IDAC #<=> NbreIDAC #>= 0,
IDA #<=> NbreIDA #>= 0,
IDAsi #<=> NbreIDAsi #>= 0,
ID25 #<=> NbreID25 #>= 0,
ID40 #<=> NbreID40 #>= 0,
ID63 #<=> NbreID63 #>= 0,
Peigne #<=> NbrePeigne #>= 0,
PeigneEmb #<=> NbrePeigneEmb #> 0,
PeigneEmb #<=> NbreModulesPE #> 0,
PeigneVertical #<=> Hauteur #> 0,
D2 #<=> NbreD2 #> 0,
D6 #<=> NbreD6 #> 0,
D10 #<=> NbreD10 #> 0,
D16 #<=> NbreD16 #> 0,
D20 #<=> NbreD20 #> 0,
D25 #<=> NbreD25 #> 0,
D32 #<=> NbreD32 #> 0,
DD10 #<=> NbreDD10 #> 0,
DD16 #<=> NbreDD16 #> 0,
DD20 #<=> NbreDD20 #> 0,
DD25 #<=> NbreDD25 #> 0,
DD32 #<=> NbreDD32 #> 0,
CircuitSpecialise #<=> NbreCircuitS #> 0,
CircuitEclairage #<=> NbreCircuitE #> 0,
CircuitPC16A #<=> NbreSocles #> 0,
CircuitPC16A #<=> NbreCircuitPC #>0,
CircuitChauffeEau #<=>PuissanceChauffeE #>0,
CircuitChauffage #<=>Puissance #>0,
CircuitChauffage #<=> FilPilote,
Cuisine #<=> SurfaceCui #>0,
Sejour #<=> SurfaceSej #>0,
Telerupteur #<=>NbreModulesTel #>0,
Contacteur #<=> NbreModulesCon #>0,
Minuterie #<=> NbreModulesMinuterie #>0,
InterrupteurHoraire #<=> NbreModulesIH #>0,
Televariateur #<=> NbreModulesTLV  #>0,
Temporisateur #<=> NbreModulesTemp  #>0,
GestionnaireFilPilote #<=> NbreModulesGFP  #>0,
Coffret #<=> Marge #>0,
InterDiff #<=> NbreInterD #>0,
InterDiff #<=> NbreModulesID #>0,
Disjoncteur #<=> NbreDisj #>0,
Disjoncteur #<=> NbreModulesD #>0,
Disjdiff #<=> NbreModulesDD #>0,
DisjDiff #<=> NbreDD #>0,
Fusible #<=> NbreFusible #>0,
Fusible #<=> NbreModulesF #>0,
Logement #<=> TypeLog #>0,
Logement #<=> SurfaceLog #>0,
Logement #<=> NbrePieces #>0,
Logement #<=> Departement #>0,
Circuit #<=> NbreCircuit #>0,
Parafoudre #<=> NbreModulesP #>0,
PeigneEmb * Manuelle #= 0,
PeigneEmb * Automatique #= 0,
Telerupteur * Contacteur #= 0,

CircuitPC16A #==> (NbreSocles #=< 5) #\/ (NbreSocles #=< 8),
Departement #==> Parafoudre #=1,
CircuitCuisson #==> NbreCircuitS #>=1,

% ˆ modifier selon notes de feuille et C14
(TypeLog #\<=> 1) #==> (NbreCircuitS #>= 3) #/\ (Congelateur #\/ Four #\/ LaveVaisselle #\/ SecheLinge #\/ LaveLinge) #/\ (Congelateur + Four + LaveLinge + LaveVaisselle + SecheLinge #>= 3),

Congelateur #==> NbreCircuitS #=1 #/\ DisjDiff,

TypeLog #= 1 #/\ (Congelateur #\/ Four #\/ SecheLinge #\/ LaveLinge #\/ LaveVaisselle) #==> NbreCircuitS #=1,
TypeLog #= 2 #==> Disjoncteur #=1 #/\ (NbreD32 #=1) #/\ (NbreD16 #=2),

SurfaceLog #> 35 #==> NbreCircuit #>= 2,

5750 #< Puissance #==> Disjoncteur #=1 #/\ NbreD32 #=1,
Puissance #=< 7250 #==> Disjoncteur #=1 #/\ NbreD32 #=1,
4500 #< Puissance #==> Disjoncteur #=1 #/\ NbreD25 #=1,
Puissance #=< 5750 #==> Disjoncteur #=1 #/\ NbreD25 #=1,
3500 #< Puissance #==> Disjoncteur #=1 #/\ NbreD20 #=1,
Puissance #=< 4500 #==> Disjoncteur #=1 #/\ NbreD20 #=1,
2250 #< Puissance #==> Disjoncteur #=1 #/\ NbreD16 #=1,
Puissance #=<3500 #==> Disjoncteur #=1 #/\ NbreD16 #=1,
% TC0030
Puissance #=< 2250 #==> (Disjoncteur #=1 #/\ NbreD16 #=1) #\/ (Fusible #= 1 #/\ F10 #= 1),
% TC0031
Circuit #==> Disjoncteur #\/ Fusible #\/ DisjDiff,
% TC0040
Circuit #==> DisjDiff #\/ InterDiff,
%ˆ rafaire par rapport au modle feuille et TC41
Parafoudre #==> DisjDiff #= 0,
SurfaceLog #=< 35 #==> NbreInterD #>=1 #/\ NbreIDAC #>=1 #/\ NbreID25 #>=1, 

35 #< SurfaceLog #==> (NbreInterD #>= 2) #/\ (NbreIDAC #>= 2) #/\ (NbreID40 #>= 2),
SurfaceLog #=< 100 #==> (NbreInterD #>= 2) #/\ (NbreIDAC #>= 2) #/\ (NbreID40 #>= 2),

SurfaceLog #> 100 #==> NbreInter #>= 3 #/\ NbreIDAC #>= 3 #/\ NbreID40 #>= 3, 
% celle de eventuellemet TC0046
CircuitCuisson #\/ LaveLinge #\/ A #==> InterDiff #/\ IDA #=1  #/\ NbreID40 #=1,
A #<=> (CircuitEclairage #=1 #/\ CircuitPC16A #=1 #/\ NbreCircuitE #=1 #/\ NbreCircuitPC #=1) #\/ (CircuitEclairage #=1 #/\ NbreCircuitE #=2) #\/ (CircuitPC16A #=1 #/\ NbreCircuitPC #=2),

LaveLinge #\/ LaveVaisselle #==> DisjDiff,
CircuitEclairage #\/ CircuitVoletsRoulants #==> (NbreD16 #=1) #\/ (NbreFusible #=1 #/\ F10 #=1),
CircuitVMC #==> NbreD16 #=1,
GestionnaireFilPilote #==> NbreD2 #=1,
CircuitChauffeEau #==> (D20 #=1) #\/ (NbreFusible #=1 #/\ F16 #=1),
M #= ((NbreModulesID * NbreInterD) + (NbreModulesD * NbreDisj) + (NbreModuleDD * NbreDD) + (NbreModulesF * NbreFusible) + NbreModulesTel + NbreModulesCon+ NbreModulesMinuterie + NbreModuleP + NbreModuleTemp + NbreModulesGFP + NbreModulesIH + NbreModulesTelevariateur) * 20/100,
X #= ModuleParRangee * Rangee,
Y #= X - M,
(M #=< Y) #==> Marge #= M,
Hauteur #= Rangee,

%les variables  A, M,X, Y sont déclarées mais leurs domaines de valeurs ne le sont plus
%Il reste à traiter les contraintes TC0045, TC0047, TC0050, TC0065, TC0067

fd_labelingff(TabElec).
