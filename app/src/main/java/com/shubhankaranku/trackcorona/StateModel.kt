package com.shubhankaranku.trackcorona

data class StateModel (
    val statecode:String,
    val active:Int,
    val confirmed:Int,
    val recovered:Int,
    val deceased:Int

        )