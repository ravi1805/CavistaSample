package com.cavista.sample.service

abstract class NetworkClient {
    abstract fun setupNetworkClient(httpUrlString: String)
    abstract fun <T> getNetworkClient(apiService:Class<T>): T
}
