package com.vassa.paintactivity.domain.entity.profile
/**
 * @author Vassa
 * 03.07.2023
 * version code = 1
 * */
data class LocalProfileDomEntity(
    var id: Int,
    var name: String,
    var avatar: Int,
    var color: Int,
    var imposter: Int,
    var wins: Int
) {
}