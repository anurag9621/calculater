package com.example.mycalculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric: Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
        val inputText = findViewById<TextView>(R.id.tvInput)
        inputText.append((view as Button).text)
        lastNumeric = true


    }

    fun onClear(view: View){
        val inputText = findViewById<TextView>(R.id.tvInput)
        inputText.text = ""
        lastNumeric = false
        lastDot= false
    }

    fun onDecimalPoint(view: View){
        val inputText = findViewById<TextView>(R.id.tvInput)
        if (lastNumeric && !lastDot){
            inputText.append(".")
            lastNumeric = false
            lastDot = true
        }
    }


    fun onEqual(view: View){
        val inputText = findViewById<TextView>(R.id.tvInput)
        if(lastNumeric){
            var tvValue = inputText.text.toString()
            var prefix = ""
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }



                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    inputText.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                }else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    inputText.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    inputText.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                } else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    inputText.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String{
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length - 2)
        return  value
    }

    fun onOperator(view: View){
        val inputText = findViewById<TextView>(R.id.tvInput)
        if (lastNumeric && !isOperatorAdded(inputText.text.toString())){
            inputText.append((view as Button).text)
            lastNumeric = false
            lastDot = false

        }
    }

    private fun isOperatorAdded(value: String):Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}