package br.com.peoplemanager.infrastructure.controller;

import br.com.peoplemanager.application.usecase.address.ListAddress;
import br.com.peoplemanager.application.usecase.address.SaveAddress;
import br.com.peoplemanager.domain.entity.dto.AddressDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/person/{personId}/addresses")
@CrossOrigin(origins = "*")
public class AddressController {

    private final SaveAddress saveAddress;

    private final ListAddress listAddress;

    public AddressController(SaveAddress saveAddress, ListAddress listAddress) {
        this.saveAddress = saveAddress;
        this.listAddress = listAddress;
    }

    @PostMapping()
    public ResponseEntity<Void> personsAddresses(
            @PathVariable("personId") Long personId,
            @RequestBody AddressDto addressRequest) {
        Long idAddressSaved = saveAddress.execute(personId, addressRequest);
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/".concat(String.valueOf(idAddressSaved)))
                        .buildAndExpand(idAddressSaved)
                        .toUri())
                .build();
    }

//    @PutMapping("/{addressId}")
//    public ResponseEntity<Void> updateAddress(
//            @PathVariable("personId") Long personId,
//            @PathVariable Long addressId,
//            @RequestBody @Valid AddressDto addressRequest, BindingResult bindingResult) {
//        ValidRequest.valid(bindingResult);
//        addressService.updateAddress(addressRequest, personId, addressId);
//        return ResponseEntity.ok().build();
//    }

//    @PatchMapping("/{addressId}/principal")
//    public ResponseEntity<Void> updatePrincipalAddress(
//            @PathVariable("personId") Long personId,
//            @PathVariable Long addressId) {
//        addressService.updatePrincipalAddress(personId, addressId);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping()
    public ResponseEntity<List<AddressDto>> getAddresses(
            @PathVariable("personId") Long personId) {
        return ResponseEntity.ok().body(listAddress.execute(personId));
    }
}
