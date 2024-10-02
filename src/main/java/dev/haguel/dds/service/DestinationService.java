package dev.haguel.dds.service;

import dev.haguel.dds.DTO.DestinationDTO;
import dev.haguel.dds.dao.DestinationRepository;
import dev.haguel.dds.model.Destination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DestinationService {
    private final DestinationRepository destinationRepository;

    public Destination createDestination(DestinationDTO destinationDTO) {
        Destination destination = new Destination(destinationDTO.getCountry(), destinationDTO.getCity(),
                destinationDTO.getAddress());

        destinationRepository.save(destination);

        return destination;
    }
}
