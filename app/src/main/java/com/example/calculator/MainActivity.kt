package com.example.calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.parseColor("#696969")


        var value : Double = 0.0
        var ac : Boolean = false

        val btnZero : Button = findViewById(R.id.zero)
        val btnOne  : Button = findViewById(R.id.one)
        val btnTwo  : Button = findViewById(R.id.two)
        val btnThree: Button = findViewById(R.id.three)
        val btnFour : Button = findViewById(R.id.four)
        val btnFive : Button = findViewById(R.id.five)
        val btnSix  : Button = findViewById(R.id.six)
        val btnSeven: Button = findViewById(R.id.seven)
        val btnEight: Button = findViewById(R.id.eight)
        val btnNine : Button = findViewById(R.id.nine)
        val btnAdd  : Button = findViewById(R.id.plus)   // +
        val btnMul  : Button = findViewById(R.id.minus)   // -
        val btnSub  : Button = findViewById(R.id.mul)   // ×
        val btnDiv  : Button = findViewById(R.id.div)   // ÷
        val btnEqual: Button = findViewById(R.id.equal)  // =
        val btnAc : Button = findViewById(R.id.ac)      // AC

        val resultView : TextView = findViewById(R.id.result)



        btnZero.setOnClickListener {
            numBtnAction("0");
        }

        btnOne.setOnClickListener {
            numBtnAction("1");
        }

        btnTwo.setOnClickListener {
            numBtnAction("2");
        }

        btnThree.setOnClickListener {
            numBtnAction("3");
        }

        btnFour.setOnClickListener {
            numBtnAction("4");
        }

        btnFive.setOnClickListener {
            numBtnAction("5");
        }

        btnSix.setOnClickListener {
            numBtnAction("6");
        }

        btnSeven.setOnClickListener {
            numBtnAction("7");
        }

        btnEight.setOnClickListener {
            numBtnAction("8");
        }

        btnNine.setOnClickListener {
            numBtnAction("9");
        }

        btnAdd.setOnClickListener {
            calcBtnAction("+")
        }

        btnSub.setOnClickListener {
            calcBtnAction("-")
        }

        btnMul.setOnClickListener {
            calcBtnAction("*")
        }

        btnDiv.setOnClickListener {
            calcBtnAction("/")
        }

        btnEqual.setOnClickListener {
            equalBtnAction()
        }

        btnAc.setOnClickListener {
            acBtnAction()
        }


    }
}