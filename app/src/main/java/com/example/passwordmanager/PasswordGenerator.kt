package com.example.passwordmanager

import java.security.SecureRandom

class PasswordGenerator {
    private val lowercaseAlpha="abcdefghijklmnopqrstuvwxyz"
    private val uppercaseAlpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val numbers="1234567890"
    private val specialChar=" !@#$%^&*()-_=+[{}]|;:'?<>,./\""

    fun generatePassword(passwordLength: Int, uppercaseRequired: Boolean, numbersRequired: Boolean, specialCharactersRequired: Boolean): String {
        var validChars = lowercaseAlpha
        if(uppercaseRequired){
            validChars += uppercaseAlpha
        }
        if(numbersRequired){
            validChars += numbers
        }
        if(specialCharactersRequired){
            validChars += specialChar
        }

        val secureRandom = SecureRandom()
        var curPwd = StringBuilder(passwordLength)
        for (x : Int in 0 until passwordLength){
            val random : Int = (validChars.indices).random()
            curPwd.append(validChars[random])
        }
        var validPassword = false
        while(!validPassword) {
            validPassword = true;
            if (!curPwd.contains("[a-z]".toRegex())) {
                validPassword = false
                val replacement = (Math.random() * curPwd.length).toInt()
                curPwd[replacement] = lowercaseAlpha[secureRandom.nextInt(lowercaseAlpha.length)]
            }
            if (uppercaseRequired and !curPwd.contains("[A-Z]".toRegex())) {
                validPassword = false
                val replacement = (Math.random() * curPwd.length).toInt()
                curPwd[replacement] = uppercaseAlpha[secureRandom.nextInt(uppercaseAlpha.length)]
            }
            if (numbersRequired and !curPwd.contains("[0-9]".toRegex())) {
                validPassword = false
                val replacement = (secureRandom.nextInt(curPwd.length)).toInt()
                curPwd[replacement] = numbers[secureRandom.nextInt(numbers.length)]
            }
            if (specialCharactersRequired and !curPwd.contains("""[ !@#$%^&*()\-_=+\[{}\]|;:'?<>,./"]""".toRegex())) {
                validPassword = false
                val replacement = (Math.random() * curPwd.length).toInt()
                curPwd[replacement] = specialChar[secureRandom.nextInt(specialChar.length)]
            }

        }
        return curPwd.toString()
    }

}


fun main(){
    println("Testing PasswordGenerator")
    val blah = PasswordGenerator()
    val blah2 = blah.generatePassword(5, true, true, true)
    println(blah2)

}
