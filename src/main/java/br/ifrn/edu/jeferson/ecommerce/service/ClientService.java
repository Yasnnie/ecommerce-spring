package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.*;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.*;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.*;
import br.ifrn.edu.jeferson.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteMapper clienteMapper;




    public ClienteResponseDTO salvar(ClienteRequestDTO clienteDTO) {

        if(clienteRepository.existsByEmail(clienteDTO.getEmail()))
            throw new BusinessException("Já existe um cliente com esse email");

       if(clienteRepository.existsByCpf(clienteDTO.getCpf()))
           throw new BusinessException("Já existe um cliente com esse CPF");

        Cliente newClient = clienteMapper.toEntity(clienteDTO);

        Cliente salvo = clienteRepository.save(newClient);
        return clienteMapper.toDTO(salvo);
    }

    public Page<ClienteResponseDTO> listarClientes(Pageable pageable) {

        Page<Cliente> clientesPage = clienteRepository.findAll(pageable);

        System.out.println(clientesPage.getContent());
        return clientesPage.map(clienteMapper::toDTO);
    }


    public ClienteResponseDTO getById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        System.out.println("cliente: " + cliente);
        if(!cliente.isPresent())
            throw  new ResourceNotFoundException("Cliente não encontrado");

        return clienteMapper.toDTO(cliente.get());
    }
}
