package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* if (savedInstanceState != null) {
         numCorAns =savedInstanceState.getInt("amountOfCorrectAns")
             numUnCorAns =savedInstanceState.getInt("amountOfUnCorrectAns")
         }
        */
        var indicators = arrayOf(
            Random.nextInt(0, 100).toString(),
            Random.nextInt(0, 1000).toString(),
            Random.nextInt(0, 1000).toString(), expression[3].toString()
        )
        indicators.shuffle()

        var listView: ListView = findViewById(R.id.listView)
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        var adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_single_choice, indicators
        )
        listView.adapter = adapter

        var text: TextView = findViewById(R.id.textListofChilds)
        text.text =
            expression[0].toString() + expression[2].toString() + expression[1].toString() + " ="
        var textAnswer: TextView = findViewById(R.id.CorrectAnswers)

        textAnswer.text = "Количество правильных ответов: "


        var selection: TextView = findViewById(R.id.selection)
        selection.text = "Количество неправильных ответов: "


        listView.setOnItemClickListener { parent, view, position, id ->

            var selectedItem = indicators[position]
            val textView = view as TextView
            selectedItem = textView.text as String

            if (selectedItem.toString() == expression[3].toString()) {
                numCorAns += 1
                if (numCorAns == 10){increaseMod *=10}
                if (numCorAns == 20){increaseMod *=10}
                textAnswer.text = "Количество правильных ответов: " + numCorAns
                expression = mathExpr(increaseMod)
                indicators = arrayOf(
                    Random.nextInt(0, 100).toString(),
                    Random.nextInt(0, 1000).toString(),
                    Random.nextInt(0, 1000).toString(), expression[3].toString()
                )
                text.text =
                    expression[0].toString() + expression[2].toString() + expression[1].toString() + " ="
                adapter = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_single_choice, indicators
                )
                listView.adapter = adapter


            } else {
                numUnCorAns += 1
                selection.text = "Количество неправильных ответов: $numUnCorAns"

                expression = mathExpr(increaseMod)
                indicators = arrayOf(
                    Random.nextInt(0, 100).toString(),
                    Random.nextInt(-100, 0).toString(),
                    Random.nextInt(100, 1000).toString(), expression[3].toString()
                )
                text.text =
                    expression[0].toString() + expression[2].toString() + expression[1].toString() + " ="
                adapter = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_single_choice, indicators
                )
                listView.adapter = adapter

            }

        }

    }

    fun mathExpr(a: Int = 10): Array<Any> {

        var num1 = Random.nextInt(0, a)
        var num2 = Random.nextInt(1, a)
        var operation = Random.nextInt(0, 4)

        if (operation == 0) {
            return arrayOf(num1, num2, " + ", num1 + num2)
        }
        if (operation == 1) {
            return arrayOf(num1, num2, " - ", num1 - num2)
        }
        if (operation == 2) {
            return arrayOf(num1, num2, " * ", num1 * num2)
        } else {
            return arrayOf(num1, num2, " / ", num1 / num2)
        }
    }

    var expression = mathExpr()
    var numCorAns = 0
    var numUnCorAns = 0
    var increaseMod = 10

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("amountOfCorrectAns", numCorAns)
        outState.putInt("amountOfUnCorrectAns", numUnCorAns)
    }
}





