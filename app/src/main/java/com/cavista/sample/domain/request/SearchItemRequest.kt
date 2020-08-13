package com.cavista.sample.domain.request

data class SearchItemRequest(val inputSearchKey: String, val pageSeqNumber: Int = 1)