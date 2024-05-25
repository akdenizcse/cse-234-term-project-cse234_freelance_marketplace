package com.simurgapp.istebu.Model

import android.content.IntentSender.OnFinished


data class FreelancerClass(
    var UID: String,
    var name: String,
    var surname: String,
    var imageURL: String,
    var dailyPrice: Int,
    var email: String,
    var phoneNumber: String,
    var education: String,
    var rating: Float = 0.0f,
    var pastProjects: MutableList<String> = mutableListOf(),
    var ongoingProjects: MutableList<String> = mutableListOf(),
    var skills: MutableList<String> = mutableListOf(),
    var reviews: MutableList<String> = mutableListOf(),
    var comments: MutableList<String> = mutableListOf(),
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
data class CareerFields(
    var name: String,
    var imageURL: String,
    var description: String,
    var jobs: MutableList<String> = mutableListOf(),
    var freelancers: MutableList<FreelancerClass> = mutableListOf(),
)

data class JobsClass(
    var UID: String,
    var name: String,
    var description: String,
    var imageURL: String,
    var skills: MutableList<String> = mutableListOf(),
    var salary: Float,
    var country: String,
    var city: String,
    var careerField: String,
    var employer: String,
    var applicants: MutableList<String> = mutableListOf(),
    var ongoing: Boolean = false,
    var completed: Boolean = false,
    var reviews: MutableList<String> = mutableListOf(),
    var score: Float = 0.0f,
    var isFinished: Boolean
)