package com.test.jumanji

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {


    //Create global variables for the views in the layout
    private var progressBar: ProgressBar?=null
    private var tvProgress: TextView? = null
    private var tvQuestion:TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne:TextView? = null
    private var tvOptionTwo:TextView? = null
    private var tvOptionThree:TextView? = null
    private var tvOptionFour:TextView? = null
    /**
     * This function is auto created by Android when the Activity Class is created.
     */

    // TODO 2 A global variables for current position and questions list.
    // START
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null
    // END


    // TODO (STEP 5: A global variables for selected option.)
    // START
    private var mSelectedOptionPosition: Int = 0
    // END


    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_quiz_question)


        progressBar=findViewById(R.id.progressbar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_Question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)

        // TODO (STEP 3: Make the questions list and the current position variable global and remove the logs here.)
        // START
        mQuestionsList = Constants.getQuestions()
        // END

        setQuestion()

        // TODO (STEP 4: Set all the click events for Options using the interface onClick listener)
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
    }



    // TODO (STEP 1 : Lets create a function to set the question in the UI components which we have done earlier the onCreate method. And make some of the variables global which we will be using later.)
    // START
    /**
     * A function for setting the question to UI components.
     */
    private fun setQuestion() {

        val question: Question =
            mQuestionsList!![mCurrentPosition - 1] // Getting the question from the list with the help of current position.

        progressBar?.progress =
            mCurrentPosition // Setting the current progress in the progressbar using the position of question
        tvProgress?.text =
            "$mCurrentPosition" + "/" + progressBar?.max // Setting up the progress text

        // Now set the current question and the options in the UI
        tvQuestion?.text = question.question
        ivImage?.setImageResource(question.image)
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }

            }

            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }

            }

            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }

            }

            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }

            }
            R.id.btn_submit ->{
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++ //changes to next question

                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                        }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if(question!!.correctAns != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    answerView(question.correctAns, R.drawable.correct_option_border_bg)
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1-> {
                tvOptionOne?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2-> {
                tvOptionTwo?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3-> {
                tvOptionThree?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4-> {
                tvOptionFour?.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }

        }
    }

    // TODO (STEP 6: Create a function for view for highlighting the selected option.)
    // START

    /**
     * A function to set the view of selected option view.
     */
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionActivity,
            R.drawable.selected_option_border_bg
        )
    }


    // TODO (STEP 8: Create a function to set default options view.)
    // START
    /**
     * A function to set default options view when the new question is loaded or when the answer is reselected.
     */
    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3,it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQuestionActivity,
                R.drawable.default_option_border_bg
            )
        }
    }
}


//Todo check why colours are not changing