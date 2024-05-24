package com.simurgapp.istebu.Model





data class FreelancerClass(
    var UID: String,
    var name: String,
    var surname: String,
    var imageURL: String,
    var email: String,
    var phoneNumber: String,
    var education: String,
    var score: Float = 0.0f,
    var completedProjects: MutableList<String> = mutableListOf(),
    var ongoingProjects: MutableList<String> = mutableListOf(),
    var skills: MutableList<String> = mutableListOf(),
    var reviews: MutableList<String> = mutableListOf(),
    var country: String,
    var city: String,
)
data class UserClass(
    var UID : String,
    var name : String,
    var surname : String,
    var imageURL: String,
    var email : String,
    var phoneNumber: String,
    var education: String,
    var score: Float = 0.0f,
    var completedProjects: MutableList<String> = mutableListOf(),
    var ongoingProjects: MutableList<String> = mutableListOf(),
    var skills: MutableList<String> = mutableListOf(),
    var reviews: MutableList<String> = mutableListOf(),
    var country : String,
    var city : String,
)