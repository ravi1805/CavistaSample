package com.cavista.sample.service.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.cavista.sample.service.exception.IErrorResponse
import java.io.Serializable

class ErrorResponseInJsonImpl : IErrorResponse, Serializable {

    @Expose
    var status: Int = 500
    @Expose
    var data: ErrorData? = null

    override fun getStatusCode(): Int {
        return status
    }

    override fun getMessage(): String? {
        return data?.error ?: ""
    }

    /*
     "data": {
    "error": "Not following tag",
    "request": "/3/account/me/follow/tag/funny",
    "method": "DELETE"
  },
  "success": false,
  "status": 409

  */

}

data class ErrorData(val error: String)
