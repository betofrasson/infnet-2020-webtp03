package br.com.infnet.appcob.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.infnet.appcob.model.negocio.Debito;
import br.com.infnet.appcob.model.repository.IDebitoRepository;

@Service
public class DebitoService {

	@Autowired
	private IDebitoRepository debitoRepository;
	
	public void incluir(Debito debito) {
		debitoRepository.save(debito);
	}
	
	public List<Debito> obterListaPorDevedor(Integer devedor) {
		return debitoRepository.obterListaPorDevedor(devedor);
	}
	
	public List<Debito> obterQtdeAbertos() {
		return debitoRepository.obterQtdeAbertos();
	}
	
	public Float obterSaldo() {
		return debitoRepository.obterSaldo();
	}
	
	public Debito obterPorId(Integer id) {
		return debitoRepository.findById(id).orElse(new Debito(0));
	}
	
}
