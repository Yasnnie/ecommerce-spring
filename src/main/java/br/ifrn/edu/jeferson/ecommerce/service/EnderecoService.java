package br.ifrn.edu.jeferson.ecommerce.service;


import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.EnderecoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    EnderecoMapper enderecoMapper;

    @Autowired
    ClienteRepository clienteRepository;


    public EnderecoResponseDTO salvar(Long id,EnderecoRequestDTO enderecoRequestDTO) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Cliente não encontrado"));
        Endereco newEndereco = enderecoMapper.toEntity(enderecoRequestDTO);
        newEndereco.setCliente(cliente);

       Endereco endereco =  enderecoRepository.save(newEndereco);
       return enderecoMapper.toDTO(endereco);

    }

}
