package br.com.infnet.appcob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.infnet.appcob.model.negocio.Debito;
import br.com.infnet.appcob.model.negocio.Devedor;
import br.com.infnet.appcob.model.service.DebitoService;
import br.com.infnet.appcob.model.service.DevedorService;

@Controller
public class DebitosController {

	@Autowired private DevedorService devedorService;
	@Autowired private DebitoService debitoService;
	
	@RequestMapping(value = "/devedores/debitos/{id}", method = RequestMethod.GET)
	public String lista(
			Model model,
			@PathVariable Integer id
			) {
		
		Devedor devedor = devedorService.obterPorId(id);
		model.addAttribute("devedor", devedor);
		
		model.addAttribute("debitos", debitoService.obterListaPorDevedor(id));
		
		return "debitos/lista";
	}
	
	@RequestMapping(value = "/devedores/debitos/{id}/cadastrar", method = RequestMethod.GET)
	public String cadastra(
			Model model,
			@PathVariable Integer id
			) {
		
		Devedor devedor = devedorService.obterPorId(id);
		model.addAttribute("devedor", devedor);
		
		return "debitos/detalhe";
	}
	
	@RequestMapping(value = "/debitos/incluir", method = RequestMethod.POST)
	public String incluir(
				Model model,
				Debito debito
			) {
		
		debito.setStatus("aberto");
		debitoService.incluir(debito);
		
		return lista(model, debito.getDevedor().getId());
	}
	
	@RequestMapping(value = "/debitos/quitar/{id}", method = RequestMethod.GET)
	public String quitar(
			Model model,
			@PathVariable Integer id
			) {
		
		Debito debito = debitoService.obterPorId(id);
		debito.setId(id);		
		debito.setStatus("quitado");
		debitoService.incluir(debito);

		return lista(model, debito.getDevedor().getId());
	}
	
	@RequestMapping(value = "/debitos/abrir/{id}", method = RequestMethod.GET)
	public String abrir(
			Model model,
			@PathVariable Integer id
			) {
		
		Debito debito = debitoService.obterPorId(id);
		debito.setId(id);		
		debito.setStatus("aberto");
		debitoService.incluir(debito);

		return lista(model, debito.getDevedor().getId());
	}
	
	@RequestMapping(value = "/devedores/debitos_consulta/{id}", method = RequestMethod.GET)
	public String consulta(
			Model model,
			@PathVariable Integer id
			) {
		
		Devedor devedor = devedorService.obterPorId(id);
		model.addAttribute("devedor", devedor);
		
		model.addAttribute("debitos", debitoService.obterListaPorDevedor(id));
		
		return "debitos/lista_consulta";
	}
}
