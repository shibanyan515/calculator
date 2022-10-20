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


        var value: Double = 0.0
        var ac: Boolean = false
        var equal: Boolean = false
        var operator: Boolean = false
        var point: Boolean = false
        var bracket : Boolean = false


        val btnZero: Button = findViewById(R.id.zero)
        val btnOne: Button = findViewById(R.id.one)
        val btnTwo: Button = findViewById(R.id.two)
        val btnThree: Button = findViewById(R.id.three)
        val btnFour: Button = findViewById(R.id.four)
        val btnFive: Button = findViewById(R.id.five)
        val btnSix: Button = findViewById(R.id.six)
        val btnSeven: Button = findViewById(R.id.seven)
        val btnEight: Button = findViewById(R.id.eight)
        val btnNine: Button = findViewById(R.id.nine)
        val btnAdd: Button = findViewById(R.id.plus)   // +
        val btnMul: Button = findViewById(R.id.mul)   // -
        val btnMinus: Button = findViewById(R.id.minus)   // ×
        val btnDiv: Button = findViewById(R.id.div)   // ÷
        val btnEqual: Button = findViewById(R.id.equal)  // =
        val btnAc: Button = findViewById(R.id.ac)      // AC
        val btnDel: Button = findViewById(R.id.del)   //del
        val btnPoint: Button = findViewById(R.id.point) //point
        val btnBracketLeft: Button = findViewById(R.id.bracketLeft)
        val btnBracketRight: Button = findViewById(R.id.bracketRight)

        val resultView: TextView = findViewById(R.id.result)

        var tmpNum :String
        val inputArray:Array<String>

//        val opStack = ArrayDeque()
//
//        val numStack = ArrayDeque()

        fun numBtnAction(num: String) {
            resultView.text = if (resultView.text.toString() != "0" && ac == false) {
                resultView.text.toString() + num
            } else {
                ac = false
                num
            }
        }

        fun opBtnAction(op : String) {
            resultView.text = if(ac == false && operator == false) {
                    resultView.text.toString() + op
        } else {
            return
                }
        }

        fun bracketBtnAction(br : String) {
            resultView.text = resultView.text.toString() + br
        }

        fun equalBtnAction() {
            tmpNum = ""
            value = 0.0
            ac = false
            equal = true
            operator = false
            point= false
        }

        fun acBtnAction() {
            tmpNum = ""
            value = 0.0
            ac = true
            equal = false
            operator = false
            point= false
        }

        fun delBtnAction() {
            if(resultView.text.length > 0) {
                //一文字削除
            }
        }




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
            opBtnAction("+")
        }

        btnMinus.setOnClickListener {
            opBtnAction("-")
        }

        btnMul.setOnClickListener {
            opBtnAction("*")
        }

        btnDiv.setOnClickListener {
            opBtnAction("/")
        }

        btnEqual.setOnClickListener {
            equalBtnAction()
        }

        btnAc.setOnClickListener {
            acBtnAction()
        }

        btnDel.setOnClickListener {
            delBtnAction()
        }

        btnPoint.setOnClickListener {
            if (point == false && bracket && operator) {
                numBtnAction(".")
                point = true
            }
        }

        btnBracketLeft.setOnClickListener {
            bracketBtnAction("(")
        }

        btnBracketRight.setOnClickListener {
            bracketBtnAction(")")
        }


    }
}