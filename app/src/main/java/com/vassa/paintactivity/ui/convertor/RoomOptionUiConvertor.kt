package com.vassa.paintactivity.ui.convertor

import com.vassa.paintactivity.domain.entity.socket.RoomOptionDomEntity
import com.vassa.paintactivity.ui.intent.RoomOptionIntent

class RoomOptionUiConvertor {
    companion object{
        fun roomOptionDomToUiConvertor(roomDomEntity: RoomOptionDomEntity): RoomOptionIntent {
            return RoomOptionIntent(
                roomDomEntity.playerCount,
                roomDomEntity.imposter,
                roomDomEntity.author,
                roomDomEntity.pack_id,
                roomDomEntity.lang,
                roomDomEntity.inkCount,
                roomDomEntity.timer,
                roomDomEntity.timeSek,
                roomDomEntity.group,
                )
        }

        fun roomOptionUiToDomConvertor(roomDataEntity: RoomOptionIntent): RoomOptionDomEntity {
            return RoomOptionDomEntity(
                roomDataEntity.playerCount,
                roomDataEntity.imposter,
                roomDataEntity.author,
                roomDataEntity.pack_id,
                roomDataEntity.lang,
                roomDataEntity.inkCount,
                roomDataEntity.timer,
                roomDataEntity.timeSek,
                roomDataEntity.group,
            )
        }
    }
}