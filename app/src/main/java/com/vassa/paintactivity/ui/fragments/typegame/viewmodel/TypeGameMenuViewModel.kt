package com.vassa.paintactivity.ui.fragments.typegame.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vassa.paintactivity.domain.entity.profile.GlobalProfileDomEntity
import com.vassa.paintactivity.domain.usecase.GlobalProfileUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TypeGameMenuViewModel(private var globalProfileUseCase: GlobalProfileUseCase) : ViewModel() {
    private var globalProfile = MutableLiveData<GlobalProfileDomEntity>()

    fun getGlobalProfile(): LiveData<GlobalProfileDomEntity> {
        return globalProfile
    }

    fun loadGlobalProfile() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                globalProfile.value = globalProfileUseCase.loadProfile()
            }
        }
    }
}