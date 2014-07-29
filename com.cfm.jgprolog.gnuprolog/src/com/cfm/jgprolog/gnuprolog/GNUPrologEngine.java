package com.cfm.jgprolog.gnuprolog;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

import com.cfm.jgprolog.core.CompoundTerm;
import com.cfm.jgprolog.core.PrologEngine;
import com.cfm.jgprolog.core.PrologException;
import com.cfm.jgprolog.core.QueryResult;
import com.jogamp.common.nio.PointerBuffer;

class GNUPrologEngine implements PrologEngine {

	@Override
	public void startProlog() {
		LongBuffer buf = ByteBuffer.allocateDirect(8).asLongBuffer();
		buf.put(0);
		buf.rewind();
		
		PointerBuffer argv = PointerBuffer.allocateDirect(1);
		argv.referenceBuffer(buf);
		argv.rewind();
		
		JGProlog.Pl_Start_Prolog(1, argv);
	}

	@Override
	public void stopProlog() {
		JGProlog.Pl_Stop_Prolog();
	}

	@Override
	public void resetProlog() {
		JGProlog.Pl_Reset_Prolog();
	}

	@Override
	public void consult(String absFilePath) throws PrologException {
		GNUPrologCompound cmp = new GNUPrologCompound("consult", new GNUPrologAtom(absFilePath));
		
		GNUPrologQueryResult res = new GNUPrologQueryResult(cmp, false);
		res.endQuery(JGProlog.PL_CUT);
	}

	@Override
	public QueryResult runQuery(CompoundTerm query) throws PrologException {
		return new GNUPrologQueryResult(query);
	}
}
