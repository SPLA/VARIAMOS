%| ?- g_assign(cpt,0), productline(_,_), g_inc(cpt), fail;g_read(cpt,N). Instead of | ?- findall(L, productline(L), LSol), length(LSol, NbSolutions).
%statistics(runtime, _), g_assign(cpt,0), productline(_,_), g_inc(cpt, 10), !, statistics(runtime, [_,Temps]).
%statistics(runtime, _), g_assign(cpt,0), productline(_,_), g_inc(cpt), fail;g_read(cpt,N), statistics(runtime, [_,Temps]).
%consult('/Volumes/Data/Dropbox/CosminRaulCamille/chapitre_configuration/ElectricParkingBrakeSystem.pl').

:-use_module(library(clpfd)).
productline(L):-



L = [ParkingBrakeService, PBSManual, PBSAutomatic, PBSAssistance, BrakeLock, BLOnSystemDecision, BLOnUserCommand, BrakeRelease, BROnSystemDecision, BROnUserCommand,  HillStartAssistance, EPBEnabled, ESCEnabled, HSADisableFunction, HSAOnDemand, HSAAutomatic, RegulationZone, EU, US, Other, ClutchPedal, ClutchPres, GearBox, GBAutomatic, GBManual, VehicleTrailer, TrailerW1, TrailerW2, ArchitectureDesignAlternatives, PullerCable, ElectricActuator, CalipserWithIntegr, TraditionalPB, RedundancyFunctions, AuxiliaryBrakeRelease, ForceDistributionDesignAlternatives, FullEPB, DownsizedAndESPDynamicBraking, IntegratedDownsizedCalipers, DownsizedAndExtratorqueESP, SoftwareAllocationDesignAlternatives, DedicatedECU, ESPECU, SpecificCockPitECU, TiltAngleFunctionAllocation, TiltSensorIn1, TiltSensorIn2, TiltSensorIn3, ElectricalActionComponents, DCMotor, EActuator, BrakingStrategy, Specific, Dynamic, Confort, Static, ForceMonitorOnEngineStop, Temporary, Permanent, InputInformation, TiltSensor, EffortSensor, ClutchPosition, DoorPosition, VSpeed,VehicleModel,VehicleWeight],


VehicleModel in  0 \/ 91 \/ 43 \/ 47 \/ 45,
VehicleWeight in 2000..2500,
GBManual in  0 \/ 4 \/ 5 \/ 6,
[TrailerW1, TrailerW2] ins 400 \/ 450 \/ 500 \/ 1000 \/ 1050 \/ 1400 \/ 1450,
[DCMotor, EActuator] ins 0..20,
[Confort, Static] ins 0..100,
FullEPB in 0..2000,
[ParkingBrakeService, PBSManual, PBSAutomatic, PBSAssistance, BrakeLock, BLOnSystemDecision, BLOnUserCommand, BrakeRelease, BROnSystemDecision, BROnUserCommand,  HillStartAssistance, EPBEnabled, ESCEnabled, HSADisableFunction, HSAOnDemand, HSAAutomatic, RegulationZone, EU, US, Other, ClutchPedal, ClutchPres, GearBox, GBAutomatic, VehicleTrailer, ArchitectureDesignAlternatives, PullerCable, ElectricActuator, CalipserWithIntegr, TraditionalPB, RedundancyFunctions, AuxiliaryBrakeRelease, ForceDistributionDesignAlternatives, DownsizedAndESPDynamicBraking, IntegratedDownsizedCalipers, DownsizedAndExtratorqueESP, SoftwareAllocationDesignAlternatives, DedicatedECU, ESPECU, SpecificCockPitECU, TiltAngleFunctionAllocation, TiltSensorIn1, TiltSensorIn2, TiltSensorIn3, ElectricalActionComponents, BrakingStrategy, Specific, Dynamic, ForceMonitorOnEngineStop, Temporary, Permanent, InputInformation, TiltSensor, EffortSensor, ClutchPosition, DoorPosition, VSpeed, AuxVP, AuxV1, AuxV2] ins 0..1,

%tests de configuration
% CONSTRAINTS:
% if PullerCable variant is implemented, the comfort brakeing strategy is available
(PullerCable #= 1) #==> Comfort #> 0,
% the vehicle slows down as follows : dynamic ->  comfort -> static -> stopped
Static #> Comfort,
TrailerW1 #< TrailerW2,


%group cardinality of the VP ParkingBrakeService
(PBSManual #> 0) #<==>  Bool_PBSManual,
(PBSAutomatic #> 0) #<==>  Bool_PBSAutomatic,
(PBSAssistance #> 0) #<==>  Bool_PBSAssistance,
ParkingBrakeService #= Bool_PBSManual + Bool_PBSAutomatic + Bool_PBSAssistance,

%group cardinality of the VP BrakeLock
(BLOnSystemDecision #> 0) #<==>  Bool_BLOnSystemDecision,
(BLOnUserCommand #> 0) #<==>  Bool_BLOnUserCommand,
BrakeLock #=< Bool_BLOnSystemDecision + Bool_BLOnUserCommand,
Bool_BLOnSystemDecision + Bool_BLOnUserCommand #=< 2*BrakeLock,

%group cardinality of the VP BrakeRelease
(BROnSystemDecision #> 0) #<==>  Bool_BROnSystemDecision,
(BROnUserCommand #> 0) #<==>  Bool_BROnUserCommand,
BrakeRelease #=< Bool_BROnSystemDecision + Bool_BROnUserCommand,
Bool_BROnSystemDecision + Bool_BROnUserCommand #=< 2*BrakeRelease,

%group cardinality of the VP AuxVP
AuxVP*2 #= AuxV1 + AuxV2,

%group cardinality of the VP HillStartAssistance (in this one I changed the cardinality 0..1 by 1..1)
(EPBEnabled #> 0) #<==>  Bool_EPBEnabled,
(ESCEnabled #> 0) #<==>  Bool_ESCEnabled,
HillStartAssistance #= Bool_EPBEnabled + Bool_ESCEnabled,

%group cardinality of the VP HSADisableFunction
(HSAOnDemand #> 0) #<==>  Bool_HSAOnDemand,
(HSAAutomatic #> 0) #<==>  Bool_HSAAutomatic,
HSADisableFunction #= Bool_HSAOnDemand + Bool_HSAAutomatic,

%group cardinality of the VP RegulationZone
(EU #> 0) #<==>  Bool_EU,
(US #> 0) #<==>  Bool_US,
(Other #> 0) #<==>  Bool_Other,
RegulationZone #=< Bool_EU + Bool_US + Bool_Other,
Bool_EU + Bool_US + Bool_Other #=< 3*RegulationZone,

%optionality ClutchPedal
ClutchPedal #>= ClutchPres,

%group cardinality of the VP GearBox
(GBAutomatic #> 0) #<==>  Bool_GBAutomatic,
(GBManual #> 0) #<==>  Bool_GBManual,
GearBox #= Bool_GBAutomatic + Bool_GBManual,

%group cardinality of the VP VehicleTrailer
(TrailerW1 #> 0) #<==>  Bool_TrailerW1,
(TrailerW2 #> 0) #<==>  Bool_TrailerW2,
VehicleTrailer #= Bool_TrailerW1 + Bool_TrailerW2,

%group cardinality of the VP ArchitectureDesignAlternatives
(PullerCable #> 0) #<==>  Bool_PullerCable,
(ElectricActuator #> 0) #<==>  Bool_ElectricActuator,
(CalipserWithIntegr #> 0) #<==>  Bool_CalipserWithIntegr,
(TraditionalPB #> 0) #<==>  Bool_TraditionalPB,
ArchitectureDesignAlternatives #= Bool_PullerCable + Bool_ElectricActuator + Bool_CalipserWithIntegr + Bool_TraditionalPB,

%optionality RedundancyFunctions
RedundancyFunctions #>= AuxiliaryBrakeRelease,

%group cardinality of the VP ForceDistributionDesignAlternatives
(FullEPB #> 0) #<==>  Bool_FullEPB,
(DownsizedAndESPDynamicBraking #> 0) #<==>  Bool_DownsizedAndESPDynamicBraking,
(IntegratedDownsizedCalipers #> 0) #<==>  Bool_IntegratedDownsizedCalipers,
(DownsizedAndExtratorqueESP #> 0) #<==>  Bool_DownsizedAndExtratorqueESP,
ForceDistributionDesignAlternatives #= Bool_FullEPB + Bool_DownsizedAndESPDynamicBraking + Bool_IntegratedDownsizedCalipers + Bool_DownsizedAndExtratorqueESP,

%group cardinality of the VP SoftwareAllocationDesignAlternatives
(DedicatedECU #> 0) #<==>  Bool_DedicatedECU,
(ESPECU #> 0) #<==>  Bool_ESPECU,
(SpecificCockPitECU #> 0) #<==>  Bool_SpecificCockPitECU,
SoftwareAllocationDesignAlternatives #= Bool_DedicatedECU + Bool_ESPECU + Bool_SpecificCockPitECU,

%group cardinality of the VP TiltAngleFunctionAllocation (in this one I changed the cardinality 0..1 by 1..1)
(TiltSensorIn1 #> 0) #<==>  Bool_TiltSensorIn1,
(TiltSensorIn2 #> 0) #<==>  Bool_TiltSensorIn2,
(TiltSensorIn3 #> 0) #<==>  Bool_TiltSensorIn3,
TiltAngleFunctionAllocation #= Bool_TiltSensorIn1 + Bool_TiltSensorIn2 + Bool_TiltSensorIn3,

%group cardinality of the VP ElectricalActionComponents
(DCMotor #> 0) #<==>  Bool_DCMotor,
(EActuator #> 0) #<==>  Bool_EActuator,
ElectricalActionComponents #= Bool_DCMotor + Bool_EActuator,

%group cardinality of the VP BrakingStrategy
(Specific #> 0) #<==>  Bool_Specific,
(Dynamic #> 0) #<==>  Bool_Dynamic,
(Confort #> 0) #<==>  Bool_Confort,
(Static #> 0) #<==>  Bool_Static,
BrakingStrategy #=< Bool_Specific + Bool_Dynamic + Bool_Confort + Bool_Static,
Bool_Specific + Bool_Dynamic + Bool_Confort + Bool_Static #=< 4*BrakingStrategy,

%group cardinality of the VP ForceMonitorOnEngineStop
(Temporary #> 0) #<==>  Bool_Temporary,
(Permanent #> 0) #<==>  Bool_Permanent,
ForceMonitorOnEngineStop #= Bool_Temporary + Bool_Permanent,

%group cardinality of the VP InputInformation (group cardinality 1..5?)
(TiltSensor #> 0) #<==>  Bool_TiltSensor,
(EffortSensor #> 0) #<==>  Bool_EffortSensor,
(ClutchPosition #> 0) #<==>  Bool_ClutchPosition,
(DoorPosition #> 0) #<==>  Bool_DoorPosition,
(VSpeed #> 0) #<==>  Bool_VSpeed,
InputInformation #=< Bool_TiltSensor + Bool_EffortSensor + Bool_ClutchPosition + Bool_DoorPosition + Bool_VSpeed,
Bool_TiltSensor + Bool_EffortSensor + Bool_ClutchPosition + Bool_DoorPosition + Bool_VSpeed #=< 5*InputInformation,


%Includes

(TraditionalPB #> 0) #==> (PBSManual #> 0),
(HillStartAssistance #> 0) #==> (PBSAssistance #> 0),
(HSADisableFunction #> 0) #==> (HillStartAssistance #> 0),
(AuxVP #> 0) #==> (HSADisableFunction #> 0),
(EPBEnabled #> 0) #==> (AuxV1 #> 0),
(TrailerW1 #> 0) #==> (AuxV2 #> 0),
(EU #> 0) #==> (AuxiliaryBrakeRelease #> 0),
(PullerCable #> 0) #==> (Temporary #> 0),
(PullerCable #> 0) #==> (DCMotor #> 0),
(DownsizedAndExtratorqueESP #> 0) #==> (PullerCable #> 0),
(ElectricActuator #> 0) #==> (Permanent #> 0),
(ElectricActuator #> 0) #==> (EActuator #> 0),
(CalipserWithIntegr #> 0) #==> (Permanent #> 0),
(IntegratedDownsizedCalipers #> 0) #==> (ElectricActuator #> 0),
(DownsizedAndESPDynamicBraking #> 0) #==> (ESPECU #> 0),
(DownsizedAndExtratorqueESP #> 0) #==> (ESPECU #> 0),
(TiltSensorIn1 #> 0) #==> (DedicatedECU #> 0),
(TiltSensorIn2 #> 0) #==> (ESPECU #> 0),
(Dynamic #> 0) #==> (VSpeed #> 0),
(Confort #> 0) #==> (VSpeed #> 0),


%Excludes

BLOnSystemDecision * PBSManual #= 0,
BROnSystemDecision * PBSManual #= 0,
TrailerW2 * HillStartAssistance #= 0,
ClutchPres * GBAutomatic #= 0,
TraditionalPB * DownsizedAndESPDynamicBraking #= 0,

label(L).


