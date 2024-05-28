package com.simurgapp.istebu.Model

import android.icu.util.Currency


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
    var careerFields: MutableList<String> = mutableListOf(),
    var completedGivenProjects: MutableList<String> = mutableListOf(),
    var ongoingGivenProjects: MutableList<String> = mutableListOf(),
    var reviews: MutableList<String> = mutableListOf(),
    var comments: MutableList<String> = mutableListOf(),
    var country: String,
    var city: String,
    var defination: String = ""
)
data class UserClass(
    var UID : String,
    var name : String,
    var surname : String,
    var imageURL: String,
    var email : String,
    var phoneNumber: String,
    var completedGivenProjects: MutableList<String> = mutableListOf(),
    var ongoingGivenProjects: MutableList<String> = mutableListOf(),
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
    var isFinished: Boolean,
)
data class ProjectClass(
    var UID: String,
    var projectName: String,
    var clientID: String,
    var skills: MutableList<String>,
    var freelancersID: MutableList<String> = mutableListOf(),
    var description: String,
    var date: String,
    var imageURL: MutableList<String> = mutableListOf(),
    var necessaryBranches: MutableList<String> = mutableListOf(),
    var numberPeople: Int,
    var budget: Float,
    var isFinished: Boolean,
    var estimatedTime: Int,
    var projectType: String ,//Fixed or Hourly
    var experienceLevel: String,
    var offers: MutableList<OffersClass> = mutableListOf(),
    var offersPrice: MutableList<Float> = mutableListOf(),

)
data class OffersClass(
    var UID: String,
    var price: Int,
    var estimatedTime: Int,
    var projectID: String,
    var freelancerID: String,
    var date: String,
    var isAccepted: Boolean,
    var isRejected: Boolean,
    var isFinished: Boolean,
    var comment: String,
    )