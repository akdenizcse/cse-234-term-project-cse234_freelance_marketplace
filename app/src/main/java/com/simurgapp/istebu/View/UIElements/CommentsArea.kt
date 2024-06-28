package com.simurgapp.istebu.View.UIElements


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simurgapp.istebu.Model.tempData

@Composable
fun CommentsArea(comments: List<String> , onShowAll : () -> Unit){
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
    val comment5 = randomComments.shuffled().take(5)
    val name5 = randomNames.shuffled().take(5)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Comments",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF006400)
            )
            IconButton(
                onClick = {
                    onShowAll()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Show all",
                    tint = Color(0xFF006400)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        comment5.forEachIndexed { index, comment ->
            PicTextItem(sire = "", title = name5.random(), subtitle = comment5.random(), imageUrl = tempData().images.random()){
            }
            if (index < comments.size - 1) {
                Divider(
                    color = Color.Gray.copy(alpha = 0.3f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}