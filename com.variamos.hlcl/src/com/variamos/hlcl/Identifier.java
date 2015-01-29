package com.variamos.hlcl;

public class Identifier implements BooleanExpression, NumericExpression, Comparable<Identifier>{
	
	protected String id;
	protected Domain domain;
	
	protected Identifier() {
		super();
		domain = new RangeDomain();
	}

	protected Identifier(String id) {
		this();
		this.id = firstUpperCase(id);
	}


	public String getId() {
		return id;
	}

//	public String getName() {
//		return name;
//	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identifier other = (Identifier) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Identifier other) {
		return id.compareTo(other.getId());
	}
	public String toString() {
		return "Identifier [id=" + id + "]";
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
	public static String firstUpperCase(String str){
		if( !Character.isUpperCase(str.charAt(0)) ){
			return Character.toUpperCase(str.charAt(0)) + str.substring(1);
		}
		return str;
	}
}
