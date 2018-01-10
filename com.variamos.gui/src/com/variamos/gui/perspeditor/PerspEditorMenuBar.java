package com.variamos.gui.perspeditor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxResources;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.gui.configurator.guiactions.ExportConfigurationAction;
import com.variamos.gui.configurator.guiactions.SaveProductsAction;
import com.variamos.gui.core.mxgraph.editor.BasicGraphEditor;
import com.variamos.gui.core.mxgraph.editor.EditorActionsController.AlignCellsAction;
import com.variamos.gui.core.mxgraph.editor.EditorActionsController.AutosizeAction;
import com.variamos.gui.core.mxgraph.editor.EditorActionsController.ColorAction;
import com.variamos.gui.core.mxgraph.editor.EditorActionsController.KeyValueAction;
import com.variamos.gui.core.mxgraph.editor.EditorActionsController.PromptValueAction;
import com.variamos.gui.core.mxgraph.editor.EditorActionsController.SetLabelPositionAction;
import com.variamos.gui.core.mxgraph.editor.EditorActionsController.SetStyleAction;
import com.variamos.gui.core.mxgraph.editor.EditorActionsController.StyleAction;
import com.variamos.gui.core.mxgraph.editor.EditorActionsController.ToggleAction;
import com.variamos.gui.core.viewcontrollers.VariamosGUIPerpectiveEditorActions.ExitAction;
import com.variamos.gui.core.viewcontrollers.VariamosGUIPerpectiveEditorActions.LoadAction;
import com.variamos.gui.core.viewcontrollers.VariamosGUIPerpectiveEditorActions.NewAction;
import com.variamos.gui.core.viewcontrollers.VariamosGUIPerpectiveEditorActions.SaveAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.actions.AboutAction;
import com.variamos.gui.perspeditor.actions.CheckUpdateAction;
import com.variamos.gui.perspeditor.actions.ClearSimulationAction;
import com.variamos.gui.perspeditor.actions.ClearVerificationAction;
import com.variamos.gui.perspeditor.actions.ConfigureAssemblyAction;
import com.variamos.gui.perspeditor.actions.ElementOperationAssociationAction;
import com.variamos.gui.perspeditor.actions.ExternalContextAction;
import com.variamos.gui.perspeditor.actions.HideAdvancedPerspectiveAction;
import com.variamos.gui.perspeditor.actions.HideSimulationDashBoardAction;
import com.variamos.gui.perspeditor.actions.HideSimulationsCustomizationBox;
import com.variamos.gui.perspeditor.actions.NextSimulationAction;
import com.variamos.gui.perspeditor.actions.OperationAction;
import com.variamos.gui.perspeditor.actions.ParentElementAction;
import com.variamos.gui.perspeditor.actions.RootElementAction;
import com.variamos.gui.perspeditor.actions.ShowAdvancedPerspectiveAction;
import com.variamos.gui.perspeditor.actions.ShowSimulationCustomizationBox;
import com.variamos.gui.perspeditor.actions.ShowSimulationDashBoardAction;
import com.variamos.gui.perspeditor.actions.StartSimulationAction;
import com.variamos.gui.perspeditor.actions.VariableLabelingAssociationAction;
import com.variamos.gui.perspeditor.actions.VariableOperationAssociationAction;
import com.variamos.gui.perspeditor.actions.VerificationAction;
import com.variamos.gui.perspeditor.actions.VerifyDeadElementAction;
import com.variamos.gui.perspeditor.actions.VerifyFalseOptElementAction;

@SuppressWarnings("serial")

public class PerspEditorMenuBar extends JMenuBar {

	private VariamosGraphEditor editor;

	public PerspEditorMenuBar(BasicGraphEditor variamosGraphEditor) {
		this.editor=(VariamosGraphEditor)variamosGraphEditor;
		init(variamosGraphEditor);
	}

	private JMenu loadFileMenu() {
		JMenu menu = new JMenu("File");
		menu.setMnemonic('F');
		Action action;
		action = editor.bind(mxResources.get("new"), new NewAction());
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menu.add(action);
		action = editor.bind(mxResources.get("load"), new LoadAction());
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		menu.add(action);
		menu.addSeparator();
		action = editor.bind(mxResources.get("save"), new SaveAction(false));
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menu.add(action);
		action = editor.bind(mxResources.get("saveAs"), new SaveAction(true));
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		menu.add(action);

		menu.addSeparator();

		action = editor.bind(mxResources.get("exit"), new ExitAction());
		action.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menu.add(action);
		return menu;

	}
	
	private JMenu loadLayoutMenu() {
		JMenu layoutMenu=new JMenu(mxResources.get("layout"));
		layoutMenu.setMnemonic('L');
		layoutMenu.addSeparator();

		layoutMenu.add(editor.graphLayout("verticalHierarchical", true));
		layoutMenu.add(editor.graphLayout("horizontalHierarchical", true));

		layoutMenu.addSeparator();

		layoutMenu.add(editor.graphLayout("verticalStack", true));
		layoutMenu.add(editor.graphLayout("horizontalStack", true));

		layoutMenu.addSeparator();

		layoutMenu.add(editor.graphLayout("verticalTree", true));
		layoutMenu.add(editor.graphLayout("horizontalTree", true));

		layoutMenu.addSeparator();

		layoutMenu.add(editor.graphLayout("placeEdgeLabels", true));
		layoutMenu.add(editor.graphLayout("parallelEdges", true));

		layoutMenu.addSeparator();

		layoutMenu.add(editor.graphLayout("organicLayout", true));
		layoutMenu.add(editor.graphLayout("circleLayout", true));
		return layoutMenu;
	}


	private void init(BasicGraphEditor editor) {

		JMenu menu=new JMenu();
		Action al=null;
		// Luisa: ISSUE #245 HOT FIX
		// Configuration/simulation perspective does not need any layout functionality
		if (editor.getPerspective() != 4) {
			JMenu fileMenu = loadFileMenu();
			add(fileMenu);
			JMenu layoutMenu = loadLayoutMenu();
			add(layoutMenu);
		}
		final VariamosGraphEditor finalEditor = (VariamosGraphEditor) editor;
		if (editor.getPerspective() == 2) {
			menu = (JMenu) menu.add(new JMenu(mxResources.get("models")));
			menu.setMnemonic('M');
			// menu.add(editor.bind(mxResources.get("verifyVoidModel"), new
			// VerifyVoidModelAction()));
			// menu.add(editor.bind(mxResources.get("verifyFalseProductLine"),
			// new
			// VerifyFalseProductLineModelAction()));
			JCheckBoxMenuItem item = new JCheckBoxMenuItem("REFAS Model");
			item.setState(true);
			item.setEnabled(false);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();
					if (selected)
						finalEditor.updateDefects("Root", true);
					else
						finalEditor.updateDefects("Root", false);
				}
			});
			menu.add(item);

			add(menu);

			menu = (JMenu) menu.add(new JMenu(mxResources.get("verifyDefects")));
			menu.setMnemonic('I');

			menu.add(editor.bind(mxResources.get("verifyRoot"), new RootElementAction()));
			menu.add(editor.bind(mxResources.get("verifyParents"), new ParentElementAction()));
			menu.add(editor.bind(mxResources.get("verifyDeadElement"), new VerifyDeadElementAction()));
			menu.add(editor.bind(mxResources.get("verifyFalseOptionalElements"), new VerifyFalseOptElementAction()));
			menu.addSeparator();
			menu.add(editor.bind(mxResources.get("clearElements"), new ClearVerificationAction()));
			add(menu);

			menu = (JMenu) menu.add(new JMenu(mxResources.get("verifyDefectsOptions")));
			menu.setMnemonic('D');
			item = new JCheckBoxMenuItem(mxResources.get("verifyRoot"));
			item.setState(true);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();
					if (selected)
						finalEditor.updateDefects("Root", true);
					else
						finalEditor.updateDefects("Root", false);
				}
			});
			menu.add(item);

			item = new JCheckBoxMenuItem(mxResources.get("verifyParents"));
			item.setState(true);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();
					if (arg0.getSource() instanceof Component) {

						if (selected)
							finalEditor.updateDefects("Parent", true);
						else
							finalEditor.updateDefects("Parent", false);
					}
				}
			});
			menu.add(item);

			item = new JCheckBoxMenuItem(mxResources.get("verifyDeadElement"));
			item.setState(true);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();

					if (selected)
						finalEditor.updateDefects("Dead", true);
					else
						finalEditor.updateDefects("Dead", false);
				}
			});
			menu.add(item);

			item = new JCheckBoxMenuItem(mxResources.get("verifyFalseOptionalElements"));
			item.setState(true);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();

					if (selected)
						finalEditor.updateDefects("FalseOpt", true);
					else
						finalEditor.updateDefects("FalseOpt", false);
				}
			});

			menu.add(item);
			menu.addSeparator();
			menu.add(editor.bind(mxResources.get("clearElements"), new ClearVerificationAction()));
			al = editor.bind(mxResources.get("verifyElements"), new VerificationAction());
			al.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
			menu.add(al);
			add(menu);

		}
		if (editor.getPerspective() == 2 || (editor.getPerspective() == 4)) {
			List<InstElement> menus = ((VariamosGraphEditor) editor).getEditedModel().getOperationalModel()
					.getVariabilityVertex("OpMOperGroup");
			int cantMenu = 1;
			String pre1 = "", pre2 = "";

			for (InstElement menuElement : menus) {
				if ((boolean) menuElement.getInstAttribute("execAll").getValue() == false) {
					cantMenu = 1;
					pre1 = "";
				} else if (editor.getPerspective() == 2) {
					cantMenu = 2;
					pre1 = "Individual ";
					pre2 = "Group ";
				}
				for (int i = 0; i < cantMenu; i++) {
					if ((boolean) menuElement.getInstAttribute("visible").getValue() == true
							&& ((String) menuElement.getInstAttribute("menuType").getValue())
									.equals(editor.getPerspective() + "")) {
						menu = (JMenu) menu.add(
								new JMenu((i == 0 ? pre1 : pre2) + menuElement.getInstAttribute("opgname").getValue()));
						// menu.setMnemonic();
						for (InstElement operRel : menuElement.getTargetRelations()) {
							if (i == 0) {
								InstElement e = operRel.getTargetRelations().get(0);
								if (!(boolean) e.getInstAttribute("visible").getValue())
									continue;
								JMenuItem menuItem = new JMenuItem((String) e.getInstAttribute("opname").getValue());

								menuItem.setName(e.getIdentifier());
								menuItem.setAction(
										editor.bind(menuItem, e.getIdentifier(), new OperationAction(), null));
								
								//domain implementation menu issue
								if(e.getInstAttribute("opname").getValue().equals("Configure Integration")) {
									menu.add(editor.bind("Configure Integration", new ConfigureAssemblyAction()));
								}else {
									menu.add(menuItem);
								}
								menuItem.setText((String) e.getInstAttribute("opname").getValue());
								
								boolean iterate = (boolean) e.getInstAttribute("iteration").getValue();
								if (iterate) {
									JMenuItem menuItem2 = new JMenuItem("Next Element");
									menuItem2.setName("N:" + e.getIdentifier());
									menuItem2.setAction(editor.bind(menuItem2, "N:" + e.getIdentifier(),
											new OperationAction(), null));
									menu.add(menuItem2);
									menuItem2.setText("Next Element");
								}
								// menu.add(editor.bind(oper.getTargetRelations()
								// .get(0).getIdentifier(), (String) oper
								// .getTargetRelations().get(0)
								// .getInstAttribute("name").getValue(),
								// new OperationAction(), null));
							} else {
								JCheckBoxMenuItem item = new JCheckBoxMenuItem(
										operRel.getTargetRelations().get(0).getIdentifier());
								item.setState(true);
								item.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent arg0) {
										AbstractButton aButton = (AbstractButton) arg0.getSource();
										boolean selected = aButton.getModel().isSelected();

										if (selected)
											finalEditor.updateDefects("FalseOpt", true);
										else
											finalEditor.updateDefects("FalseOpt", false);
									}
								});

								menu.add(item);
							}
						}
						if ((boolean) menuElement.getInstAttribute("execAll").getValue() == true && i == 1) {
							menu.add(editor.bind(mxResources.get("verifyElements"), new VerificationAction())); // FIXME
				}
						if ((boolean) menuElement.getInstAttribute("clearButton").getValue() == true) {
							menu.addSeparator();
							menu.add(editor.bind(mxResources.get("clearElements"), new ClearVerificationAction()));
						}
						add(menu);
					}
				}
			}
		}
		if (editor.getPerspective() == 1) {
			menu = (JMenu) menu.add(new JMenu(mxResources
					.get("translationConfiguration")));
			menu.setMnemonic('C');
			// Action a = editor.bind(mxResources.get("operationDefinition"),
			// new OperationDefinitionAction());
			// menu.add(a);
			Action a = editor.bind(
					mxResources.get("elementOperationAssociation"),
					new ElementOperationAssociationAction());
			menu.add(a);
			a = editor.bind(mxResources.get("variableOperationAssociation"),
					new VariableOperationAssociationAction());
			menu.add(a);
			a = editor.bind(mxResources.get("variableLabelingAssociation"),
					new VariableLabelingAssociationAction());
			menu.add(a);
			add(menu);
			
		}
		

		if (editor.getPerspective() == 4) {

			// Luisa: ISSUE #245 HOT FIX
			Action a;
			/*
			 * menu = (JMenu) menu .add(new JMenu(mxResources.get("configuration")));
			 * menu.setMnemonic('C'); a = editor.bind(mxResources.get("startConfiguration"),
			 * new ClearConfigurationAction()); a.setEnabled(false); menu.add(a);
			 * 
			 * a = editor.bind(mxResources.get("restartConfiguration"), new
			 * ClearConfigurationAction()); menu.add(a); menu.addSeparator();
			 * 
			 * a = editor.bind(mxResources.get("saveConfiguration"), new
			 * SaveConfigurationAction(true)); menu.add(a); a.setEnabled(false); a =
			 * editor.bind(mxResources.get("saveProducts"), new SaveProductsAction());
			 * menu.add(a); a.setEnabled(false); menu.addSeparator();
			 * 
			 * add(menu);
			 */

			// Luisa: ISSUE #245 HOT FIX

			menu = (JMenu) menu.add(new JMenu(mxResources.get("simulation")));
			menu.setMnemonic('S');
			a = editor.bind(mxResources.get("resetSimulation"), new ClearSimulationAction());
			a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
			menu.add(a);
			a = editor.bind(mxResources.get("startSimulation"), new StartSimulationAction());
			a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			menu.add(a);
			a = editor.bind(mxResources.get("nextSimulation"), new NextSimulationAction());
			a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
			menu.add(a);

			add(menu);

			menu = (JMenu) menu.add(new JMenu(mxResources.get("dashboard")));
			menu.setMnemonic('D');

			a = editor.bind(mxResources.get("showSimulationDashBoard"), new ShowSimulationDashBoardAction());
			a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
			menu.add(a);
			menu.add(editor.bind(mxResources.get("hideSimulationDashBoard"), new HideSimulationDashBoardAction()));
			JCheckBoxMenuItem item = new JCheckBoxMenuItem(mxResources.get("nameSimulationDashBoard"));
			item.setState(true);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();

					if (selected) {
						finalEditor.showNames(true);
						finalEditor.updateDashBoard(true, false, false);
					} else {
						finalEditor.showNames(false);
						finalEditor.updateDashBoard(true, false, false);
					}
				}
			});
			menu.add(item);

			menu.addSeparator();

			a = editor.bind(mxResources.get("savePartialSolution"), new SaveProductsAction());
			menu.add(a);

			a = editor.bind(mxResources.get("exportConfiguration"), new ExportConfigurationAction(true));
			menu.add(a);

			menu.addSeparator();

			menu.setMnemonic('U');
			a = editor.bind(mxResources.get("externalContext"), new ExternalContextAction());
			menu.add(a);
			add(menu);
			add(menu);

		}
		
		menu = (JMenu) menu.add(new JMenu(mxResources.get("window")));
		menu.setMnemonic('W');
		menu.add(editor.bind(mxResources.get("showAdvancedPerspectives"), new ShowAdvancedPerspectiveAction()));
		menu.add(editor.bind(mxResources.get("hideAdvancedPerspectives"), new HideAdvancedPerspectiveAction()));
		menu.add(editor.bind(mxResources.get("showSimulationCustomizationBox"), new ShowSimulationCustomizationBox()));
		menu.add(editor.bind(mxResources.get("hideSimulationCustomizationBox"), new HideSimulationsCustomizationBox()));
		add(menu);

		menu = (JMenu) menu.add(new JMenu(mxResources.get("help")));
		menu.setMnemonic('H');
		menu.add(editor.bind(mxResources.get("about"), new AboutAction()));

		menu.add(editor.bind(mxResources.get("checkUpdates"), new CheckUpdateAction()));
		add(menu);
	}
	
	/**
	 * Adds menu items to the given shape menu. This is factored out because the
	 * shape menu appears in the menubar and also in the popupmenu.
	 */
	public static void populateShapeMenu(JMenu menu, BasicGraphEditor editor) {
		menu.add(editor.bind(mxResources.get("home"), mxGraphActions.getHomeAction(),
				"/com/variamos/gui/perspeditor/images/house.gif"));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("exitGroup"), mxGraphActions.getExitGroupAction(),
				"/com/variamos/gui/perspeditor/images/up.gif"));
		menu.add(editor.bind(mxResources.get("enterGroup"), mxGraphActions.getEnterGroupAction(),
				"/com/variamos/gui/perspeditor/images/down.gif"));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("group"), mxGraphActions.getGroupAction(),
				"/com/variamos/gui/perspeditor/images/group.gif"));
		menu.add(editor.bind(mxResources.get("ungroup"), mxGraphActions.getUngroupAction(),
				"/com/variamos/gui/perspeditor/images/ungroup.gif"));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("removeFromGroup"), mxGraphActions.getRemoveFromParentAction()));

		menu.add(editor.bind(mxResources.get("updateGroupBounds"), mxGraphActions.getUpdateGroupBoundsAction()));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("collapse"), mxGraphActions.getCollapseAction(),
				"/com/variamos/gui/perspeditor/images/collapse.gif"));
		menu.add(editor.bind(mxResources.get("expand"), mxGraphActions.getExpandAction(),
				"/com/variamos/gui/perspeditor/images/expand.gif"));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("toBack"), mxGraphActions.getToBackAction(),
				"/com/variamos/gui/perspeditor/images/toback.gif"));
		menu.add(editor.bind(mxResources.get("toFront"), mxGraphActions.getToFrontAction(),
				"/com/variamos/gui/perspeditor/images/tofront.gif"));

		menu.addSeparator();

		JMenu submenu = (JMenu) menu.add(new JMenu(mxResources.get("align")));

		submenu.add(editor.bind(mxResources.get("left"), new AlignCellsAction(mxConstants.ALIGN_LEFT),
				"/com/variamos/gui/perspeditor/images/alignleft.gif"));
		submenu.add(editor.bind(mxResources.get("center"), new AlignCellsAction(mxConstants.ALIGN_CENTER),
				"/com/variamos/gui/perspeditor/images/aligncenter.gif"));
		submenu.add(editor.bind(mxResources.get("right"), new AlignCellsAction(mxConstants.ALIGN_RIGHT),
				"/com/variamos/gui/perspeditor/images/alignright.gif"));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("top"), new AlignCellsAction(mxConstants.ALIGN_TOP),
				"/com/variamos/gui/perspeditor/images/aligntop.gif"));
		submenu.add(editor.bind(mxResources.get("middle"), new AlignCellsAction(mxConstants.ALIGN_MIDDLE),
				"/com/variamos/gui/perspeditor/images/alignmiddle.gif"));
		submenu.add(editor.bind(mxResources.get("bottom"), new AlignCellsAction(mxConstants.ALIGN_BOTTOM),
				"/com/variamos/gui/perspeditor/images/alignbottom.gif"));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("autosize"), new AutosizeAction()));

	}

	/**
	 * Adds menu items to the given format menu. This is factored out because the
	 * format menu appears in the menubar and also in the popupmenu.
	 */
	public static void populateFormatMenu(JMenu menu, BasicGraphEditor editor) {
		JMenu submenu = (JMenu) menu.add(new JMenu(mxResources.get("background")));

		submenu.add(editor.bind(mxResources.get("fillcolor"), new ColorAction("Fillcolor", mxConstants.STYLE_FILLCOLOR),
				"/com/variamos/gui/perspeditor/images/fillcolor.gif"));
		submenu.add(
				editor.bind(mxResources.get("gradient"), new ColorAction("Gradient", mxConstants.STYLE_GRADIENTCOLOR)));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("image"), new PromptValueAction(mxConstants.STYLE_IMAGE, "Image")));
		submenu.add(editor.bind(mxResources.get("shadow"), new ToggleAction(mxConstants.STYLE_SHADOW)));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("opacity"),
				new PromptValueAction(mxConstants.STYLE_OPACITY, "Opacity (0-100)")));

		submenu = (JMenu) menu.add(new JMenu(mxResources.get("label")));

		submenu.add(editor.bind(mxResources.get("fontcolor"), new ColorAction("Fontcolor", mxConstants.STYLE_FONTCOLOR),
				"/com/variamos/gui/perspeditor/images/fontcolor.gif"));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("labelFill"),
				new ColorAction("Label Fill", mxConstants.STYLE_LABEL_BACKGROUNDCOLOR)));
		submenu.add(editor.bind(mxResources.get("labelBorder"),
				new ColorAction("Label Border", mxConstants.STYLE_LABEL_BORDERCOLOR)));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("rotateLabel"), new ToggleAction(mxConstants.STYLE_HORIZONTAL, true)));

		submenu.add(editor.bind(mxResources.get("textOpacity"),
				new PromptValueAction(mxConstants.STYLE_TEXT_OPACITY, "Opacity (0-100)")));

		submenu.addSeparator();

		JMenu subsubmenu = (JMenu) submenu.add(new JMenu(mxResources.get("position")));

		subsubmenu.add(editor.bind(mxResources.get("top"),
				new SetLabelPositionAction(mxConstants.ALIGN_TOP, mxConstants.ALIGN_BOTTOM)));
		subsubmenu.add(editor.bind(mxResources.get("middle"),
				new SetLabelPositionAction(mxConstants.ALIGN_MIDDLE, mxConstants.ALIGN_MIDDLE)));
		subsubmenu.add(editor.bind(mxResources.get("bottom"),
				new SetLabelPositionAction(mxConstants.ALIGN_BOTTOM, mxConstants.ALIGN_TOP)));

		subsubmenu.addSeparator();

		subsubmenu.add(editor.bind(mxResources.get("left"),
				new SetLabelPositionAction(mxConstants.ALIGN_LEFT, mxConstants.ALIGN_RIGHT)));
		subsubmenu.add(editor.bind(mxResources.get("center"),
				new SetLabelPositionAction(mxConstants.ALIGN_CENTER, mxConstants.ALIGN_CENTER)));
		subsubmenu.add(editor.bind(mxResources.get("right"),
				new SetLabelPositionAction(mxConstants.ALIGN_RIGHT, mxConstants.ALIGN_LEFT)));

		submenu.addSeparator();

		submenu.add(
				editor.bind(mxResources.get("wordWrap"), new KeyValueAction(mxConstants.STYLE_WHITE_SPACE, "wrap")));
		submenu.add(
				editor.bind(mxResources.get("noWordWrap"), new KeyValueAction(mxConstants.STYLE_WHITE_SPACE, null)));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("hide"), new ToggleAction(mxConstants.STYLE_NOLABEL)));

		menu.addSeparator();

		submenu = (JMenu) menu.add(new JMenu(mxResources.get("line")));

		submenu.add(
				editor.bind(mxResources.get("linecolor"), new ColorAction("Linecolor", mxConstants.STYLE_STROKECOLOR),
						"/com/variamos/gui/perspeditor/images/linecolor.gif"));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("orthogonal"), new ToggleAction(mxConstants.STYLE_ORTHOGONAL)));
		submenu.add(editor.bind(mxResources.get("dashed"), new ToggleAction(mxConstants.STYLE_DASHED)));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("linewidth"),
				new PromptValueAction(mxConstants.STYLE_STROKEWIDTH, "Linewidth")));

		submenu = (JMenu) menu.add(new JMenu(mxResources.get("connector")));

		submenu.add(editor.bind(mxResources.get("straight"), new SetStyleAction("straight"),
				"/com/variamos/gui/perspeditor/images/straight.gif"));

		submenu.add(editor.bind(mxResources.get("horizontal"), new SetStyleAction(""),
				"/com/variamos/gui/perspeditor/images/connect.gif"));
		submenu.add(editor.bind(mxResources.get("vertical"), new SetStyleAction("vertical"),
				"/com/variamos/gui/perspeditor/images/vertical.gif"));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("entityRelation"),
				new SetStyleAction("edgeStyle=mxEdgeStyle.EntityRelation"),
				"/com/variamos/gui/perspeditor/images/entity.gif"));
		submenu.add(editor.bind(mxResources.get("arrow"), new SetStyleAction("arrow"),
				"/com/variamos/gui/perspeditor/images/arrow.gif"));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("plain"), new ToggleAction(mxConstants.STYLE_NOEDGESTYLE)));

		menu.addSeparator();

		submenu = (JMenu) menu.add(new JMenu(mxResources.get("linestart")));

		submenu.add(editor.bind(mxResources.get("open"),
				new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_OPEN),
				"/com/variamos/gui/perspeditor/images/open_start.gif"));
		submenu.add(editor.bind(mxResources.get("classic"),
				new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_CLASSIC),
				"/com/variamos/gui/perspeditor/images/classic_start.gif"));
		submenu.add(editor.bind(mxResources.get("block"),
				new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_BLOCK),
				"/com/variamos/gui/perspeditor/images/block_start.gif"));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("diamond"),
				new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_DIAMOND),
				"/com/variamos/gui/perspeditor/images/diamond_start.gif"));
		submenu.add(editor.bind(mxResources.get("oval"),
				new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_OVAL),
				"/com/variamos/gui/perspeditor/images/oval_start.gif"));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("none"),
				new KeyValueAction(mxConstants.STYLE_STARTARROW, mxConstants.NONE)));
		submenu.add(editor.bind(mxResources.get("size"),
				new PromptValueAction(mxConstants.STYLE_STARTSIZE, "Linestart Size")));

		submenu = (JMenu) menu.add(new JMenu(mxResources.get("lineend")));

		submenu.add(editor.bind(mxResources.get("open"),
				new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OPEN),
				"/com/variamos/gui/perspeditor/images/open_end.gif"));
		submenu.add(editor.bind(mxResources.get("classic"),
				new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC),
				"/com/variamos/gui/perspeditor/images/classic_end.gif"));
		submenu.add(editor.bind(mxResources.get("block"),
				new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_BLOCK),
				"/com/variamos/gui/perspeditor/images/block_end.gif"));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("diamond"),
				new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_DIAMOND),
				"/com/variamos/gui/perspeditor/images/diamond_end.gif"));
		submenu.add(editor.bind(mxResources.get("oval"),
				new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OVAL),
				"/com/variamos/gui/perspeditor/images/oval_end.gif"));

		submenu.addSeparator();

		submenu.add(
				editor.bind(mxResources.get("none"), new KeyValueAction(mxConstants.STYLE_ENDARROW, mxConstants.NONE)));
		submenu.add(
				editor.bind(mxResources.get("size"), new PromptValueAction(mxConstants.STYLE_ENDSIZE, "Lineend Size")));

		menu.addSeparator();

		submenu = (JMenu) menu.add(new JMenu(mxResources.get("alignment")));

		submenu.add(editor.bind(mxResources.get("left"),
				new KeyValueAction(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT),
				"/com/variamos/gui/perspeditor/images/left.gif"));
		submenu.add(editor.bind(mxResources.get("center"),
				new KeyValueAction(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER),
				"/com/variamos/gui/perspeditor/images/center.gif"));
		submenu.add(editor.bind(mxResources.get("right"),
				new KeyValueAction(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_RIGHT),
				"/com/variamos/gui/perspeditor/images/right.gif"));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("top"),
				new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_TOP),
				"/com/variamos/gui/perspeditor/images/top.gif"));
		submenu.add(editor.bind(mxResources.get("middle"),
				new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE),
				"/com/variamos/gui/perspeditor/images/middle.gif"));
		submenu.add(editor.bind(mxResources.get("bottom"),
				new KeyValueAction(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_BOTTOM),
				"/com/variamos/gui/perspeditor/images/bottom.gif"));

		submenu = (JMenu) menu.add(new JMenu(mxResources.get("spacing")));

		submenu.add(editor.bind(mxResources.get("top"),
				new PromptValueAction(mxConstants.STYLE_SPACING_TOP, "Top Spacing")));
		submenu.add(editor.bind(mxResources.get("right"),
				new PromptValueAction(mxConstants.STYLE_SPACING_RIGHT, "Right Spacing")));
		submenu.add(editor.bind(mxResources.get("bottom"),
				new PromptValueAction(mxConstants.STYLE_SPACING_BOTTOM, "Bottom Spacing")));
		submenu.add(editor.bind(mxResources.get("left"),
				new PromptValueAction(mxConstants.STYLE_SPACING_LEFT, "Left Spacing")));

		submenu.addSeparator();

		submenu.add(
				editor.bind(mxResources.get("global"), new PromptValueAction(mxConstants.STYLE_SPACING, "Spacing")));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("sourceSpacing"),
				new PromptValueAction(mxConstants.STYLE_SOURCE_PERIMETER_SPACING, mxResources.get("sourceSpacing"))));
		submenu.add(editor.bind(mxResources.get("targetSpacing"),
				new PromptValueAction(mxConstants.STYLE_TARGET_PERIMETER_SPACING, mxResources.get("targetSpacing"))));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("perimeter"),
				new PromptValueAction(mxConstants.STYLE_PERIMETER_SPACING, "Perimeter Spacing")));

		submenu = (JMenu) menu.add(new JMenu(mxResources.get("direction")));

		submenu.add(editor.bind(mxResources.get("north"),
				new KeyValueAction(mxConstants.STYLE_DIRECTION, mxConstants.DIRECTION_NORTH)));
		submenu.add(editor.bind(mxResources.get("east"),
				new KeyValueAction(mxConstants.STYLE_DIRECTION, mxConstants.DIRECTION_EAST)));
		submenu.add(editor.bind(mxResources.get("south"),
				new KeyValueAction(mxConstants.STYLE_DIRECTION, mxConstants.DIRECTION_SOUTH)));
		submenu.add(editor.bind(mxResources.get("west"),
				new KeyValueAction(mxConstants.STYLE_DIRECTION, mxConstants.DIRECTION_WEST)));

		submenu.addSeparator();

		submenu.add(editor.bind(mxResources.get("rotation"),
				new PromptValueAction(mxConstants.STYLE_ROTATION, "Rotation (0-360)")));

		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("rounded"), new ToggleAction(mxConstants.STYLE_ROUNDED)));

		menu.add(editor.bind(mxResources.get("style"), new StyleAction()));
	}
}
