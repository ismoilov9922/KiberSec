package uz.roboticslab.kibersec.utils

import uz.roboticslab.kibersec.models.Question
import uz.roboticslab.kibersec.models.TitleData

object LoadList {
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val question1 = Question(
            1,
            "Oldingi senariyda qaysi Xavfsizlik maqsadi buzilgan ?",
            "Maxfiylik",
            "Butunlik",
            "Mavjudligi",
            "Ruxsat berish",
            2
        )
        questionsList.add(question1)
        val question2 = Question(
            1,
            "Which of the following languages is used as a scripting language in the Unity 3D game engine?",
            "Java",
            "C#",
            "C++",
            "Objective-C",
            4
        )
        questionsList.add(question2)

        return questionsList
    }

    fun getTitleList(): List<TitleData> {
        val list = ArrayList<TitleData>()
        list.add(TitleData("Kiber  xavfsizligi tushunchasi va axborotni himoyalash muammolari", "lesson1"))
        list.add(TitleData("Kiber Xavfsizlik nima?", "lesson2"))
        list.add(TitleData("Dunyodagi eng yaxshi kiberxavfsizlik maktablari", "lesson3"))
        list.add(TitleData("Aloqa kanallari", "lesson4"))
        list.add(TitleData("Serverlardi ximoya qilish", "lesson5"))
        list.add(TitleData("Xavfsizlikning asosiy yo’nalishlari", "lesson6"))
        list.add(TitleData("Axborot-kommunikatsion tizimlar va tarmoqlarda taxdidlar va zaifliklar", "lesson7"))
        list.add(TitleData("Blockchain ishlab chiqarish", "lesson8"))
        list.add(TitleData("Kriptograf", "lesson9"))
        list.add(TitleData("Kiberxavfsizlikda ishlash uchun qanday malakalar kerak?", "lesson10"))
        return list
    }
}