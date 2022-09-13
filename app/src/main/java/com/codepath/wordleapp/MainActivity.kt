package com.codepath.wordleapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.codepath.wordleapp.FourLetterWordList.getRandomFourLetterWord

class MainActivity : AppCompatActivity() {
    private val wordToGuess = getRandomFourLetterWord();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var counter = 1
        val guessButton = findViewById<Button>(R.id.guessButton)
        var wordEntered = findViewById<EditText>(R.id.enterWord)
        var result = findViewById<TextView>(R.id.result)

        guessButton.setOnClickListener {
            Log.i("wordtoguess", wordToGuess)

            it.hideKeyboard();
            Log.i("count", "counter = $counter")
            if(counter<=3) {
                if(counter==1) {
                    val guessWord1 = findViewById<TextView>(R.id.word1)
                    val guessWordCheck1 = findViewById<TextView>(R.id.word1Check)
                    if (wordEntered.text.length==4) {
                        counter++;
                        guessWord1.text = wordEntered.text
//                        guessWord1.visibility = View.VISIBLE
                        var hashWord1 = checkGuess(wordEntered.text.toString().uppercase())
                        guessWordCheck1.text = hashWord1
//                        guessWordCheck1.visibility = View.VISIBLE

                        if (hashWord1 == "OOOO") {
                            result.text = "You guessed it right!\n" + wordEntered.text.toString()
                        } else {
                            result.text = "You guessed it wrong!\n Please try again"
                        }
                        result.visibility = View.VISIBLE
                        wordEntered.setText("")
                    } else {
                        Toast.makeText(it.context, "Please enter a 4 letter word", Toast.LENGTH_SHORT).show()
                    }
                } else if(counter==2) {
                    val guessWord2 = findViewById<TextView>(R.id.word2)
                    val guessWordCheck2 = findViewById<TextView>(R.id.word2Check)

                    if (wordEntered.text.length==4) {
                        counter++;
                        guessWord2.text = wordEntered.text
//                        guessWord2.visibility = View.VISIBLE
                        Log.i("word2","${guessWord2.text.toString()}")
                        var hashWord2 = checkGuess(guessWord2.text.toString().uppercase())
                        Log.i("hash","${hashWord2}")
                        guessWordCheck2.text = hashWord2
//                        guessWordCheck2.visibility = View.VISIBLE

                        if (hashWord2 == "OOOO") {
                            result.text = "You guessed it right!\n" + wordEntered.text.toString()
                        } else {
                            result.text = "You guessed it wrong!\n Please try again"
                        }
                        result.visibility = View.VISIBLE
                        wordEntered.setText("")
                    } else {
                        Toast.makeText(it.context, "Please enter a 4 letter word", Toast.LENGTH_SHORT)
                            .show()
                    }

                } else {
                    val guessWord3 = TextView(findViewById(R.id.word3))
                    val guessWordCheck3 = TextView(findViewById(R.id.word3Check))
                    if (wordEntered.text.length==4) {
                        counter++;
                        guessWord3.text = wordEntered.text
//                        guessWord3.visibility = View.VISIBLE
                        var hashWord3 = checkGuess(guessWord3.text.toString().uppercase())
                        guessWordCheck3.text = hashWord3
//                        guessWordCheck3.visibility = View.VISIBLE

                        if (hashWord3 == "OOOO") {
                            result.text = "You guessed it right!\n" + wordEntered.text.toString()
                        } else {
                            result.text = "You guessed it wrong!\n Please try again"
                        }
                        result.visibility = View.VISIBLE
                        wordEntered.setText("")
                    } else {
                        Toast.makeText(it.context, "Please enter a 4 letter word", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            } else {
                result.text = "Out of attempts! The word is \n" + wordEntered.text.toString()
            }
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        Log.i("checkGuess", guess)
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    private fun View.hideKeyboard()
    {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}