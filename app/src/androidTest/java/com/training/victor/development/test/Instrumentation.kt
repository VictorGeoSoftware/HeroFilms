package com.training.victor.development.test

import android.os.Bundle
import android.support.test.runner.MonitoringInstrumentation
import com.training.victor.development.BuildConfig
import com.training.victor.development.utils.myTrace
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberInstrumentationCore

@CucumberOptions(features = ["features"], glue = ["com.training.victor.development.test"])
class Instrumentation: MonitoringInstrumentation() {
    private val instrumentationCore = CucumberInstrumentationCore(this)

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)

        val tags = BuildConfig.TEST_TAGS
        myTrace("Instrumetation - tags :: $tags")

        if (tags.isNotEmpty()) {
            arguments?.putString("tags", tags
                .replace(",", "--")
                .replace("\\s",""))
        }

        instrumentationCore.create(arguments)
        start()
    }

    override fun onStart() {
        super.onStart()
        waitForIdleSync()
        instrumentationCore.start()
    }
}