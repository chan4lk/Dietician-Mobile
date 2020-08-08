package com.dietician.local.mapper

import com.dietician.data.model.TokenData
import com.dietician.local.model.TokenLocal
import javax.inject.Inject

class TokenDataLocalMapper @Inject constructor() : Mapper<TokenData, TokenLocal> {
    override fun from(e: TokenLocal): TokenData {
        return TokenData(
            token = e.token
        )
    }

    override fun to(t: TokenData, userName: String): TokenLocal {
        return TokenLocal(
            userName = userName,
            token = t.token
        )
    }

}