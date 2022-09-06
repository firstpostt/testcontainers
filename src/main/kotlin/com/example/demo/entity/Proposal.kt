package com.example.demo.entity

import com.example.demo.CustomStatus
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "proposal")
data class Proposal(

  @Id
  @Column(name = "proposal_id")
  val proposalId: String,

  @Column(name = "amount", nullable = true)
  val amount: BigDecimal?,

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  val status: CustomStatus

)
