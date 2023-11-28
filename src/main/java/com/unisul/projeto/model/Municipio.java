package com.unisul.projeto.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Municipio<T> implements Comparable<Municipio<T>> {

	private final T nome;
	private Double distancia = Double.MAX_VALUE;
	private List<Municipio<T>> menorDistancia = new LinkedList<>();
	private Map<Municipio<T>, Double> cidadesAdjacentes = new HashMap<>();
	private Map<Municipio<T>, Integer> pedagios = new HashMap<>();
	private String tipoVeiculo;
	private double custoRota;
	private int qtdPedagios;

	public Municipio(T nome) {
		this.nome = nome;
	}

	public T getNome() {
		return nome;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public List<Municipio<T>> getMenorDistancia() {
		return menorDistancia;
	}

	public void setMenorDistancia(List<Municipio<T>> menorDistancia) {
		this.menorDistancia = menorDistancia;
	}

	public Map<Municipio<T>, Double> getCidadesAdjacentes() {
		return cidadesAdjacentes;
	}

	public void setCidadesAdjacentes(Map<Municipio<T>, Double> cidadesAdjacentes) {
		this.cidadesAdjacentes = cidadesAdjacentes;
	}

	public Map<Municipio<T>, Integer> getPedagios() {
		return pedagios;
	}

	public void setPedagios(Map<Municipio<T>, Integer> pedagios) {
		this.pedagios = pedagios;
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public double getCustoRota() {
		return custoRota;
	}

	public void setCustoRota(double custoRota) {
		this.custoRota = custoRota;
	}

	public void addCidadesAdjacentes(Municipio<T> cidade, double peso, Integer pedagio) {
		cidadesAdjacentes.put(cidade, peso);
		pedagios.put(cidade, pedagio);
	}

	public int getQtdPedagios() {
		return qtdPedagios;
	}

	public void setQtdPedagios(int qtdPedagios) {
		this.qtdPedagios = qtdPedagios;
	}

	@Override
	public int compareTo(Municipio<T> node) {
		return Double.compare(this.distancia, node.getDistancia());
	}
}
