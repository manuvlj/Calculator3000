package com.manudavid.calculator3000

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    companion object{
        private const val EMPTY = ""
        private const val SPACE = " "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numeros
        uno_button.setOnClickListener { appendOnScreen(string= "1", canClear= true) }
        dos_button.setOnClickListener { appendOnScreen(string= "2", canClear= true) }
        tres_button.setOnClickListener { appendOnScreen(string= "3", canClear= true) }
        cuatro_button.setOnClickListener { appendOnScreen(string= "4", canClear= true) }
        cinco_button.setOnClickListener { appendOnScreen(string= "5", canClear= true) }
        seis_button.setOnClickListener { appendOnScreen(string= "6", canClear= true) }
        siete_button.setOnClickListener { appendOnScreen(string= "7", canClear= true) }
        ocho_button.setOnClickListener { appendOnScreen(string= "8", canClear= true) }
        nueve_button.setOnClickListener { appendOnScreen(string= "9", canClear= true) }
        cero_button.setOnClickListener { appendOnScreen(string= "0", canClear= true) }
        punto_button.setOnClickListener { appendOnScreen(string= ".", canClear= true) }

        //Operaciones
        mas_button.setOnClickListener { appendOnScreen(string = "+", canClear = false) }
        menos_button.setOnClickListener { appendOnScreen(string = "-", canClear = false) }
        mult_button.setOnClickListener { appendOnScreen(string = "*", canClear = false) }
        div_button.setOnClickListener { appendOnScreen(string = "/", canClear = false) }

        del_button.setOnClickListener {
            operaciones_textView.text = EMPTY
            resultado_textView.text = EMPTY
        }

        atras_button.setOnClickListener {
            val string = operaciones_textView.text.toString()
            if(string.isNotEmpty()){
                operaciones_textView.text = string.substring(0, string.length-1)
            }
            resultado_textView.text = EMPTY
        }

        igual_button.setOnClickListener {
            try {
                val expression = ExpressionBuilder(operaciones_textView.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if(result == longResult.toDouble()){
                    resultado_textView.text = longResult.toString()
                }else{
                    resultado_textView.text = result.toString()
                }
            }catch (e:Exception){
                Log.d("Exception", "message : " + e.message)
            }
        }
    }


    fun appendOnScreen (string: String, canClear: Boolean){

        if(canClear){
            resultado_textView.text = EMPTY
            operaciones_textView.append(string)
        }else{
            operaciones_textView.append(resultado_textView.text)
            operaciones_textView.append(string)
            resultado_textView.text = EMPTY
        }
    }
}