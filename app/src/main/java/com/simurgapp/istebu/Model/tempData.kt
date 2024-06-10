package com.simurgapp.istebu.Model

import java.lang.StrictMath.random
import kotlin.random.Random.Default.nextInt

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
    val careerFields = mutableListOf(mutableListOf("Graphic Design", "https://niecollege.ac.ke/wp-content/uploads/2024/01/graphic-design.jpg","Logo Design", "Brand Identity", "Print Design", "Digital Design", "Typography", "Packaging Design", "Motion Graphics", "Other"),
        mutableListOf("Programming", "https://cdn.sanity.io/images/tlr8oxjg/production/1ca7b34a8d5308a03ae186dfe72caabce0327fe2-1456x816.png?w=3840&q=100&fit=clip&auto=format","Web Development", "Mobile Development", "Game Development", "Data Science", "Machine Learning", "Artificial Intelligence", "Database Development", "Blockchain Development", "Cloud Computing", "Full Stack Development", "Software Development", "DevOps", "Cybersecurity", "Other"),
        mutableListOf("Teaching", "https://d138zd1ktt9iqe.cloudfront.net/media/seo_landing_files/file-teaching-skills-1605625101.jpg","Mathematics", "Physics", "Chemistry", "Biology", "Geography", "History", "Economics", "Business", "Marketing", "Management", "Finance", "Accounting", "Other"),
        mutableListOf("Translation", "https://www.textmaster.com/blog/wp-content/uploads/2021/05/Professional-Translation-Service-725x350.png","Technical Translation", "Literary Translation", "Legal Translation", "Medical Translation", "Localization", "Other"),
        mutableListOf("Fitness Training", "https://media.post.rvohealth.io/wp-content/uploads/2020/02/man-exercising-plank-push-up-1200x628-facebook.jpg", "Personal Training", "Group Fitness", "Strength Training","Cardio Training", "https://example.com/cardio-training.jpg","Yoga Instruction","Other"),
        mutableListOf("UI/UX Design", "https://assets-global.website-files.com/6100d0111a4ed76bc1b9fd54/64664e9cd07202af8bcdc5e4_5757453.jpg",  "User Research", "Wireframing", "Prototyping", "Interaction Design", "Visual Design", "App Design", "Web Design", "Mobile Design", "Game Design", "AR/VR Design", "Other"),
        mutableListOf("Video Editing", "https://p16-capcut-va.ibyteimg.com/tos-maliva-i-6rr7idwo9f-us/537850b4865c4607b503d62f432b55f3~tplv-6rr7idwo9f-image.image", "Film Editing", "Animation Editing", "Color Correction", "Sound Editing", "Motion Graphics", "Other"),
        mutableListOf("Voiceover", "https://d26oc3sg82pgk3.cloudfront.net/files/media/edit/image/532/original.jpg", "Commercial Voiceover", "Narration Voiceover", "Audiobook Narration", "Character Voices", "E-learning Voiceover", "Other"),
        mutableListOf("Life Coaching", "https://media.licdn.com/dms/image/C4E12AQHlx3wRC8V5ww/article-cover_image-shrink_600_2000/0/1520201246974?e=2147483647&v=beta&t=amcFx-vprxO0gh_5CWre2pa7JD6E3WYAuVcCUCQkTDM", "Career Coaching", "Health Coaching", "Relationship Coaching", "Executive Coaching", "Motivational Coaching", "Other"),
        mutableListOf("Nutrition Coach", "https://assets-global.website-files.com/6372cf43b12f4050f98e2731/642c5391ef6700cabf1364a5_5ff8af100a2fb1c8051c753e_Image.jpeg", "Diet Planning", "Sports Nutrition", "Weight Management", "Child Nutrition", "Holistic Nutrition", "Other"),
        mutableListOf("Photoshop", "https://cdn.hosting.com.tr/blog/wp-content/uploads)2021/02/Photoshop-manipulasyon.png", "Photo Editing", "Graphic Creation", "Retouching", "Compositing", "Digital Painting", "Other"),
        mutableListOf("Illustrator", "https://cdn.dribbble.com/users/1338391/screenshots/17547297/media/87cde54877f594d7e40ff90094adc782.jpg?resize=400x300&vertical=center", "Character Design", "Concept Art", "Editorial Illustration", "Children's Book Illustration", "Medical Illustration", "Other"),
        mutableListOf("Animation", "https://media.licdn.com/dms/image/C5612AQHiq9lH4yIqpA/article-cover_image-shrink_720_1280/0/1527115539573?e=2147483647&v=beta&t=wBqqEAVkS_XM4vjdxFx8G2RZuFmTz3ufACIqToOd0_w", "2D Animation", "3D Animation", "Stop Motion Animation", "Motion Graphics", "Visual Effects (VFX)", "Other"),
        mutableListOf("Social Media Management", "https://www.adobe.com/express/learn/blog/media_114b76a23afe9ee97a6d31503ae2ce6edf0140dd2.jpeg?width=1200&format=pjpg&optimize=medium", "Content Creation", "Community Management", "Analytics & Reporting", "Influencer Marketing", "Ad Campaign Management", "Other"),
        mutableListOf("Marketing", "https://www.cio.com/wp-content/uploads/2023/05/digital-marketing-ts-100598676-orig-4.jpg?quality=50&strip=all", "Digital Marketing", "SEO", "Content Marketing", "Email Marketing", "Market Research", "Other"),
        mutableListOf("Copywriting", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRQzH2CS8cPQKbW1yergHMKdUCMjnH6dCz_F3U4zYlpA&s", "Web Copy", "Advertising Copy", "SEO Copywriting", "Technical Writing", "Creative Writing", "Other"),
        mutableListOf("Accountant", "https://nowcfo.com/wp-content/uploads/2022/09/microsoftteams-1200x570.jpg", "Financial Accounting", "Tax Accounting", "Auditing", "Management Accounting", "Forensic Accounting", "Other")

    )


    val freelancerClass : MutableList<FreelancerClass> = mutableListOf()
    fun getFreeLancerClass() : MutableList<FreelancerClass> {
        for (i in 0..30) {
            println("girdi $i")
            try {
                freelancerClass.add(FreelancerClass(UID = i.toString(),
                    name = names[i],
                    surname = names.random(),
                    imageURL = images.random(),
                    dailyPrice = nextInt(100, 1000),
                    email = "berkyesilduman@gmail.com",
                    phoneNumber = "0532 123 45 67",
                    rating = 4.5f,
                    education = "Akdeniz University",
                    pastProjects = mutableListOf("Project A", "Project B", "Project C"),
                    ongoingProjects = mutableListOf("Project D", "Project E"),
                    careerFields = mutableListOf("Software Developer", "Web Developer", "Mobile Developer"),

                    completedGivenProjects = mutableListOf("Project F", "Project G"),
                    ongoingGivenProjects = mutableListOf("Project H", "Project I"),
                    country = "Turkey",
                    city = "Antalya",
                    defination = "I am a software developer. I have been working in this field for 5 years. I have completed many projects. I am very professional",

                    job = jobs.random(),
                    reviews = mutableListOf("Excellent work!", "Very professional."),
                    comments = mutableListOf("Great work!", "Very professional.", "Highly recommend!"),


                    ))
            }catch (e: IndexOutOfBoundsException) {
                println(e.message)
            }
        }
        return freelancerClass
    }
}
