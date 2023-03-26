package com.example.jankenultra


import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
<<<<<<<< HEAD:app/src/main/java/com/example/jankenultra/CreditFragment2Author.kt
 * Use the [CreditFragment2Author.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreditFragment2Author : Fragment() {
========
 * Use the [CreditFragment1Logo.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreditFragment1Logo : Fragment() {
>>>>>>>> 4af338644ca4fd354b05e97640670d9c3977b61a:app/src/main/java/com/example/jankenultra/CreditFragment1Logo.kt
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
<<<<<<<< HEAD:app/src/main/java/com/example/jankenultra/CreditFragment2Author.kt
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credit_fragment2_author, container, false)
========
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_credit_fragment1_logo, container, false)
        val myTypeface = Typeface.createFromAsset(requireActivity().assets, "fonts/edosz.ttf")
        val tv = view.findViewById<View>(R.id.JankenCredits) as TextView
        tv.typeface = myTypeface
        return view
>>>>>>>> 4af338644ca4fd354b05e97640670d9c3977b61a:app/src/main/java/com/example/jankenultra/CreditFragment1Logo.kt
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
<<<<<<<< HEAD:app/src/main/java/com/example/jankenultra/CreditFragment2Author.kt
         * @return A new instance of fragment CreditFragment2Author.
========
         * @return A new instance of fragment CreditFragment1Logo.
>>>>>>>> 4af338644ca4fd354b05e97640670d9c3977b61a:app/src/main/java/com/example/jankenultra/CreditFragment1Logo.kt
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
<<<<<<<< HEAD:app/src/main/java/com/example/jankenultra/CreditFragment2Author.kt
            CreditFragment2Author().apply {
========
            CreditFragment1Logo().apply {
>>>>>>>> 4af338644ca4fd354b05e97640670d9c3977b61a:app/src/main/java/com/example/jankenultra/CreditFragment1Logo.kt
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}