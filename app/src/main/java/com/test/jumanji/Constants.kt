package com.test.jumanji

object Constants {
    fun getQuestions():ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1, "What country does this flag belong?",
            R.drawable.australia,
            "Austalia","india", "norway","brazil",1
        )
        questionsList.add(que1)

        val que2 = Question(
            2, "What country does this flag belong?",
            R.drawable.brazil,
            "Austalia","india", "norway","brazil",4
        )
        questionsList.add(que1)

        val que3 = Question(
            3, "What country does this flag belong?",
            R.drawable.indian,
            "Austalia","india", "norway","brazil",2
        )
        questionsList.add(que1)

        val que4 = Question(
            4, "What country does this flag belong?",
            R.drawable.norway,
            "Austalia","india", "norway","brazil",3
        )
        questionsList.add(que1)


        return questionsList
    }
}