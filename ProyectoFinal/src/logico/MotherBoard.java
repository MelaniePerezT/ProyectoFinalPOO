package logico;

import java.util.ArrayList;

public class MotherBoard extends Producto {
	
	private String modelo;
	private String tipoSocket;
	private String tipoRam;
	private ArrayList <String> listaDiscoDuroAceptados;
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getTipoSocket() {
		return tipoSocket;
	}
	public void setTipoSocket(String tipoSocket) {
		this.tipoSocket = tipoSocket;
	}
	public String getTipoRam() {
		return tipoRam;
	}
	public void setTipoRam(String tipoRam) {
		this.tipoRam = tipoRam;
	}
	public ArrayList<String> getListaDiscoDuroAceptados() {
		return listaDiscoDuroAceptados;
	}
	public void setListaDiscoDuroAceptados(ArrayList<String> listaDiscoDuroAceptados) {
		this.listaDiscoDuroAceptados = listaDiscoDuroAceptados;
	}
	public MotherBoard(String numSerie, int cantDisponible, String marca, float precio, String modelo,
			String tipoSocket, String tipoRam, ArrayList<String> listaDiscoDuroAceptados) {
		super(numSerie, cantDisponible, marca, precio);
		this.modelo = modelo;
		this.tipoSocket = tipoSocket;
		this.tipoRam = tipoRam;
		this.listaDiscoDuroAceptados = listaDiscoDuroAceptados;
	}

}
