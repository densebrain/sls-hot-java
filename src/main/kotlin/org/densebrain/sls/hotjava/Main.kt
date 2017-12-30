package org.densebrain.sls.hotjava

import org.koin.KoinContext
import org.koin.dsl.module.applicationContext
import org.koin.standalone.StandAloneContext
import org.koin.standalone.StandAloneContext.koinContext
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneKoinContext

var coreModule = applicationContext {
  provide { InterceptServer() }
}


fun main(args:Array<String>) {
  startKoin(listOf(coreModule))

  val server = (koinContext as KoinContext).get<InterceptServer>()

}