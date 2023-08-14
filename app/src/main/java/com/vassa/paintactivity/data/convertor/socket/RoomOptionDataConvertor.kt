package com.vassa.paintactivity.data.convertor.socket

import com.vassa.paintactivity.data.entity.intents.RoomOptionDataEntity
import com.vassa.paintactivity.data.entity.socket.InfoClientDataEntity
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity
import com.vassa.paintactivity.domain.entity.socket.RoomOptionDomEntity

class RoomOptionDataConvertor {

    companion object {
        fun roomOptionDomToDataConvertor(roomDomEntity: RoomOptionDomEntity): RoomOptionDataEntity {
            return RoomOptionDataEntity(
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

        fun roomOptionDataDomToConvertor(roomDataEntity: RoomOptionDataEntity): RoomOptionDomEntity {
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