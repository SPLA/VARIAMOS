package com.variamos.gui.pl.editor.shapes;

import java.awt.Polygon;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.shape.mxIMarker;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;

public class MandatoryMarker implements mxIMarker{

	@Override
	public mxPoint paintMarker(mxGraphics2DCanvas canvas, mxCellState state,
			String type, mxPoint pe, double nx, double ny, double size,
			boolean source) {
		Polygon poly = new Polygon();
        poly.addPoint((int) Math.round(pe.getX()), (int) Math.round(pe.getY()));
        poly.addPoint((int) Math.round(pe.getX() - nx - ny / 2), (int) Math.round(pe.getY() - ny + nx / 2));
        poly.addPoint((int) Math.round(pe.getX() + ny / 2 - nx), (int) Math.round(pe.getY() - ny - nx / 2));
        canvas.getGraphics().draw(poly);
        return new mxPoint(-nx, -ny);
	}

}
