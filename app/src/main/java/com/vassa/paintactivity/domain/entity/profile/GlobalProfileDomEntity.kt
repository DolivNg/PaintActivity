package com.vassa.paintactivity.domain.entity.profile

/**
 * @author Vassa
 * 03.07.2023
 * version code = 1
 * */
data class GlobalProfileDomEntity(
    var id: Int,
    var name: String,
    var premium: Boolean,
    var timePremium: Boolean,
    var imposterCount: Int,
    var avatar: Int,
    var color: Int,
    var wins: Int
) {
}