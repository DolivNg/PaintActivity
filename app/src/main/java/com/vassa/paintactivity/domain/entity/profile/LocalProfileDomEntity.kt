package com.vassa.paintactivity.domain.entity.profile

data class LocalProfileDomEntity(
    var id: Int,
    var name: String,
    var avatar: Int,
    var color: Int,
    var imposter: Int,
    var wins: Int
) {
}