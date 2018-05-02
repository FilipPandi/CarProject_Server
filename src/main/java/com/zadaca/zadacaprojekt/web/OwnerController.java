package com.zadaca.zadacaprojekt.web;


import com.zadaca.zadacaprojekt.domain.Owner;
import com.zadaca.zadacaprojekt.dto.OwnerDTO;
import com.zadaca.zadacaprojekt.service.OwnerManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({"/owners"})
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

    @GetMapping("/list")
    public Page<Owner> getOwnersPage(Pageable pageable) {

        return ownerManager.getAllOwnerPages(pageable);
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

    @PutMapping("/save/{id}")
    public Owner updateOwner(@PathVariable("id") Long id, @RequestBody OwnerDTO owner) {

        Owner o = ownerManager.getById(id);
        o.setFirstName(owner.getFirstName());
        o.setLastName(owner.getLastName());
        o.setOib(owner.getOib());
        o.setAddress(owner.getAddress());

        return ownerManager.save(o);
    }

    @GetMapping("/count")
    public Long getOwnerCount() {
        return ownerManager.getOwnerCount();
    }

}
