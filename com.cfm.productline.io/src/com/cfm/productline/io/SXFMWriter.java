package com.cfm.productline.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cfm.productline.Constraint;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.constraints.ExcludesConstraint;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.cfm.productline.constraints.MandatoryConstraint;
import com.cfm.productline.constraints.OptionalConstraint;
import com.cfm.productline.constraints.RequiresConstraint;


public class SXFMWriter {
	public void writeSXFM(ProductLine pl, File outFile) throws IOException{
		
		if( pl.getRoots().size() > 1 ){
			//TODO: Is not a feature tree !! What should be done?
		}

		FileWriter fos = new FileWriter(outFile);
		fos.write(getSXFMContent(pl));
		fos.close();
		
	}
	
	public String getSXFMContent(ProductLine pl){
		StringBuffer buf = new StringBuffer();
		buf.append("<feature_model name=\"");
		buf.append(pl.getName());
		buf.append("\">\n");
		buf.append("<meta>\n");
		
		buf.append("<data name=\"description\">");
		//strBuf.append("Model Description");
		buf.append("</data>\n");
		
		buf.append("<data name=\"creator\">");
		//strBuf.append("Model's creator");
		buf.append("</data>\n");
		
		buf.append("<data name=\"email\">");
		//strBuf.append("Model creator's email");
		buf.append("</data>\n");
		
		buf.append("<data name=\"date\">");
		//strBuf.append("Model creation date");
		buf.append("</data>\n");
		
		buf.append("<data name=\"department\">");
		//strBuf.append("Model creator's department");
		buf.append("</data>\n");
		
		buf.append("<data name=\"organization\">");
		//strBuf.append("Model creator's organization");
		buf.append("</data>\n");
		
		buf.append("<data name=\"address\">");
		//strBuf.append("Model creator's address");
		buf.append("</data>\n");
		
		buf.append("<data name=\"phone\">");
		//strBuf.append("Model creator's phone");
		buf.append("</data>\n");
		
		buf.append("<data name=\"website\">");
		//strBuf.append("Model creator's website");
		buf.append("</data>\n");
		
		buf.append("<data name=\"reference\">");
		//strBuf.append("Model's related publication");
		buf.append("</data>\n");
		
		buf.append("</meta>\n");
		buf.append("<feature_tree>\n");
		printFeatureTree(pl, buf);
		buf.append("</feature_tree>\n");
		buf.append("<constraints>\n");
		printConstraints(pl, buf);
		buf.append("</constraints>\n");
		buf.append("</feature_model>");
		
		return buf.toString();
	}
	
	private void printConstraints(ProductLine pl, StringBuffer buf) {
		for(Constraint c : pl.getConstraints()){
			if( 
					( c instanceof OptionalConstraint ) | 
					( c instanceof MandatoryConstraint ) | 
					( c instanceof GroupConstraint ) 
			){
						continue; //Already covered
			}
			
			if( c instanceof ExcludesConstraint ){
				printExcludes( (ExcludesConstraint)c, buf);
				continue;
			}
			
			if( c instanceof RequiresConstraint ){
				printRequires( (RequiresConstraint)c, buf );
				continue;
			}
			
			//Other constraints are just printed
			printConstraint(c, buf);
		}
	}
	
	private void printConstraint(Constraint c, StringBuffer buf){
		
		if( c instanceof GenericConstraint ){
			fillBlank(buf, 1);
			buf.append(c.getIdentifier()).append(": ").append(((GenericConstraint) c).getText()).append("\n");
		}
	}
	
	private void printRequires(RequiresConstraint c, StringBuffer buf) {
		fillBlank(buf, 1);
		buf.append(c.getIdentifier()).append(": ~").append(c.getFeature1Id())
			.append(" or ").append(c.getFeature2Id()).append("\n");
	}


	private void printExcludes(ExcludesConstraint c, StringBuffer buf) {
		fillBlank(buf, 1);
		buf.append(c.getIdentifier()).append(": ~").append(c.getFeature1Id())
			.append(" or ~").append(c.getFeature2Id()).append("\n");
	}

	private void printFeatureTree(ProductLine pl, StringBuffer str){
		Set<String> roots = pl.getRoots();
		
		if( roots.size() > 1 )
			return;
		
		Object[] rootsArray = roots.toArray();
		
		VariabilityElement root = pl.getVariabilityElement((String)rootsArray[0]);
		
		Set<VariabilityElement> visited = new HashSet<>();
		
		printFeatureBranch(pl, str, "r", root, 0, visited);
	}
	


	private void printFeatureBranch(ProductLine pl, StringBuffer str, String type, VariabilityElement varPoint, int depth, Set<VariabilityElement> visited){
		if( visited.contains(varPoint))
			return;
		
		visited.add(varPoint);
		
		fillBlank(str, depth);
		printNodeLine(varPoint, type, str, depth);
		
		//Descendence
		for( Constraint c : pl.getConstraintsFor(varPoint) ){
			if( c instanceof OptionalConstraint ){
				OptionalConstraint opC = (OptionalConstraint)c;
				if( opC.getFeature1Id().equals(varPoint.getIdentifier()) ){
					VariabilityElement p = pl.getVariabilityElement(opC.getFeature2Id());
					printFeatureBranch(pl, str, "o", p, depth + 1, visited);
//					printOptionalLine(vp, str, depth + 1);
//					printFeatureBranch(pl, str, vp, depth + 1, visited);
				}
			}
			
			if( c instanceof MandatoryConstraint ){
				MandatoryConstraint opC = (MandatoryConstraint)c;
				if( opC.getFeature1Id().equals(varPoint.getIdentifier()) ){
					VariabilityElement p = pl.getVariabilityElement(opC.getFeature2Id());
//					printMandatoryLine(vp, str, depth + 1);
//					printFeatureBranch(pl, str, vp, depth + 1, visited);
					printFeatureBranch(pl, str, "m", p, depth + 1, visited);
				}
			}
			
			if( c instanceof GroupConstraint ){
				GroupConstraint gc = (GroupConstraint)c;
				if( gc.getParent().equals(varPoint.getIdentifier()) ){
					printGroupLine(gc, str, depth + 1);
					List<String> related = gc.getRelatedIds();
					for(int i = 1; i < related.size(); i++){
						VariabilityElement p = pl.getVariabilityElement(related.get(i));
//						printFeatureBranch(pl, str, vp, depth + 1, visited);
						printFeatureBranch(pl, str, "", p, depth + 2, visited);
					}
				}
			}
		}
	}
	
//	private void printOptionalLine(VariabilityElement p, StringBuffer str, int depth){
//		fillBlank(str, depth);
//		str.append(":o ").append(p.getPropertyAs(Property.NAME)).append(" (").append(p.getIdentifier()).append(")\n");
//	}
	
	private void printNodeLine(VariabilityElement p, String type, StringBuffer str, int depth){
		str.append(":").append(type);
//		if( depth == 0 )
//			str.append("r");
		str.append(" ").append(p.getName())
		 .append(" (").append(p.getIdentifier()).append(")\n");
	}
	private void printGroupLine(GroupConstraint gc, StringBuffer str, int depth) {
		String upper = ( gc.getUpperLimit() == GroupConstraint.INFINITE ) ? "*" : gc.getUpperLimit() + "";
		fillBlank(str, depth);
		str.append(":g [").append(gc.getLowerLimit()).append(",").append(upper).append("]\n");
	}

//	private void printMandatoryLine(VariabilityElement p, StringBuffer str, int depth) {
//		printNodeLine(p, "m", str, depth);
////		fillBlank(str, depth);
////		str.append(":m ").append(p.getPropertyAs(Property.NAME)).append(" (").append(p.getIdentifier()).append(")\n");
//	}
	private void fillBlank(StringBuffer str, int depth){
		for(int i = 0; i < depth; i++)
			str.append("\t");
	}
}
