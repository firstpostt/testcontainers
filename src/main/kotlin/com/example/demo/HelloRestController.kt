package com.example.demo

import com.example.demo.entity.Proposal
import com.example.demo.repo.ProposalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class HelloRestController @Autowired constructor(val proposalRepository: ProposalRepository) {

    @GetMapping("/hello")
    fun hello(): ResponseEntity<Proposal>{
        var result = proposalRepository.findByIdOrNull("123")

        if (result == null) {
            result = proposalRepository.save(Proposal("999", BigDecimal.TEN, CustomStatus.STARTED))
        }

        return ResponseEntity.ok(result)
    }
}
