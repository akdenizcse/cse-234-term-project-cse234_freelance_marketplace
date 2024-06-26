package com.simurgapp.istebu.View.Profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.simurgapp.istebu.Model.tempData
import com.simurgapp.istebu.View.UIElements.PicTextItem

@Composable
fun CommentsView(navController: NavController) {

    val randomNames = listOf(
        "Zehra Bozkurt",
        "Ali Berk Yesilduman",
        "John",
        "Jane",
        "Alice",
        "Bob",
        "Charlie",
        "David",
        "Eve",
        "Frank",
        "Grace",
        "Hank",
        "Merve",
        "Ahmet",
        "Selin",
        "Emre",
        "Cem",
        "Ayşe",
        "Fatma",
        "Mehmet",
        "Mustafa",
        "Elif",
        "Zeynep",
        "Kerem",
        "Ezgi",
        "Deniz",
        "Sinem",
        "Ozan",
        "Buse",
        "Kaan",
        "Burak",
        "Eylül",
        "Berkay",
        "Oğuz",
        "Furkan",
        "Aslı",
        "Barış",
        "Canan",
        "Derya",
        "Eren",
        "Ferhat",
        "Gizem",
        "Halil",
        "Işıl",
        "Jale",
        "Kadir",
        "Leman",
        "Melih",
        "Nalan",
        "Okan",
        "Peri",
        "Rıza",
        "Sibel",
        "Tamer",
        "Ufuk",
        "Vildan",
        "Yasemin",
        "Zeki",
        "Adem",
        "Betül",
        "Cansu",
        "Demir",
        "Efe",
        "Gül",
        "Hakan",
        "İlker",
        "Jülide",
        "Koray",
        "Levent",
        "Melike",
        "Nihat",
        "Orhan",
        "Pelin",
        "Rüya",
        "Selma",
        "Tayfun",
        "Umut",
        "Volkan",
        "Yusuf",
        "Zara"
    )
    val randomComments = listOf(
        "Great job!",
        "I love it!",
        "Amazing!",
        "I'm impressed!",
        "I'm speechless!",
        "I'm in awe!",
        "I'm in love!",
        "I'm in shock!",
        "I'm in tears!",
        "I'm in heaven!",
        "I'm in paradise!",
        "I'm in seventh heaven!",
        "This is fantastic!",
        "Absolutely wonderful!",
        "I'm thrilled!",
        "Brilliant!",
        "Outstanding!",
        "I'm blown away!",
        "Superb!",
        "Phenomenal!",
        "Incredible!",
        "Exceptional!",
        "Magnificent!",
        "Marvelous!",
        "Extraordinary!",
        "Sensational!",
        "Remarkable!",
        "Breathtaking!",
        "Spectacular!",
        "Astounding!",
        "Awesome!",
        "Wonderful!",
        "Fabulous!",
        "Terrific!",
        "Stunning!",
        "Mind-blowing!",
        "Wonderful creation!",
        "Beautiful work!",
        "Fantastic effort!",
        "Such talent!",
        "You outdid yourself!",
        "Wow, just wow!",
        "So impressive!",
        "I'm amazed!",
        "You nailed it!",
        "Kudos to you!",
        "Top-notch!",
        "First-rate!",
        "First-class!",
        "A masterpiece!",
        "You’re a genius!",
        "Bravo!",
        "Incredible effort!",
        "You've got a gift!",
        "Pure genius!",
        "You have talent!",
        "Astonishing!",
        "Magnificent!",
        "Inspirational!",
        "Perfection!",
        "Unbelievable!",
        "Artistic excellence!",
        "So beautiful!",
        "Unreal!",
        "Top quality!",
        "Perfect!",
        "Exquisite!",
        "Enchanting!",
        "Charming!",
        "Fantastic!",
        "Magical!",
        "Divine!",
        "Gorgeous!",
        "Radiant!",
        "Lovely!",
        "So creative!",
        "Stellar!",
        "Top-tier!",
        "Exemplary!",
        "Unmatched!",
        "Peerless!",
        "Supreme!",
        "Dazzling!",
        "Immaculate!",
        "Well done!",
        "Hats off to you!",
        "Masterful!",
        "Elite!",
        "Unparalleled!",
        "Transcendent!",
        "Elegant!",
        "Refined!",
        "Flawless!",
        "Premium!",
        "Distinguished!",
        "Superior!",
        "Amazing work!",
        "Such skill!"
    )
    val comment30 = randomComments.shuffled().take(30)
    val name30 = randomNames.shuffled().take(30)


    Column(
        modifier = Modifier.padding(16.dp, bottom = 66.dp).verticalScroll(rememberScrollState())
    ) {

        for (i in 0 until 30) {
            PicTextItem(
                "",
                name30[i],
                comment30[i],
                imageUrl = tempData().images.random()
            ) {

            }

        }

    }


}