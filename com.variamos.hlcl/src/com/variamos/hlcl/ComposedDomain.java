package com.variamos.hlcl;

import java.util.ArrayList;
import java.util.List;

public class ComposedDomain implements Domain {
	private List<Domain> domains;

	public ComposedDomain() {
		domains = new ArrayList<>();
	}

	public List<Domain> getDomains() {
		return domains;
	}

	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}

	@Override
	public int size() {
		int size = 0;

		for (Domain d : domains)
			size += d.size();

		return size;
	}

	@Override
	public String getStringRepresentation() {
		StringBuffer str = new StringBuffer();

		for (int i = 0; i < domains.size(); i++) {
			str.append(domains.get(i).getStringRepresentation());
			if (i < domains.size() - 1)
				str.append(", ");
		}

		return str.toString();
	}

	@Override
	public List<Integer> getPossibleValues() {
		List<Integer> list = new ArrayList<>();

		for (Domain d : domains)
			for (Integer i : d.getPossibleValues())
				if (!list.contains(i))
					list.add(i);

		return list;
	}

	@Override
	public List<String> getPossibleStringValues() {
		List<String> list = new ArrayList<>();

		for (Domain d : domains)
			for (String i : d.getPossibleStringValues())
				if (!list.contains(i))
					list.add(i);

		return list;
	}
}
