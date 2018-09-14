
#  **Version Tracker**

***Last update*** : *September 2018*  

in this document are listed all deleted methods and variables in a class, their respective class, component, a commit(A working one, before deleting methods/class) and a reference date. 

Note : deleted class, are not in bold. 

### **Update 14/09/2018** 

##### **Branch:**  integration_variamos.
##### **Actual version ID(Before Delete):** b93ef12ccf51e8196a85cd1d8aa9fc84e04acee1
##### **Update reason:** some related methods deleted because no use. 

#### **com.variamos.common** 
* **src**
    + **main**  
        - **java** 
             * **com**
                * **variamos**
                    * **common**
                        * **core**
                            * **utilies**
                                * **SetUtil.java**
                                * **FileUtils.java**
                            *  **exceptions**
                                * **FunctionalException.java**
                                * **MXGraphException.java**
                                * **TechnicalException.java**
                            *  **model-enums**
                                * **NotationType.java**

#### **com.variamos.io**
* **src**
    + **main**  
        - **java** 
             * **com**
                * **variamos**
                    * **io**
                        * **core**
                            * **importExport**
                                * **ConfigurationDTO.java**
                                * **ConfigurationIO.java**
                                * ExportDefectAnalyzer.java

#### **com.variamos.hlcl** 

* **src**
    + **main**  
        - **java** 
             * **com**
                * **variamos**
                    * **hlcl**
                        * **model**
                            * **Labeling.java** 
                            * **domains**
                                * **IntervalDomain.java**
                                * **RangeDomain.java**
                                * **StringDomain.java**
                            * **expressions**
                                * **HlclFactory.java**
                                * **NumericIdentifier.java**

#### **com.variamos.reasoning** 

* **src**
    + **main**  
        - **java** 
             * **com**
                * **variamos**
                    * **reasoning**
                        * **core**
                            * **transformer** 
                                * ProductLineTransformerRules.java
                                * **VariabilityModelTransformer.java**
                        * **defectAnalyzer**
                            * **CauCosAnayzer.java**   
                            * DefectAnalyzerController.java
                            * **model**
                                * **defects** 
                                    * **FalseProductLine.java**
                                * **diagnosis**
                                    *  **CauCos.java**
                                    *  ClassificationCategoriesEnum.java
                                * **dto**
                                    * DefectAnalyzerControllerOutDTO.java
                                * **transformation**
                                    * **RangeDomainDefectAnalyzer.java**
                                    * **TransformerConstants.java**
                        * **medic**
                            * * **model**
                                * **diagnoseAlgorithm**
                                    * **MinimalSetsDFSIterationsHLCL.java**
                                    * **Path.java**
                                * **graph**
                                    * **ConstraintGraphHLCL.java**
                        * **util**
                            * **ConstraintRepresentationUtil.java**
                            * ExportDefectAnalyzer.java
                            * **VerifierUtilExpression.java** 

#### **com.variamos.solver** 

* **src**
    + **main**  
        - **java** 
             * **com**
                * **variamos**
                    * **solver**
                        * **core**
                            * **ConfigurationTask.java** 
                            * **SWIPrologSolver.java**
                            * **compiler**
                                * **Hlcl2Prolog.java**
                        * **model**
                            * **ConfigurationModeEnum.java**
                            * **ConfigurationOptionsDTO.java**
                            * **SolverSolution.java**
                            * **compiler**
                                * **ConstraintSymbolsConstant.java**
                                * GNUPrologSymbolsConstant.java
                                * StageInTransformation.java
                                * **SWIPrologSymbolsConstant.java**


#### **com.variamos.dynsup** 

* **src**
    + **main**  
        - **java** 
             * **com**
                * **variamos**
                    * **dynsup**
                        * **instance**
                        * **model**
                            * **ElemAttribAttribute.java**
                            * **ElemAttribute.java**
                            * **ModelExpr.java**
                            * **OpersElement.java**
                            * **OpersExpr.java**    
                            * OpersValue.java
                            * **SyntaxElement.java**
                        * **staticexpr**
                            * **ElementExpressionSet.java** 
                        * **staticexprsup**
                            * **AbstractExpression.java**
                            * **AndBooleanExpression.java**
                            * AssignBooleanExpression.java 
                            * **DiffNumericExpression.java**
                            * **DoubleImplicationBooleanExpression.java**
                            * **EqualsComparisonExpression.java**
                            * GreaterBooleanExpression.java
                            * **GreaterOrEqualsBooleanExpression.java**
                            * **ImplicationBooleanExpression.java**
                            * **LessBooleanExpression.java**
                            * **LessOrEqualsBooleanExpression.java**
                            * LiteralBooleanExpression.java
                            * **NotBooleanExpression.java**
                            * NotBooleanExpression.java
                            * OrBooleanExpression.java
                            * **ProdNumericExpression.java**
                            * **SumNumericExpression.java**
                        * **statictypes**
                            * **SatisficingType.java**
                        * **translation**
                            * **ModelExpr2HLCL.java**
                            * **SolverOpersTask.java**
                            * **TranslationExpressionSet.java**
                        * **types**
                            * **OpersDefectType.java**
                            * **OpersExecType.java**
                            * **OpersSubOpType.java**
                            * **SetType.java**

#### **com.variamos.gui** 

* **src**
    + **main**  
        - **java** 
             * **com**
                * **variamos**
                    * **gui**
                        * **core**
                            * **maineditor**
                                * **models**
                                    * **DynamicBehaviorDTO.java**
                            * **mxgraph**
                                * **editor**
                                    * **BasicGraphEditor.java**
                                    * **DefaultFileFilter.java**
                                    * **EditorActionsController.java**
                                    * **EditorPalette.java**
                                    * GraphConfigDialog.java


