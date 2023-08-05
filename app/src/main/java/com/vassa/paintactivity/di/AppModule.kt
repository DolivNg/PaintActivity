package com.vassa.paintactivity.di

import com.vassa.paintactivity.ui.fragments.achivmets.AchivmentViewModel
import com.vassa.paintactivity.ui.fragments.game.multiplayer.GameMultiplayerViewModel
import com.vassa.paintactivity.ui.fragments.local.LocalGameViewModel
import com.vassa.paintactivity.ui.fragments.makepen.modelview.MakePenViewModel
import com.vassa.paintactivity.ui.fragments.menu.viewmodel.MainMenuViewModel
import com.vassa.paintactivity.ui.fragments.multyplayer.create.MultiplayerCreateViewModel
import com.vassa.paintactivity.ui.fragments.multyplayer.lobby.MultiplayerLobbyViewModel
import com.vassa.paintactivity.ui.fragments.option.modelview.OptionViewModel
import com.vassa.paintactivity.ui.fragments.party.PartyGameViewModel
import com.vassa.paintactivity.ui.fragments.rules.RuleViewModel
import com.vassa.paintactivity.ui.fragments.typegame.viewmodel.TypeGameMenuViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
/**
 *@author Vassa
 * version 1.2
 * 08.07.2023
 * */
val appModule = module {
    viewModel<MainMenuViewModel> {
        MainMenuViewModel(get())
    }

    viewModel<AchivmentViewModel> {
        AchivmentViewModel()
    }

    viewModel<LocalGameViewModel> {
        LocalGameViewModel()
    }

    viewModel<MakePenViewModel> {
        MakePenViewModel()
    }

    viewModel<MultiplayerCreateViewModel> {
        MultiplayerCreateViewModel(get(),get())
    }

    viewModel<MultiplayerLobbyViewModel> {
        MultiplayerLobbyViewModel()
    }

    viewModel<OptionViewModel> {
        OptionViewModel(get(),get(),get(),get())
    }

    viewModel<PartyGameViewModel> {
        PartyGameViewModel()
    }

    viewModel<RuleViewModel> {
        RuleViewModel()
    }

    viewModel<TypeGameMenuViewModel> {
        TypeGameMenuViewModel(get())
    }

    viewModel<GameMultiplayerViewModel> {
        GameMultiplayerViewModel(get())
    }

}