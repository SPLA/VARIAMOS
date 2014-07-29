package com.cfm.productline.editor.shapes;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.shape.mxIMarker;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;

public class OptionalMarker implements mxIMarker{
	
	private static final int RADIUS = 3;
	
	@Override
	public mxPoint paintMarker(mxGraphics2DCanvas canvas, mxCellState state,
			String type, mxPoint pe, double nx, double ny, double size,
			boolean source) {
//		Polygon poly = new Polygon();
//        poly.addPoint((int) Math.round(pe.getX()), (int) Math.round(pe.getY()));
//        poly.addPoint((int) Math.round(pe.getX() - nx - ny / 2), (int) Math.round(pe.getY() - ny + nx / 2));
//        poly.addPoint((int) Math.round(pe.getX() + ny / 2 - nx), (int) Math.round(pe.getY() - ny - nx / 2));
//        canvas.getGraphics().draw(poly);
//		Color prev = canvas.getGraphics().getColor();
//		canvas.getGraphics().setColor(Color.white);
//		canvas.getGraphics().fillOval( (int)(pe.getX() -nx - RADIUS), (int)(pe.getY() -ny - RADIUS), RADIUS * 2 , RADIUS * 2);
//		canvas.getGraphics().setColor(prev);
		System.out.println("RENDERING !!!");
		canvas.getGraphics().drawOval( (int)(pe.getX() -nx - RADIUS), (int)(pe.getY() -ny - RADIUS), RADIUS * 2 , RADIUS * 2);
        return new mxPoint(-nx, -ny);
	}

}
