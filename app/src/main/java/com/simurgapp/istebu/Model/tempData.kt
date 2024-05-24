package com.simurgapp.istebu.Model

class tempData {
    val names = mutableListOf("John", "Jane", "Alice", "Bob", "Charlie", "David", "Emma", "Fiona", "George", "Hannah",
        "Irene", "Jack", "Katherine", "Liam", "Mia", "Noah", "Olivia", "Paul", "Quinn", "Rachel",
        "Samuel", "Tina", "Uma", "Victor", "Wendy", "Xander", "Yara", "Zane", "Aaron", "Bella",
        "Catherine", "Daniel", "Eva", "Frank", "Grace", "Harry", "Isabella", "Jacob", "Karen",
        "Laura", "Michael", "Natalie", "Oscar", "Penelope", "Quincy", "Rebecca", "Steven", "Tracy",
        "Ulysses", "Vanessa", "Will", "Xenia", "Yvonne", "Zachary", "Abigail", "Brian", "Chloe",
        "Derek", "Elena", "Fred", "Gina", "Henry", "Isla", "James", "Kara", "Leon", "Megan",
        "Nina", "Owen", "Paula", "Quinn", "Ron", "Sara", "Thomas", "Ursula", "Vince", "Willa",
        "Xavier", "Yasmin", "Zoe", "Amelia", "Blake", "Cleo", "Diana", "Ethan", "Felicity", "Gavin",
        "Hazel", "Ivan", "Jade", "Kyle", "Lily", "Mark", "Nora", "Omar")
    val jobs = mutableListOf(
        "Software Developer", "Data Scientist", "Graphic Designer", "Project Manager", "Product Manager",
        "Sales Manager", "Marketing Specialist", "Customer Service Representative", "Financial Analyst",
        "Human Resources Manager", "Operations Manager", "Business Analyst", "Quality Assurance Engineer",
        "Content Writer", "Social Media Manager", "IT Support Specialist", "System Administrator", "DevOps Engineer",
        "Network Engineer", "Cyber Security Specialist", "Mobile App Developer", "UX/UI Designer", "Database Administrator",
        "SEO Specialist", "Digital Marketing Manager", "Web Developer", "Cloud Engineer", "AI Engineer", "Machine Learning Engineer",
        "Blockchain Developer", "Game Developer", "Technical Support Engineer", "Technical Writer", "Recruiter", "Account Manager",
        "Brand Manager", "Event Planner", "Public Relations Specialist", "Advertising Manager", "Art Director", "Creative Director",
        "Interior Designer", "Architect", "Civil Engineer", "Mechanical Engineer", "Electrical Engineer", "Chemical Engineer",
        "Biomedical Engineer", "Environmental Engineer", "Industrial Engineer", "Supply Chain Manager", "Logistics Manager",
        "Purchasing Manager", "Inventory Manager", "Procurement Specialist", "Health and Safety Manager", "Research Scientist",
        "Laboratory Technician", "Pharmacist", "Nurse", "Doctor", "Dentist", "Veterinarian", "Physical Therapist", "Occupational Therapist",
        "Chiropractor", "Dietitian", "Nutritionist", "Personal Trainer", "Fitness Instructor", "Yoga Instructor", "Pilates Instructor",
        "Massage Therapist", "Speech Therapist", "Psychologist", "Counselor", "Social Worker", "Teacher", "Professor", "Librarian",
        "School Administrator", "Tutor", "Translator", "Interpreter", "Journalist", "Editor", "Photographer", "Videographer", "Film Director",
        "Producer", "Musician", "Singer", "Actor", "Actress", "Comedian", "Chef", "Baker", "Bartender", "Waiter", "Waitress"
    )
    val images = mutableListOf("https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg",
        "https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
        "https://www.justgeek.fr/wp-content/uploads/2022/12/exemple-image-midjourney.webp",
        "https://korben.info/app/uploads/2022/11/Manu23_Space_dog_b9d89ef4-c665-4ee7-a22d-a185775c3d11.webp",
        "https://phototrend.fr/wp-content/uploads/2022/05/a-photo-of-a-raccoon-wearing-an-astronaut-helmet.jpeg",
        "https://www.justgeek.fr/wp-content/uploads/2022/12/exemple-image-midjourney.webp",
        )

    val freelancerClass : MutableList<FreelancerClass> = mutableListOf()
    fun getFreeLancerClass() : MutableList<FreelancerClass> {
        for (i in 0..94) {
            try {
                freelancerClass.add(FreelancerClass("UID$i", names[i], "Surname$i", images.random(), "email$i", "phoneNumber$i", "education$i", 0.0f,names,names,jobs,jobs , "country$i", "city$i"))
            }catch (e: IndexOutOfBoundsException) {
                println(e.message)
            }
        }
        return freelancerClass
    }
}