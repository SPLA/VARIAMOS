package com.variamos.gui.maineditor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.ImageIcon;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxCellOverlay;
import com.mxgraph.swing.util.mxICellOverlay;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstCell;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.gui.core.io.ConsoleTextArea;




/**
 * This class is useful o draw overlays for showing states and errors in operations that are executed in the GUI
 * @author Juan Munoz
 *
 */
@SuppressWarnings("serial")
public class VariamosGraphComponent extends mxGraphComponent {

	private boolean simulationStarted = false;

	public void setSimulationStarted(boolean simulationStarted) {
		this.simulationStarted = simulationStarted;
	}

	public VariamosGraphComponent(mxGraph graph, Color bgColor) {
		super(graph);
		setToolTips(true);
		setAutoExtend(true);
		setAutoScroll(false);
		setDragEnabled(false);
		setPanning(true);
		configureSelectionHandler();

		final mxGraph finalGraph = graph;
		getViewport().setOpaque(true);
		getViewport().setBackground(bgColor);

		// Installs automatic validation
		graph.getModel().addListener(mxEvent.CHANGE, new mxIEventListener() {
			@Override
			public void invoke(Object sender, mxEventObject evt) {
				// ArrayList list = ((ArrayList) evt.getProperty("changes"));
				/*
				 * if (list != null && list.size() <= 3 && list.get(0)
				 * instanceof mxGraphModel.mxValueChange) { mxCell value =
				 * (mxCell) ((mxGraphModel.mxValueChange) list
				 * .get(0)).getCell(); clearCellOverlays(value);
				 * validateGraph(value, new Hashtable<Object, Object>());
				 * drawCellIcons(finalGraph.getModel().getRoot()); } else
				 */{
					clearCellOverlays();
					validateGraph();
					drawCellIcons(finalGraph.getModel().getRoot());
				}
			}

		});
	}

	
	private void configureSelectionHandler() {
		graph.getSelectionModel().addListener(mxEvent.CHANGE,
				new mxIEventListener() {

					@Override
					public void invoke(Object sender, mxEventObject evt) {

					}
				});
	}

	private void drawCellIcons(Object cell) {
		mxIGraphModel model = graph.getModel();
		mxCell parentCell = (mxCell) model.getChildAt(cell, 0);
		List<String> redx = new ArrayList<String>();
		for (int red = 1; red < 7; red++)
			redx.add(imagesBasePath + "red-x-" + red + ".gif");

		if (model.getChildCount(parentCell) > 0) {
			int parentCount = model.getChildCount(parentCell);

			for (int j = 0; j < parentCount; j++) {

				mxCell tmp3 = (mxCell) model.getChildAt(parentCell, j);
				int childCount = model.getChildCount(tmp3);
				for (int i = 0; i < childCount; i++) {
					mxCell childCell = (mxCell) model.getChildAt(tmp3, i);

					InstElement childValue = ((InstCell) childCell.getValue())
							.getInstElement();
					int child2Count = model.getChildCount(childCell);
					if (child2Count > 0)
						for (int k = 0; k < child2Count; k++) {
							mxCell child2Cell = (mxCell) model.getChildAt(
									childCell, k);
							InstElement child2Value = ((InstCell) child2Cell
									.getValue()).getInstElement();
							// if
							// (SharedActions.validateConceptType(child2Value,
							// "GeneralConcept")) {
							drawStatusBar(child2Cell, parentCell);
							drawErrorIcons(child2Cell, child2Value, redx);
							// }
						}
					else {
						// if (SharedActions.validateConceptType(childValue,
						// "GeneralConcept")) {
						drawStatusBar(childCell, parentCell);
						drawErrorIcons(childCell, childValue, redx);
						// }
					}
				}
			}
		}

		/*
		 * if (model.getChildCount(parentCell) > 0) { parentCell = (mxCell)
		 * model.getChildAt(parentCell, 0); // TODO // implement // for other //
		 * model // views int childCount = model.getChildCount(parentCell);
		 * 
		 * for (int i = 0; i < childCount; i++) { mxCell childCell = (mxCell)
		 * model.getChildAt(parentCell, i); Object childValue =
		 * childCell.getValue(); int child2Count =
		 * model.getChildCount(childCell); if (child2Count > 0) for (int k = 0;
		 * k < child2Count; k++) { mxCell child2Cell = (mxCell)
		 * model.getChildAt( childCell, k); Object child2Value =
		 * child2Cell.getValue(); drawErrorIcons(child2Cell, child2Value, redx);
		 * } else drawErrorIcons(childCell, childValue, redx); } }
		 */
	}

	private String imagesBasePath = "/com/variamos/gui/perspeditor/images/test/";

	private void drawStatusBar(mxCell childCell, mxCell parentCell) {
		InstElement val = ((InstCell) childCell.getValue()).getInstElement();

		// For simulation perspective
		if (childCell.getGeometry() != null && val != null
				&& val instanceof InstConcept && parentCell.getValue() != null) {
			try {
				InstConcept instConcept = (InstConcept) val;

				for (InstAttribute ia : instConcept.getInstAttributes()
						.values()) {
					if (ia.getType() != null && ia.getType().equals("Boolean")
							&& ia.getValue() instanceof String)
						if (((String) ia.getValue()).equals("0"))
							ia.setValue(false);
						else
							ia.setValue(true);
				}
				String backtophint = "", sidehint = "";

				String sim_core = imagesBasePath + "sim_core.png";
				String sim_core_req = imagesBasePath + "sim_core_req.png";
				String sim_dead = imagesBasePath + "sim_dead.png";
				String sim_inactive = imagesBasePath + "sim_inactive.png";
				String sim_outmessage = imagesBasePath + "outmessage.png";
				String sim_normal = imagesBasePath + "sim_normal.png";
				String sim_notavailable = imagesBasePath
						+ "sim_notavailable.png";
				String sim_prohibit = imagesBasePath + "design_prohibited.png";
				String sim_selected = imagesBasePath + "sim_selected.png";
				String sim_backcolor = sim_normal;

				String sim_green1 = imagesBasePath + "sim_green1.png";
				String sim_green2 = imagesBasePath + "sim_green2.png";
				String sim_green2_tmp = imagesBasePath + "sim_green2_tmp.png";
				String sim_green3 = imagesBasePath + "sim_green3.png";

				String sim_red2 = imagesBasePath + "sim_red2.png";
				String sim_red2_tmp = imagesBasePath + "sim_red2_tmp.png";
				String sim_red3 = imagesBasePath + "sim_red3.png";
				if (simulationStarted) {
					sim_backcolor = sim_red3;
					backtophint = "Not selected";
				}
				
				/* domain implementation */
				if (instConcept.getInstAttribute("SelectedToIntegrate") != null)
				{
					if (!(instConcept.getInstAttribute("SelectedToIntegrate").getValue() instanceof Boolean))
						return;
					if ((boolean) instConcept.getInstAttribute("SelectedToIntegrate").getValue()) {
						backtophint = "Selected";
						mxCellOverlay over3 = new mxCellOverlay(
								new ImageIcon(
										mxGraphComponent.class
												.getResource(sim_selected)),
								backtophint);
						over3.setVerticalAlign(mxConstants.ALIGN_TOP);
						over3.setAlign(mxConstants.ALIGN_CENTER);
						addCellOverlay(childCell, over3);
					}			
				}
				/* domain implementation */
				
				if (instConcept.getInstAttribute("Core") != null
						&& instConcept.getInstAttribute("Required") != null) {
					if (!(instConcept.getInstAttribute("Core").getValue() instanceof Boolean))
						return;
					if ((boolean) instConcept.getInstAttribute("Core")
							.getValue()
							&& !(boolean) instConcept.getInstAttribute(
									"Required").getValue()) {
						sim_backcolor = sim_core;
						backtophint = "Required (by an implication)";
					} else if ((boolean) instConcept.getInstAttribute("Core")
							.getValue()
							&& (boolean) instConcept.getInstAttribute(
									"Required").getValue()) {
						sim_backcolor = sim_core_req;
						backtophint = "Required (by manual selection)";
						// } else if (!(boolean) instConcept
						// .getInstAttribute("Active").getValue()) {
						// sim_backcolor = sim_inactive;
						// backtophint = "Inactive by user";
					} else if ((boolean) instConcept.getInstAttribute("Dead")
							.getValue()) {
						sim_backcolor = sim_dead;
						backtophint = "Dead element";
					} else if ((boolean) instConcept.getInstAttribute("Proh")
							.getValue()) {
						sim_backcolor = sim_prohibit;
						backtophint = "Prohibit element";
					} else if (instConcept.getInstAttribute("Var") != null
							&& (boolean) instConcept.getInstAttribute("Var")
									.getValue()) {
						sim_backcolor = sim_inactive;
						backtophint = "Variant Feature";
					} else if ((boolean) instConcept.getInstAttribute("Exclu")
							.getValue()) {
						sim_backcolor = sim_notavailable;
						backtophint = "Not selected for this solution";
					} else if ((boolean) instConcept.getInstAttribute("Sel")
							.getValue()) {
						sim_backcolor = sim_selected;
						// backtophint = "Selected for this solution";
					} else {
						backtophint = "Not selected";
					}
					// if (!backtophint.equals("")) {
					// if (sim_normal != sim_backcolor) {
					mxCellOverlay over2 = new mxCellOverlay(new ImageIcon(
							mxGraphComponent.class.getResource(sim_backcolor)),
							backtophint);
					over2.setVerticalAlign(mxConstants.ALIGN_TOP);
					over2.setAlign(mxConstants.ALIGN_CENTER);
					addCellOverlay(childCell, over2);
					// }
					// }
					if (instConcept.getInstAttribute("outAnaSel") != null) {

						mxCellOverlay over3 = new mxCellOverlay(new ImageIcon(
								mxGraphComponent.class
										.getResource(sim_outmessage)),
								"Element Selected from Analysis");
						over3.setVerticalAlign(mxConstants.ALIGN_MIDDLE);
						over3.setAlign(mxConstants.ALIGN_RIGHT);
						if ((boolean) instConcept.getInstAttribute("outAnaSel")
								.getValue())
							addCellOverlay(childCell, over3);
						else {
							for (mxICellOverlay o : this
									.getCellOverlays(childCell)) {
								if (((mxCellOverlay) o).getVerticalAlign()
										.equals(mxConstants.ALIGN_MIDDLE)
										&& ((mxCellOverlay) o)
												.getAlign()
												.equals(mxConstants.ALIGN_RIGHT))
									removeCellOverlay(childCell, o);
							}
						}
					}

					if (instConcept.getInstAttribute("inAnaSel") != null) {

						mxCellOverlay over3 = new mxCellOverlay(new ImageIcon(
								mxGraphComponent.class
										.getResource(sim_outmessage)),
								"Element Selected for Analysis");
						over3.setVerticalAlign(mxConstants.ALIGN_MIDDLE);
						over3.setAlign(mxConstants.ALIGN_LEFT);
						if ((boolean) instConcept.getInstAttribute("inAnaSel")
								.getValue())
							addCellOverlay(childCell, over3);
						else {
							for (mxICellOverlay o : this
									.getCellOverlays(childCell)) {
								if (((mxCellOverlay) o).getVerticalAlign()
										.equals(mxConstants.ALIGN_MIDDLE)
										&& ((mxCellOverlay) o).getAlign()
												.equals(mxConstants.ALIGN_LEFT))
									removeCellOverlay(childCell, o);
							}
						}
					}

					if ((boolean) instConcept.getInstAttribute("Required")
							.getValue()) {
						mxCellOverlay over3 = new mxCellOverlay(
								new ImageIcon(
										mxGraphComponent.class
												.getResource(sim_green1)),
								backtophint);
						// backtophint += "\n Core (First green circle)";
						over3.setVerticalAlign(mxConstants.ALIGN_TOP);
						over3.setAlign(mxConstants.ALIGN_CENTER);
						addCellOverlay(childCell, over3);
					}
					if ((boolean) instConcept.getInstAttribute("Sel")
							.getValue()
							&& ((boolean) instConcept.getInstAttribute(
									"ConfSel").getValue() || (boolean) instConcept
									.getInstAttribute("TestConfSel").getValue())) {
						mxCellOverlay over3 = new mxCellOverlay(
								new ImageIcon(
										mxGraphComponent.class
												.getResource(sim_green2)),
								"Configuration Selected");

						backtophint = "Configuration Selected";
						over3.setVerticalAlign(mxConstants.ALIGN_TOP);
						over3.setAlign(mxConstants.ALIGN_CENTER);
						addCellOverlay(childCell, over3);
					}
					if (!(boolean) instConcept.getInstAttribute("Sel")
							.getValue()
							&& ((boolean) instConcept.getInstAttribute(
									"ConfSel").getValue() || (boolean) instConcept
									.getInstAttribute("TestConfSel").getValue())) {
						mxCellOverlay over3 = new mxCellOverlay(new ImageIcon(
								mxGraphComponent.class
										.getResource(sim_green2_tmp)),
								"Configuration Selected (Only testing)");

						backtophint = "Configuration Selected (Only testing)";
						over3.setVerticalAlign(mxConstants.ALIGN_TOP);
						over3.setAlign(mxConstants.ALIGN_CENTER);
						addCellOverlay(childCell, over3);
					}
					if ((boolean) instConcept.getInstAttribute("SimulSel")
							.getValue()) {
						mxCellOverlay over3 = new mxCellOverlay(
								new ImageIcon(
										mxGraphComponent.class
												.getResource(sim_green3)),
								"Simulation selected");
						over3.setVerticalAlign(mxConstants.ALIGN_TOP);
						over3.setAlign(mxConstants.ALIGN_CENTER);
						addCellOverlay(childCell, over3);
					}
					if (!(boolean) instConcept.getInstAttribute("Exclu")
							.getValue()
							&& ((boolean) instConcept.getInstAttribute(
									"ConfNotSel").getValue() || (boolean) instConcept
									.getInstAttribute("TestConfNotSel")
									.getValue())) {
						mxCellOverlay over3 = new mxCellOverlay(new ImageIcon(
								mxGraphComponent.class
										.getResource(sim_red2_tmp)),
								"Configuration Not Selected (Only testing)");
						over3.setVerticalAlign(mxConstants.ALIGN_TOP);
						over3.setAlign(mxConstants.ALIGN_CENTER);
						addCellOverlay(childCell, over3);
					}
					if ((boolean) instConcept.getInstAttribute("Exclu")
							.getValue()
							&& ((boolean) instConcept.getInstAttribute(
									"ConfNotSel").getValue() || (boolean) instConcept
									.getInstAttribute("TestConfNotSel")
									.getValue())) {
						mxCellOverlay over3 = new mxCellOverlay(new ImageIcon(
								mxGraphComponent.class.getResource(sim_red2)),
								"Configuration Not Selected");
						over3.setVerticalAlign(mxConstants.ALIGN_TOP);
						over3.setAlign(mxConstants.ALIGN_CENTER);
						addCellOverlay(childCell, over3);
					}

					// if ((boolean) instConcept.getInstAttribute("NNotSel")
					// .getValue()) {
					// mxCellOverlay over3 = new mxCellOverlay(new ImageIcon(
					// mxGraphComponent.class.getResource(sim_red3)),
					// backbottomhint + "Not selected");
					// backbottomhint = "Not selected";
					// over3.setVerticalAlign(mxConstants.ALIGN_TOP);
					// over3.setAlign(mxConstants.ALIGN_CENTER);
					// addCellOverlay(childCell, over3);
					// }
				}
			} catch (Exception e) {
				System.out.println("Cell draw error 01");
				e.printStackTrace();
				// ConsoleTextArea.addText(e.getStackTrace());
			}
		}

		// For design perspective
		if (childCell.getGeometry() != null && val != null
				&& parentCell.getValue() == null && val instanceof InstConcept) {
			String design_required = imagesBasePath + "design_required.png";
			String design_normal = imagesBasePath + "design_normal.png";
			String design_dead = imagesBasePath + "design_dead.png";
			String design_prohibit = imagesBasePath + "design_prohibited.png";
			String design_core = imagesBasePath + "design_core.png";
			String backtophint = "", icon = "";
			InstConcept instConcept = (InstConcept) val;
			try {
				if ((boolean) instConcept.getInstAttribute("Required")
						.getValue()) {
					backtophint = "Part of the core (by manual selection)";
					icon = design_required;
				} else if ((boolean) instConcept.getInstAttribute("Core")
						.getValue()) {
					backtophint = "Part of the core (by implicatiom)";
					icon = design_core;
				} else if ((boolean) instConcept.getInstAttribute("Dead")
						.getValue()) {
					backtophint = "Dead element (by implication)";
					icon = design_dead;
				} else if ((boolean) instConcept.getInstAttribute("Proh")
						.getValue()) {
					backtophint = "Prohibited element (by manual selection)";
					icon = design_prohibit;
				} else {
					backtophint = "Not part of the core (Variability point)";
					icon = design_normal;
				}
				mxCellOverlay over3 = new mxCellOverlay(new ImageIcon(
						mxGraphComponent.class.getResource(icon)), backtophint);
				over3.setVerticalAlign(mxConstants.ALIGN_TOP);
				over3.setAlign(mxConstants.ALIGN_CENTER);
				addCellOverlay(childCell, over3);

			} catch (Exception e) {
				System.out.println("Cell draw error 02");
				// ConsoleTextArea.addText(e.getStackTrace());
			}
		}
		// }
		// }
		// }
	}

	private void drawErrorIcons(mxCell childCell, Object childValue,
			List<String> redx) {
		if (childCell.getGeometry() != null && childValue != null
				&& childValue instanceof InstElement) {
			try {
				InstElement instConcept = (InstElement) childValue;
				String error = "/com/variamos/gui/perspeditor/images/test/design_dead_arrow.png";
				if (childValue instanceof InstConcept)
					error = "/com/variamos/gui/perspeditor/images/test/design_dead.png";
				String defects = "";
				for (String defect : instConcept.getDefects().values()) {
					defects = defects + " * " + defect;
				}
				if (!defects.equals("")) {
					mxCellOverlay over3 = new mxCellOverlay(new ImageIcon(
							mxGraphComponent.class.getResource(error)), defects);
					over3.setVerticalAlign(mxConstants.ALIGN_MIDDLE);
					over3.setAlign(mxConstants.ALIGN_RIGHT);
					addCellOverlay(childCell, over3);
				}

				/*
				 * if ((boolean) instConcept.getInstAttribute(
				 * "VerificationError").getValue()) { mxCellOverlay over3 = new
				 * mxCellOverlay( new ImageIcon( mxGraphComponent.class
				 * .getResource(error)),
				 * "This element is a false optional or too many roots features"
				 * ); over3.setVerticalAlign(mxConstants.ALIGN_TOP);
				 * over3.setAlign(mxConstants.ALIGN_RIGHT); addCellOverlay(tmp,
				 * over3); }
				 */
				if (instConcept.getInstAttribute("HasParent") != null
						&& !instConcept.getInstAttribute("HasParent")
								.getAsBoolean()) {
					mxCellOverlay over3 = new mxCellOverlay(new ImageIcon(
							mxGraphComponent.class.getResource(error)),
							"This element must have a parent");
					over3.setVerticalAlign(mxConstants.ALIGN_BOTTOM);
					over3.setAlign(mxConstants.ALIGN_RIGHT);
					addCellOverlay(childCell, over3);
				}

			} catch (Exception e) {
				ConsoleTextArea.addText(e.getStackTrace());
			}
		}
	}

	
	
	@Override
	public String getEditingValue(Object cell, EventObject trigger) {
		return super.getEditingValue(cell, trigger);
	}
}

