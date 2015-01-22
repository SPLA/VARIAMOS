package com.variamos.gui.refas.editor;

import java.awt.CheckboxMenuItem;
import java.awt.Component;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.variamos.gui.common.actions.ConfigureAction;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.refas.configurator.guiactions.LoadConfigurationAction;
import com.variamos.gui.refas.configurator.guiactions.SaveConfigurationAction;
import com.variamos.gui.refas.configurator.guiactions.SaveProductsAction;
import com.variamos.gui.refas.editor.actions.ExitAction;
import com.variamos.gui.refas.editor.actions.NewAction;
import com.variamos.gui.refas.editor.actions.OpenAction;
import com.variamos.gui.refas.editor.actions.ParentElementAction;
import com.variamos.gui.refas.editor.actions.RootElementAction;
import com.variamos.gui.refas.editor.actions.SaveAction;
import com.variamos.gui.refas.editor.actions.ToggleAssetVisibilityAction;
import com.variamos.gui.refas.editor.actions.TogglePLVisibilityAction;
import com.variamos.gui.refas.editor.actions.VerifyDeadElementAction;
//import com.variamos.gui.pl.editor.actions.VerifyDeadElementAction;
//import com.variamos.gui.pl.editor.actions.VerifyFalseOptionalElementAction;
//import com.variamos.gui.pl.editor.actions.VerifyFalseProductLineModelAction;
//import com.variamos.gui.pl.editor.actions.VerifyVoidModelAction;
import com.variamos.gui.refas.editor.actions.VerifyFalseOptElementAction;

@SuppressWarnings("serial")
public class RefasMenuBar extends JMenuBar {

	VariamosGraphEditor editor;

	public RefasMenuBar(BasicGraphEditor basicGraphEditor) {
		init(basicGraphEditor);
	}

	private void init(BasicGraphEditor editor) {
		JMenu menu = new JMenu("File");
		menu.add(editor.bind(mxResources.get("new"), new NewAction()));
		menu.add(editor.bind(mxResources.get("load"), new OpenAction()));
		menu.addSeparator();
		menu.add(editor.bind(mxResources.get("save"), new SaveAction(false)));
		menu.add(editor.bind(mxResources.get("saveAs"), new SaveAction(true)));
		menu.addSeparator();

		menu.add(editor.bind(mxResources.get("exit"), new ExitAction()));

		add(menu);

		menu = (JMenu) menu.add(new JMenu(mxResources.get("layout")));

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

		menu = (JMenu) menu.add(new JMenu(mxResources.get("verifyDefects")));
		// menu.add(editor.bind(mxResources.get("verifyVoidModel"), new
		// VerifyVoidModelAction()));
		// menu.add(editor.bind(mxResources.get("verifyFalseProductLine"), new
		// VerifyFalseProductLineModelAction()));
		menu.add(editor.bind(mxResources.get("verifyRoot"),
				new RootElementAction()));
		menu.add(editor.bind(mxResources.get("verifyParents"),
				new ParentElementAction()));
		menu.add(editor.bind(mxResources.get("verifyDeadElement"),
				new VerifyDeadElementAction()));
		menu.add(editor.bind(mxResources.get("verifyFalseOptionalElements"),
				new VerifyFalseOptElementAction()));
		add(menu);

		menu = (JMenu) menu.add(new JMenu(mxResources
				.get("verifyDefectsOptions")));
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(
				mxResources.get("verifyRoot"));
		item.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {

				if (arg0.getSource() instanceof Component) {
					Component component = (Component) arg0.getSource();

					while (component != null
							&& !(component instanceof VariamosGraphEditor)) {
						component = component.getParent();
					}
					if (((JCheckBoxMenuItem) arg0.getSource()).getState())
						((VariamosGraphEditor) component).updateDefects("Root",
								true);
					else
						((VariamosGraphEditor) component).updateDefects("Root",
								false);
				}
			}

		});
		menu.add(item);

		item = new JCheckBoxMenuItem(mxResources.get("verifyParents"));
		item.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {

				if (arg0.getSource() instanceof Component) {
					Component component = (Component) arg0.getSource();

					while (component != null
							&& !(component instanceof VariamosGraphEditor)) {
						component = component.getParent();
					}
					if (((JCheckBoxMenuItem) arg0.getSource()).getState())
						((VariamosGraphEditor) component).updateDefects(
								"Parent", true);
					else
						((VariamosGraphEditor) component).updateDefects(
								"Parent", false);
				}
			}

		});
		menu.add(item);

		item = new JCheckBoxMenuItem(mxResources.get("verifyDeadElement"));
		item.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {

				if (arg0.getSource() instanceof Component) {
					Component component = (Component) arg0.getSource();

					while (component != null
							&& !(component instanceof VariamosGraphEditor)) {
						component = component.getParent();
					}
					if (((JCheckBoxMenuItem) arg0.getSource()).getState())
						((VariamosGraphEditor) component).updateDefects("Dead",
								true);
					else
						((VariamosGraphEditor) component).updateDefects("Dead",
								false);
				}
			}

		});
		menu.add(item);

		item = new JCheckBoxMenuItem(
				mxResources.get("verifyFalseOptionalElements"));
		item.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {

				if (arg0.getSource() instanceof Component) {
					Component component = (Component) arg0.getSource();

					while (component != null
							&& !(component instanceof VariamosGraphEditor)) {
						component = component.getParent();
					}
					if (((JCheckBoxMenuItem) arg0.getSource()).getState())
						((VariamosGraphEditor) component).updateDefects(
								"FalseOpt", true);
					else
						((VariamosGraphEditor) component).updateDefects(
								"FalseOpt", false);
				}
			}

		});
		menu.add(item);
		menu.addSeparator();
		add(menu);

		/*
		 * menu = (JMenu) menu.add(new JMenu(mxResources.get("configuration")));
		 * menu.add(editor.bind(mxResources.get("configure"), new
		 * ConfigureAction()));
		 * menu.add(editor.bind(mxResources.get("saveConfiguration"), new
		 * SaveConfigurationAction(true)));
		 * menu.add(editor.bind(mxResources.get("loadConfiguration"), new
		 * LoadConfigurationAction()));
		 * menu.add(editor.bind(mxResources.get("saveProducts"), new
		 * SaveProductsAction())); add(menu);
		 */
	}

}
