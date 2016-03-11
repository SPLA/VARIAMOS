package com.variamos.perspsupport.expressionsupport;

import com.variamos.perspsupport.opers.OpersAbstractElement;
import com.variamos.perspsupport.opers.OpersRelType;

/**
 * A class to support the groups of operations (displayed as menus on the
 * modeling or simulation perspectives). Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-12-20
 */
public class OpersOperationGroup extends OpersAbstractElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6532989148655801713L;

	private int position;
	private int type;

	public static final String VAR_MENUTYPE_IDEN = "menuType",
			VAR_MENUTYPE_NAME = "Oper Group Type",
			VAR_MENUTYPE_CLASS = OpersRelType.class.getCanonicalName(),
			VAR_NAME_IDEN = "name", VAR_NAME_NAME = "Oper Group Name",
			VAR_SHORTCUT_IDEN = "shorcut",
			VAR_SHORTCUT_NAME = "Oper Group Shortcut";

	public OpersOperationGroup(int position, String identifier, int type) {
		super(identifier);
		this.position = position;
		this.type = type;
	}

	public OpersOperationGroup() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
