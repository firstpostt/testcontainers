package com.example.demo.repo

import com.example.demo.entity.Proposal
import org.springframework.data.repository.CrudRepository

interface ProposalRepository : CrudRepository<Proposal, String>
