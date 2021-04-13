package com.example.education.mvvm

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.education.baseModels.BaseViewModel
import com.example.education.R

class MainViewModel : BaseViewModel() {

    val editTextLoginHint = "Please put your login"
    val editTextPasswordHint = "Please put your password"
    val buttonLoginName = "Log In"
    val buttonPager2Name = "To Pager2"
    var textData = MutableLiveData("")

    fun logIn (view : View){
        //FIXME правильно ли выполнен переход?
        val navHostController = view.findNavController()
        navHostController.navigate(R.id.recyclerFragment)
    }

    fun toPager2(view: View){
        val navHostController = view.findNavController()
        navHostController.navigate(R.id.viewPagerFragment)
    }

}