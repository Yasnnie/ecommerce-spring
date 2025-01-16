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
        return clientesPage.map(clienteMapper::toDTO);
    }


    public ClienteResponseDTO getById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(!cliente.isPresent())
            throw  new ResourceNotFoundException("Cliente não encontrado");

        return clienteMapper.toDTO(cliente.get());
    }

    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Cliente não encontrado"));

        if (!cliente.getEmail().equals(clienteRequestDTO.getEmail()) && clienteRepository.existsByEmail( clienteRequestDTO.getEmail()) ) {
            throw  new BusinessException("Email já existente");
        }

        if (!cliente.getCpf().equals(clienteRequestDTO.getCpf()) && clienteRepository.existsByCpf(clienteRequestDTO.getCpf()) ) {
            throw  new BusinessException("CPF já cadastrado");
        }

        clienteMapper.updateEntityFromDTO(clienteRequestDTO, cliente);
        var updatedClient = clienteRepository.save(cliente);

        return clienteMapper.toDTO(updatedClient);
    }
}
