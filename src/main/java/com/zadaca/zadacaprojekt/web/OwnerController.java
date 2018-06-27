package com.zadaca.zadacaprojekt.web;


import com.zadaca.zadacaprojekt.domain.Owner;
import com.zadaca.zadacaprojekt.dto.OwnerDTO;
import com.zadaca.zadacaprojekt.service.OwnerManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/owner"})
public class OwnerController {

private final OwnerManager ownerManager;

    public OwnerController(OwnerManager ownerManager) {
        this.ownerManager = ownerManager;
    }


    @PostMapping("/save")
    public OwnerDTO save(@RequestBody OwnerDTO owners) {
        Owner o = new Owner();
            o.setFirstName(owners.getFirstName());
            o.setLastName(owners.getLastName());
            o.setAddress(owners.getAddress());
            o.setOib(owners.getOib());

            OwnerDTO saveOwner = new OwnerDTO(ownerManager.save(o));

            return saveOwner;
    }

    @PutMapping("/save/{id}")
    public OwnerDTO updateOwner(@PathVariable("id") Long id, @RequestBody OwnerDTO owner) {

        Owner o = ownerManager.getById(id);

        o.setFirstName(owner.getFirstName());
        o.setLastName(owner.getLastName());
        o.setOib(owner.getOib());
        o.setAddress(owner.getAddress());


        OwnerDTO updateOwner = new OwnerDTO(ownerManager.save(o));
        return updateOwner;
    }


    @GetMapping("/list")
    public Page<OwnerDTO> getOwnersPage(Pageable pageable) {

        Page<Owner> owner = ownerManager.getAllOwnerPages(pageable);

        List<OwnerDTO> ownerDTOList = owner.getContent()
                .stream()
                .map(OwnerDTO::new)
                .collect(Collectors.toList());

        return new PageImpl<>(ownerDTOList, pageable, owner.getTotalElements());
    }

    @GetMapping("/{id}")
    public OwnerDTO findById(@PathVariable("id") Long id) {
        OwnerDTO ownerDTO = new OwnerDTO(ownerManager.getById(id));
        return ownerDTO;
    }


    @DeleteMapping({"/{id}"})
    public void deleteOwners(@PathVariable("id") Long id) {
        ownerManager.deleteOwner(id);
    }

    @GetMapping("/count")
    public Long getOwnerCount() {
        return ownerManager.getOwnerCount();
    }

}
