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
import javax.swing.KeyStroke;

import com.mxgraph.util.mxResources;
import com.variamos.gui.configurator.guiactions.ExportConfigurationAction;
import com.variamos.gui.configurator.guiactions.LoadConfigurationAction;
import com.variamos.gui.configurator.guiactions.SaveConfigurationAction;
import com.variamos.gui.configurator.guiactions.SaveProductsAction;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.actions.AboutAction;
import com.variamos.gui.perspeditor.actions.CheckUpdateAction;
import com.variamos.gui.perspeditor.actions.ClearConfigurationAction;
import com.variamos.gui.perspeditor.actions.ClearSimulationAction;
import com.variamos.gui.perspeditor.actions.ClearVerificationAction;
import com.variamos.gui.perspeditor.actions.ElementOperationAssociationAction;
import com.variamos.gui.perspeditor.actions.ExitAction;
import com.variamos.gui.perspeditor.actions.ExternalContextAction;
import com.variamos.gui.perspeditor.actions.HideAdvancedPerspectiveAction;
import com.variamos.gui.perspeditor.actions.HideSimulationDashBoardAction;
import com.variamos.gui.perspeditor.actions.HideSimulationsCustomizationBox;
import com.variamos.gui.perspeditor.actions.NewAction;
import com.variamos.gui.perspeditor.actions.NextSimulationAction;
import com.variamos.gui.perspeditor.actions.OpenAction;
import com.variamos.gui.perspeditor.actions.OperationDefinitionAction;
import com.variamos.gui.perspeditor.actions.ParentElementAction;
import com.variamos.gui.perspeditor.actions.RootElementAction;
import com.variamos.gui.perspeditor.actions.SaveAction;
import com.variamos.gui.perspeditor.actions.ShowAdvancedPerspectiveAction;
import com.variamos.gui.perspeditor.actions.ShowSimulationCustomizationBox;
import com.variamos.gui.perspeditor.actions.ShowSimulationDashBoardAction;
import com.variamos.gui.perspeditor.actions.StartSimulationAction;
import com.variamos.gui.perspeditor.actions.ToggleAssetVisibilityAction;
import com.variamos.gui.perspeditor.actions.TogglePLVisibilityAction;
import com.variamos.gui.perspeditor.actions.VariableLabelingAssociationAction;
import com.variamos.gui.perspeditor.actions.VariableOperationAssociationAction;
import com.variamos.gui.perspeditor.actions.VerificationAction;
import com.variamos.gui.perspeditor.actions.VerifyDeadElementAction;
import com.variamos.gui.perspeditor.actions.VerifyFalseOptElementAction;
import com.variamos.perspsupport.instancesupport.InstElement;

@SuppressWarnings("serial")
public class PerspEditorMenuBar extends JMenuBar {

	VariamosGraphEditor editor;

	public PerspEditorMenuBar(BasicGraphEditor basicGraphEditor) {
		init(basicGraphEditor);
	}

	private void init(BasicGraphEditor editor) {
		JMenu menu = new JMenu("File");
		menu.setMnemonic('F');
		Action al;
		al = editor.bind(mxResources.get("new"), new NewAction());
		al.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menu.add(al);
		al = editor.bind(mxResources.get("load"), new OpenAction());
		al.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		menu.add(al);
		menu.addSeparator();
		al = editor.bind(mxResources.get("save"), new SaveAction(false));
		al.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menu.add(al);
		al = editor.bind(mxResources.get("saveAs"), new SaveAction(true));
		al.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		menu.add(al);
		menu.addSeparator();
		al = editor.bind(mxResources.get("exit"), new ExitAction());
		al.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menu.add(al);

		add(menu);

		menu = (JMenu) menu.add(new JMenu(mxResources.get("layout")));
		menu.setMnemonic('L');
		menu.add(editor
				.bind("Toggle Assets", new ToggleAssetVisibilityAction()));
		menu.add(editor.bind("Toggle Variability Elements",
				new TogglePLVisibilityAction()));
		menu.addSeparator();

		menu.add(editor.graphLayout("verticalHierarchical", true));
		menu.add(editor.graphLayout("horizontalHierarchical", true));

		menu.addSeparator();

		// menu.add(editor.graphLayout("verticalPartition", false));
		// menu.add(editor.graphLayout("horizontalPartition", false));
		//
		// menu.addSeparator();

		menu.add(editor.graphLayout("verticalStack", true));
		menu.add(editor.graphLayout("horizontalStack", true));

		menu.addSeparator();

		menu.add(editor.graphLayout("verticalTree", true));
		menu.add(editor.graphLayout("horizontalTree", true));

		menu.addSeparator();

		menu.add(editor.graphLayout("placeEdgeLabels", true));
		menu.add(editor.graphLayout("parallelEdges", true));

		menu.addSeparator();

		menu.add(editor.graphLayout("organicLayout", true));
		menu.add(editor.graphLayout("circleLayout", true));
		add(menu);
		final VariamosGraphEditor finalEditor = (VariamosGraphEditor) editor;

		if (editor.getPerspective() == 2) {
			menu = (JMenu) menu
					.add(new JMenu(mxResources.get("verifyDefects")));
			menu.setMnemonic('I');
			// menu.add(editor.bind(mxResources.get("verifyVoidModel"), new
			// VerifyVoidModelAction()));
			// menu.add(editor.bind(mxResources.get("verifyFalseProductLine"),
			// new
			// VerifyFalseProductLineModelAction()));
			menu.add(editor.bind(mxResources.get("verifyRoot"),
					new RootElementAction()));
			menu.add(editor.bind(mxResources.get("verifyParents"),
					new ParentElementAction()));
			menu.add(editor.bind(mxResources.get("verifyDeadElement"),
					new VerifyDeadElementAction()));
			menu.add(editor.bind(
					mxResources.get("verifyFalseOptionalElements"),
					new VerifyFalseOptElementAction()));
			menu.addSeparator();
			menu.add(editor.bind(mxResources.get("clearElements"),
					new ClearVerificationAction()));
			add(menu);

			menu = (JMenu) menu.add(new JMenu(mxResources
					.get("verifyDefectsOptions")));
			menu.setMnemonic('D');
			JCheckBoxMenuItem item = new JCheckBoxMenuItem(
					mxResources.get("verifyRoot"));
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

			item = new JCheckBoxMenuItem(
					mxResources.get("verifyFalseOptionalElements"));
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
			menu.add(editor.bind(mxResources.get("clearElements"),
					new ClearVerificationAction()));
			al = editor.bind(mxResources.get("verifyElements"),
					new VerificationAction());
			al.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(
					KeyEvent.VK_W, ActionEvent.CTRL_MASK));
			menu.add(al);
			add(menu);

		}
		if (editor.getPerspective() == 2 || (editor.getPerspective() == 4)) {
			List<InstElement> menus = ((VariamosGraphEditor) editor)
					.getEditedModel().getSemanticRefas()
					.getVariabilityVertex("CSOpMenu");
			for (InstElement menuElement : menus) {
				if ((boolean) menuElement.getInstAttribute("visible")
						.getValue() == true
						&& ((String) menuElement.getInstAttribute("menuType")
								.getValue()).equals(editor.getPerspective()
								+ "")) {
					menu = (JMenu) menu.add(new JMenu(menuElement
							.getIdentifier()));
					// menu.setMnemonic();
					for (InstElement oper : menuElement.getTargetRelations()) {
						JCheckBoxMenuItem item = new JCheckBoxMenuItem(oper
								.getTargetRelations().get(0).getIdentifier());
						item.setState(true);
						item.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								AbstractButton aButton = (AbstractButton) arg0
										.getSource();
								boolean selected = aButton.getModel()
										.isSelected();

								if (selected)
									finalEditor.updateDefects("FalseOpt", true);
								else
									finalEditor
											.updateDefects("FalseOpt", false);
							}
						});
						menu.add(item);
					}
					add(menu);
				}
			}
		}
		if (editor.getPerspective() == 1) {
			menu = (JMenu) menu.add(new JMenu(mxResources
					.get("translationConfiguration")));
			menu.setMnemonic('C');
			Action a = editor.bind(mxResources.get("operationDefinition"),
					new OperationDefinitionAction());
			menu.add(a);
			a = editor.bind(mxResources.get("elementOperationAssociation"),
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

			menu = (JMenu) menu
					.add(new JMenu(mxResources.get("configuration")));
			menu.setMnemonic('C');
			Action a = editor.bind(mxResources.get("startConfiguration"),
					new ClearConfigurationAction());
			a.setEnabled(false);
			menu.add(a);

			a = editor.bind(mxResources.get("restartConfiguration"),
					new ClearConfigurationAction());
			menu.add(a);
			menu.addSeparator();
			a = editor.bind(mxResources.get("loadConfiguration"),
					new ClearSimulationAction());
			a.setEnabled(false);
			menu.add(a);
			a = editor.bind(mxResources.get("saveConfiguration"),
					new SaveConfigurationAction(true));
			menu.add(a);
			a.setEnabled(false);
			a = editor.bind(mxResources.get("saveProducts"),
					new SaveProductsAction());
			menu.add(a);
			a.setEnabled(false);
			menu.addSeparator();

			add(menu);

			menu = (JMenu) menu.add(new JMenu(mxResources.get("simulation")));
			menu.setMnemonic('S');
			a = editor.bind(mxResources.get("resetSimulation"),
					new ClearSimulationAction());
			a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(
					KeyEvent.VK_R, ActionEvent.CTRL_MASK));
			menu.add(a);
			a = editor.bind(mxResources.get("startSimulation"),
					new StartSimulationAction());
			a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(
					KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			menu.add(a);
			a = editor.bind(mxResources.get("nextSimulation"),
					new NextSimulationAction());
			a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(
					KeyEvent.VK_T, ActionEvent.CTRL_MASK));
			menu.add(a);

			add(menu);

			menu.addSeparator();
			a = editor.bind(mxResources.get("loadSolution"),
					new LoadConfigurationAction());
			menu.add(a);
			a.setEnabled(false);

			a = editor.bind(mxResources.get("saveCurrentSolution"),
					new SaveProductsAction());
			menu.add(a);

			a = editor.bind(mxResources.get("exportConfiguration"),
					new ExportConfigurationAction(true));
			menu.add(a);

			menu.addSeparator();

			menu.setMnemonic('U');
			a = editor.bind(mxResources.get("externalContext"),
					new ExternalContextAction());
			menu.add(a);
			add(menu);
			menu = (JMenu) menu.add(new JMenu(mxResources.get("dashboard")));
			menu.setMnemonic('D');

			a = editor.bind(mxResources.get("showSimulationDashBoard"),
					new ShowSimulationDashBoardAction());
			a.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(
					KeyEvent.VK_B, ActionEvent.CTRL_MASK));
			menu.add(a);
			menu.add(editor.bind(mxResources.get("hideSimulationDashBoard"),
					new HideSimulationDashBoardAction()));
			JCheckBoxMenuItem item = new JCheckBoxMenuItem(
					mxResources.get("nameSimulationDashBoard"));
			item.setState(true);
			item.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AbstractButton aButton = (AbstractButton) arg0.getSource();
					boolean selected = aButton.getModel().isSelected();

					if (selected) {
						finalEditor.showNames(true);
						finalEditor.updateDashBoard(false, false);
					} else {
						finalEditor.showNames(false);
						finalEditor.updateDashBoard(false, false);
					}
				}
			});
			menu.add(item);
			add(menu);

		}

		menu = (JMenu) menu.add(new JMenu(mxResources.get("window")));
		menu.setMnemonic('W');
		menu.add(editor.bind(mxResources.get("showAdvancedPerspectives"),
				new ShowAdvancedPerspectiveAction()));
		menu.add(editor.bind(mxResources.get("hideAdvancedPerspectives"),
				new HideAdvancedPerspectiveAction()));
		menu.add(editor.bind(mxResources.get("showSimulationCustomizationBox"),
				new ShowSimulationCustomizationBox()));
		menu.add(editor.bind(mxResources.get("hideSimulationCustomizationBox"),
				new HideSimulationsCustomizationBox()));
		add(menu);

		menu = (JMenu) menu.add(new JMenu(mxResources.get("help")));
		menu.setMnemonic('H');
		menu.add(editor.bind(mxResources.get("about"), new AboutAction()));

		menu.add(editor.bind(mxResources.get("checkUpdates"),
				new CheckUpdateAction()));
		add(menu);
	}
}
