package com.jdreamer.ml;

import java.io.IOException;

import org.apache.giraph.conf.StrConfOption;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

public class EmployeeShortestPathVertex extends
		Vertex<Text, IntWritable, NullWritable, IntWritable> {
	public static final StrConfOption SOURCE_ID = new StrConfOption(
			"emp_source_id", "Shanae Dailey");

	private final IntWritable DEFAULT = new IntWritable(Integer.MAX_VALUE);
	private final IntWritable msg = new IntWritable(1);

	@Override
	public void compute(Iterable<IntWritable> messages) throws IOException {
		if (getSuperstep() == 0) {
			setValue(DEFAULT);

			if (isSource()) {

				for (Edge<Text, NullWritable> e : getEdges()) {
					sendMessage(e.getTargetVertexId(), msg);
				}
			}
		}

		int min = getValue().get();
		for (IntWritable msg : messages) {
			min = Math.min(msg.get(), min);
		}

		if (min < getValue().get()) {
			setValue(new IntWritable(min));
			msg.set(min + 1);

			sendMessageToAllEdges(msg);
		}

		voteToHalt();
	}

	private boolean isSource() {
		return getId().toString().equals(SOURCE_ID.get(getConf()));
	}
}
