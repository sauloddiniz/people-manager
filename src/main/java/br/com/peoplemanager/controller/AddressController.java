package br.com.peoplemanager.controller;

import br.com.peoplemanager.dto.address.AddressDto;
import br.com.peoplemanager.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/person/{personId}/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping()
    public ResponseEntity<Void> personsAddresses(
            @PathVariable("personId") Long personId,
            @RequestBody @Valid AddressDto addressRequest, BindingResult bindingResult) {
        ValidRequest.valid(bindingResult);
        Long idAddressSaved = addressService.saveAddress(addressRequest, personId);
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
            @RequestBody @Valid AddressDto addressRequest, BindingResult bindingResult) {
        ValidRequest.valid(bindingResult);
        addressService.updateAddress(addressRequest, personId, addressId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{addressId}/principal")
    public ResponseEntity<Void> updatePrincipalAddress(
            @PathVariable("personId") Long personId,
            @PathVariable Long addressId) {
        addressService.updatePrincipalAddress(personId, addressId);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<AddressDto>> getAddresses(
            @PathVariable("personId") Long personId,
            @RequestParam(defaultValue = "") String ids) {
        return ResponseEntity.ok().body(addressService.getAddresses(personId, ids));
    }
}
