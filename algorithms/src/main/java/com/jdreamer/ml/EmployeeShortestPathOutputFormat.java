package com.jdreamer.ml;

import java.io.IOException;

import org.apache.giraph.graph.Vertex;
import org.apache.giraph.io.formats.TextVertexOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class EmployeeShortestPathOutputFormat extends
		TextVertexOutputFormat<Text, IntWritable, NullWritable> {

	@Override
	public TextVertexWriter createVertexWriter(TaskAttemptContext arg0)
			throws IOException, InterruptedException {
		return new EmployeeRDFVertexWriter();
	}

	private class EmployeeRDFVertexWriter extends
			EmployeeShortestPathOutputFormat.TextVertexWriter {
		private Text valOut = new Text();

		@Override
		public void writeVertex(
				Vertex<Text, IntWritable, NullWritable, ?> vertex)
				throws IOException, InterruptedException {
			valOut.set(vertex.getValue().toString());
			if (vertex.getValue().get() == Integer.MAX_VALUE) {
				valOut.set("No path");
			}

			getRecordWriter().write(vertex.getId(), valOut);
		}
	}

}
