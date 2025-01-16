package br.ifrn.edu.jeferson.ecommerce.service;


import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.EnderecoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        if (enderecoRepository.existsByCliente_Id(id))
            throw new BusinessException("O cliente já possui um endereço.");


        Endereco newEndereco = enderecoMapper.toEntity(enderecoRequestDTO);
        newEndereco.setCliente(cliente);

       Endereco endereco =  enderecoRepository.save(newEndereco);
       return enderecoMapper.toDTO(endereco);

    }

    public EnderecoResponseDTO getByClientId(Long id) {
        Endereco endereco = enderecoRepository.findByCliente_Id(id).orElseThrow( () -> new ResourceNotFoundException("Endereço não encontrado"));
        return enderecoMapper.toDTO(endereco);
    }

    public EnderecoResponseDTO atualizar(Long id, EnderecoRequestDTO enderecoRequestDTO) {
        Endereco endereco = enderecoRepository.findByCliente_Id(id).orElseThrow( () -> new ResourceNotFoundException("Endereço não encontrado"));

        enderecoMapper.updateEntityFromDTO(enderecoRequestDTO, endereco);
        return enderecoMapper.toDTO(enderecoRepository.save(endereco));
    }

}
