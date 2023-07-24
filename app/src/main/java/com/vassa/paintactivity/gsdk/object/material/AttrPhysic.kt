package com.vassa.paintactivity.gsdk.`object`.material

import com.vassa.paintactivity.gsdk.`object`.gameobject.GameObject

data class AttrPhysic(
    var solid : Boolean = false,//
    var physical : Boolean = false,// Use physics
    var material : Boolean = false,//Use mask
    var rotation : Boolean = false,//Can rotation sprite
    var wind : Boolean = false, // affect wind
    var gravity : Boolean = false,// affect gravity
    var friction : Boolean = false, // Use Friction
    var stuck : Boolean = false, // Can stuck to object
    var rotationFriction : Boolean = false, // friction

    var stackObject : GameObject? = null,

    var rotationSpeed : Double = 0.0,

    var frictionX : Double = 0.0, // Friction X
    var frictionY : Double = 0.0, // Friction Y

    var impulseX : Double = 0.0, //impulse X
    var impulseY : Double = 0.0, //

    var angle : Double = 0.0,
    var gravityY : Double = 0.0,
    var windX : Double = 0.0,
    var speedX: Double = 0.0, // Vector move x
    var speedY: Double = 0.0, // Vector move y
    var mass : Double = 1.0, // Mass
)