package com.variamos.gui.maineditor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.ImageIcon;

import com.cfm.productline.constraints.ExcludesConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.cfm.productline.constraints.MandatoryConstraint;
import com.cfm.productline.constraints.OptionalConstraint;
import com.cfm.productline.constraints.RequiresConstraint;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxCellOverlay;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;
import com.variamos.pl.editor.logic.ConstraintMode;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.MetaVertex;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

@SuppressWarnings("serial")
public class VariamosGraphComponent extends mxGraphComponent {

	public VariamosGraphComponent(mxGraph graph, Color bgColor) {
		super(graph);

		setToolTips(true);
		setAutoExtend(true);
		setAutoScroll(false);
		setDragEnabled(false);
		setPanning(true);
		configureConnectionHandler();
		configureSelectionHandler();

		final mxGraph finalGraph = graph;
		getViewport().setOpaque(true);
		getViewport().setBackground(bgColor);

		// Installs automatic validation
		graph.getModel().addListener(mxEvent.CHANGE, new mxIEventListener() {
			public void invoke(Object sender, mxEventObject evt) {
				clearCellOverlays();
				validateGraph();
				drawCellIcons(finalGraph.getModel().getRoot());
			}

		});
	}

	public void updateGraph(mxGraph graph) {
		setGraph(graph);
		configureConnectionHandler();
		configureSelectionHandler();
		final mxGraph finalGraph = graph;
		// Installs automatic validation
		graph.getModel().addListener(mxEvent.CHANGE, new mxIEventListener() {
			public void invoke(Object sender, mxEventObject evt) {
				clearCellOverlays();
				validateGraph();
				drawCellIcons(finalGraph.getModel().getRoot());
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

	public void drawCellIcons(Object cell) {
		mxIGraphModel model = graph.getModel();
		Object tmp2 = model.getChildAt(cell, 0);

		if (model.getChildCount(tmp2) > 0) {
			int parentCount = model.getChildCount(tmp2);

			for (int j = 0; j < parentCount; j++) {

				mxCell tmp3 = (mxCell) model.getChildAt(tmp2, j);
				int childCount = model.getChildCount(tmp3);
				for (int i = 0; i < childCount; i++) {
					mxCell tmp = (mxCell) model.getChildAt(tmp3, i);
					if (tmp.isVertex()) {

						// Verify is a semantic general element
						MetaVertex metaElement = ((MetaVertex) ((InstVertex) tmp
								.getValue()).getTransSupportMetaElement());
						IntSemanticElement semElement = metaElement == null ? null
								: metaElement.getTransSemanticConcept();
						while (semElement != null
								&& semElement.getIdentifier() != null
								&& !semElement.getIdentifier().equals(
										"SemGeneralElement"))
							semElement = semElement.getParent();

						if (semElement != null
								&& semElement.getIdentifier() != null
								&& semElement.getIdentifier().equals(
										"SemGeneralElement")) {
							Object val = tmp.getValue();

							// For simulation perspective
							if (tmp.getGeometry() != null && val != null
									&& val instanceof InstConcept
									&& ((mxCell) tmp2).getValue() != null) {
								try {
									InstConcept instConcept = (InstConcept) val;
									String backtophint = null, backbottomhint = null;

									String sim_core = "/com/variamos/gui/refas/editor/images/sim_core.png";
									String sim_dead = "/com/variamos/gui/refas/editor/images/sim_dead.png";
									String sim_inactive = "/com/variamos/gui/refas/editor/images/sim_inactive.png";
									String sim_normal = "/com/variamos/gui/refas/editor/images/sim_normal.png";
									String sim_notavailable = "/com/variamos/gui/refas/editor/images/sim_notavailable.png";
									String sim_selected = "/com/variamos/gui/refas/editor/images/sim_selected.png";
									String sim_backcolor = sim_normal;

									String sim_green1 = "/com/variamos/gui/refas/editor/images/sim_green1.png";
									String sim_green2 = "/com/variamos/gui/refas/editor/images/sim_green2.png";
									String sim_green3 = "/com/variamos/gui/refas/editor/images/sim_green3.png";

									String sim_red2 = "/com/variamos/gui/refas/editor/images/sim_red2.png";
									String sim_red3 = "/com/variamos/gui/refas/editor/images/sim_red3.png";

									if ((boolean) instConcept.getInstAttribute(
											"Required").getValue()
											|| (boolean) instConcept
													.getInstAttribute("Core")
													.getValue()) {
										sim_backcolor = sim_core;
										backtophint = "Element part of the core (red on sides)";
									} else if (!(boolean) instConcept
											.getInstAttribute("Active")
											.getValue()) {
										sim_backcolor = sim_inactive;
										backtophint = "Element inactivated by user (black background)";
									} else if ((boolean) instConcept
											.getInstAttribute("Dead")
											.getValue()) {
										sim_backcolor = sim_dead;
										backtophint = "Dead element (red background)";
									} else if ((boolean) instConcept
											.getInstAttribute("NotAvailable")
											.getValue()) {
										sim_backcolor = sim_notavailable;
										backtophint = "Not available for this configuration (red background)";
									} else if ((boolean) instConcept
											.getInstAttribute("Selected")
											.getValue()) {
										sim_backcolor = sim_selected;
										backtophint = "Element selected for this configuration (green background)";
									}
									mxCellOverlay over2 = new mxCellOverlay(
											new ImageIcon(
													mxGraphComponent.class
															.getResource(sim_backcolor)),
											backtophint);
									over2.setVerticalAlign(mxConstants.ALIGN_TOP);
									over2.setAlign(mxConstants.ALIGN_CENTER);
									addCellOverlay(tmp, over2);

									if ((boolean) instConcept.getInstAttribute(
											"Required").getValue()) {
										mxCellOverlay over3 = new mxCellOverlay(
												new ImageIcon(
														mxGraphComponent.class
																.getResource(sim_green1)),
												backtophint
														+ "\n Core (First green circle)");
										backtophint += "\n Core (First green circle)";
										over3.setVerticalAlign(mxConstants.ALIGN_TOP);
										over3.setAlign(mxConstants.ALIGN_CENTER);
										addCellOverlay(tmp, over3);
									}
									if ((boolean) instConcept.getInstAttribute(
											"ConfigSelected").getValue()) {
										mxCellOverlay over3 = new mxCellOverlay(
												new ImageIcon(
														mxGraphComponent.class
																.getResource(sim_green2)),
												backtophint
														+ "; Configuration Selected (Second green circle)");

										backtophint += "; Configuration Selected (Second green circle)";
										over3.setVerticalAlign(mxConstants.ALIGN_TOP);
										over3.setAlign(mxConstants.ALIGN_CENTER);
										addCellOverlay(tmp, over3);
									}
									if ((boolean) instConcept.getInstAttribute(
											"NextReqSelected").getValue()) {
										mxCellOverlay over3 = new mxCellOverlay(
												new ImageIcon(
														mxGraphComponent.class
																.getResource(sim_green3)),
												backtophint
														+ "; Config/Simul selected (Third green circle)");
										over3.setVerticalAlign(mxConstants.ALIGN_TOP);
										over3.setAlign(mxConstants.ALIGN_CENTER);
										addCellOverlay(tmp, over3);
									}
									if ((boolean) instConcept.getInstAttribute(
											"ConfigNotSelected").getValue()) {
										mxCellOverlay over3 = new mxCellOverlay(
												new ImageIcon(
														mxGraphComponent.class
																.getResource(sim_red2)),
												backbottomhint
														+ "; Configuration Not Selected (Second red circle)");
										backbottomhint += "; Configuration Not Selected (Second red circle)";
										over3.setVerticalAlign(mxConstants.ALIGN_TOP);
										over3.setAlign(mxConstants.ALIGN_CENTER);
										addCellOverlay(tmp, over3);
									}

									if ((boolean) instConcept.getInstAttribute(
											"NextNotSelected").getValue()) {
										mxCellOverlay over3 = new mxCellOverlay(
												new ImageIcon(
														mxGraphComponent.class
																.getResource(sim_red3)),
												backbottomhint
														+ "; Config/Simul not selected (Third red circle)");
										over3.setVerticalAlign(mxConstants.ALIGN_TOP);
										over3.setAlign(mxConstants.ALIGN_CENTER);
										addCellOverlay(tmp, over3);
									}
								} catch (Exception e) {
									System.out.println("Cell draw error");
									e.printStackTrace();
								}
							}

							// For design perspective
							if (tmp.getGeometry() != null && val != null
									&& ((mxCell) tmp2).getValue() == null
									&& val instanceof InstConcept) {
								String design_required = "/com/variamos/gui/refas/editor/images/design_required.png";
								String design_normal = "/com/variamos/gui/refas/editor/images/design_normal.png";
								String design_dead = "/com/variamos/gui/refas/editor/images/design_dead.png";
								String design_core = "/com/variamos/gui/refas/editor/images/design_core.png";
								String backtophint = "", icon = "";
								InstConcept instConcept = (InstConcept) val;
								try {
									if ((boolean) instConcept.getInstAttribute(
											"Required").getValue()) {
										backtophint = "Element is part of the core. Marked as required";
										icon = design_required;
									} else if ((boolean) instConcept
											.getInstAttribute("Core")
											.getValue()) {
										backtophint = "Element is part of the core. Not marked as required";
										icon = design_core;
									} else if ((boolean) instConcept
											.getInstAttribute("Dead")
											.getValue()) {
										backtophint = "Element is dead. It cannot be selected";
										icon = design_dead;
									} else {
										backtophint = "Element is not part of the core. Not marked as required";
										icon = design_normal;
									}
									mxCellOverlay over3 = new mxCellOverlay(
											new ImageIcon(
													mxGraphComponent.class
															.getResource(icon)),
											backtophint);
									over3.setVerticalAlign(mxConstants.ALIGN_TOP);
									over3.setAlign(mxConstants.ALIGN_CENTER);
									addCellOverlay(tmp, over3);

								} catch (Exception e) {
									System.out.println("Cell draw error");
									// e.printStackTrace();
								}
							}
						}
					}
				}
			}

		}
		List<String> redx = new ArrayList<String>();
		for (int red = 1; red < 7; red++)
			redx.add("/com/variamos/gui/refas/editor/images/red-x-" + red
					+ ".gif");

		if (model.getChildCount(tmp2) > 0) {
			tmp2 = model.getChildAt(tmp2, 0); // TODO implement for other model
												// views
			int childCount = model.getChildCount(tmp2);

			for (int i = 0; i < childCount; i++) {
				mxCell tmp = (mxCell) model.getChildAt(tmp2, i);
				Object val = tmp.getValue();
				if (tmp.getGeometry() != null && val != null
						&& val instanceof InstConcept) {
					try {
						InstConcept instConcept = (InstConcept) val;
						String error = "/com/mxgraph/examples/swing/images/x-red.gif";
						int pos = 0;
						for (String defect : instConcept.getDefects().values()) {
							mxCellOverlay over3 = new mxCellOverlay(
									new ImageIcon(mxGraphComponent.class
											.getResource(redx.get(pos))),
									defect);
							over3.setVerticalAlign(mxConstants.ALIGN_MIDDLE);
							over3.setAlign(mxConstants.ALIGN_RIGHT);
							addCellOverlay(tmp, over3);
						}

						/*
						 * if ((boolean) instConcept.getInstAttribute(
						 * "VerificationError").getValue()) { mxCellOverlay
						 * over3 = new mxCellOverlay( new ImageIcon(
						 * mxGraphComponent.class .getResource(error)),
						 * "This element is a false optional or too many roots features"
						 * ); over3.setVerticalAlign(mxConstants.ALIGN_TOP);
						 * over3.setAlign(mxConstants.ALIGN_RIGHT);
						 * addCellOverlay(tmp, over3); }
						 */
						if (!instConcept.getInstAttribute("HasParent")
								.getAsBoolean()) {
							mxCellOverlay over3 = new mxCellOverlay(
									new ImageIcon(
											mxGraphComponent.class
													.getResource(error)),
									"This element must have a parent");
							over3.setVerticalAlign(mxConstants.ALIGN_BOTTOM);
							over3.setAlign(mxConstants.ALIGN_RIGHT);
							addCellOverlay(tmp, over3);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	private void configureConnectionHandler() {
		getConnectionHandler().setCreateTarget(false);
		getConnectionHandler().setEnabled(true);
		getConnectionHandler().addListener(mxEvent.CONNECT, onConnect);
	}

	private mxIEventListener onConnect = /**
	 * @author jcmunoz: Creates relations
	 *         between nodes, works for group constraints now.
	 *
	 */
	new mxIEventListener() {

		@Override
		public void invoke(Object sender, mxEventObject evt) {
			mxCell insertedCell = (mxCell) evt.getProperty("cell");
			AbstractGraph graph = (AbstractGraph) getGraph();

			mxCell source = (mxCell) insertedCell.getSource();
			mxCell target = (mxCell) insertedCell.getTarget();

			if (source.getValue() instanceof GroupConstraint) {

				// Remove previous cells between them
				Object[] edges = graph.getEdgesBetween(source, target, false);
				graph.removeCells(edges);
				graph.addCell(insertedCell);

				GroupConstraint gc = (GroupConstraint) source.getValue();
				gc.addChildId(target.getId());
				// gc.printDebug(System.out);
				return;
			}

			if (target.getValue() instanceof GroupConstraint) {

				// Remove previous cells between them
				Object[] edges = graph.getEdgesBetween(source, target, false);
				graph.removeCells(edges);
				graph.addCell(insertedCell);

				GroupConstraint gc = (GroupConstraint) target.getValue();
				// If there is a parent, remove it
				if (gc.getParent() != null) {
					// Removing parent
					Object[] parentEdges = graph.getEdgesBetween(
							graph.getCellById(gc.getParent()), target, false);
					graph.removeCells(parentEdges);
				}

				gc.setParent(source.getId());
				// gc.printDebug(System.out);
				return;
			}

			ConstraintMode mode = graph.getConsMode();

			switch (mode) {
			case Optional:
				OptionalConstraint op = new OptionalConstraint(source.getId(),
						target.getId());
				insertedCell.setValue(op);
				insertedCell.setStyle("ploptional");
				break;
			case Mandatory:
				MandatoryConstraint om = new MandatoryConstraint(
						source.getId(), target.getId());
				insertedCell.setValue(om);
				insertedCell.setStyle("plmandatory");
				break;

			case Requires:
				RequiresConstraint rq = new RequiresConstraint(source.getId(),
						target.getId());
				insertedCell.setValue(rq);
				insertedCell.setStyle("plrequires");
				break;
			case Excludes:
				ExcludesConstraint ec = new ExcludesConstraint(source.getId(),
						target.getId());
				insertedCell.setValue(ec);
				insertedCell.setStyle("plexcludes");
				break;

			case Default:
				graph.removeCells(new Object[] { insertedCell });
				// Create a new constraint
				graph.connectDefaultConstraint(source, target);
				break;
			default:
				break;
			}

			// System.out.println("Source = " + source.getValue() +
			// ", Target = " + target.getValue());

		}

	};

	@Override
	public String getEditingValue(Object cell, EventObject trigger) {
		return super.getEditingValue(cell, trigger);
	}
}
