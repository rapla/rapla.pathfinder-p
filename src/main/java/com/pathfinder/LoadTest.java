/**
 * 
 */
package com.pathfinder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.server.VaadinRequest;

/**
 * @author tim
 * 
 */
public class LoadTest {

	private static final Logger LOGGER = LogManager.getLogger(LoadTest.class);

	private VaadinRequest request;

	public LoadTest(VaadinRequest request) {
		this.request = request;
	}

	public void startLoadTest() {

		logPerformance();

		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (int zaehler = 0; zaehler < 3000; zaehler++) {

					LOGGER.info("Start new UI (" + zaehler + ")");
					logPerformance();

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					new Thread(new Runnable() {
						@Override
						public void run() {
							PathfinderUI ui = new PathfinderUI();
							ui.init(request);
						}
					}).start();
				}

			}
		}).start();
	}

	private static final long MEGABYTE = 1024L * 1024L;
	private Runtime runtime = Runtime.getRuntime();

	private long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}

	public void logPerformance() {

		long memory = runtime.totalMemory() - runtime.freeMemory();
		LOGGER.info("Used memory is bytes: " + memory);
		LOGGER.info("Used memory is megabytes: " + bytesToMegabytes(memory));
	}
}
