package br.com.peoplemanager.infrastructure.controller;

import br.com.peoplemanager.domain.usecase.address.*;
import br.com.peoplemanager.dto.AddressDto;
import br.com.peoplemanager.domain.model.Address;
import br.com.peoplemanager.dto.mapper.AddressDtoMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person/{personId}/address")
public class AddressController {
    public static final String PRINCIPAL = "principal";
    private final SaveAddress saveAddress;
    private final ListAddressByIdPerson listAddress;
    private final UpdateAddress updateAddress;
    private final GetAddress getAddress;
    private final UpdatePrincipalAddress updatePrincipalAddress;

    public AddressController(SaveAddress saveAddress, ListAddressByIdPerson listAddress,
                             UpdateAddress updateAddress, GetAddress getAddress,
                             UpdatePrincipalAddress updatePrincipalAddress) {
        this.saveAddress = saveAddress;
        this.listAddress = listAddress;
        this.updateAddress = updateAddress;
        this.getAddress = getAddress;
        this.updatePrincipalAddress = updatePrincipalAddress;
    }

    @PostMapping()
    public ResponseEntity<Void> personsAddresses(
            @PathVariable("personId") Long personId,
            @RequestBody AddressDto addressRequest) {

        Long idAddressSaved = saveAddress.execute(personId,
                AddressDtoMapper.toModel(addressRequest)).getAddressId();

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/".concat(String.valueOf(idAddressSaved)))
                        .buildAndExpand(idAddressSaved)
                        .toUri())
                .build();
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Void> updateAddress(
            @PathVariable("personId") Long personId,
            @PathVariable Long addressId,
            @RequestBody AddressDto addressRequest) {

        updateAddress.execute(personId, addressId,
                AddressDtoMapper.toModel(addressRequest));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDto> getAddress(
            @PathVariable("personId") Long personId,
            @PathVariable Long addressId) {
        Address address = getAddress.execute(personId, addressId);
        return ResponseEntity.ok().body(AddressDtoMapper.toDto(address));
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<Void> updatePrincipalAddress(
            @PathVariable("personId") Long personId,
            @PathVariable Long addressId,
            @RequestBody Map<String, Boolean> principal) {

        boolean isPrincipal = principal.get(PRINCIPAL);
        updatePrincipalAddress.execute(personId, addressId, isPrincipal);

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<AddressDto>> getAddresses(
            @PathVariable("personId") Long personId) {
        List<Address> addresses = listAddress.execute(personId);
        return ResponseEntity.ok().body(converter(addresses));
    }

    @NotNull
    private static List<AddressDto> converter(List<Address> addresses) {
        return addresses.stream().map(AddressDtoMapper::toDto).toList();
    }
}
