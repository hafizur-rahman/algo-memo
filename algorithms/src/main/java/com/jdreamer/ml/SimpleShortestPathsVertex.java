package com.jdreamer.ml;

import java.io.IOException;

import org.apache.giraph.conf.LongConfOption;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;

public class SimpleShortestPathsVertex extends
		Vertex<LongWritable, DoubleWritable, FloatWritable, DoubleWritable> {
	public static final LongConfOption SOURCE_ID = new LongConfOption(
			"SimpleShortestPathsVertex.sourceId", 1);

	@Override
	public void compute(Iterable<DoubleWritable> messages) throws IOException {
		if (getSuperstep() == 0) {
			setValue(new DoubleWritable(Double.MAX_VALUE));
		}

		double minDist = isSource() ? 0d : Double.MAX_VALUE;
		for (DoubleWritable message : messages) {
			minDist = Math.min(minDist, message.get());
		}

		if (minDist < getValue().get()) {
			setValue(new DoubleWritable(minDist));

			for (Edge<LongWritable, FloatWritable> edge : getEdges()) {
				double dist = minDist + edge.getValue().get();
				sendMessage(edge.getTargetVertexId(), new DoubleWritable(dist));
			}
		}

		voteToHalt();
	}

	private boolean isSource() {
		return getId().get() == SOURCE_ID.get(getConf());
	}
}
