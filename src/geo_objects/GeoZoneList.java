package geo_objects;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import map.MapLayer;

/**
 * @author Ahcene Bounceur
 * @version 1.0
 */

public class GeoZoneList extends Thread {

	public LinkedList<GeoZone> geoZoneList = null;		
	
	public GeoZoneList() {
		geoZoneList = new LinkedList<GeoZone>();
	}
	
	public GeoZoneList(LinkedList<GeoZone> geoZoneList) {
		this.geoZoneList = geoZoneList ; 
	}
	
	public void add(String str) {
		GeoZone geoZone = new GeoZone(str, this);
		MapLayer.getMapViewer().addMouseListener(geoZone);
		MapLayer.getMapViewer().addKeyListener(geoZone);
		geoZoneList.add(geoZone);
	}
	
	public void add(String [] str) {		
		for(int i=0; i<str.length; i++) {
			geoZoneList.add(new GeoZone(str, this));
		}
	}
	
	public void add(GeoZone geoZone) {
		MapLayer.getMapViewer().addMouseListener(geoZone);
		MapLayer.getMapViewer().addKeyListener(geoZone);
		geoZoneList.add(geoZone);		
	}
	
	public void draw(Graphics g) {
		for(GeoZone geoZone : geoZoneList) {
			geoZone.draw(g);
		}
	}
	
	public void init() {
		System.out.println("INIT GZ");
		for(GeoZone geoZone : geoZoneList) {
			delete(geoZone);
		}
		geoZoneList = new LinkedList<GeoZone>();
	}
	
	public void delete(GeoZone geoZone) {	
		MapLayer.getMapViewer().removeMouseListener(geoZone);
		MapLayer.getMapViewer().removeKeyListener(geoZone);
		geoZone = null;
		geoZoneList.remove(geoZone);
	}
	
	public boolean intersect(Polygon p) {
		for (GeoZone geoZone : geoZoneList){
			if (geoZone.intersect(p))
				return true;
		}
		return false;
	}

	public LinkedList<GeoZone> getGeoZoneList() {
		return geoZoneList;
	}
	
	public boolean contains(Point2D p) {
		for(GeoZone geoZone : geoZoneList) {
			if(geoZone.contains(p)) return true;
		}
		return false;
	}
	
	public boolean contains(double px, double py) {
		for(GeoZone geoZone : geoZoneList) {
			if(geoZone.contains(px, py)) return true;
		}
		return false;
	}
	
	public void reduce(double xref, double yref, double zm) {
		for(GeoZone geo : geoZoneList) {
			geo.reduce(xref, yref, zm);
		}
	}
	
	public boolean isEmpty() {
		return (geoZoneList.size()==0);
	}
}