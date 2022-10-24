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



// 入力受付の条件用bool　ほか
        var value: Double = 0.0
        var number : Boolean = false
        var ac: Boolean = false
        var equal: Boolean = true
        var operator: Boolean = false
        var point: Boolean = false
        var headIsZero : Boolean = false
        var leftBracket : Boolean = false
        var cntLeftBracket : Int = 0
        var rightBracket : Boolean = false



// ボタン
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
        val btnMul: Button = findViewById(R.id.mul)   // ×
        val btnMinus: Button = findViewById(R.id.minus)   // -
        val btnDiv: Button = findViewById(R.id.div)   // ÷
        val btnEqual: Button = findViewById(R.id.equal)  // =
        val btnAc: Button = findViewById(R.id.ac)      // AC
        val btnDel: Button = findViewById(R.id.del)   //del
        val btnPoint: Button = findViewById(R.id.point) //point
        val btnBracketLeft: Button = findViewById(R.id.bracketLeft)
        val btnBracketRight: Button = findViewById(R.id.bracketRight)

        val resultView: TextView = findViewById(R.id.result)



//　やりたいこと：ボタン入力の結果成立した表示される文字列を左から読んで、
// 　　　　　　　 要素(数字、演算子、カッコ "("　、　")" 　)を順に格納、
//     　　　　　逆ポーランド記法に整形して計算
//              逆ポを使うのは馴染みがあるため。他の方法の方が簡単にすむ可能性あり

var tmpNum :String = ""
var inputArray:ArrayList<String> = arrayListOf()
var textIsNum : Boolean = false
//          inputArray +=tmpNum
 val opStack = ArrayDeque<String>()
//
//        val numStack = ArrayDeque()
//        if (number == true){
//            inputArray += tmpNum
//        }

//        if(textIsNum == true ) {
//            inputArray.add(tmpNum)
//            textIsNum = false
//        }

        //inputArray=逆ポーランド記法になった式の入った配列　から実際に計算をする関数
        val numStack = ArrayDeque<Double>()

        fun calcuRPoland (){
            var a :Double
            var b :Double

            for(i in 0 .. inputArray.size-1) {
                if(inputArray.elementAt(i)=="+") {
                    a = numStack.removeLast()
                    b = numStack.removeLast()
                    numStack.add(a+b)
                }
                else if(inputArray.elementAt(i)=="-") {
                    b = numStack.removeLast()
                    a = numStack.removeLast()
                    numStack.add(a-b)
                }
                else if(inputArray.elementAt(i)=="*") {
                    a = numStack.removeLast()
                    b = numStack.removeLast()
                    numStack.add(a*b)
                }
                else if(inputArray.elementAt(i)=="/") {
                    b = numStack.removeLast()
                    a = numStack.removeLast()
                    numStack.add(a/b)
                } else {
                    numStack.add(inputArray.elementAt(i).toDouble())
                }
            }
            println(numStack.last())
            val x: Double = 1.0
            val y: Double = 0.0

            if(numStack.last()==x/y||numStack.last()==-1*x/y) {
                resultView.text = "ゼロ除算です"
                numStack.removeLast()
                // subResultView ="ゼロ除算です" ?

            } else {
                resultView.text = numStack.removeLast().toString()
            }
        }


        fun toRPoland (){
            for(i in 0..resultView.text.length-1) {
                if(resultView.text.elementAt(i).toString() == "(" || resultView.text.elementAt(i).toString() == "*" ||resultView.text.elementAt(i) == '/') {

                    if(textIsNum == true ) {
                        inputArray.add(tmpNum)
                        tmpNum=""
                        textIsNum = false
                    }

                    opStack.add(resultView.text.elementAt(i).toString())

                    //push
                }
                else if ((resultView.text.elementAt(i).toString() == "+" || resultView.text.elementAt(i).toString() == "-") ) {

                    if(textIsNum == true ) {

                        inputArray.add(tmpNum)
                        tmpNum=""
                        textIsNum = false
                    }

                    if(resultView.text.length > 0) {
//                        if (opStack.last() == "*" || opStack.last() == "/") {
//                            //配列外参照注意
//                            //popしたあとpush
//                            inputArray.add(opStack.removeLast())
//                            opStack.add(resultView.text.elementAt(i).toString())
                        //「優先度が低いものがくるまで」で試す
                            while(opStack.isEmpty()==false&&(opStack.last() == "*" || opStack.last() == "/" ||opStack.last() == "+" || opStack.last() == "-")) {
                                inputArray.add(opStack.removeLast())

                            }
                        opStack.add(resultView.text.elementAt(i).toString())
                        //  inputArray.add(resultView.text.elementAt(i).toString())
                        } else {
                            //pushしてstack積む
                            opStack.add(resultView.text.elementAt(i).toString())

                            if(textIsNum == true ) {
                                inputArray.add(tmpNum)
                                tmpNum=""
                                textIsNum = false
                            }
                        }

                } else if (resultView.text.elementAt(i).toString() == ")") {

                    if(textIsNum == true ) {
                        inputArray.add(tmpNum)
                        tmpNum=""
                        textIsNum = false
                    }

                    while(true) {
                        if (opStack.last() != "(") {
                            inputArray.add(opStack.removeLast())
                        } else {
                            opStack.removeLast()
                            break

                            // スタック見て　( が出るまでpop
                            // (　は捨てる
                        }

                    }
                } else {
                    textIsNum = true
                    tmpNum += (resultView.text.elementAt(i).toString())
                    //数字
                }
            }

            if(textIsNum==true) {
                inputArray.add(tmpNum)
                tmpNum = ""
            }

            while(true) {
                inputArray.add(opStack.removeLast())
                if(opStack.isEmpty()) {
                    break
                }
                //全部pop
            }
//                        ここだけはLIFOじゃなくてFIFOってことでは？
//            while(true) {
//                if (opStack.first() == "(") {
//                    opStack.removeFirst()
//                } else {
//                    inputArray.add(opStack.removeFirst())
//                }
//
//                if (opStack.isEmpty() == true) {
//                    break
//                }
//            }
            for(i in 0 .. inputArray.size-1) {
                println(inputArray.elementAt(i))
            }

            calcuRPoland()
            inputArray = arrayListOf()

        }

        //各ボタンが押された時の処理用の関数

        fun acBtnAction() {
            resultView.text = ""

            number = false
            ac = true
            equal = false
            operator = false
            point = false
            leftBracket = false
            rightBracket = false
            headIsZero = false
            cntLeftBracket = 0


        }
        // 1~9
        fun numBtnAction(num: String) {
            //todo イコールの直後に数字が押された時の処理
            //todo )の直後に数字が押された時の処理
            if(resultView.text == "ゼロ除算です"){
                acBtnAction()
            }

            resultView.text = resultView.text.toString() + num

                number = true
                ac = false
                equal = false
                operator = false
                point = false
                leftBracket = false
                rightBracket = false
                headIsZero = false

        }

        fun zeroBtnAction (zero: String) {
            //todo イコールの直後に数字が押された時の処理
            //todo )の直後に数字が押された時の処理
            if(resultView.text == "ゼロ除算です"){
                acBtnAction()
            }

            if(headIsZero==true&&point== false) {

            } else {
                resultView.text = resultView.text.toString() + zero

                number = true
                ac = false
                equal = false
                operator = false
                point = false
                leftBracket = false
                rightBracket = false
      //保留 この下のheadIsZeroも全部保留          headIsZero = false

            }

        }

        fun opBtnAction(op : String) {
            if(resultView.text == "ゼロ除算です"){
                acBtnAction()
            }
            if(ac == false && operator == false && leftBracket ==false) {
                resultView.text = resultView.text.toString() + op

                number = false
                ac = false
                equal = false
                operator = true
                point = false
                leftBracket = false
                rightBracket = false
                headIsZero = false




            }
        }
//ここまで見た
    fun bracketLeftBtnAction(br : String) {
    if(resultView.text == "ゼロ除算です"){
        acBtnAction()
    }
    //todo 先頭でも使えて、)(にならない条件
    if(resultView.text.length == 0 || operator==true || leftBracket==true) {
        resultView.text = resultView.text.toString() + br
        cntLeftBracket++

        number = false
        ac = false
        equal = false
        operator = false
        point = false
        leftBracket = true
        rightBracket = false
        headIsZero = false
    }
}

        fun bracketRightBtnAction(br : String) {
            //todo 前が(でもなく、演算子でもない条件
            if(cntLeftBracket>0 && operator==false && leftBracket==false) {
                resultView.text = resultView.text.toString() + br

                number = false
                ac = false
                equal = false
                operator = false
                point = false
                leftBracket = false
                rightBracket = true
                headIsZero = false


                cntLeftBracket--
            }
        }

        fun equalBtnAction() {
            if (cntLeftBracket == 0 && operator!=true && ac !=true){

            toRPoland()

            number = true
            ac = false
            equal = true
            operator = false
            point = false
            leftBracket = false
            rightBracket = false
            headIsZero = false
        }
            //todo textを配列に渡す関数
        }



        fun delBtnAction() {
            if(resultView.text.length > 0) {
                //todo textが空のとき何もしない

                //一文字削除
                resultView.text = resultView.text.dropLast(1)
                //削除の結果0文字になったらacをtrue?
            if(resultView.text.length==0){
                acBtnAction()
            }
                //削除した文字の前の文字に対応した処理？
            }
        }

        fun pointBtnAction (pnt: String) {
            if(number==true) {
                resultView.text = resultView.text.toString() + pnt

                number = false
                ac = true
                equal = false
                operator = false
                point = true
                leftBracket = false
                rightBracket = false
                headIsZero = false

            }
        }




//ボタンが押された時の処理
        btnZero.setOnClickListener {
            //ゼロ除算の処理に問題あり
            // 5/0で下の処理をすると、5/0.2とかができなくなる
            //除算記号側でなんかするべきかも
            //そもそも下の条件つけて0押すとアプリが落ちる
            //←無入力だったから配列外参照で落ちてた
         //   if(resultView.text.substring(resultView.text.length - 1) != "/"){ //ゼロ除算
            zeroBtnAction("0");
  //      }
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
            if(equal!=true)
            equalBtnAction()
        }

        btnAc.setOnClickListener {
            acBtnAction()
        }

        btnDel.setOnClickListener {
            delBtnAction()
        }

        btnPoint.setOnClickListener {
            if (point == false && leftBracket==false && rightBracket == false && operator == false) {
                pointBtnAction(".")
                point = true
            }
        }

        btnBracketLeft.setOnClickListener {
            bracketLeftBtnAction("(")
            //読んだ関数内でやるべき？
        }

        btnBracketRight.setOnClickListener {
            bracketRightBtnAction(")")
        }





    }
}