package com.jdreamer.ml;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.giraph.edge.Edge;
import org.apache.giraph.edge.EdgeFactory;
import org.apache.giraph.io.formats.TextVertexInputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import com.google.common.collect.Lists;

public class EmployeeRDFTextInputFormat extends
		TextVertexInputFormat<Text, IntWritable, NullWritable> {

	@Override
	public TextVertexReader createVertexReader(InputSplit split,
			TaskAttemptContext context) throws IOException {
		return new EmployeeRDFVertexReader();
	}

	public class EmployeeRDFVertexReader extends
			TextVertexReaderFromEachLineProcessed<String[]> {
		private final Pattern TAB = Pattern.compile("[\\t]");
		private final Pattern COLON = Pattern.compile("[:]");
		private final Pattern COMMA = Pattern.compile("[,]");

		/**
		 * Cached vertex id for the current line
		 */
		private Text id;

		@Override
		protected String[] preprocessLine(Text line) throws IOException {
			String[] tokens = TAB.split(line.toString());
			id = new Text(tokens[0]);

			return tokens;
		}

		@Override
		protected Text getId(String[] tokens) throws IOException {
			return id;
		}

		@Override
		protected IntWritable getValue(String[] tokens) throws IOException {
			return new IntWritable(0);
		}

		@Override
		protected Iterable<Edge<Text, NullWritable>> getEdges(String[] tokens)
				throws IOException {
			final String subtoken = COLON.split(tokens[2])[1];
			final String[] subs = COMMA.split(subtoken);

			final List<Edge<Text, NullWritable>> edges = Lists
					.newArrayListWithCapacity(subs.length);
			for (String sub : subs) {
				if (!"none".equals(sub)) {
					edges.add(EdgeFactory.create(new Text(sub)));
				}
			}

			return edges;
		}
	}
}
