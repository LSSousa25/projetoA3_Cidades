package com.unisul.projeto.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import com.unisul.projeto.model.Municipio;

public class Grafo<T> {

	public void calcularCaminhoMaisCurto(Municipio<T> origem) {
		origem.setDistancia((double) 0);
		Set<Municipio<T>> settledcidade = new HashSet<>();
		Queue<Municipio<T>> unsettledcidade = new PriorityQueue<>(Collections.singleton(origem));

		while (!unsettledcidade.isEmpty()) {
			Municipio<T> cidadeAtual = unsettledcidade.poll();

			for (Map.Entry<Municipio<T>, Double> entry : cidadeAtual.getCidadesAdjacentes().entrySet()) {
				Municipio<T> cidadeAdjacente = entry.getKey();
				Double pesoDaRota = entry.getValue();

				if (!settledcidade.contains(cidadeAdjacente)) {
					avaliarCaminho(cidadeAdjacente, pesoDaRota, cidadeAtual);
					unsettledcidade.add(cidadeAdjacente);
				}
			}

			settledcidade.add(cidadeAtual);
		}
	}

	public void calcularMenorCustoAPartirDeOrigem(Municipio<T> origem, String tipoVeiculo) {
		origem.setDistancia((double) 0);
		Set<Municipio<T>> settledCidade = new HashSet<>();
		Queue<Municipio<T>> unsettledCidade = new PriorityQueue<>(Collections.singleton(origem));

		while (!unsettledCidade.isEmpty()) {
			Municipio<T> cidadeAtual = unsettledCidade.poll();

			for (Map.Entry<Municipio<T>, Double> entry : cidadeAtual.getCidadesAdjacentes().entrySet()) {
				Municipio<T> cidadeAdjacente = entry.getKey();
				Double pesoDaRota = entry.getValue();

				if (!settledCidade.contains(cidadeAdjacente)) {
					avaliarCustoCaminho(cidadeAdjacente, pesoDaRota, cidadeAtual, tipoVeiculo);
					unsettledCidade.add(cidadeAdjacente);
				}
			}

			settledCidade.add(cidadeAtual);
		}
	}

	private void avaliarCaminho(Municipio<T> cidadeAdjacente, Double pesoDaRota, Municipio<T> cidadeorigem) {
		Double newDistancia = cidadeorigem.getDistancia() + pesoDaRota;

		if (newDistancia < cidadeAdjacente.getDistancia()) {
			cidadeAdjacente.setDistancia(newDistancia);
			List<Municipio<T>> newCaminho = new ArrayList<>(cidadeorigem.getMenorDistancia());
			newCaminho.add(cidadeorigem);
			cidadeAdjacente.setMenorDistancia(newCaminho);
		}
	}

	private void avaliarCustoCaminho(Municipio<T> cidadeAdjacente, Double pesoDaRota, Municipio<T> cidadeAtual,
			String tipoVeiculo) {
		Double newDistancia = cidadeAtual.getDistancia() + pesoDaRota;

		// Adicione essas variáveis para o cálculo do custo da rota
		Double custoCombustivel = 0.0; // Custo do Combustível (por litro, por exemplo)

		// Chamada ao novo método para calcular consumo e quantidade de eixos
		VeiculoInfo veiculoInfo = calcularConsumoEQuantidadeEixos(tipoVeiculo);

		Double consumo = veiculoInfo.getConsumo();
		Integer qtdEixos = veiculoInfo.getQtdEixos();

		// Cálculo do custo da rota
		Double custoRota = (pesoDaRota / consumo * custoCombustivel);

		// Adiciona o custo dos pedágios, se houver mais de um
		if (cidadeAtual.getPedagios().containsKey(cidadeAdjacente)) {
			double pedagioValue = cidadeAtual.getPedagios().get(cidadeAdjacente);

			if (pedagioValue > 0) {
				int qtdPedagios = cidadeAtual.getQtdPedagios() + 1; // Incrementa a quantidade de pedágios
				cidadeAdjacente.setQtdPedagios(qtdPedagios);

				custoRota += pedagioValue * (qtdEixos * 1.50);
			}
		}

		if (newDistancia < cidadeAdjacente.getDistancia()) {
			cidadeAdjacente.setDistancia(newDistancia);
			cidadeAdjacente.setCustoRota(custoRota);
			List<Municipio<T>> newCaminho = new ArrayList<>(cidadeAtual.getMenorDistancia());
			newCaminho.add(cidadeAtual);
			cidadeAdjacente.setMenorDistancia(newCaminho);
		}
	}

	private VeiculoInfo calcularConsumoEQuantidadeEixos(String tipoVeiculo) {
		VeiculoInfo veiculoInfo = new VeiculoInfo();

		switch (tipoVeiculo.toUpperCase()) {
		case "MOTO":
			veiculoInfo.setConsumo(30.0);
			veiculoInfo.setQtdEixos(1);
			break;
		case "CARRO":
			veiculoInfo.setConsumo(14.0);
			veiculoInfo.setQtdEixos(2);
			break;
		case "CAMINHAO":
			veiculoInfo.setConsumo(2.5);
			veiculoInfo.setQtdEixos(4);
			break;
		case "ONIBUS":
			veiculoInfo.setConsumo(3.0);
			veiculoInfo.setQtdEixos(6);
			break;
		case "MICRO-ONIBUS":
			veiculoInfo.setConsumo(6.0);
			veiculoInfo.setQtdEixos(2);
			break;
		}

		return veiculoInfo;
	}

	private static class VeiculoInfo {
		private Double consumo;
		private Integer qtdEixos;

		public Double getConsumo() {
			return consumo;
		}

		public void setConsumo(Double consumo) {
			this.consumo = consumo;
		}

		public Integer getQtdEixos() {
			return qtdEixos;
		}

		public void setQtdEixos(Integer qtdEixos) {
			this.qtdEixos = qtdEixos;
		}
	}

	public void imprimirCaminho(List<Municipio<T>> cidade) {
		cidade.forEach(node -> {
			String path = node.getMenorDistancia().stream().map(Municipio::getNome).map(Objects::toString)
					.collect(Collectors.joining(" -> "));

			System.out.println((path.isBlank() ? "%s : %s".formatted(node.getNome(), node.getDistancia())
					: "%s -> %s : %s".formatted(path, node.getNome(), node.getDistancia())));
		});
	}

	public void imprimirCustoCaminho(List<Municipio<T>> cidade) {
		cidade.forEach(node -> {
			String path = node.getMenorDistancia().stream().map(Municipio::getNome).map(Objects::toString)
					.collect(Collectors.joining(" -> "));

			System.out.println((path.isBlank()
					? "%s : %s pedágios: %d".formatted(node.getNome(), node.getCustoRota(), node.getQtdPedagios())
					: "%s -> %s : %s pedágios: %d".formatted(path, node.getNome(), node.getCustoRota(),
							node.getQtdPedagios())));
		});
	}

	public String obterCaminhoComoString(List<Municipio<T>> cidade) {
		StringBuilder resultBuilder = new StringBuilder();

		cidade.forEach(node -> {
			String path = node.getMenorDistancia().stream().map(Municipio::getNome).map(Objects::toString)
					.collect(Collectors.joining(" -> "));

			resultBuilder.append(path.isBlank() ? String.format("%s : %s", node.getNome(), node.getDistancia())
					: String.format("%s -> %s : %s", path, node.getNome(), node.getDistancia()));
			resultBuilder.append(String.format(" (Pedágios: %d)", node.getQtdPedagios()));
			resultBuilder.append(System.lineSeparator());
		});

		return resultBuilder.toString();
	}

	public String obterCustoComoString(List<Municipio<T>> cidade) {
		StringBuilder resultBuilder = new StringBuilder();

		cidade.forEach(node -> {
			String path = node.getMenorDistancia().stream().map(Municipio::getNome).map(Objects::toString)
					.collect(Collectors.joining(" -> "));

			resultBuilder.append(path.isBlank() ? String.format("%s : %s", node.getNome(), node.getCustoRota())
					: String.format("%s -> %s : %s", path, node.getNome(), node.getCustoRota()));
			resultBuilder.append(String.format(" (Pedágios: %d)", node.getQtdPedagios()));
			resultBuilder.append(System.lineSeparator());
		});

		return resultBuilder.toString();
	}

	public String obterCaminhoComoString(Municipio<T> destino) {
		List<Municipio<T>> caminho = destino.getMenorDistancia();
		StringBuilder pathBuilder = new StringBuilder();

		for (int i = caminho.size(); i >= 0; i--) {
			Municipio<T> node = caminho.get(i);
			pathBuilder.append(node.getNome());

			if (i > 0) {
				pathBuilder.append(" -> ");
			}
		}

		return pathBuilder.toString();
	}
}
