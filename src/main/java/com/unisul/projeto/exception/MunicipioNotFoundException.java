package com.unisul.projeto.exception;

public class MunicipioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MunicipioNotFoundException(String mensagem) {
		super(mensagem);
	}
}
