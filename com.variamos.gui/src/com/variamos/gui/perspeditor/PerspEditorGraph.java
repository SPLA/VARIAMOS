package com.variamos.gui.perspeditor;

import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxGraphicsCanvas2D;
import com.mxgraph.io.mxCodec;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.shape.mxStencil;
import com.mxgraph.shape.mxStencilRegistry;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstCell;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstOverTwoRel;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.instance.InstVertex;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.gui.core.io.ConsoleTextArea;
import com.variamos.gui.maineditor.MainFrame;

public class PerspEditorGraph extends mxGraph {

	public static final String PL_EVT_NODE_CHANGE = "plEvtNodeChange";
	private InstanceModel modelInstance = null;
	private int modelViewIndex = 0;
	private int modelViewSubIndex = -1;
	private boolean validation = true;
	private int perspective = 2;

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public int getModelViewIndex() {
		return modelViewIndex;
	}

	public void setModelViewIndex(int modelView) {
		this.modelViewIndex = modelView;
	}

	public int getModelViewSubIndex() {
		return modelViewSubIndex;
	}

	public void setModelViewSubIndex(int modelSubView) {
		this.modelViewSubIndex = modelSubView;
	}

	public PerspEditorGraph(int perspective) {
		init();
		this.perspective = perspective;
	}

	public PerspEditorGraph(int perspective, InstanceModel refasModel) {
		init();
		this.perspective = perspective;
		this.modelInstance = refasModel;
	}

	public void defineInitialGraph() {
		mxCell root = new mxCell();
		mxCell parent = new mxCell();
		root.insert(parent);
		InstAttribute att = new InstAttribute();
		att.setInstAttributeAttribute("versionNumber",
				MainFrame.getVariamosVersionNumber());
		parent.setValue(att);
		getModel().setRoot(root);
		List<InstElement> views = null;
		if (modelInstance.getSyntaxModel() != null)
			views = modelInstance.getSyntaxModel().getVariabilityVertex(
					"SyMView");
		int pos = 0;
		if (views != null && views.size() == 0) {
			// Load Syntax and Semantic
			for (InstElement instVertex : modelInstance.getVertices()) {
				mxCell child = new mxCell();
				child.setValue(new InstCell(child, instVertex, false));
				child.setId(instVertex.getIdentifier());
				addCell(child);
				String id = instVertex.getIdentifier();

				child.setVisible(true);
				child.setStyle(instVertex.getTransSupportMetaElement()
						.getStyle());
				child.setGeometry(new mxGeometry(50 + pos * 3, 50 + pos * 3,
						120, 100));
				child.setVertex(true);
				mxGraphModel model = (mxGraphModel) getModel();
				model.getCells().remove(child.getId());
				model.getCells().put("0" + id, child);
				child.setId("0" + id);
				pos++;

			}
			/*
			 * for (InstView instView :
			 * refasModel.getVariabilityVertex("SyMView")) { if
			 * (instView.getChildViews().size() == 0) { mxCell child = new
			 * mxCell(instView.getIdentifier()); child.setValue(new
			 * InstCell(child, instView, false)); addCell(child); String id =
			 * instView.getIdentifier(); child.setVisible(true);
			 * child.setStyle(instView.getTransSupportMetaElement()
			 * .getStyle()); child.setGeometry(new mxGeometry(50 + pos * 3, 50 +
			 * pos * 3, 120, 40)); child.setVertex(true); mxGraphModel model =
			 * (mxGraphModel) getModel();
			 * model.getCells().remove(child.getId()); model.getCells().put("0"
			 * + id, child); child.setId("0" + id); pos++; } for (InstView
			 * instChildView : instView.getChildViews()) { mxCell child2 = new
			 * mxCell(); child2.setValue(new InstCell(child2, instChildView,
			 * false)); child2.setId(instChildView.getIdentifier());
			 * addCell(child2); String id2 = instChildView.getIdentifier();
			 * child2.setVisible(true);
			 * child2.setStyle(instChildView.getTransSupportMetaElement()
			 * .getStyle()); child2.setGeometry(new mxGeometry(50 + pos * 3, 50
			 * + pos * 3, 120, 40)); child2.setVertex(true); mxGraphModel model2
			 * = (mxGraphModel) getModel();
			 * model2.getCells().remove(child2.getId());
			 * model2.getCells().put("0" + id2, child2); child2.setId("0" +
			 * id2); pos++;
			 * 
			 * } }
			 */
			for (InstPairwiseRel instEdge : modelInstance
					.getConstraintInstEdgesCollection()) {
				if (instEdge.getSourceRelations().size() != 0
						&& instEdge.getIdentifier() != null
						&& !instEdge.getIdentifier().equals("")) {
					mxCell child = new mxCell();
					child.setValue(new InstCell(child, instEdge, false));
					child.setId(instEdge.getIdentifier());
					addCell(child);
					mxCell source = this.getCellById(modelViewIndex
							+ instEdge.getSourceRelations().get(0)
									.getIdentifier());
					mxCell target = this.getCellById(modelViewIndex
							+ instEdge.getTargetRelations().get(0)
									.getIdentifier());
					child.setStyle("");
					SyntaxElement e = instEdge.getTransSupportMetaElement();
					if (e != null) {
						child.setStyle(e.getStyle());
					}

					child.setSource(source);
					child.setTarget(target);
					mxGeometry geo = new mxGeometry();
					String id = instEdge.getIdentifier();
					if (source != null)
						source.insertEdge(child, true);
					if (target != null)
						target.insertEdge(child, false);
					child.setGeometry(geo);
					child.setVisible(true);
					child.setVertex(false);
					child.setEdge(true);
					mxGraphModel model = (mxGraphModel) getModel();
					model.getCells().remove(child.getId());
					model.getCells().put(modelViewIndex + id, child);
					child.setId(modelViewIndex + id);
				}
			}
		}

		// Load views for System Design and simulation
		int i = 0;
		if (views != null) {
			for (@SuppressWarnings("unused")
			InstElement view : views) {
				if (view.getInstAttribute("Visible").getAsBoolean() == false)
					continue;
				mxCell child = new mxCell();
				child.setValue(new InstCell(null, null, false));
				child.setId("mv" + i);
				addCell(child, parent);
				/*
				 * if (view instanceof InstView) { InstView instView =
				 * (InstView) view; if (instView.getChildViews().size() > 0) {
				 * for (int j = 0; j < instView.getChildViews().size(); j++) {
				 * mxCell child2 = new mxCell(); child2.setValue(new
				 * InstCell(child2, null, false)); child2.setId("mv" + i + "-" +
				 * j); addCell(child2, child); } } }
				 */
				i++;
			}
		}
	}

	public void createNewEdge(InstPairwiseRel instEdge) {
		if (instEdge.getSourceRelations().size() != 0
				&& instEdge.getIdentifier() != null
				&& !instEdge.getIdentifier().equals("")) {
			mxCell child = new mxCell();
			child.setValue(new InstCell(child, instEdge, false));
			child.setId(instEdge.getIdentifier());
			addCell(child);
			mxCell source = this.getCellById(modelViewIndex
					+ instEdge.getSourceRelations().get(0).getIdentifier());
			mxCell target = this.getCellById(modelViewIndex
					+ instEdge.getTargetRelations().get(0).getIdentifier());
			child.setStyle("");
			SyntaxElement e = instEdge.getTransSupportMetaElement();
			if (e != null) {
				child.setStyle(e.getStyle());
			}

			child.setSource(source);
			child.setTarget(target);
			mxGeometry geo = new mxGeometry();
			String id = instEdge.getIdentifier();
			if (source != null)
				source.insertEdge(child, true);
			if (target != null)
				target.insertEdge(child, false);
			child.setGeometry(geo);
			child.setVisible(true);
			child.setVertex(false);
			child.setEdge(true);
			mxGraphModel model = (mxGraphModel) getModel();
			model.getCells().remove(child.getId());
			model.getCells().put(modelViewIndex + id, child);
			child.setId(modelViewIndex + id);
		}
	}

	public void removeEdge(InstPairwiseRel instEdge) {
		if (instEdge.getIdentifier() != null
				&& !instEdge.getIdentifier().equals("")) {

			mxGraphModel model = (mxGraphModel) getModel();
			mxCell child = (mxCell) model.getCells().get(
					modelViewIndex + instEdge.getIdentifier());
			// model.getCells().remove(modelViewIndex +
			// instEdge.getIdentifier());
			mxCell source = this.getCellById(modelViewIndex
					+ instEdge.getSourceRelations().get(0).getIdentifier());
			mxCell target = this.getCellById(modelViewIndex
					+ instEdge.getTargetRelations().get(0).getIdentifier());
			if (source != null)
				source.removeEdge(child, true);
			if (target != null)
				target.removeEdge(child, false);
			model.getCells().remove(child);
		}
	}

	public List<String> getValidElements(int modelView, int modelSubView) {
		if (modelInstance.getSyntaxModel() == null)
			return null;
		return modelInstance.getSyntaxModel().modelElements(modelView,
				modelSubView);
	}

	String getResource(String rsc) {
		StringBuilder result = new StringBuilder("");

		// Get file from resources folder
		InputStream in = getClass().getResourceAsStream(rsc);
		BufferedReader input = new BufferedReader(new InputStreamReader(in));

		Scanner scanner = new Scanner(input);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			result.append(line).append("\n");
		}
		scanner.close();
		return result.toString();
	}

	protected void init() {
		setLabelsVisible(true);
		setAllowDanglingEdges(false);
		// Register custom styles

		// Loads the default styles sheet from an external file
		// To draw elements on the Graph
		/*
		 * mxCodec codec = new mxCodec(); Document doc =
		 * mxUtils.loadDocument(VariamosGraphComponent.class .getResource(
		 * "/com/variamos/gui/perspeditor/style/styles.xml") .toString());
		 * codec.decode(doc.getDocumentElement(), stylesheet);
		 */
		setCellsEditable(false);
		setAllowNegativeCoordinates(false);
		addListeners();
		// Loads the defalt stylesheet from an external file
		loadStyles();
		loadStencil();
	}

	private void addListeners() {
		addListener(mxEvent.CELLS_REMOVED, new mxIEventListener() {

			@Override
			public void invoke(Object sender, mxEventObject evt) {
				Object[] removedCells = (Object[]) evt.getProperty("cells");
				for (Object remObj : removedCells) {
					mxCell cell = (mxCell) remObj;
					removingRefaElements(cell);
					removingVertex(cell, (mxCell) evt.getProperty("parent"));
					if (!cell.isEdge())
						removingClones(cell);
				}
			}

		});

		addListener(mxEvent.CELL_CONNECTED, new mxIEventListener() {
			@Override
			public void invoke(Object sender, mxEventObject evt) {
				mxCell cellC = (mxCell) evt.getProperty("terminal");
				Object edge = cellC.getEdgeAt(cellC.getEdgeCount() - 1);
				mxCell parentCell = (mxCell) evt.getProperty("parent");
				int indexCell = 0;
				Object obj = edge;
				mxCell cell = (mxCell) obj;
				if (cell == null)
					return;

				if (cell.isEdge())
					if (!connectingEdge(cell, parentCell, indexCell)) {
						System.out
								.println("Relation not supported by the MetaModel"
										+ cell.getId());

					}
			}

		});

		addListener(mxEvent.CELLS_ADDED, new mxIEventListener() {

			@Override
			public void invoke(Object sender, mxEventObject evt) {
				Object[] addedCells = (Object[]) evt.getProperty("cells");
				mxCell parentCell = (mxCell) evt.getProperty("parent");
				int indexCell = (int) evt.getProperty("index");
				for (Object obj : addedCells) {
					mxCell cell = (mxCell) obj;
					if (cell == null)
						return;

					if (!cell.isEdge())
						addingVertex(cell, parentCell, indexCell);
				}
				for (Object obj : addedCells) {
					mxCell cell = (mxCell) obj;
					if (cell == null)
						return;

					if (cell.isEdge()) {
						if (!addingEdge(cell, parentCell, indexCell)) {
							System.out
									.println("Relation not supported by the MetaModel");
						}
					}
				}
			}
		});

		addListener(mxEvent.CELLS_MOVED, new mxIEventListener() {

			@Override
			public void invoke(Object sender, mxEventObject evt) {
				Object[] addedCells = (Object[]) evt.getProperty("cells");
				mxCell parentCell = (mxCell) evt.getProperty("parent");
				// int indexCell = (int) evt.getProperty("index");
				for (Object obj : addedCells) {
					mxCell cell = (mxCell) obj;
					if (cell == null)
						return;

					// if (!cell.isEdge()) {
					movingVertex(cell, parentCell, 0);

					// }
				}
			}

		});
	}

	public void loadStyles() {
		mxCodec codec = new mxCodec();
		Document doc = mxUtils.loadDocument(MainFrame.getFilesUrl()
				+ "styles.xml");
		codec.decode(doc.getDocumentElement(), stylesheet);

	}

	public void loadStencil() {
		Document doc;
		doc = mxUtils.loadDocument(MainFrame.getFilesUrl() + "shapes.xml");
		Element shapes = doc.getDocumentElement();
		NodeList list = shapes.getElementsByTagName("shape");

		for (int i = 0; i < list.getLength(); i++) {
			Element shape = (Element) list.item(i);
			mxStencilRegistry.addStencil(shape.getAttribute("name"),
					new mxStencil(shape) {
						@Override
						protected mxGraphicsCanvas2D createCanvas(
								final mxGraphics2DCanvas gc) {
							// Redirects image loading to graphics canvas
							return new mxGraphicsCanvas2D(gc.getGraphics()) {
								@Override
								protected Image loadImage(String src) {
									// Adds image base path to relative
									// image URLs
									if (!src.startsWith("/")
											&& !src.startsWith("http://")
											&& !src.startsWith("https://")
											&& !src.startsWith("file:")) {
										src = gc.getImageBasePath() + src;
									}

									// Call is cached
									return gc.loadImage(src);
								}
							};
						}
					});
		}
	}

	protected void movingVertex(mxCell cell, mxCell parent, int index) {
		// System.out.println("moving");
	}

	@Override
	public boolean isValidConnection(Object source, Object target) {
		// Do not validate connections in the syntax mm
		// if (perspective == 3)
		// return true;
		// Do not validate connections in the semantic mm
		// if (perspective == 2)
		// return true;
		if (perspective == 4)
			return false;
		if (validation) {
			if (!(source instanceof mxCell) || !(target instanceof mxCell)) {
				return super.isValidConnection(source, target);
			}

			mxCell s = (mxCell) source;
			mxCell t = (mxCell) target;

			if (s.isEdge() || t.isEdge())
				return false;
			InstElement instSource = ((InstCell) s.getValue()).getInstElement();
			InstElement instTarget = ((InstCell) t.getValue()).getInstElement();

			HashMap<String, InstAttribute> map = new HashMap<String, InstAttribute>();
			InstPairwiseRel directRelation = new InstPairwiseRel(map, null);
			InstanceModel refas = getModelInstance();
			List<InstElement> opersParents = null;
			if (directRelation.getTransSupportMetaElement() != null
					&& directRelation.getTransSupportMetaElement()
							.getTransInstSemanticElement() != null)
				opersParents = directRelation.getTransSupportMetaElement()
						.getTransInstSemanticElement().getParentOpersConcept();
			refas.updateValidationLists(directRelation, instSource, instTarget,
					refas.getParentSMMSyntaxElement(directRelation),
					opersParents);
			InstAttribute ia = directRelation
					.getInstAttribute(InstPairwiseRel.VAR_METAPAIRWISE);
			List<InstElement> pwrList = ia.getValidationMEList();
			if (pwrList == null || pwrList.size() == 0) {
				directRelation.clearMetaPairwiseRelation();
				return false;
			}

			return super.isValidConnection(source, target);
		}
		return true;
	}

	protected boolean connectingEdge(mxCell cell, mxCell parent, int index) {
		InstElement source = ((InstCell) cell.getSource().getValue())
				.getInstElement();

		Object targetO = cell.getTarget();
		if (targetO == null)
			return false;
		InstElement target = ((InstCell) cell.getTarget().getValue())
				.getInstElement();
		Object value = cell.getValue();
		String id = null;
		HashMap<String, InstAttribute> map = new HashMap<String, InstAttribute>();
		// TODO fill the map with allowed relation types
		if (value instanceof InstCell) {
			InstPairwiseRel directRelation = (InstPairwiseRel) ((InstCell) value)
					.getInstElement();
			InstanceModel refas = getModelInstance();
			directRelation.clearRelations();
			source.addTargetRelation(directRelation, true);
			target.addSourceRelation(directRelation, true);
			List<InstElement> parents = null;
			if (directRelation.getTransSupportMetaElement() != null) {
				cell.setStyle(directRelation.getTransSupportMetaElement()
						.getStyle());
				if (directRelation.getTransSupportMetaElement()
						.getTransInstSemanticElement() != null)
					parents = directRelation.getTransSupportMetaElement()
							.getTransInstSemanticElement()
							.getParentOpersConcept();
			}
			refas.updateValidationLists(directRelation, source, target,
					refas.getParentSMMSyntaxElement(directRelation), parents);
			InstAttribute ia = directRelation
					.getInstAttribute(InstPairwiseRel.VAR_METAPAIRWISE);
			List<InstElement> pwrList = ia.getValidationMEList();
			mxGraphModel refasGraph = (mxGraphModel) getModel();
			if (pwrList.size() == 0) {
				directRelation.clearMetaPairwiseRelation();
				return false;
			}
			directRelation.setTransSupInstElement(pwrList.get(0));
			cell.setStyle(directRelation.getTransSupportMetaElement()
					.getStyle());
			directRelation.createAttributes(map);
		}
		return true;
	}

	protected boolean addingEdge(mxCell cell, mxCell parent, int index) {
		InstElement source = ((InstCell) cell.getSource().getValue())
				.getInstElement();
		InstElement target = ((InstCell) cell.getTarget().getValue())
				.getInstElement();
		Object value = cell.getValue();
		String id = null;
		HashMap<String, InstAttribute> map = new HashMap<String, InstAttribute>();
		// TODO fill the map with allowed relation types
		if (value instanceof InstCell) {
			InstPairwiseRel element = (InstPairwiseRel) ((InstCell) value)
					.getInstElement();

			String elementIdentifier = element.getIdentifier();
			if (elementIdentifier != null && !"".equals(elementIdentifier)) {
				try {
					InstElement value2 = ((InstCell) cell.getValue())
							.getInstElement();

					InstPairwiseRel instElement = (InstPairwiseRel) value2
							.clone();
					InstCell instCell = new InstCell(cell, instElement,
							((InstCell) cell.getValue()).isCloned());
					cell.setValue(instCell);

					InstanceModel pl = getModelInstance();
					// InstPairwiseRel directRelation = new InstPairwiseRel(map,
					// null);
					InstanceModel refas = getModelInstance();

					id = refas.addNewConstraintInstEdge(instElement);
					cell.setValue(new InstCell(cell, instElement, false));
					source.addTargetRelation(instElement, true);
					target.addSourceRelation(instElement, true);
				//	instElement.setTransSupInstElement(value2
				//			.getTransSupInstElement().clone());
					mxGraphModel refasGraph = (mxGraphModel) getModel();
					mxGraphModel model2 = refasGraph;
					if (modelViewSubIndex != -1) {
						refasGraph.getCells().put(
								modelViewIndex + id + "-" + modelViewSubIndex,
								cell);
						cell.setId(modelViewIndex + id + "-"
								+ modelViewSubIndex);
					} else {
						refasGraph.getCells().put(modelViewIndex + id, cell);
						cell.setId(modelViewIndex + id);
					}

				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
		}
		InstPairwiseRel directRelation = new InstPairwiseRel(map, null);
		InstanceModel refas = getModelInstance();

		id = refas.addNewConstraintInstEdge(directRelation);
		cell.setValue(new InstCell(cell, directRelation, false));
		source.addTargetRelation(directRelation, true);
		target.addSourceRelation(directRelation, true);
		List<InstElement> parents = null;
		if (directRelation.getTransSupportMetaElement() != null) {
			cell.setStyle(directRelation.getTransSupportMetaElement()
					.getStyle());
			if (directRelation.getTransSupportMetaElement()
					.getTransInstSemanticElement() != null)
				parents = directRelation.getTransSupportMetaElement()
						.getTransInstSemanticElement().getParentOpersConcept();
		}
		refas.updateValidationLists(directRelation, source, target,
				refas.getParentSMMSyntaxElement(directRelation), parents);
		InstAttribute ia = directRelation
				.getInstAttribute(InstPairwiseRel.VAR_METAPAIRWISE);
		List<InstElement> pwrList = ia.getValidationMEList();
		mxGraphModel refasGraph = (mxGraphModel) getModel();
		refasGraph.getCells().remove(cell.getId());
		if (pwrList.size() == 0) {
			directRelation.clearRelations();
			directRelation.clearMetaPairwiseRelation();
			// cell.setVisible(false); // TODO workaround to hide non allowed
			// relations - fix delete
			return false;
		}
		directRelation.setTransSupInstElement(pwrList.get(0));
		cell.setStyle(directRelation.getTransSupportMetaElement().getStyle());
		directRelation.createAttributes(map);
		if (modelViewSubIndex != -1) {
			refasGraph.getCells().put(
					modelViewIndex + id + "-" + modelViewSubIndex, cell);
			cell.setId(modelViewIndex + id + "-" + modelViewSubIndex);
		} else {
			refasGraph.getCells().put(modelViewIndex + id, cell);
			cell.setId(modelViewIndex + id);
		}

		return true;
	}

	public InstCell getInstCell() {
		// Move new element to the current View - clone if
		// necessary in multiple views
		mxGraphModel refasGraph = (mxGraphModel) getModel();
		// Main Root
		Object rootCell = refasGraph.getRoot();
		// Null Root
		mxCell viewsParent = (mxCell) refasGraph.getChildAt(rootCell, 0);
		// Top level view /Element
		InstCell instCell = null;
		int iTop = 0;
		while ((instCell == null || instCell.getInstElement() != null)
				&& iTop < viewsParent.getChildCount()) {
			mxCell topLevelView = (mxCell) refasGraph.getChildAt(viewsParent,
					iTop);
			InstCell value = (InstCell) topLevelView.getValue();
			iTop++;
			if (value != null && value.getInstElement() != null) {
				return value;
			}
			int iMed = 0;
			while ((instCell == null || instCell.getInstElement() != null)
					&& iMed < topLevelView.getChildCount()) {
				mxCell secondLevelCell = (mxCell) refasGraph.getChildAt(
						topLevelView, iMed);
				InstCell value2 = (InstCell) secondLevelCell.getValue();
				if (value2 != null && value2.getInstElement() != null
						&& iMed == modelViewIndex) {
					// modelViewIndex = iMed;
					// modelViewSubIndex = -1;
					return value2;
				}
				iMed++;
				int iLow = 0;
				while ((instCell == null || instCell.getInstElement() != null)
						&& iLow < secondLevelCell.getChildCount()) {
					InstCell element = (InstCell) ((mxCell) refasGraph
							.getChildAt(secondLevelCell, iLow)).getValue();
					if (element != null && element.getInstElement() != null
							&& iMed == modelViewIndex
							&& iLow == modelViewSubIndex) {
						// modelViewIndex = iMed;
						// modelViewSubIndex = iLow;
						return element;
					}
					iLow++;
				}
			}
		}
		return null;

	}

	// TODO review from here for requirements

	protected void removingVertex(mxCell cell, mxCell parent) {
		((InstCell) cell.getValue()).setMxCell(cell);
		InstElement value = ((InstCell) cell.getValue()).getInstElement();
		if (value instanceof InstVertex) {
			String id = null;
			String elementIdentifier = null;
			InstanceModel pl = getModelInstance();
			InstElement instElement = ((InstCell) cell.getValue())
					.getInstElement();
			if (cell.getGeometry() != null) {
				if (instElement instanceof InstVertex) {
					InstElement element = instElement;
					elementIdentifier = element.getIdentifier();
					if (element.getTransSupportMetaElement().isEditable() == false)
						this.addCell(cell, parent);
				}
			}
		}

	}

	@Override
	public Object[] moveCells(Object[] cells, double dx, double dy,
			boolean clone, Object target, Point location) {
		Object[] out = super.moveCells(cells, dx, dy, clone, target, location);

		if (clone)
			for (Object cell : out) {
				mxCell c = (mxCell) cell;
				Object value = c.getValue();
				if (value instanceof InstCell) {

					// try {
					// c.setValue(((InstCell) c.getValue()).clone());
					// } catch (CloneNotSupportedException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
				}
			}
		return out;
	}

	protected boolean addingVertex(mxCell cell, mxCell parent, int index) {
		((InstCell) cell.getValue()).setMxCell(cell);
		InstElement value = ((InstCell) cell.getValue()).getInstElement();
		if (value instanceof InstVertex) {
			String id = null;
			String elementIdentifier = null;
			InstanceModel pl = getModelInstance();
			InstElement instElement = ((InstCell) cell.getValue())
					.getInstElement();
			if (cell.getGeometry() != null) {
				if (instElement instanceof InstVertex) {
					elementIdentifier = instElement.getIdentifier();
					if (elementIdentifier != null
							&& !"".equals(elementIdentifier))
						if (cell.getId().equals(elementIdentifier)
								|| cell.getId().equals(
										modelViewIndex + elementIdentifier))
							return false;
						else
							try {

								instElement = value.clone();
								InstCell instCell = new InstCell(cell,
										instElement,
										((InstCell) cell.getValue()).isCloned());
								cell.setValue(instCell);

							} catch (CloneNotSupportedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

					if (instElement instanceof InstOverTwoRel)
						id = pl.addNewInstGroupDependency((InstOverTwoRel) instElement);
					else
						id = pl.addNewVariabilityInstElement(instElement);
				} else {
					/*
					 * elementIdentifier = ((Constraint) instElement)
					 * .getIdentifier(); if (elementIdentifier != null &&
					 * !"".equals(elementIdentifier)) return false;
					 */
					return false;
				}
				if (id != null) {

					// Move new element to the current View - clone if
					// necessary in multiple views
					mxGraphModel refasGraph = (mxGraphModel) getModel();
					// Main Root
					Object rootCell = refasGraph.getRoot();
					// Null Root
					mxCell viewsParent = (mxCell) refasGraph.getChildAt(
							rootCell, 0);
					// Top level view
					Object topLevelView = null;
					// Parent to add new element
					mxCell correctParentCell = null;
					mxGraphModel model = refasGraph;
					model.getCells().remove(cell.getId());
					if (modelViewSubIndex != -1) {
						// Top Level View
						topLevelView = refasGraph.getChildAt(viewsParent,
								modelViewIndex);
						// Child view
						correctParentCell = (mxCell) refasGraph.getChildAt(
								topLevelView, modelViewSubIndex);

						model.getCells().put(
								modelViewIndex + id + "-" + modelViewSubIndex,
								cell);
						cell.setId(modelViewIndex + id + "-"
								+ modelViewSubIndex);
					} else if (modelViewIndex != -1) {
						// Top Level view
						correctParentCell = (mxCell) refasGraph.getChildAt(
								viewsParent, modelViewIndex);
						model.getCells().put(modelViewIndex + id, cell);
						cell.setId(modelViewIndex + id);
					} else {
						// Graph without Views
						// Add to the view Parent
						correctParentCell = viewsParent;
						model.getCells().put(id, cell);
						cell.setId(id);
					}
					// Remove from original position
					parent.remove(index);
					// Add to the parent according to the model
					model.add(correctParentCell, cell, 0);

					if (instElement instanceof InstVertex) {
						String name = null;
						if (instElement instanceof InstConcept) {
							InstConcept c = (InstConcept) instElement;
							name = c.getTransSupportMetaElement()
									.getAutoIdentifier();
						}
						if (instElement instanceof InstOverTwoRel) {
							InstOverTwoRel c = (InstOverTwoRel) instElement;
							name = c.getSupportMetaOverTwoRelation()
									.getAutoIdentifier();
						}
						viewsParent = (mxCell) refasGraph.getChildAt(rootCell,
								0);
						// Null Root
						for (int i = 0; i < refasGraph
								.getChildCount(viewsParent); i++) {
							mxCell mv1 = (mxCell) refasGraph.getChildAt(
									viewsParent, i);
							String mv1id = mv1.getId();
							if (refasGraph.getChildCount(mv1) > 0
									&& mv1.getChildAt(0).getId()
											.startsWith(mv1id)) {
								for (int j = 0; j < refasGraph
										.getChildCount(mv1); j++) {
									mxCell mv2 = (mxCell) refasGraph
											.getChildAt(mv1, j);
									if (modelInstance.getSyntaxModel()
											.elementsValidation(name, i, j)
											&& (i != modelViewIndex || j != modelViewSubIndex)) {

										mxCell c2 = null;
										try {
											c2 = (mxCell) cell.clone();
										} catch (CloneNotSupportedException e) {
											ConsoleTextArea.addText(e
													.getStackTrace());
										}
										c2.setId(i + id + "-" + j);

										c2.setValue(new InstCell(c2,
												((InstCell) cell.getValue())
														.getInstElement(), true));

										model.add(mv2, c2, mv2.getChildCount());
										// getModel().setVisible(c2, false);
										mv2.setVisible(true);
										// getModel().setVisible(c2, true);
										mv2.setVisible(false);
										// Add a clone to other models
									}

								}
								correctParentCell.setVisible(false);
								correctParentCell.setVisible(true);

							} else {
								// if (valid[i] && i != modelViewIndex) {
								if (modelInstance.getSyntaxModel()
										.elementsValidation(name, i, -1)
										&& i != modelViewIndex) {

									mxCell c2 = null;
									try {
										c2 = (mxCell) cell.clone();
									} catch (CloneNotSupportedException e) {
										ConsoleTextArea.addText(e
												.getStackTrace());
									}
									c2.setId(i + id);
									c2.setValue(new InstCell(c2,
											((InstCell) cell.getValue())
													.getInstElement(), true));
									// getModel().setVisible(c2, false);
									// getModel().setVisible(c2, true);
									model.add(mv1, c2, mv1.getChildCount());
									mv1.setVisible(true);
									mv1.setVisible(false);
									// Add a clone to other
									correctParentCell.setVisible(false);
									correctParentCell.setVisible(true);
								}
							}
						}
					}
					this.refresh();
					return true;
				} else {
					// If the element is not allowed in the View, is removed
					mxGraphModel model = (mxGraphModel) getModel();
					model.getCells().remove(cell.getId());
					if (parent.getIndex(cell) == index)
						parent.remove(index);
					return false;
				}
			} else
				cell.setGeometry(new mxGeometry(50, 50, 100, 40));
		}
		return true;
	}

	protected void removingRefaElements(mxCell cell) {
		InstElement instElement = ((InstCell) cell.getValue()).getInstElement();
		if (instElement != null) {
			for (int ii = 0; ii < cell.getEdgeCount(); ii++) {
				mxCell edge = (mxCell) cell.getEdgeAt(ii);
				InstElement instElement2 = ((InstCell) edge.getValue())
						.getInstElement();
				modelInstance.removeElement(instElement2);
			}
			modelInstance.removeElement(instElement);
		}
	}

	protected void removingClones(mxCell cell) {
		mxIGraphModel refasGraph = getModel();

		Object o = refasGraph.getRoot(); // Main Root
		Object o1 = refasGraph.getChildAt(o, 0); // Null Root
		for (int mvInd = 0; mvInd < refasGraph.getChildCount(o1); mvInd++) {
			mxCell mv0 = (mxCell) refasGraph.getChildAt(o1, mvInd);
			// Root model view mvInd
			if (refasGraph.getChildCount(mv0) > 0) {
				for (int i = 0; i < refasGraph.getChildCount(mv0); i++) {
					mxCell mv1 = (mxCell) refasGraph.getChildAt(mv0, i);
					for (int j = 0; j < refasGraph.getChildCount(mv1); j++) {
						mxCell mv2 = (mxCell) refasGraph.getChildAt(mv1, j);
						if (cell.getValue().equals(mv2.getValue())) {
							mxCell child = (mxCell) mv1.getChildAt(j);
							for (int ii = 0; ii < child.getEdgeCount(); ii++) {
								mxCell edge = (mxCell) child.getEdgeAt(ii);
								removingRefaElements(edge);
								mv1.remove(edge);
							}
							removingRefaElements(child);
							mv1.remove(mv2);
						}
					}
					mxCell mv2 = (mxCell) refasGraph.getChildAt(mv0, i);
					if (cell.getValue().equals(mv2.getValue())) {
						mxCell child = (mxCell) mv0.getChildAt(i);
						for (int ii = 0; ii < child.getEdgeCount(); ii++) {
							mxCell edge = (mxCell) child.getEdgeAt(ii);
							removingRefaElements(edge);
							mv0.remove(edge);
						}
						removingRefaElements(child);
						mv0.remove(mv2);
					}

				}
			}
		}
	}

	public void setModelInstance(InstanceModel pl) {
		modelInstance = pl;
		defineInitialGraph();
		try {
			mxGraphLayout layout = new mxOrganicLayout(this);
			layout.execute(getDefaultParent()); // todo change root?
		} catch (Exception e) {
			// System.out.println("RefasGraph: Auto Layout failed");
		}
	}

	public void runOrganicLayout() {
		try {
			mxGraphLayout layout = new mxOrganicLayout(this);
			layout.execute(getDefaultParent()); // todo change root?
		} catch (Exception e) {
			System.out.println("RefasGraph: Auto Layout failed");
		}
	}

	public InstanceModel getModelInstance() {
		/*
		 * if (refasModel == null) { refasModel = new
		 * Refas(PerspectiveType.modeling); return refasModel; }
		 */
		return modelInstance;
	}

	public mxCell getCellById(String id) {
		return (mxCell) ((mxGraphModel) getModel()).getCell(id);
	}

	@Override
	public String validateEdge(Object edge, Object source, Object target) {
		return super.validateEdge(edge, source, target);
	}

	@Override
	public String validateCell(Object objCell, Hashtable<Object, Object> context) {
		if (objCell instanceof mxCell) {
			// mxCell cell = (mxCell) objCell;

			/*
			 * if (cell.getValue() instanceof GroupGConstraint) {
			 * GroupGConstraint gc = (GroupGConstraint) cell.getValue(); if
			 * (gc.getParent() == null) return "Needs Parent"; }
			 */

			/*
			 * if ( // TODO evaluate new constraints cell.getValue() instanceof
			 * GroupGConstraint || cell.getValue() instanceof
			 * MandatoryConstraint || cell.getValue() instanceof
			 * OptionalConstraint || cell.getValue() instanceof
			 * RequiresConstraint || cell.getValue() instanceof
			 * ExcludesConstraint || cell.getValue() instanceof
			 * GenericConstraint) {
			 * 
			 * } else {
			 * 
			 * if (cell.getValue() instanceof Constraint) return
			 * "Not Implemented Yet"; }
			 */
		}
		return super.validateCell(objCell, context);
	}

	@Override
	public String convertValueToString(Object obj) {
		mxCell cell = (mxCell) obj;
		// VariabilityPoint

		if (cell.getValue() instanceof InstCell) {
			InstCell instCell = (InstCell) cell.getValue();
			InstElement element = instCell.getInstElement();
			List<InstElement> syntaxParents = modelInstance
					.getParentSMMSyntaxElement(element);
			if (element != null) {
				element.createInstAttributes(syntaxParents);
				return element.getText(syntaxParents);
			}
		}
		return super.convertValueToString(obj);
	}

	@Override
	public void cellLabelChanged(Object cell, Object value, boolean autoSize) {
		super.cellLabelChanged(cell, value, autoSize);
	}

	public void refreshVariable(InstCell instCell) {

		mxCell cell = null;
		InstElement instElement = instCell.getInstElement();
		if (modelViewIndex == -1)
			cell = getCellById(instElement.getIdentifier());
		else if (modelViewSubIndex == -1)
			cell = getCellById(modelViewIndex + instElement.getIdentifier());
		else
			cell = getCellById(modelViewIndex + instElement.getIdentifier()
					+ "-" + modelViewSubIndex);
		if (cell != null) {
			getModel().setValue(cell, instCell);
			this.fireEvent(new mxEventObject(PL_EVT_NODE_CHANGE, "cell", cell,
					"element", instCell));
		}
	}
}
