package com.vassa.paintactivity.data.convertor.socket

import com.vassa.paintactivity.data.entity.socket.InfoClientDataEntity
import com.vassa.paintactivity.domain.entity.socket.InfoClientDomEntity

class InfoClientConvertor {
    companion object {
        fun infoClientDomToDataConvertor(infoClientDomEntity: InfoClientDomEntity): InfoClientDataEntity {
            return InfoClientDataEntity(
                infoClientDomEntity.avatarId,
                infoClientDomEntity.colorId,
                infoClientDomEntity.name,
                infoClientDomEntity.room,
                infoClientDomEntity.typeCl,
                infoClientDomEntity.key
            )
        }

        fun infoClientDataDomToConvertor(infoClientDataEntity: InfoClientDataEntity): InfoClientDomEntity {
            return InfoClientDomEntity(
                infoClientDataEntity.avatarId,
                infoClientDataEntity.colorId,
                infoClientDataEntity.name,
                infoClientDataEntity.room,
                infoClientDataEntity.typeCl,
                infoClientDataEntity.key
            )
        }
    }
}