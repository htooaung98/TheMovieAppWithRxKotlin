package com.example.themovieapp.activities

val numberList = listOf(1,2,3,4,5)
fun numberListCalculator(numList:List<Int>,operator:Char,calculate:(Int,Int,Char)->Int):Int{


    var initialValue=0

    for(number in numList){
        initialValue = calculate(initialValue,number,operator)
    }
    return 0
}

fun main(){
    val result = numberListCalculator(numberList,'+', calculate = {num1,num2,operator ->
        when(operator){
            '+'-> num1+num2
            '-'-> num1-num2
            '*'-> num1*num2
            '/'-> num1/num2
            else -> 0
        }

    })
    print("Result is $result")
}