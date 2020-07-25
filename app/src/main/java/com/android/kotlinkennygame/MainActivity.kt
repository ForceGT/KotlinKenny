package com.android.kotlinkennygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var score :Int =0
    var timeOver : Boolean = false
    var handler :Handler= Handler()
    var runnable :Runnable = Runnable{}
    var imgArray = ArrayList<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        score =0
        hideImages()

        imgArray   = arrayListOf(imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12)

        object : CountDownTimer (10000,1000){
            override fun onFinish() {
                timeText.text = "Time Up"
                timeOver= true
                timeText.startAnimation(AnimationUtils.loadAnimation(applicationContext, R.anim.fade_out))
                timeText.scaleX=1.25f
                handler.removeCallbacks(runnable)
            }

            override fun onTick(p0: Long) {
                timeText.text= "Time: " +p0/1000
            }
        }.start()
    }
    fun updateScore(view: View){
        if (view.isVisible && timeOver!=true){
            score++
            scoreText.text= "Score: "+ score
        }

    }

    fun hideImages(){

        runnable = object :Runnable{
            override fun run() {

                for(image in imgArray){
                    image.visibility = View.INVISIBLE;
                }

                val random = Random()
                val index= random.nextInt(8-0)
                imgArray[index].visibility= View.VISIBLE;
                handler.postDelayed(runnable, 500)
            }
        }
        handler.post(runnable)
    }
}


