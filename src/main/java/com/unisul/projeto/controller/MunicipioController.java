package com.unisul.projeto.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unisul.projeto.graph.Grafo;
import com.unisul.projeto.model.Municipio;

@RestController
@RequestMapping("/municipios")
@CrossOrigin
public class MunicipioController {

	@Autowired
	private Grafo<String> grafo;

	@GetMapping("/buscar-rota")
	public ResponseEntity<RotaDetalhado> buscarRota(@RequestParam String origem, @RequestParam String destino,
			@RequestParam String tipoCalculo, @RequestParam String tipoVeiculo) {
		Municipio<String> Armazem = new Municipio<>("Armazem");
		Municipio<String> BracodoNorte = new Municipio<>("BracodoNorte");
		Municipio<String> CapivarideBaixo = new Municipio<>("CapivarideBaixo");
		Municipio<String> Garopaba = new Municipio<>("Garopaba");
		Municipio<String> GraoPara = new Municipio<>("GraoPara");
		Municipio<String> Gravatal = new Municipio<>("Gravatal");
		Municipio<String> Imarui = new Municipio<>("Imarui");
		Municipio<String> Imbituba = new Municipio<>("Imbituba");
		Municipio<String> Jaguaruna = new Municipio<>("Jaguaruna");
		Municipio<String> Laguna = new Municipio<>("Laguna");
		Municipio<String> Orleans = new Municipio<>("Orleans");
		Municipio<String> PedrasGrandes = new Municipio<>("PedrasGrandes");
		Municipio<String> PescariaBrava = new Municipio<>("PescariaBrava");
		Municipio<String> RioFortuna = new Municipio<>("RioFortuna");
		Municipio<String> Sangao = new Municipio<>("Sangao");
		Municipio<String> SantaRosadeLima = new Municipio<>("SantaRosadeLima");
		Municipio<String> SaoLudgero = new Municipio<>("SaoLudgero");
		Municipio<String> SaoMartinho = new Municipio<>("SaoMartinho");
		Municipio<String> TrezedeMaio = new Municipio<>("TrezedeMaio");
		Municipio<String> Tubarao = new Municipio<>("Tubarao");

		Garopaba.addCidadesAdjacentes(Imbituba, 30.40, 0);
		Imbituba.addCidadesAdjacentes(Imarui, 29.20, 0);
		Imbituba.addCidadesAdjacentes(Laguna, 34.90, 1);
		Laguna.addCidadesAdjacentes(PescariaBrava, 20.30, 0);
		Imarui.addCidadesAdjacentes(PescariaBrava, 36.50, 0);
		Imarui.addCidadesAdjacentes(SaoMartinho, 37.00, 0);
		PescariaBrava.addCidadesAdjacentes(SaoMartinho, 39.70, 0);
		PescariaBrava.addCidadesAdjacentes(CapivarideBaixo, 13.00, 0);
		CapivarideBaixo.addCidadesAdjacentes(Tubarao, 8.90, 0);
		SaoMartinho.addCidadesAdjacentes(Armazem, 14.90, 0);
		SaoMartinho.addCidadesAdjacentes(RioFortuna, 17.10, 0);
		RioFortuna.addCidadesAdjacentes(SantaRosadeLima, 18.10, 0);
		RioFortuna.addCidadesAdjacentes(BracodoNorte, 20.70, 0);
		BracodoNorte.addCidadesAdjacentes(GraoPara, 13.40, 0);
		BracodoNorte.addCidadesAdjacentes(SaoLudgero, 8.80, 0);
		BracodoNorte.addCidadesAdjacentes(Gravatal, 13.50, 0);
		Gravatal.addCidadesAdjacentes(Armazem, 11.80, 0);
		Gravatal.addCidadesAdjacentes(Tubarao, 23.2, 0);
		SaoLudgero.addCidadesAdjacentes(Orleans, 12.80, 0);
		Orleans.addCidadesAdjacentes(PedrasGrandes, 17.40, 0);
		PedrasGrandes.addCidadesAdjacentes(Tubarao, 26.1, 0);
		Armazem.addCidadesAdjacentes(Tubarao, 28.1, 0);
		Tubarao.addCidadesAdjacentes(TrezedeMaio, 27.5, 1);
		Tubarao.addCidadesAdjacentes(Sangao, 26.5, 1);
		Tubarao.addCidadesAdjacentes(Jaguaruna, 21.4, 1);
		Sangao.addCidadesAdjacentes(Jaguaruna, 13.60, 0);
		Sangao.addCidadesAdjacentes(TrezedeMaio, 13.2, 0);
		Imbituba.addCidadesAdjacentes(Garopaba, 30.40, 0);
		Imarui.addCidadesAdjacentes(Imbituba, 29.20, 0);
		Laguna.addCidadesAdjacentes(Imbituba, 34.90, 1);
		PescariaBrava.addCidadesAdjacentes(Laguna, 20.30, 0);
		PescariaBrava.addCidadesAdjacentes(Imarui, 36.50, 0);
		SaoMartinho.addCidadesAdjacentes(Imarui, 37.00, 0);
		SaoMartinho.addCidadesAdjacentes(PescariaBrava, 39.70, 0);
		CapivarideBaixo.addCidadesAdjacentes(PescariaBrava, 13.00, 0);
		Tubarao.addCidadesAdjacentes(CapivarideBaixo, 8.90, 0);
		Armazem.addCidadesAdjacentes(SaoMartinho, 14.90, 0);
		RioFortuna.addCidadesAdjacentes(SaoMartinho, 17.10, 0);
		SantaRosadeLima.addCidadesAdjacentes(RioFortuna, 18.10, 0);
		BracodoNorte.addCidadesAdjacentes(RioFortuna, 20.70, 0);
		GraoPara.addCidadesAdjacentes(BracodoNorte, 13.40, 0);
		SaoLudgero.addCidadesAdjacentes(BracodoNorte, 8.80, 0);
		Gravatal.addCidadesAdjacentes(BracodoNorte, 13.50, 0);
		Armazem.addCidadesAdjacentes(Gravatal, 11.80, 0);
		Tubarao.addCidadesAdjacentes(Gravatal, 23.2, 0);
		Orleans.addCidadesAdjacentes(SaoLudgero, 12.80, 0);
		PedrasGrandes.addCidadesAdjacentes(Orleans, 17.40, 0);
		Tubarao.addCidadesAdjacentes(PedrasGrandes, 26.1, 0);
		Tubarao.addCidadesAdjacentes(Armazem, 28.1, 0);
		TrezedeMaio.addCidadesAdjacentes(Tubarao, 27.5, 1);
		Sangao.addCidadesAdjacentes(Tubarao, 26.5, 1);
		Jaguaruna.addCidadesAdjacentes(Tubarao, 21.4, 1);
		Jaguaruna.addCidadesAdjacentes(Sangao, 13.60, 0);
		TrezedeMaio.addCidadesAdjacentes(Sangao, 13.2, 0);

		Municipio<String> origemMunicipio = null;
		Municipio<String> destinoMunicipio = null;

		for (Municipio<String> municipio : Arrays.asList(Armazem, BracodoNorte, CapivarideBaixo, Garopaba, GraoPara,
				Gravatal, Imarui, Imbituba, Jaguaruna, Laguna, Orleans, PedrasGrandes, PescariaBrava, RioFortuna,
				Sangao, SantaRosadeLima, SaoLudgero, SaoMartinho, TrezedeMaio, Tubarao)) {
			if (municipio.getNome().equalsIgnoreCase(origem)) {
				origemMunicipio = municipio;
			}
			if (municipio.getNome().equalsIgnoreCase(destino)) {
				destinoMunicipio = municipio;
			}

			if (origemMunicipio != null && destinoMunicipio != null) {
				break;
			}
		}

		if (origemMunicipio == null || destinoMunicipio == null) {
			return ResponseEntity.badRequest()
					.body(new RotaDetalhado("Origem ou destino não encontrados na lista de municípios."));
		}

		String rota = "";
		if ("caminho".equalsIgnoreCase(tipoCalculo)) {
			this.grafo.calcularCaminhoMaisCurto(origemMunicipio);
			this.grafo.imprimirCaminho(List.of(destinoMunicipio));
			rota = this.grafo.obterCaminhoComoString(List.of(destinoMunicipio));

		} else if ("custo".equalsIgnoreCase(tipoCalculo)) {
			this.grafo.calcularMenorCustoAPartirDeOrigem(origemMunicipio, tipoVeiculo);
			this.grafo.imprimirCustoCaminho(List.of(destinoMunicipio));
			rota = this.grafo.obterCustoComoString(List.of(destinoMunicipio));
		}

		String[] partesCaminho = rota.split(";");

		if (partesCaminho.length == 3) {
			RotaDetalhado caminhoDetalhado = new RotaDetalhado(partesCaminho[0], partesCaminho[1], partesCaminho[2]);

			return ResponseEntity.ok(caminhoDetalhado);
		} else {
			return ResponseEntity.badRequest().body(new RotaDetalhado("Formato de caminho inválido"));
		}
	}

// Classe para representar a resposta como JSON
// Classe para representar os detalhes do caminho
	private static class RotaDetalhado {
		private String rota;
		private String distancia;
		private String pedagios;

		public RotaDetalhado(String rota, String distancia, String pedagios) {
			this.rota = rota;
			this.distancia = distancia;
			this.pedagios = pedagios;
		}

		public RotaDetalhado(String string) {
		}

		public String getRota() {
			return rota;
		}

		public String getDistancia() {
			return distancia;
		}

		public String getPedagios() {
			return pedagios;
		}
	}
}