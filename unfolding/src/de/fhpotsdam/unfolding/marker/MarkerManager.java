package de.fhpotsdam.unfolding.marker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import processing.core.PGraphics;
import de.fhpotsdam.unfolding.Map;

public class MarkerManager {

	public static Logger log = Logger.getLogger(MarkerManager.class);

	Map map;
	List<Marker> markers;

	public MarkerManager(Map map, Marker... markers) {
		this(map, Arrays.asList(markers));
	}

	public MarkerManager(Map map, List<Marker> markers) {
		this.map = map;
		this.markers = markers;
	}

	public void setMarkers(List<Marker> markers) {
		this.markers = markers;
	}

	public void addMarker(Marker marker) {
		if (markers == null) {
			markers = new ArrayList<Marker>();
		}
		markers.add(marker);
	}

	public List<Marker> getMarkers() {
		return markers;
	}

	public Marker isInside(float checkX, float checkY) {
		Marker foundMarker = null;
		for (Marker marker : markers) {
			if (marker.isInside(map, checkX, checkY)) {
				foundMarker = marker;
				break;
			}
		}
		return foundMarker;
	}

	public void draw() {
		for (Marker marker : markers) {
			PGraphics pg = map.mapDisplay.getPG();
			float[] xy = map.mapDisplay.getInnerObjectFromLocation(marker.getLocation());
			float x = xy[0];
			float y = xy[1];
			marker.draw(pg, x, y);
		}
	}

	public void drawOuter() {
		for (Marker marker : markers) {
			PGraphics pg = map.mapDisplay.getOuterPG();
			float[] xy = map.mapDisplay.getObjectFromLocation(marker.getLocation());
			float x = xy[0];
			float y = xy[1];
			marker.drawOuter(pg, x, y);
		}
	}

}
