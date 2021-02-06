package com.narabel.batch.batch.simpleDataList.listener;

import java.text.MessageFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class ChunkCountListener implements ChunkListener {
	private static final Logger log = LogManager.getLogger(ChunkCountListener.class);

	private MessageFormat fmt = new MessageFormat("{0} items procesados");

	private int loggingInterval = 100;

	@Override
	public void beforeChunk(ChunkContext context) {
	}

	@Override
	public void afterChunk(ChunkContext context) {

		int count = context.getStepContext().getStepExecution().getReadCount();

		// If the number of records processed so far is a multiple of the logging
		// interval then output a log message.
		if (count > 0 && count % loggingInterval == 0) {
			log.info(fmt.format(new Object[] { new Integer(count) }));
		}
	}

	@Override
	public void afterChunkError(ChunkContext context) {
	}

	public void setItemName(String itemName) {
		this.fmt = new MessageFormat("{0} " + itemName + " procesados");
	}

	public void setLoggingInterval(int loggingInterval) {
		this.loggingInterval = loggingInterval;
	}
}
