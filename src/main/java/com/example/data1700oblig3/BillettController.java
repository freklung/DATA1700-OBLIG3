package com.example.data1700oblig3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillettController {

    @Autowired
    BillettRepository billettRepository;

    @GetMapping("/getBilletterFraDB")
    public Billett getBilletterFraDB(@RequestParam int id){
        return billettRepository.søkPåId(id);
    }

    @PostMapping("/nyBillett")
    public void nyBillett(){
        billettRepository.nyBillett(new Billett("film1", "2", "Frederik", "Larsen", "46310199", "klungervik.frederik@gmail.com"));
    }

    @GetMapping("/getBilletter")
    public List<Billett> getBilletter(){
        return billettRepository.alleBilletter();
    }

    @DeleteMapping("/slettBillett")
    public String slettBillett(@RequestParam int id){
        billettRepository.slettById(id);
        return "slettet billet med id: " + id;
    }

    @PostMapping("/nyBilletIDB")
    public void nyBillettIDB(Billett billett){
        billettRepository.nyBillett(billett);
    }

    @PostMapping("/oppdaterBillett")
    public String oppdaterBillett(Billett billett){
        billettRepository.oppdaterBillett(billett);
        return "billett oppdatert: " + billett;
    }
}