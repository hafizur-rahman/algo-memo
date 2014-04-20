package com.jdreamer.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AlbumShuffle {

	private List<Song> shuffleByAlbum(List<Song> songs) {
		final Map<String, Double> albums = new HashMap<String, Double>();

		Random r = new Random(System.currentTimeMillis());
		for (Song s : songs) {
			if (!albums.containsKey(s.album)) {
				albums.put(s.album, r.nextDouble());
			}

			s.setRandomRank(r.nextDouble());
		}

		Collections.sort(songs, new Comparator<Song>() {

			@Override
			public int compare(Song s1, Song s2) {
				int c1 = albums.get(s1.album).compareTo(albums.get(s2.album));
				return c1 == 0 ? Double.compare(s1.randomRank, s2.randomRank)
						: c1;
			}
		});

		return songs;
	}

	public static void main(String[] args) {
		List<Song> songs = new ArrayList<Song>();
		songs.add(new Song("A1", "S1", "T1", 1));
		songs.add(new Song("A1", "S1", "T2", 2));
		songs.add(new Song("A1", "S2", "T3", 3));
		songs.add(new Song("A2", "S1", "T4", 4));
		songs.add(new Song("A3", "S2", "T5", 5));
		songs.add(new Song("A3", "S1", "T6", 6));

		List<Song> shuffled = new AlbumShuffle().shuffleByAlbum(songs);
		for (Song s : shuffled) {
			System.out.println(s.album + " " + s.title + " " + s.track);
		}
	}
}

class Song {
	String album;
	String artist;
	String title;
	int track;

	public Song(String album, String artist, String title, int track) {
		this.album = album;
		this.artist = artist;
		this.title = title;
		this.track = track;
	}

	double randomRank;
	public void setRandomRank(double randomRank) {
		this.randomRank = randomRank;
	}
}
