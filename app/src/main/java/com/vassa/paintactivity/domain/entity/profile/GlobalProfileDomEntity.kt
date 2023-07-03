package com.vassa.paintactivity.domain.entity.profile


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