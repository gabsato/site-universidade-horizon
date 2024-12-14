package acc.br.site_universidade.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import acc.br.site_universidade.model.Endereco;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
	@GetMapping("/{cep}/json")
	Endereco getEnderecoPorCep(@PathVariable("cep") String cep);
}