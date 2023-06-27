package com.vassa.paintactivity.di

import com.vassa.paintactivity.ui.fragments.achivmets.AchivmentViewModel
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

val appModule = module {
    viewModel<MainMenuViewModel> {
        MainMenuViewModel()
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
        MultiplayerCreateViewModel()
    }

    viewModel<MultiplayerLobbyViewModel> {
        MultiplayerLobbyViewModel()
    }

    viewModel<OptionViewModel> {
        OptionViewModel()
    }

    viewModel<PartyGameViewModel> {
        PartyGameViewModel()
    }

    viewModel<RuleViewModel> {
        RuleViewModel()
    }

    viewModel<TypeGameMenuViewModel> {
        TypeGameMenuViewModel()
    }

}